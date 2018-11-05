package com.fsalmeron.encuestasfcm.controller;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsalmeron.encuestasfcm.filter.EncuestaFilter;
import com.fsalmeron.encuestasfcm.filter.ResultadoFilter;
import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.model.Pregunta;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.model.Resultado;
import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.EncuestaService;
import com.fsalmeron.encuestasfcm.service.ResultadoService;
import com.fsalmeron.encuestasfcm.service.UsuarioService;

@Controller
@RequestMapping(value = "/encuestas")
public class EncuestaController {

	private static final Logger logger = LoggerFactory.getLogger(EncuestaController.class);

	@Autowired
	private EncuestaService encuestaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ResultadoService resultadoService;
	
	//http://localhost:8080/EncuestasFCM/encuestas/getAll
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getEncuestas() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		EncuestaFilter encuestaFilter = new EncuestaFilter();
		encuestaFilter.setActivo(Boolean.TRUE);
		List<Encuesta> encuestas = (List<Encuesta>) encuestaService.filter(encuestaFilter);
		Collections.sort(encuestas, new Comparator<Encuesta>() {
		    @Override
		    public int compare(Encuesta e1, Encuesta e2) {
		        return e1.getTitulo().compareTo(e2.getTitulo());
		    }
		});
		
		for(Encuesta encuesta : encuestas) {
			JSONObject json = new JSONObject();
			json.put("id", encuesta.getId());
			json.put("titulo", encuesta.getTitulo());
			json.put("descripcion", encuesta.getDescripcion());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Usuario user = usuarioService.getById(encuesta.getIdUsuarioAlta());
			json.put("usuario", user.getNombre() + " " + user.getApellido());
			json.put("fecha", format.format(encuesta.getFechaAlta()));
			json.put("geolocalizada", encuesta.getIsGeolicalizada());
			json.put("resoluciones", encuesta.getResoluciones());
			json.put("isSexoRestriccion", encuesta.getIsSexoRestriction() != null ? encuesta.getIsSexoRestriction() : 0);
			json.put("isEdadRestriccion", encuesta.getIsEdadRestriction() != null ? encuesta.getIsEdadRestriction() : 0);
			responseArray.put(json);
		}
		
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/encuestas/openEncuesta?idEncuesta=1
	@RequestMapping(value = "/openEncuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String abrirEncuesta(@RequestParam("idEncuesta") Integer idEncuesta) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		response.put("id", encuesta.getId());
		response.put("titulo", encuesta.getTitulo());
		response.put("descripcion", encuesta.getDescripcion());
		for (Pregunta pregunta : encuesta.getPreguntas()) {
			JSONObject json = new JSONObject();
			json.put("idPregunta", pregunta.getId());
			json.put("descripcionPregunta", pregunta.getDescripcion());
			json.put("idTipoRespuesta", pregunta.getTipoRespuesta().getId());
			json.put("numeroEscala", pregunta.getNumeroEscala() != null ? pregunta.getNumeroEscala() : 0);
			responseArray.put(json);
			JSONArray respuestasArray = new JSONArray();
			for (Respuesta respuesta : pregunta.getRespuestas()) {
				JSONObject jsonRespuesta = new JSONObject();
				jsonRespuesta.put("idRespuesta", respuesta.getId());
				jsonRespuesta.put("descripcionRespuesta", respuesta.getDescripcion());
				jsonRespuesta.put("idTipoRespuesta", respuesta.getTipoRespuesta().getId());
				respuestasArray.put(jsonRespuesta);
			}
			json.put("respuesta", respuestasArray);
		}
		response.put("preguntas", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/openEncuestaValidar", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String abrirEncuestaValidar(@RequestParam("idEncuesta") Integer idEncuesta, @RequestParam("idUsuario") Integer idUsuario) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		JSONObject response = new JSONObject();
		
		Integer cantidad = resultadoService.getResultadosByUsuario(idUsuario, idEncuesta);
		
		if (cantidad > 0) {
			response.put("respondida", Boolean.TRUE);
		} else {
			JSONArray responseArray = new JSONArray();
			response.put("id", encuesta.getId());
			response.put("respondida", Boolean.FALSE);
			response.put("titulo", encuesta.getTitulo());
			response.put("descripcion", encuesta.getDescripcion());
			for (Pregunta pregunta : encuesta.getPreguntas()) {
				JSONObject json = new JSONObject();
				json.put("idPregunta", pregunta.getId());
				json.put("descripcionPregunta", pregunta.getDescripcion());
				json.put("idTipoRespuesta", pregunta.getTipoRespuesta().getId());
				json.put("numeroEscala", pregunta.getNumeroEscala() != null ? pregunta.getNumeroEscala() : 0);
				responseArray.put(json);
				JSONArray respuestasArray = new JSONArray();
				for (Respuesta respuesta : pregunta.getRespuestas()) {
					JSONObject jsonRespuesta = new JSONObject();
					jsonRespuesta.put("idRespuesta", respuesta.getId());
					jsonRespuesta.put("descripcionRespuesta", respuesta.getDescripcion());
					jsonRespuesta.put("idTipoRespuesta", respuesta.getTipoRespuesta().getId());
					respuestasArray.put(jsonRespuesta);
				}
				json.put("respuesta", respuestasArray);
			}
			response.put("preguntas", responseArray);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	
	//http://localhost:8080/EncuestasFCM/encuestas/saveEncuesta?titulo=nombre&descripcion=nombre&idUsuario=2
	@RequestMapping(value = "/saveEncuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearEncuesta(@RequestParam("titulo") String titulo , @RequestParam("descripcion") String descripcion, @RequestParam("isGeolocalizada") Boolean isGeolocalizada, @RequestParam("isSexo") Integer isSexo, @RequestParam("isEdad") Integer isEdad, @RequestParam("idUsuario") Integer idUsuario) {
		Encuesta encuesta = new Encuesta();
		encuesta.setActivo(Boolean.TRUE);
		encuesta.setTitulo(titulo);
		encuesta.setDescripcion(descripcion);
		encuesta.setResoluciones(0);
		encuesta.setIsGeolicalizada(isGeolocalizada);
		encuesta.setIsSexoRestriction(isSexo);
		encuesta.setIsEdadRestriction(isEdad);
		encuesta.setFechaAlta(new Date());
		encuesta.setIdUsuarioAlta(idUsuario);
		JSONObject response = encuestaService.save(encuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/encuestas/updateEncuesta?idEncuesta=3&titulo=nuevo%20titulo&descripcion=nueva%20descripcion&idUsuario=3
	@RequestMapping(value = "/updateEncuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String modificarEncuesta(@RequestParam("idEncuesta") Integer idEncuesta, @RequestParam("titulo") String titulo, @RequestParam("descripcion") String descripcion, @RequestParam("isGeolocalizada") Boolean isGeolocalizada, @RequestParam("isSexo") Integer isSexo, @RequestParam("isEdad") Integer isEdad, @RequestParam("idUsuario") Integer idUsuario) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		encuesta.setTitulo(titulo);
		encuesta.setDescripcion(descripcion);
		encuesta.setIsGeolicalizada(isGeolocalizada);
		encuesta.setIsSexoRestriction(isSexo != 0 ? isSexo : null);
		encuesta.setIsEdadRestriction(isEdad != 0 ? isEdad : null);
		JSONObject response = encuestaService.save(encuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/encuestas/disableEncuesta?idEncuesta=3&idUsuario=2
	@RequestMapping(value = "/disableEncuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deshabilitarEncuesta(@RequestParam("idEncuesta") Integer idEncuesta, @RequestParam("idUsuario") Integer idUsuario) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		JSONObject response = encuestaService.inhabilitar(encuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/encuestas/enableEncuesta?idEncuesta=3&idUsuario=2
	@RequestMapping(value = "/enableEncuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String habilitarEncuesta(@RequestParam("idEncuesta") Integer idEncuesta, @RequestParam("idUsuario") Integer idUsuario) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		JSONObject response = encuestaService.habilitar(encuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/encuestas/deleteEncuesta?idEncuesta=3
	@RequestMapping(value = "/deleteEncuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String eliminarEncuesta(@RequestParam("idEncuesta") Integer idEncuesta) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		JSONObject response = encuestaService.delete(encuesta);
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/incrementarResolucion", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String incrementarResolucionEncuesta(@RequestParam("idEncuesta") Integer idEncuesta) {
		Encuesta encuesta = encuestaService.getById(idEncuesta);
		encuesta.setResoluciones(encuesta.getResoluciones() + 1);
		encuestaService.saveOrUpdate(encuesta);
		JSONObject response = new JSONObject();
		response.put("exito", Boolean.TRUE);
		logger.debug(response.toString());
		return response.toString();
	}
}

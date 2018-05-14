package com.fsalmeron.encuestasfcm.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.service.PreguntaService;
import com.fsalmeron.encuestasfcm.service.RespuestaService;
import com.fsalmeron.encuestasfcm.service.TipoRespuestaService;

@Controller
@RequestMapping(value = "/respuestas")
public class RespuestaController {

	private static final Logger logger = LoggerFactory.getLogger(RespuestaController.class);

	@Autowired
	private RespuestaService respuestaService;
	
	@Autowired
	private PreguntaService preguntaService;
	
	@Autowired
	private TipoRespuestaService tipoRespuestaService;
	
	//http://localhost:8080/EncuestasFCM/respuestas/saveRespuesta?descripcion=respuesta%20numero%201&idPregunta=4&idTipoRespuesta=1&idUsuario=2
	@RequestMapping(value = "/saveRespuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearRespuesta(@RequestParam("descripcion") String descripcion , @RequestParam("idPregunta") Integer idPregunta, @RequestParam("idTipoRespuesta") Integer idTipoRespuesta, @RequestParam("idUsuario") Integer idUsuario) {
		Respuesta respuesta = new Respuesta();
		respuesta.setDescripcion(descripcion);
		respuesta.setpregunta(preguntaService.getById(idPregunta));
		respuesta.setTipoRespuesta(tipoRespuestaService.getById(idTipoRespuesta));
		JSONObject response = respuestaService.save(respuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/respuestas/updateRespuesta?idRespuesta=7&descripcion=respuesta%20numero%202&idUsuario=1
	@RequestMapping(value = "/updateRespuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String modificarRespuesta(@RequestParam("idRespuesta") Integer idRespuesta, @RequestParam("descripcion") String descripcion, @RequestParam("idUsuario") Integer idUsuario) {
		Respuesta respuesta = respuestaService.getById(idRespuesta);
		respuesta.setDescripcion(descripcion);
		JSONObject response = respuestaService.save(respuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/respuestas/deleteRespuesta?idRespuesta=7&idUsuario=1
	@RequestMapping(value = "/deleteRespuesta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String eliminarRespuesta(@RequestParam("idRespuesta") Integer idRespuesta, @RequestParam("idUsuario") Integer idUsuario) {
		Respuesta respuesta = respuestaService.getById(idRespuesta);
		JSONObject response = respuestaService.delete(respuesta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
}

package com.fsalmeron.encuestasfcm.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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
import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.model.Resultado;
import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.RespuestaService;
import com.fsalmeron.encuestasfcm.service.ResultadoService;
import com.fsalmeron.encuestasfcm.service.UsuarioService;

@Controller
@RequestMapping(value = "/resultados")
public class ResultadoController {

	private static final Logger logger = LoggerFactory.getLogger(RespuestaController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RespuestaService respuestaService;
	
	@Autowired
	private ResultadoService resultadoService;
	
	@RequestMapping(value = "/saveResultado", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearResultado(@RequestParam("latitud") String latitud , @RequestParam("longitud") String longitud, @RequestParam("edadEncuestado") Integer edadEncuestado , @RequestParam("sexoEncuestado") Integer sexoEncuestado , @RequestParam("idUsuario") Integer idUsuario , @RequestParam("idRespuesta") Integer idRespuesta, @RequestParam("descripcion") String descripcion, @RequestParam("idEncuesta") Integer idEncuesta) {
		Resultado resultado = new Resultado();
		resultado.setLatitud(latitud);
		resultado.setLongitud(longitud);
		resultado.setEdadEncuestado(edadEncuestado);
		resultado.setSexoEncuestado(sexoEncuestado);
		resultado.setUsuario(usuarioService.getById(idUsuario));
		resultado.setRespuesta(respuestaService.getById(idRespuesta));
		resultado.setDescripcion(descripcion);
		resultado.setIdEncuesta(idEncuesta);
		JSONObject response = resultadoService.save(resultado);
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/getResultados", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getEncuestas(@RequestParam("idEncuesta") Integer idEncuesta) {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		List<Resultado> resultados = resultadoService.getResultadosEncuesta(idEncuesta);
		
		for(Resultado resultado : resultados) {
			JSONObject json = new JSONObject();
			json.put("latitud", resultado.getLatitud());
			json.put("longitud", resultado.getLongitud());
			json.put("idRespuesta", resultado.getRespuesta().getId());
			json.put("descripcion", resultado.getDescripcion());
			responseArray.put(json);
		}
		
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
	
}

package com.fsalmeron.encuestasfcm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.model.Pregunta;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.service.EncuestaService;
import com.fsalmeron.encuestasfcm.service.PreguntaService;
import com.fsalmeron.encuestasfcm.service.RespuestaService;
import com.fsalmeron.encuestasfcm.service.TipoRespuestaService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/preguntas")
public class PreguntaController {

	private static final Logger logger = LoggerFactory.getLogger(PreguntaController.class);
	
	@Autowired
	private EncuestaService encuestaService;
	
	@Autowired
	private TipoRespuestaService tipoRespuestaService;
	
	@Autowired
	private PreguntaService preguntaService;
	
	@Autowired
	private RespuestaService respuestaService;
	
	//http://localhost:8080/EncuestasFCM/preguntas/savePregunta?descripcion=%C2%BFPregunta%20Web%20Service?&idEncuesta=2&numeroEscala=2&idTipoRespuesta=2&idUsuario=2
	@RequestMapping(value = "/savePregunta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearPregunta(@RequestParam("descripcion") String descripcion, @RequestParam("idEncuesta") Integer idEncuesta, @RequestParam("numeroEscala") Integer numeroEscala, @RequestParam("idTipoRespuesta") Integer idTipoRespuesta, @RequestParam("idUsuario") Integer idUsuario) {
		Pregunta pregunta = new Pregunta();
		pregunta.setDescripcion(descripcion);
		pregunta.setEncuesta(encuestaService.getById(idEncuesta));
		pregunta.setTipoRespuesta(tipoRespuestaService.getById(idTipoRespuesta));
		pregunta.setNumeroEscala(numeroEscala);
		JSONObject response = preguntaService.save(pregunta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/preguntas/updatePregunta?idPregunta=5&descripcion=Probando%20el%20update??&numeroEscala=2&idUsuario=2
	@RequestMapping(value = "/updatePregunta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String modificarPregunta(@RequestParam("idPregunta") Integer idPregunta, @RequestParam("descripcion") String descripcion, @RequestParam("numeroEscala") Integer numeroEscala, @RequestParam("idUsuario") Integer idUsuario) {
		Pregunta pregunta = preguntaService.getById(idPregunta);
		pregunta.setDescripcion(descripcion);
		pregunta.setNumeroEscala(numeroEscala);
		JSONObject response = preguntaService.save(pregunta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/preguntas/deletePregunta?idPregunta=5&idUsuario=3
	@RequestMapping(value = "/deletePregunta", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String eliminarPregunta(@RequestParam("idPregunta") Integer idPregunta, @RequestParam("idUsuario") Integer idUsuario) {
		Pregunta pregunta = preguntaService.getById(idPregunta);
		JSONObject response = preguntaService.delete(pregunta, idUsuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/removeRespuestas", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String borrandoRespuestas(HttpServletRequest http) throws IOException {
		StringBuilder stringBuilder = new StringBuilder(1000);
		Scanner scanner = new Scanner(http.getInputStream(), "UTF-8");
		while (scanner.hasNextLine()) {
			stringBuilder.append(scanner.nextLine());
		}
		
		String body = stringBuilder.toString();
		System.out.println(body);
		
		body = body.substring(body.indexOf("{"));
		System.out.println(body);		
		Pregunta pregunta = new Gson().fromJson(body, Pregunta.class);
		
		for (Respuesta respuesta : pregunta.getRespuestas()) {
			respuestaService.remove(respuesta);
		}
		
		JSONObject response = new JSONObject();
		response.put("exito", Boolean.TRUE);
		logger.debug(response.toString());
		return response.toString();
	}
	
}

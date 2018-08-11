package com.fsalmeron.encuestasfcm.controller;

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

import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.model.InformacionNoticia;
import com.fsalmeron.encuestasfcm.service.InformacionNoticiaService;

@Controller
@RequestMapping(value = "/infoNoticias")
public class InformacionNoticiaController {
	private static final Logger logger = LoggerFactory.getLogger(InformacionNoticiaController.class);
	
	@Autowired
	private InformacionNoticiaService informacionNoticiaService;
	
	//http://localhost:8080/EncuestasFCM/infoNoticias/getAll
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getInfoNoticias() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		List<InformacionNoticia> info = (List<InformacionNoticia>) informacionNoticiaService.findAll();
		for(InformacionNoticia i : info) {
			JSONObject json = new JSONObject();
			json.put("id", i.getId());
			json.put("titulo", i.getTitulo());
			json.put("descripcion", i.getDescripcion());
			json.put("url", i.getUrl());
			responseArray.put(json);
		}
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/infoNoticias/addInfo?titulo=google&descripcion=pagina%20de%20google&url=http://google.com.ar&idUsuario=2
	@RequestMapping(value = "/addInfo", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String agregarInfoNoticia(@RequestParam("titulo") String titulo, @RequestParam("descripcion") String descripcion, @RequestParam("url") String url, @RequestParam("idUsuario") Integer idUsuario) {
		InformacionNoticia info = new InformacionNoticia();
		info.setTitulo(titulo);
		info.setDescripcion(descripcion);
		info.setUrl(url);
		info.setIdUsuarioAlta(idUsuario);
		info.setFechaAlta(new Date());
		JSONObject response = informacionNoticiaService.save(info);
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/removeInfo", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String eliminarInfoNoticia(@RequestParam("idInfo") Integer idInfo) {
		InformacionNoticia info = informacionNoticiaService.getById(idInfo);
		informacionNoticiaService.remove(info);
		JSONObject response = new JSONObject();
		response.put("exito", Boolean.TRUE);
		logger.debug(response.toString());
		return response.toString();
	}
}

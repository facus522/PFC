package com.fsalmeron.encuestasfcm.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsalmeron.encuestasfcm.filter.EncuestaFilter;
import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.service.EncuestaService;

@Controller
@RequestMapping(value = "/encuestas")
public class EncuestaController {

	private static final Logger logger = LoggerFactory.getLogger(EncuestaController.class);

	@Autowired
	private EncuestaService encuestaService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getEncuestas() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		EncuestaFilter encuestaFilter = new EncuestaFilter();
		encuestaFilter.setActivo(1);
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
			responseArray.put(json);
		}
		
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
}

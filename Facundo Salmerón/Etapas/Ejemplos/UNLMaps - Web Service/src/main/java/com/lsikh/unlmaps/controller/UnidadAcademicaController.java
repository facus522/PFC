package com.lsikh.unlmaps.controller;

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

import com.lsikh.unlmaps.model.UnidadAcademica;
import com.lsikh.unlmaps.service.UnidadAcademicaService;

@Controller
@RequestMapping(value = "/unidadAcademica")
public class UnidadAcademicaController {
	
	private static final Logger logger = LoggerFactory.getLogger(UnidadAcademicaController.class);
	
	@Autowired
	private UnidadAcademicaService unidadAcademicaService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getUnidadesAcademicas() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		List<UnidadAcademica> unidades = (List<UnidadAcademica>) unidadAcademicaService.findAll();
		Collections.sort(unidades, new Comparator<UnidadAcademica>() {
		    @Override
		    public int compare(UnidadAcademica u1, UnidadAcademica u2) {
		        return u1.getNombre().compareTo(u2.getNombre());
		    }
		});
		for(UnidadAcademica unidadAcademica : unidades) {
			JSONObject json = new JSONObject();
			json.put("id", unidadAcademica.getId());
			json.put("nombre", unidadAcademica.getNombre());
			responseArray.put(json);
		}
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}

}

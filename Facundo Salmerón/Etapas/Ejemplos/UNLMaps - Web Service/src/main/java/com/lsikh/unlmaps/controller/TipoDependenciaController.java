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

import com.lsikh.unlmaps.filter.TipoDependenciaFilter;
import com.lsikh.unlmaps.model.TipoDependencia;
import com.lsikh.unlmaps.service.TipoDependenciaService;

@Controller
@RequestMapping(value = "/tipoDependencia")
public class TipoDependenciaController {
	
	private static final Logger logger = LoggerFactory.getLogger(TipoDependenciaController.class);

	@Autowired
	private TipoDependenciaService tipoDependeciaService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getTipoDependenciaBusqueda() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		TipoDependenciaFilter filter = new TipoDependenciaFilter();
		filter.setEsVisible(Boolean.TRUE);
		List<TipoDependencia> tiposDependencias = (List<TipoDependencia>) tipoDependeciaService.filter(filter);
		Collections.sort(tiposDependencias, new Comparator<TipoDependencia>() {
		    @Override
		    public int compare(TipoDependencia t1, TipoDependencia t2) {
		        return t1.getDescripcion().compareTo(t2.getDescripcion());
		    }
		});
		for(TipoDependencia tipoDependencias : tiposDependencias) {
			JSONObject json = new JSONObject();
			json.put("id", tipoDependencias.getId());
			json.put("descripcion", tipoDependencias.getDescripcion());
			responseArray.put(json);
		}
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
}

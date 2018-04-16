package com.lsikh.unlmaps.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/actualizaciones")
public class ActualizacionController {
	
	@Value("${hash.code.planos}")
	private String planosHashCode;
	
	@Value("${hash.code.db.tipo.dependencia}")
	private String tipoDependenciaHashCode;
	
	@Value("${hash.code.db.unidad.academica}")
	private String unidadAcademicaHashCode;
	
	@RequestMapping(value = "/planosHashCode", method = RequestMethod.GET)
	@ResponseBody
	public String getPlanosHashCode() {
		return planosHashCode.toString();
	}
	
	@RequestMapping(value = "/tipoDependenciaHashCode", method = RequestMethod.GET)
	@ResponseBody
	public String getTipoDependenciaHashCode() {
		return tipoDependenciaHashCode.toString();
	}
	
	@RequestMapping(value = "/unidadAcademicaHashCode", method = RequestMethod.GET)
	@ResponseBody
	public String getUnidadAcademicaHashCode() {
		return unidadAcademicaHashCode.toString();
	}

}

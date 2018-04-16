package com.lsikh.unlmaps.controller;



import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsikh.unlmaps.model.Punto;
import com.lsikh.unlmaps.service.PuntoService;

@Controller
public class TestController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private PuntoService puntoService;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String testing() {
		Punto p =  puntoService.findById(4);
		List<Punto> a = new ArrayList<Punto>(p.getVecinos());
		return "Hola Mundo!";
	}


}

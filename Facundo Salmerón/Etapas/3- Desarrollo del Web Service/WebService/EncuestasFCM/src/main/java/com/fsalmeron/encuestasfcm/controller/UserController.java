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

import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getUsuarios() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		List<Usuario> usuarios = (List<Usuario>) usuarioService.findAll();
		Collections.sort(usuarios, new Comparator<Usuario>() {
		    @Override
		    public int compare(Usuario u1, Usuario u2) {
		        return u1.getNombreUsuario().compareTo(u2.getNombreUsuario());
		    }
		});
		for(Usuario user : usuarios) {
			JSONObject json = new JSONObject();
			json.put("id", user.getId());
			json.put("nombre", user.getNombreUsuario());
			responseArray.put(json);
		}
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}
	
}


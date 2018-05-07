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

import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.SexoService;
import com.fsalmeron.encuestasfcm.service.TipoUsuarioService;
import com.fsalmeron.encuestasfcm.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SexoService sexoService;
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getUsuarios() {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		List<Usuario> usuarios = (List<Usuario>) usuarioService.findAll();
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
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearUsuario(@RequestParam("nombre") String nombre, @RequestParam("password") String password, @RequestParam("fechaNacimiento") Date fechaNacimiento, @RequestParam("mail") String mail, @RequestParam("activo") Integer activo, @RequestParam("sexo") Integer sexo, @RequestParam("tipoUsuario") Integer tipoUsuario) {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombre);
		usuario.setPassword(password);
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setMail(mail);
		usuario.setActivo(activo);
		usuario.setSexo(sexoService.getById(sexo));
		usuario.setTipoUsuario(tipoUsuarioService.getById(tipoUsuario));
		JSONObject response = usuarioService.save(usuario);
		return response.toString();
	}
	
}


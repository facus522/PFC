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

import com.fsalmeron.encuestasfcm.filter.UsuarioFilter;
import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.SexoService;
import com.fsalmeron.encuestasfcm.service.TipoUsuarioService;
import com.fsalmeron.encuestasfcm.service.UsuarioService;
import com.fsalmeron.encuestasfcm.utils.EncryptionUtil;

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
	
	//http://localhost:8080/EncuestasFCM/usuarios/getAll
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
	
	//http://localhost:8080/EncuestasFCM/usuarios/saveUser?nombre=usuarioEjemplo&password=123456&fechaNacimiento=1995/05/23&mail=ejemplo@mail.com.ar&activo=1&sexo=1&tipoUsuario=2
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearUsuario(@RequestParam("nombre") String nombre, @RequestParam("password") String password, @RequestParam("fechaNacimiento") Date fechaNacimiento, @RequestParam("mail") String mail, @RequestParam("activo") Boolean activo, @RequestParam("sexo") Integer sexo, @RequestParam("tipoUsuario") Integer tipoUsuario) {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombre);
		usuario.setPassword(EncryptionUtil.encode(password));
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setMail(mail);
		usuario.setActivo(activo);
		usuario.setSexo(sexoService.getById(sexo));
		usuario.setTipoUsuario(tipoUsuarioService.getById(tipoUsuario));
		JSONObject response = usuarioService.save(usuario);
		logger.debug(response.toString());
		return response.toString();
	}
	
	//http://localhost:8080/EncuestasFCM/usuarios/loginUser?nombre=facusalmeron&password=proyectofinal
	@RequestMapping(value = "/loginUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String loginUsuario(@RequestParam("nombre") String nombre, @RequestParam("password") String password) {
		UsuarioFilter usuarioFilter = new UsuarioFilter();
		usuarioFilter.setActivo(Boolean.TRUE);
		usuarioFilter.setNombreUsuario(nombre);
		
		Usuario usuario = usuarioService.filterUnique(usuarioFilter);
		JSONObject response = new JSONObject();
		
		if (usuario != null) {
			if (usuario.getPassword().equals(EncryptionUtil.encode(password))) {
				response.put("exito", Boolean.TRUE);
				response.put("id", usuario.getId());
				response.put("nombre", usuario.getNombreUsuario());
				response.put("sexo", usuario.getSexo().getId());
				response.put("tipoUsuario", usuario.getTipoUsuario().getId());
			} else {
				response.put("exito", Boolean.FALSE);
				response.put("error", "La contraseña ingresada es incorrecta");
			}
		} else {
			response.put("exito", Boolean.FALSE);
			response.put("error", "El nombre de usuario ingresado es incorrecto");
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
}


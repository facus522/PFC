package com.fsalmeron.encuestasfcm.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsalmeron.encuestasfcm.filter.UsuarioFilter;
import com.fsalmeron.encuestasfcm.model.Sexo;
import com.fsalmeron.encuestasfcm.model.TipoUsuario;
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
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveUsuario(@RequestParam("nombre") String nombre, @RequestParam("password") String password, @RequestParam("fechaNacimiento") Date fechaNacimiento, @RequestParam("mail") String mail, @RequestParam("activo") Integer activo, @RequestParam("sexo") Integer sexo, @RequestParam("tipoUsuario") Integer tipoUsuario) {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombre);
		usuario.setPassword(password);
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setMail(mail);
		usuario.setActivo(activo);
		usuario.setSexo(new Sexo(sexo));
		usuario.setTipoUsuario(new TipoUsuario(tipoUsuario));
		JSONObject response = new JSONObject();
		if (validarUsuarioNuevo(usuario)) {
			usuarioService.saveOrUpdate(usuario);
			response.put("exito", Boolean.TRUE);
		} else {
			response.put("exito", Boolean.FALSE);
		}
		
		return response.toString();
	}
	
	private Boolean validarUsuarioNuevo(Usuario usuario) {
		UsuarioFilter usuarioFilter = new UsuarioFilter();
		usuarioFilter.setNombreUsuario(usuario.getNombreUsuario());
		
		if (CollectionUtils.isEmpty(usuarioService.filter(usuarioFilter))) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
}


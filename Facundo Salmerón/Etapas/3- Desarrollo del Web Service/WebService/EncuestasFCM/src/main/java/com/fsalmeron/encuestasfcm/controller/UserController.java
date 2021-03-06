package com.fsalmeron.encuestasfcm.controller;

import java.io.IOException;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.google.gson.Gson;

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
	
	//http://localhost:8080/EncuestasFCM/usuarios/saveUser?nombre=usuarioEjemplo&password=123456&fechaNacimiento=1995/05/23&mail=ejemplo@mail.com.ar&activo=1&sexo=1&tipoUsuario=2&validar=
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String crearUsuario(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("dni") Integer dni, @RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("password") String password, @RequestParam("fechaNacimiento") Date fechaNacimiento, @RequestParam("mail") String mail, @RequestParam("activo") Boolean activo, @RequestParam("sexo") Integer sexo, @RequestParam("tipoUsuario") Integer tipoUsuario, @RequestParam("validar") String validar) {
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setDni(dni);
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setPassword(EncryptionUtil.encode(password));
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setMail(mail);
		usuario.setActivo(activo);
		usuario.setSexo(sexoService.getById(sexo));
		usuario.setTipoUsuario(tipoUsuarioService.getById(tipoUsuario));
		JSONObject response = usuarioService.save(usuario, validar);
		logger.debug(response.toString());
		return response.toString();
	}
	
	
	@RequestMapping(value = "/prueba", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String pruebaPost(HttpServletRequest http) throws IOException {
		StringBuilder stringBuilder = new StringBuilder(1000);
		Scanner scanner = new Scanner(http.getInputStream());
		while (scanner.hasNextLine()) {
			stringBuilder.append(scanner.nextLine());
		}
		
		String body = stringBuilder.toString();
		System.out.println(body);
		
		body = body.substring(body.indexOf("{"));
		System.out.println(body);		
		Usuario usuario = new Gson().fromJson(body, Usuario.class);
		logger.debug(body);
		return "";
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
				response.put("nombreUsuario", usuario.getNombreUsuario());
				response.put("sexo", usuario.getSexo().getId());
				response.put("tipoUsuario", usuario.getTipoUsuario().getId());
				response.put("edad", calcularEdad(usuario.getFechaNacimiento()));
				response.put("nombre", usuario.getNombre());
				response.put("apellido", usuario.getApellido());
				response.put("dni", usuario.getDni());
			} else {
				response.put("exito", Boolean.FALSE);
				response.put("error", "La contraseņa ingresada es incorrecta");
				response.put("nroError", 2);
			}
		} else {
			response.put("exito", Boolean.FALSE);
			response.put("error", "El nombre de usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	private Integer calcularEdad(Date fechaNacimiento) {
		Date hoy = new Date();
		return Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();
	}
	
}


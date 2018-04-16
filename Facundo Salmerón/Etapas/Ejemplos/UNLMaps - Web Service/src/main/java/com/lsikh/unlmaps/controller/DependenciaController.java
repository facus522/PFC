package com.lsikh.unlmaps.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsikh.unlmaps.filter.PuntoFilter;
import com.lsikh.unlmaps.filter.TipoDependenciaFilter;
import com.lsikh.unlmaps.filter.UnidadAcademicaFilter;
import com.lsikh.unlmaps.model.Punto;
import com.lsikh.unlmaps.model.TipoDependencia;
import com.lsikh.unlmaps.model.UnidadAcademica;
import com.lsikh.unlmaps.service.PuntoService;
import com.lsikh.unlmaps.service.TipoDependenciaService;
import com.lsikh.unlmaps.service.UnidadAcademicaService;


@Controller
@RequestMapping(value = "/dependencias")
public class DependenciaController {

	private static final Logger logger = LoggerFactory.getLogger(DependenciaController.class);

	@Autowired
	private TipoDependenciaService tipoDependenciaService;

	@Autowired
	private UnidadAcademicaService unidadAcademicaService;

	@Autowired
	private PuntoService puntoService;

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/getDependencias", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getDependenciaPorTipoUnidad(@RequestParam("unidad") String unidad, @RequestParam("tipo") String tipo) {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		TipoDependenciaFilter tipoDependenciaFilter = new TipoDependenciaFilter();
		tipoDependenciaFilter.setDescripcion(tipo);
		TipoDependencia tipoDependencia = (TipoDependencia) tipoDependenciaService.filterUnique(tipoDependenciaFilter);

		UnidadAcademicaFilter unidadAcademicaFilter = new UnidadAcademicaFilter();
		unidadAcademicaFilter.setNombre(unidad);
		UnidadAcademica unidadAcademica = (UnidadAcademica) unidadAcademicaService.filterUnique(unidadAcademicaFilter);

		PuntoFilter puntoFilter = new PuntoFilter();
		puntoFilter.setTipoDependencia(tipoDependencia);
		puntoFilter.setUnidadAcademica(unidadAcademica);
		List<Punto> dependencias =  (List<Punto>) puntoService.filter(puntoFilter);

		Collections.sort(dependencias, new Comparator<Punto>() {
			@Override
			public int compare(Punto p1, Punto p2) {
				return p1.getNombre().compareTo(p2.getNombre());
			}
		});

		for(Punto dependencia : dependencias) {
			JSONObject json = new JSONObject();
			json.put("id", dependencia.getId());
			json.put("nombre", dependencia.getNombre());
			responseArray.put(json);
		}

		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/getDependenciasPorTipo", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getAllByTipo(@RequestParam("tipo") Integer id) {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();

		TipoDependencia tipoDependencia = new TipoDependencia(id);

		PuntoFilter puntoFilter = new PuntoFilter();
		puntoFilter.setTipoDependencia(tipoDependencia);
		List<Punto> dependencias =  (List<Punto>) puntoService.filter(puntoFilter);

		for(Punto dependencia : dependencias) {
			JSONObject json = new JSONObject();
			json.put("id", dependencia.getId());
			json.put("latitud", dependencia.getLatitud());
			json.put("longitud", dependencia.getLongitud());
			json.put("idEdificio", dependencia.getEdificio().getId());
			json.put("idUnidadAcademica", dependencia.getUnidadAcademica().getId());
			json.put("idTipoDependencia", dependencia.getTipoDependencia().getId());
			json.put("nombre", dependencia.getNombre());
			json.put("piso", dependencia.getPiso());
			json.put("tieneImagen", dependencia.getTieneImagen());
			responseArray.put(json);
		}

		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/getEdificio", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getEdificio(@RequestParam("tipo") Integer idTipoDependencia
			, @RequestParam("unidad") Integer idUnidadAcademica) {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();

		TipoDependencia tipoDependencia = new TipoDependencia(idTipoDependencia);
		UnidadAcademica unidadAcademica = new UnidadAcademica(idUnidadAcademica);

		PuntoFilter puntoFilter = new PuntoFilter();
		puntoFilter.setTipoDependencia(tipoDependencia);
		puntoFilter.setUnidadAcademica(unidadAcademica);
		Punto edificio =  puntoService.filterUnique(puntoFilter);

		JSONObject json = new JSONObject();
		json.put("id", edificio.getId());
		json.put("latitud", edificio.getLatitud());
		json.put("longitud", edificio.getLongitud());
		json.put("idEdificio", edificio.getEdificio().getId());
		json.put("idUnidadAcademica", edificio.getUnidadAcademica().getId());
		json.put("idTipoDependencia", edificio.getTipoDependencia().getId());
		json.put("nombre", edificio.getNombre());
		json.put("piso", edificio.getPiso());
		json.put("tieneImagen", edificio.getTieneImagen());
		responseArray.put(json);

		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}


	@RequestMapping(value = "/getImagen", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImagen(@RequestParam("id") Integer id) {
		Resource fileResource = resourceLoader.getResource("classpath:Imagenes/img_" + id + ".jpg");
		try {
			InputStream in = fileResource.getInputStream();
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			logger.error("Error al enviar imagen o la imagen no existe");
		}
		return null;
	}

}

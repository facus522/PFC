package com.lsikh.unlmaps.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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

import com.lsikh.unlmaps.model.Punto;
import com.lsikh.unlmaps.service.PuntoService;

@Controller
@RequestMapping(value = "/camino")
public class CaminoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CaminoController.class);

	private static final Double[] CIUDAD_UNIVERSITARIA_BOUNDS = new Double[] {-31.641036, -60.674475, -31.639325, -60.670192};

	private List<Punto> puntos;

	@Autowired
	private PuntoService puntoService;

	@RequestMapping(value = "/getCamino", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCamino(@RequestParam("latActual") Double latActual, @RequestParam("lonActual") Double longActual, @RequestParam("piso") Integer piso,
			@RequestParam("id") Integer idObjetivo) {
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();

		Punto miPosicion = getPuntoMasCercano(latActual, longActual, piso);
		List<Punto> visitado = new ArrayList<Punto>();
		List<Punto> camino = new ArrayList<Punto>();

		PriorityQueue<Punto> cola = new PriorityQueue<Punto>();
		cola.add(miPosicion);
		visitado.add(miPosicion);

		while (!cola.isEmpty()) {
			Punto puntoCola = cola.remove();
			//Si puntoCola es el punto que estoy buscando, corto y empiezo a recorrer hacia atras para armar el camino
			if (puntoCola.getId().equals(idObjetivo)) {
				while (puntoCola != null) {
					camino.add(puntoCola);
					puntoCola = puntoCola.getPadre();
				}
				Collections.reverse(camino);
				break;
			}
			//Sino, agrego todos los vecinos de aux a la Cola y sigo buscando
			List<Punto> vecinos = new ArrayList<Punto>(puntoCola.getVecinos());
			for (int indiceVecinos = 0; indiceVecinos < puntoCola.getCantidadVecinos(); indiceVecinos++) {
				Punto vecino = vecinos.get(indiceVecinos);
				if (!visitado.contains(vecino)) {
					vecino.setPadre(puntoCola);
					vecino.costo = puntoCola.costo + calculaDistanciaCosto(vecino.getLatitud(), vecino.getLongitud(), puntoCola.getLatitud(), puntoCola.getLongitud());
					visitado.add(vecino);
					if (!cola.contains(vecino)) {
						cola.add(vecino);
					}
					for (Punto i : cola) {
						if (i.getLatitud() == vecino.getLatitud() &&
								i.getLongitud() == vecino.getLongitud() &&
								i.getPiso() == vecino.getPiso() && i.costo > vecino.costo) {
							cola.remove(i);
							cola.add(vecino);
						}
					}
				}
			}
		}

		for(Punto puntoCamino : camino) {
			JSONObject json = new JSONObject();
			json.put("id", puntoCamino.getId());
			json.put("latitud", puntoCamino.getLatitud());
			json.put("longitud", puntoCamino.getLongitud());
			json.put("piso", puntoCamino.getPiso());
			json.put("nombre", puntoCamino.getNombre());
			json.put("idEdificio", puntoCamino.getEdificio().getId());
			json.put("idUnidadAcademica", puntoCamino.getUnidadAcademica().getId());
			json.put("idTipoDependencia", puntoCamino.getTipoDependencia().getId());
			json.put("tieneImagen", puntoCamino.getTieneImagen());
			responseArray.put(json);
		}
		
		response.put("response", responseArray);
		logger.debug(response.toString());
		return response.toString();
	}

	public Punto getPuntoMasCercano(Double latitud, Double longitud, Integer piso){
		puntos = (List<Punto>) puntoService.findAll();
		Punto punto = puntos.get(0);
		if(enCiudadUniversitaria(latitud, longitud)) {
			Double distanciaReferencia = Math.pow(puntos.get(0).getLatitud() - latitud, 2) + Math.pow(puntos.get(0).getLongitud() - longitud, 2);
			for(int index = 1; index < puntos.size(); index++) {
				Double distanciaPunto = Math.pow(puntos.get(index).getLatitud() - latitud, 2) + Math.pow(puntos.get(index).getLongitud() - longitud, 2);
				if (distanciaPunto < distanciaReferencia && puntos.get(index).getPiso().equals(piso)) {
					distanciaReferencia = distanciaPunto;
					punto = puntos.get(index);
				}
			}
		}
		return punto;
	}

	public boolean enCiudadUniversitaria(Double lat, Double lon){
		return lat > CIUDAD_UNIVERSITARIA_BOUNDS[0] && lat < CIUDAD_UNIVERSITARIA_BOUNDS[2] && lon > CIUDAD_UNIVERSITARIA_BOUNDS[1] && lon < CIUDAD_UNIVERSITARIA_BOUNDS[3];
	}

	public Double calculaDistanciaCosto(Double lat1, Double lon1, Double lat2, Double lon2){
		Double dlat = (lat2 - lat1);
		Double dlon = (lon2 - lon1);
		Double dist = Math.sqrt(Math.pow(dlat,2) + Math.pow(dlon,2));
		String.format("%.2f", dist);
		return dist;
	}

}

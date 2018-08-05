package com.fsalmeron.encuestasfcm.service;

import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.InformacionNoticia;

public interface InformacionNoticiaService extends BaseService<InformacionNoticia, Integer>{
	public JSONObject save(InformacionNoticia informacionNoticia);
}

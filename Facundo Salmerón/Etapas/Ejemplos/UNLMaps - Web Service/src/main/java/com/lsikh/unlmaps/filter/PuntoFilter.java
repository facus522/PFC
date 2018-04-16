package com.lsikh.unlmaps.filter;

import com.lsikh.unlmaps.base.BaseFilter;
import com.lsikh.unlmaps.model.Edificio;
import com.lsikh.unlmaps.model.TipoDependencia;
import com.lsikh.unlmaps.model.UnidadAcademica;

public class PuntoFilter extends BaseFilter<Integer>{
	
	private TipoDependencia tipoDependencia;
	
	private UnidadAcademica unidadAcademica;
	
	private Edificio edificio;

	public TipoDependencia getTipoDependencia() {
		return tipoDependencia;
	}

	public void setTipoDependencia(TipoDependencia tipoDependencia) {
		this.tipoDependencia = tipoDependencia;
	}

	public UnidadAcademica getUnidadAcademica() {
		return unidadAcademica;
	}

	public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
		this.unidadAcademica = unidadAcademica;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

}

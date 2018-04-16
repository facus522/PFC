package com.lsikh.unlmaps.base;

import java.io.Serializable;

/**
 * Representa una entidad del dominio que puede ser persistida.
 * 
 * @author flang
 * 
 */
public interface Persistence<PK extends Serializable> extends Serializable {

	/**
	 * Devuelve el identificador unico de este objeto del dominio.
	 * 
	 * 
	 * @return identificador unico del objeto
	 */
	public PK getId();

	/**
	 * Establece el identificador unico a este objeto del dominio.
	 * 
	 * @param id
	 *            el nuevo identificador unico
	 */
	public void setId(PK id);

}

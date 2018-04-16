package com.lsikh.unlmaps.base;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseFilter<PK extends Serializable> {

	// por default solo busca entidades activas.
	private PK[] excludeIds;

	private String[] returnedAttributes;

	private String[] eagerRelationShips;

	private Integer firstResult;

	private Integer maxResult;

	private Map<String, String> aliases = new HashMap<String, String>();

	private Map<String, String> leftAliases = new HashMap<String, String>();

	public BaseFilter() {}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(getClass().getName());
		try {
			buffer.append("{ ");
			BeanInfo info = Introspector.getBeanInfo(getClass(), Object.class);
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				String value = String.valueOf((pd.getReadMethod() != null ? pd.getReadMethod().invoke(this) : "?"));
				buffer.append(" [" + pd.getName() + "=" + value + "]");
			}
			buffer.append(" }");
		} catch (Exception e) {
			buffer.append("Error: " + e.getMessage());
		}
		return buffer.toString();
	}

	public PK[] getExcludeIds() {
		return excludeIds;
	}

	/**
	 * Filtro utilizado para almacenar ids de entidades que se desean excluir del resultado
	 * 
	 * @param excludeIds
	 */
	public void setExcludeIds(PK[] excludeIds) {
		this.excludeIds = excludeIds;
	}

	public String[] getReturnedAttributes() {
		return returnedAttributes;
	}

	/**
	 * Permite indicar que atributos del pojo son los que se desean cargar en memoria NOTA: esta funcionalidad no se
	 * encuentra implementada en forma gen�rica a�n
	 * 
	 * @param returnedAttributes
	 */
	public void setReturnedAttributes(String[] returnedAttributes) {
		this.returnedAttributes = returnedAttributes;
	}

	public String[] getEagerRelationShips() {
		return eagerRelationShips;
	}

	public void setEagerRelationShips(String[] eagerRelationShips) {
		this.eagerRelationShips = eagerRelationShips;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	/**
	 * Permite saltear las primeras firstResult - 1 filas
	 * 
	 * @return
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResult() {
		return maxResult;
	}

	/**
	 * Permite setear la cantidad maxima de filas que devuelve la consulta
	 * 
	 * @param maxResult
	 */
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}

	public BaseFilter<PK> addAlias(String associationPath, String alias) {
		this.aliases.put(associationPath, alias);
		return this;
	}

	public Map<String, String> getAliases() {
		return aliases;
	}

	public BaseFilter<PK> addLeftAlias(String associationPath, String alias) {
		this.leftAliases.put(associationPath, alias);
		return this;
	}

	public Map<String, String> getLeftAliases() {
		return leftAliases;
	}

}
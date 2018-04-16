package com.lsikh.unlmaps.base;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity<PK extends Serializable> implements Persistence<PK> {

	private static final long serialVersionUID = 1L;

	public interface Attribute {

		public static final String ID = "id";

	}

	protected PK id;

	@Override
	public void setId(PK id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Persistence)) {
			return false;
		}
		Persistence<PK> other = (Persistence<PK>) obj;
		if (getId() == null || other.getId() == null) {
			return false;
		}
		return this.getId().equals(other.getId());
	}

}

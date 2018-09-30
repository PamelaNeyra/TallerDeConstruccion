package com.pe.sercosta.scks.converter;

public abstract class IAbstractConverter <M, E> {

	public abstract M convertToModel(E entity);
	
	public abstract E convertToEntity(M model);
	
}

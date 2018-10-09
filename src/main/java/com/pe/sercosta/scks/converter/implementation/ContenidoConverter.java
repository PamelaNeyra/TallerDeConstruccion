package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.models.ContenidoModel;

@Component("contenidoConverter")
public class ContenidoConverter extends IAbstractConverter<ContenidoModel, Contenido>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public ContenidoModel convertToModel(Contenido entity) {
		ContenidoModel model = MAPPER.map(entity, ContenidoModel.class);
		return model;
	}

	@Override
	public Contenido convertToEntity(ContenidoModel model) {
		Contenido entity = MAPPER.map(model, Contenido.class);
		return entity;
	}

}

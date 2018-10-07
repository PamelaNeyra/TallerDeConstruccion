package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.models.MuestraModel;

@Component("muestraConverter")
public class MuestraConverter extends IAbstractConverter<MuestraModel, Muestra>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public MuestraModel convertToModel(Muestra entity) {
		MuestraModel model = MAPPER.map(entity, MuestraModel.class);
		return model;
	}

	@Override
	public Muestra convertToEntity(MuestraModel model) {
		Muestra entity = MAPPER.map(model, Muestra.class);
		return entity;
	}

}

package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.models.PresentacionModel;

@Component("presentacionConverter")
public class PresentacionConverter extends IAbstractConverter<PresentacionModel, Presentacion>{

	private static final ModelMapper MAPPER = new ModelMapper();

	@Override
	public PresentacionModel convertToModel(Presentacion entity) {
		PresentacionModel model = MAPPER.map(entity, PresentacionModel.class);
		return model;
	}

	@Override
	public Presentacion convertToEntity(PresentacionModel model) {
		Presentacion entity = MAPPER.map(model, Presentacion.class);
		return entity;
	}
	
}

package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.models.AsignacionModel;

@Component("asignacionConverter")
public class AsignacionConverter extends IAbstractConverter<AsignacionModel, Asignacion>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public AsignacionModel convertToModel(Asignacion entity) {
		AsignacionModel model = MAPPER.map(entity, AsignacionModel.class);
		return model;
	}

	@Override
	public Asignacion convertToEntity(AsignacionModel model) {
		Asignacion entity = MAPPER.map(model, Asignacion.class);
		return entity;
	}

}

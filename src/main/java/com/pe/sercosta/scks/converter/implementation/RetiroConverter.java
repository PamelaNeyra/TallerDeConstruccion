package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Retiro;
import com.pe.sercosta.scks.models.RetiroModel;

@Component("retiroConverter")
public class RetiroConverter extends IAbstractConverter<RetiroModel, Retiro>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public RetiroModel convertToModel(Retiro entity) {
		RetiroModel model = new RetiroModel();
		model.setCantidad(entity.getCantidad());
		model.setIdLote(entity.getRetiroPK().getIdLote());
		model.setIdPresentacion(entity.getRetiroPK().getIdPresentacion());
		return model;
	}

	@Override
	public Retiro convertToEntity(RetiroModel model) {
		Retiro entity = MAPPER.map(model, Retiro.class);
		return entity;
	}

}

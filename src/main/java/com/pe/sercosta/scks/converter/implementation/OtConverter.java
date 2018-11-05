package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.models.OtModel;

@Component("otConverter")
public class OtConverter extends IAbstractConverter<OtModel, Muestra>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public OtModel convertToModel(Muestra entity) {
		OtModel model = MAPPER.map(entity, OtModel.class);
		model.setEstado(entity.getEstaMuestreado() ? "Muestreado" : "No Muestreado");
		return model;
	}

	@Override
	public Muestra convertToEntity(OtModel model) {
		Muestra entity = MAPPER.map(model, Muestra.class);
		return entity;
	}

}

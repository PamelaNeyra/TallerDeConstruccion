package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.models.PlantaModel;

public class PlantaConverter extends IAbstractConverter<PlantaModel, Planta>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public PlantaModel convertToModel(Planta entity) {
		PlantaModel model = MAPPER.map(entity, PlantaModel.class);
		return model;
	}

	@Override
	public Planta convertToEntity(PlantaModel model) {
		Planta entity = MAPPER.map(model, Planta.class);
		return entity;
	}

}

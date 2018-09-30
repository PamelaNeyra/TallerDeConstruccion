package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.models.LoteModel;

@Component("loteConverter")
public class LoteConverter extends IAbstractConverter<LoteModel, Lote>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public LoteModel convertToModel(Lote entity) {
		LoteModel model = MAPPER.map(entity, LoteModel.class);
		return model;
	}

	@Override
	public Lote convertToEntity(LoteModel model) {
		Lote entity = MAPPER.map(model, Lote.class);
		return entity;
	}

}

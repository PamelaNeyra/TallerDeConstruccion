package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.models.views.LoteSaldoView;

@Component("loteSaldoViewConverter")
public class LoteSaldoViewConverter extends IAbstractConverter<LoteSaldoView, Lote>{
	
	private static final ModelMapper MAPPER = new ModelMapper();

	@Override
	public LoteSaldoView convertToModel(Lote entity) {
		LoteSaldoView model = MAPPER.map(entity, LoteSaldoView.class);
		model.setSaldo(entity.getCantidadTotal()-entity.getComprometidoTotal());
		return model;
	}

	@Override
	public Lote convertToEntity(LoteSaldoView model) {
		Lote entity=MAPPER.map(model,Lote.class);
		return entity;
	}

}

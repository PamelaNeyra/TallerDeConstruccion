package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.models.views.LoteView;


@Component("loteViewConverter")
public class LoteViewConverter extends IAbstractConverter<LoteView, Lote>{
		
		private static final ModelMapper MAPPER = new ModelMapper();

		@Override
		public LoteView convertToModel(Lote entity) {
			LoteView model = MAPPER.map(entity, LoteView.class);
			return model;
		}

		@Override
		public Lote convertToEntity(LoteView model) {
			Lote entity = MAPPER.map(model, Lote.class);
			return entity;
		}
		
}

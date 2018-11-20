package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.PlantaPresentacion;
import com.pe.sercosta.scks.models.views.PresentacionView;

@Component("presentacionViewConverter")
public class PresentacionViewConverter extends IAbstractConverter<PresentacionView, PlantaPresentacion>{
	
	private static final ModelMapper MAPPER = new ModelMapper();

	@Override
	public PresentacionView convertToModel(PlantaPresentacion entity) {
		PresentacionView model = MAPPER.map(entity, PresentacionView.class);
		model.setDescripcion(entity.getPresentacion().getDescripcion()); 
		model.setSaldo(entity.getCantidadTotal()-entity.getComprometidoTotal());
		return model;
	}

	@Override
	public PlantaPresentacion convertToEntity(PresentacionView model) {
		PlantaPresentacion entity = MAPPER.map(model, PlantaPresentacion.class);
		return entity;
	}

}

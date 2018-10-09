package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.models.OrdenVentaModel;

@Component("ordenVentaConverter")
public class OrdenVentaConverter extends IAbstractConverter<OrdenVentaModel, OrdenVenta>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public OrdenVentaModel convertToModel(OrdenVenta entity) {
		OrdenVentaModel model = MAPPER.map(entity, OrdenVentaModel.class);
		return model;
	}

	@Override
	public OrdenVenta convertToEntity(OrdenVentaModel model) {
		OrdenVenta entity = MAPPER.map(model, OrdenVenta.class);
		return entity;
	}



	

}

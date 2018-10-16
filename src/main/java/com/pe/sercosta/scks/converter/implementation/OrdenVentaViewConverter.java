package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.models.views.OrdenVentaView;

@Component("ordenVentaViewConverter")
public class OrdenVentaViewConverter extends IAbstractConverter<OrdenVentaView, OrdenVenta>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public OrdenVentaView convertToModel(OrdenVenta entity) {
		OrdenVentaView model = MAPPER.map(entity, OrdenVentaView.class);
		return model;
	}

	@Override
	public OrdenVenta convertToEntity(OrdenVentaView model) {
		OrdenVenta entity = MAPPER.map(model, OrdenVenta.class);
		return entity;
	}

}

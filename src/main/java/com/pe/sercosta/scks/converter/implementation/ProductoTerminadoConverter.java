package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.models.ProductoTerminadoModel;

@Component("productoTerminadoConverter")
public class ProductoTerminadoConverter extends IAbstractConverter<ProductoTerminadoModel, ProductoTerminado>{

	private static final ModelMapper MAPPER = new ModelMapper();

	@Override
	public ProductoTerminadoModel convertToModel(ProductoTerminado entity) {
		ProductoTerminadoModel model = MAPPER.map(entity, ProductoTerminadoModel.class);
		return model;
	}

	@Override
	public ProductoTerminado convertToEntity(ProductoTerminadoModel model) {
		ProductoTerminado entity = MAPPER.map(model, ProductoTerminado.class);
		return entity;
	}
	
}

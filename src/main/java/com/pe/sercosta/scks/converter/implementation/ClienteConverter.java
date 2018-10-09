package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.models.views.ClienteView;

@Component("clienteConverter")
public class ClienteConverter extends IAbstractConverter<ClienteView, Cliente>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public ClienteView convertToModel(Cliente entity) {
		ClienteView model = MAPPER.map(entity, ClienteView.class);
		return model;
	}

	@Override
	public Cliente convertToEntity(ClienteView model) {
		Cliente entity = MAPPER.map(model, Cliente.class);
		return entity;
	}

}

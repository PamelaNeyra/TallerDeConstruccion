package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.models.ClienteModel;

@Component("clienteConverter")
public class ClienteConverter extends IAbstractConverter<ClienteModel, Cliente>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public ClienteModel convertToModel(Cliente entity) {
		ClienteModel model = MAPPER.map(entity, ClienteModel.class);
		return model;
	}

	@Override
	public Cliente convertToEntity(ClienteModel model) {
		Cliente entity = MAPPER.map(model, Cliente.class);
		return entity;
	}

}

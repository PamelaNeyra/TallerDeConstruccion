package com.pe.sercosta.scks.converter.implementation;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.entities.Retiro;
import com.pe.sercosta.scks.models.OrdenRetiroModel;

@Component("ordenRetiroConverter")
public class OrdenRetiroConverter extends IAbstractConverter<OrdenRetiroModel, OrdenRetiro>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public OrdenRetiroModel convertToModel(OrdenRetiro entity) {
		OrdenRetiroModel model = new OrdenRetiroModel();
		model.setDescripcion(entity.getIdMotivo().getDescripcion());
		model.setFechaRetiro(entity.getFechaRetiro());
		model.setIdOrdenRetiro(entity.getIdOrdenRetiro());
		return model;
	}

	@Override
	public OrdenRetiro convertToEntity(OrdenRetiroModel model) {
		OrdenRetiro entity = MAPPER.map(model, OrdenRetiro.class);	
		List<Retiro> listaRetiro = new ArrayList<>();
		if(model.getRetiroList() != null)
		{
			model.getRetiroList().forEach(retiro -> {
				Retiro retiroEntity = new Retiro(retiro.getIdLote(), retiro.getIdPresentacion(), 0);
				retiroEntity.setCantidad(retiro.getCantidad());
				listaRetiro.add(retiroEntity);
			});
			entity.setRetiroList(listaRetiro);
		}
		return entity;
	}

}

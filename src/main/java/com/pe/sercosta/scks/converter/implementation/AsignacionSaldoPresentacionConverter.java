package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.models.AsignacionSaldoPresentacionModel;

@Component("asignacionSaldoPresentacionConverter")
public class AsignacionSaldoPresentacionConverter extends IAbstractConverter<AsignacionSaldoPresentacionModel, Asignacion>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public AsignacionSaldoPresentacionModel convertToModel(Asignacion entity) {
		AsignacionSaldoPresentacionModel model = MAPPER.map(entity, AsignacionSaldoPresentacionModel.class);
		model.setNombreCliente(entity.getOrdenVenta().getIdCliente().getNombreCliente());
		model.setFechaAsignacion(entity.getOrdenVenta().getFechaAsignacion());
		model.setIdLote(entity.getContenido().getLote().getIdLote());
		model.setOt(entity.getContenido().getCodigoTrazabilidad());
		return model;
	}

	@Override
	public Asignacion convertToEntity(AsignacionSaldoPresentacionModel model) {
		Asignacion entity = MAPPER.map(model, Asignacion.class);
		return entity;
	}

}

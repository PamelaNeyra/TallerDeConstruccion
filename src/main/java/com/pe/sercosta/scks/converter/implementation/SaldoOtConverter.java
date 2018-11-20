package com.pe.sercosta.scks.converter.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.pe.sercosta.scks.converter.IAbstractConverter;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.models.SaldoOtModel;

@Component("otSaldoConverter")
public class SaldoOtConverter extends IAbstractConverter<SaldoOtModel, Muestreo>{

	private static final ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public SaldoOtModel convertToModel(Muestreo entity) {
		SaldoOtModel model = MAPPER.map(entity, SaldoOtModel.class);
        if(entity.getMuestra().getOt()==null) {
        	model.setOt("-");
        }else {
    	    model.setOt(entity.getMuestra().getOt());
        }
	    model.setFechaMuestreado(entity.getMuestra().getFechaMuestreado());
		model.setNombreLaboratorio(entity.getMuestra().getIdLaboratorio().getNombreLaboratorio());
		return model;
	}

	@Override
	public Muestreo convertToEntity(SaldoOtModel model) {
		Muestreo entity = MAPPER.map(model, Muestreo.class);
		return entity;
	}

}

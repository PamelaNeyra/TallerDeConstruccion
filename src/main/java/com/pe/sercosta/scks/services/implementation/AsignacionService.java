package com.pe.sercosta.scks.services.implementation;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.services.IAsignacionService;

@Service("asignacionService")
public class AsignacionService implements IAsignacionService {

	@Override
	public List<Asignacion> listarAsignacion(OrdenVenta orden) {
		// TODO Auto-generated method stub
		return null;
	}

}

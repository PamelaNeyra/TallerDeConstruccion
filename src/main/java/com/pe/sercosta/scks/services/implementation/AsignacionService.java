package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.repositories.IAsignacionRepository;
import com.pe.sercosta.scks.services.IAsignacionService;

@Service("asignacionService")
public class AsignacionService implements IAsignacionService {
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("asignacionRepository")
	private IAsignacionRepository asignacionRepository;
	
	@Override
	public List<AsignacionModel> listarAsignacion(OrdenVenta orden) {
		try {
			return asignacionRepository.listarAsignacion(sesion, orden);
		} catch (SercostaException sx) {
			throw sx;
		} catch (Exception ex) {
			throw ex;
		}
	}

}

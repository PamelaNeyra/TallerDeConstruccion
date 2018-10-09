package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.views.OrdenVentaView;
import com.pe.sercosta.scks.repositories.IOrdenVentaRepository;
import com.pe.sercosta.scks.services.IOrdenVentaService;

@Service("ordenVentaService")
public class OrdenVentaService implements IOrdenVentaService {

	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("ordenVentaRepository")
	private IOrdenVentaRepository ordenVentaRepository;
	
	@Override
	public List<OrdenVentaView> listarOrdenVenta(Planta planta) {
		try {
			return ordenVentaRepository.listarOrdenVenta(sesion, planta);
		} catch (SercostaException sx) {
			throw sx;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public OrdenVentaModel obtenerOrdenVenta(OrdenVenta orden) {
		try {
			return ordenVentaRepository.obtenerOrdenVenta(sesion, orden);
		} catch (SercostaException sx) {
			throw sx;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void registrarOrdenVenta(OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrdenVenta> buscarOrdenVenta(OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarOrdenVenta(OrdenVenta ordenVenta) {
		
		
	}

}

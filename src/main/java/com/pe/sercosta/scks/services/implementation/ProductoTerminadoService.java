package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ProductoTerminadoSaldoModel;
import com.pe.sercosta.scks.repositories.IProductoTerminadoRepository;
import com.pe.sercosta.scks.services.IProductoTerminadoService;

@Service("productoTerminadoService")
public class ProductoTerminadoService implements IProductoTerminadoService{

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : ProductoTerminado] -> ";
	
	@Autowired
	@Qualifier("productoTerminadoRepository")
	private IProductoTerminadoRepository productoTerminadoRepository;
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Override
	public List<ProductoTerminado> listarProducto(Planta planta) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			return productoTerminadoRepository.listarProductoTerminado(sesion,planta);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar productos terminados", ex.getMessage());
		} finally {
			sesion.close();
		}
	}	
	

	@Override
	public List<ProductoTerminado> buscarProducto(ProductoTerminado producto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ProductoTerminadoSaldoModel> listarProductosTerminadosSaldos(EntityManager sesion,
			Laboratorio laboratorio, Planta planta, Muestra muestra) {
		try {
			return productoTerminadoRepository.listarProductosTerminadosSaldo(sesion, laboratorio, planta, muestra);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar productos terminados saldo agrupados", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}

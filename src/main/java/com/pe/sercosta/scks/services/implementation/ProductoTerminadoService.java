package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IProductoTerminadoRepository;
import com.pe.sercosta.scks.services.IProductoTerminadoService;

@Service("productoTerminadoService")
public class ProductoTerminadoService implements IProductoTerminadoService{

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : ProductoTerminado] -> ";
	
	private IProductoTerminadoRepository productoTerminadoRepository;
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Override
	public List<ProductoTerminado> listarProducto() {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			return productoTerminadoRepository.listarProductoTerminado(sesion);
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

}

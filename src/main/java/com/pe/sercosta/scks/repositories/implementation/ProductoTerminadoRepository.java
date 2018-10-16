package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IProductoTerminadoRepository;

@Repository("productoTerminadoRepository")
public class ProductoTerminadoRepository implements IProductoTerminadoRepository{

	private static final Log LOG = LogFactory.getLog(ProductoTerminadoRepository.class);
	private static final String CAPA = "[Repository : ProductoTerminado] -> ";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoTerminado> listarProductoTerminado(EntityManager sesion, Planta planta) {
		List<ProductoTerminado> listaProductoTerminado = new ArrayList<ProductoTerminado>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_productos_terminados");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				ProductoTerminado aux = new ProductoTerminado();
				aux.setIdProductoTerminado((String) o[0]);
				//aux.setIdSubproducto((String) o[1]);
				aux.setDescripcion((String) o[1]);
				aux.setCantidadTotal((Double) o[2]);
				//aux.setComprometidoTotal();
				listaProductoTerminado.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los productos terminados", ex.getMessage());
		}
		return listaProductoTerminado;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<ProductoTerminado> buscarProductoTerminado(Session sesion, ProductoTerminado productoTerminado) {
		List<ProductoTerminado> listaBD = new ArrayList<ProductoTerminado>();
		List<ProductoTerminado> listaProductoTerminado = new ArrayList<ProductoTerminado>();
		ProductoTerminado productoTerminadoTmp = new ProductoTerminado();
		try {
			Query<?> myquery = sesion.getNamedQuery("buscarProductoTerminadoProcedimientoAlmacenado")
					.setParameter("id_producto_terminado", productoTerminado.getIdProductoTerminado());
			listaBD = (List<ProductoTerminado>) myquery.list();
			for (ProductoTerminado prstnIte : listaBD) {
				productoTerminadoTmp.setIdProductoTerminado(prstnIte.getIdProductoTerminado());
				productoTerminadoTmp.setIdSubproducto(prstnIte.getIdSubproducto());
				productoTerminadoTmp.setDescripcion(prstnIte.getDescripcion());
				productoTerminadoTmp.setCantidadTotal(prstnIte.getCantidadTotal());
				productoTerminadoTmp.setComprometidoTotal(prstnIte.getComprometidoTotal());
				listaProductoTerminado.add(productoTerminadoTmp);
			}
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al buscar el producto terminado", ex.getMessage());
		}
		return listaProductoTerminado;
	}*/

}
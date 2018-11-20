package com.pe.sercosta.scks.repositories.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ProductoTerminadoSaldoModel;
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

	@Override
	public void registrarProductoTerminado(EntityManager sesion, ProductoTerminado productoTerminado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarProductoTerminado(EntityManager sesion, ProductoTerminado productoTerminado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarProductoTerminado(EntityManager sesion, ProductoTerminado productoTerminado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductoTerminadoSaldoModel> listarProductosTerminadosSaldo(EntityManager sesion,
			Laboratorio laboratorio, Planta planta, Muestra muestra) {
		List<ProductoTerminadoSaldoModel> listaProductoTerminadoSaldoModel = new ArrayList<ProductoTerminadoSaldoModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_grupo_prod_terminado_saldos");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN);
			myquery.setParameter(1, laboratorio.getNombreLaboratorio());
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.setParameter(1, muestra.getOt());
			myquery.setParameter(1, muestra.getFechaMuestreado());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				ProductoTerminadoSaldoModel aux = new ProductoTerminadoSaldoModel();
				aux.setDescripcion((String) o[0]);
				aux.setCantidad_total((double) o[1]);
				listaProductoTerminadoSaldoModel.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los productos terminados saldos model", ex.getMessage());
		}
		return listaProductoTerminadoSaldoModel;
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
package com.pe.sercosta.scks.repositories.implementation;

//TODO: Adecuar a las interfaces necesarias
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;

@NamedNativeQueries({
		@NamedNativeQuery(name = "listarProductoTerminadoProcedimientoAlmacenado", query = "CALL listarProductoTerminado()", resultClass = ProductoTerminado.class),
		@NamedNativeQuery(name = "buscarProductoTerminadoProcedimientoAlmacenado", query = "CALL buscarProductoTerminado(:id_producto_terminado)", resultClass = ProductoTerminado.class) })
// TODO: falta el implements IPresentacionRepository
public class ProductoTerminadoRepository {

	private static final Log LOG = LogFactory.getLog(ProductoTerminadoRepository.class);
	private static final String CAPA = "[Repository : ProductoTerminado] -> ";
	
	// @Override
	public List<ProductoTerminado> listarProductoTerminado(Session sesion) {
		List<ProductoTerminado> listaBD = new ArrayList<ProductoTerminado>();
		List<ProductoTerminado> listaProductoTerminado = new ArrayList<ProductoTerminado>();
		ProductoTerminado productoTerminadoTmp = new ProductoTerminado();
		try {
			@SuppressWarnings("unchecked")
			Query<ProductoTerminado> myquery = sesion.getNamedQuery("listarProductoTerminadoProcedimientoAlmacenado");
			listaBD = myquery.list();
			for (ProductoTerminado productoTerminado : listaBD) {
				productoTerminadoTmp.setIdProductoTerminado(productoTerminado.getIdProductoTerminado());
				productoTerminadoTmp.setIdSubproducto(productoTerminado.getIdSubproducto());
				productoTerminadoTmp.setDescripcion(productoTerminado.getDescripcion());
				productoTerminadoTmp.setCantidadTotal(productoTerminado.getCantidadTotal());
				productoTerminadoTmp.setComprometidoTotal(productoTerminado.getComprometidoTotal());
				listaProductoTerminado.add(productoTerminadoTmp);
			}
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los productos terminados", ex.getMessage());
		}
		return listaProductoTerminado;
	}

	@SuppressWarnings("unchecked")
	// @Override
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
	}

}
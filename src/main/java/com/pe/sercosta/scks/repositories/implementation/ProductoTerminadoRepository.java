package com.pe.sercosta.scks.repositories.implementation;

//TODO: Adecuar a las interfaces necesarias
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import com.pe.sercosta.scks.entities.ProductoTerminado;
//import com.pe.sercosta.scks.repositories.IProductoTerminadoRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "listarProductoTerminadoProcedimientoAlmacenado", query = "CALL listarProductoTerminado()", resultClass = ProductoTerminado.class),
		@NamedNativeQuery(name = "buscarProductoTerminadoProcedimientoAlmacenado", query = "CALL buscarProductoTerminado(:id_producto_terminado)", resultClass = ProductoTerminado.class) })
// TODO: falta el implements IPresentacionRepository
public class ProductoTerminadoRepository {

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
			throw ex;
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
				productoTerminadoTmp.setIdProductoTerminado(productoTerminado.getIdProductoTerminado());
				productoTerminadoTmp.setIdSubproducto(productoTerminado.getIdSubproducto());
				productoTerminadoTmp.setDescripcion(productoTerminado.getDescripcion());
				productoTerminadoTmp.setCantidadTotal(productoTerminado.getCantidadTotal());
				productoTerminadoTmp.setComprometidoTotal(productoTerminado.getComprometidoTotal());
				listaProductoTerminado.add(productoTerminadoTmp);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return listaProductoTerminado;
	}

}
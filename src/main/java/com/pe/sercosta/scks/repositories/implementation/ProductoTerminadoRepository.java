package com.pe.sercosta.scks.repositories.implementation;

//TODO Adecuar a las interfaces

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pe.sercosta.scks.entities.ProductoTerminado;
//import com.pe.sercosta.scks.repositories.IProductoTerminadoRepository;
import com.pe.sercosta.scks.util.HibernateUtil;

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "listarProductoTerminadoProcedimientoAlmacenado",
	query = "CALL listarProductoTerminado()",
	resultClass = ProductoTerminado.class
		),
	@NamedNativeQuery(
	name = "buscarProductoTerminadoProcedimientoAlmacenado",
	query = "CALL buscarProductoTerminado(:id_producto_terminado)",
	resultClass = ProductoTerminado.class
	)
})


//falta el implements IPresentacionRepository 
public class ProductoTerminadoRepository {
	
	// @Override
	public List<ProductoTerminado> listarProductoTerminado() {
		
		List<ProductoTerminado> listaBD = new ArrayList<ProductoTerminado>();
		List<ProductoTerminado> listaProductoTerminado = new ArrayList<ProductoTerminado>();
		ProductoTerminado productoTerminadoTmp = new ProductoTerminado();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
		
			@SuppressWarnings("unchecked")
			Query<ProductoTerminado> myquery= session.getNamedQuery("listarProductoTerminadoProcedimientoAlmacenado");
			listaBD = myquery.list();
			
			for (ProductoTerminado productoTerminado : listaBD) {
				productoTerminadoTmp.setIdProductoTerminado(productoTerminado.getIdProductoTerminado());
				productoTerminadoTmp.setIdSubproducto(productoTerminado.getIdSubproducto());
				productoTerminadoTmp.setDescripcion(productoTerminado.getDescripcion());
				productoTerminadoTmp.setCantidadTotal(productoTerminado.getCantidadTotal());
				productoTerminadoTmp.setComprometidoTotal(productoTerminado.getComprometidoTotal());
				
				listaProductoTerminado.add(productoTerminadoTmp);
			}
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return listaProductoTerminado;
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public List<ProductoTerminado> buscarProductoTerminado(ProductoTerminado productoTerminado) {
		
		List<ProductoTerminado> listaBD = new ArrayList<ProductoTerminado>();
		List<ProductoTerminado> listaProductoTerminado = new ArrayList<ProductoTerminado>();
		ProductoTerminado productoTerminadoTmp = new ProductoTerminado();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query<?> myquery= session.getNamedQuery("buscarProductoTerminadoProcedimientoAlmacenado")
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
			
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return listaProductoTerminado;
		
	}
}
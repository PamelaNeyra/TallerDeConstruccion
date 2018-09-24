package com.pe.sercosta.scks.repositories.implementation;

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

import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.util.HibernateUtil;

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "registrarContenidoProcedimientoAlmacenado",
	query = "CALL registrarContenido()",
	resultClass = Contenido.class
			),
	@NamedNativeQuery(
			name = "listarContenidoProcedimientoAlmacenado",
			query = "CALL listarContenido()",
			resultClass = Contenido.class
					)
	
})
public class ContenidoRepository implements IContenidoRepository{
	
	/*Falta un metodo en la interfaz para sobreescribir el listado de contenidos*/
	
	public List<Contenido> listarContenidos() {
		
		List<Contenido> listaBD = new ArrayList<Contenido>();
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Contenido contenidoTemporal = new Contenido();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
		
			@SuppressWarnings("unchecked")
			Query<Contenido> myquery= session.getNamedQuery("listarContenidoProcedimientoAlmacenado"); /*Falta actualizar el nombre del procedimiento*/
			listaBD = myquery.list();
			
			for (Contenido contenido : listaBD) {
				contenidoTemporal.setCantidad(contenido.getCantidad());
				contenidoTemporal.setCodigoTrazabilidad(contenido.getCodigoTrazabilidad());
				contenidoTemporal.setComprometido(contenido.getComprometido());
				contenidoTemporal.setEstaMuestreado(contenido.getEstaMuestreado());
				contenidoTemporal.setLote(contenido.getLote());
				contenidoTemporal.setPresentacion(contenido.getPresentacion());
	
				listaContenidos.add(contenidoTemporal);
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
		return listaContenidos;
	}
	
	
	@Override
	public void registrarContenido(Contenido contenido) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Contenido miContenido = null; Falta ver lo de las validaciones
		 try {
			 
			 session.save(contenido);
			 tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteInBatch(Iterable<Contenido> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Contenido> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Contenido> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Contenido> findAllById(Iterable<Serializable> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Contenido getOne(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<Contenido> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void delete(Contenido entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends Contenido> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean existsById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Optional<Contenido> findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends Contenido> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <S extends Contenido> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Contenido> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
}

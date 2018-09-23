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

import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.repositories.IPresentacionRepository;
import com.pe.sercosta.scks.util.HibernateUtil;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "listarPresentacionProcedimientoAlmacenado",
	query = "CALL listarPresentacion()",
	resultClass = Presentacion.class
		),
	@NamedNativeQuery(
	name = "buscarPresentacionProcedimientoAlmacenado",
	query = "CALL buscarPresentacion(:id_presentacion)",
	resultClass = Presentacion.class
	)
})
public class PresentacionRepository implements IPresentacionRepository {
	
	@Override
	public List<Presentacion> listarPresentacion() {
		
		List<Presentacion> listaBD = new ArrayList<Presentacion>();
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		Presentacion presentacionTmp = new Presentacion();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
		
			@SuppressWarnings("unchecked")
			Query<Presentacion> myquery= session.getNamedQuery("listarPresentacionProcedimientoAlmacenado");
			listaBD = myquery.list();
			
			for (Presentacion presentacion : listaBD) {
				presentacionTmp.setIdPresentacion(presentacion.getIdPresentacion());
				presentacionTmp.setIdProductoTerminado(presentacion.getIdProductoTerminado());
				presentacionTmp.setDescripcion(presentacion.getDescripcion());
				presentacionTmp.setCantidadTotal(presentacion.getCantidadTotal());
				presentacionTmp.setComprometidoTotal(presentacion.getComprometidoTotal());
				presentacionTmp.setBloque(presentacion.getBloque());
				presentacionTmp.setIdTipoContenedor(presentacion.getIdTipoContenedor());
				presentacionTmp.setContenedor(presentacion.getContenedor());
				
				listaPresentacion.add(presentacionTmp);
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
		return listaPresentacion;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Presentacion> buscarPresentacion(Presentacion presentacion) {
		
		List<Presentacion> listaBD = new ArrayList<Presentacion>();
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		Presentacion presentacionTmp = new Presentacion();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query<?> myquery= session.getNamedQuery("buscarPresentacionProcedimientoAlmacenado")
					.setParameter("id_presentacion", presentacion.getIdPresentacion());
			listaBD = (List<Presentacion>) myquery.list();
			
			for (Presentacion prstnIte : listaBD) {
				presentacionTmp.setIdPresentacion(prstnIte.getIdPresentacion());
				presentacionTmp.setIdProductoTerminado(prstnIte.getIdProductoTerminado());
				presentacionTmp.setDescripcion(prstnIte.getDescripcion());
				presentacionTmp.setCantidadTotal(prstnIte.getCantidadTotal());
				presentacionTmp.setComprometidoTotal(prstnIte.getComprometidoTotal());
				presentacionTmp.setBloque(prstnIte.getBloque());
				presentacionTmp.setIdTipoContenedor(prstnIte.getIdTipoContenedor());
				presentacionTmp.setContenedor(prstnIte.getContenedor());
				
				listaPresentacion.add(presentacionTmp);
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
		return listaPresentacion;
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<Presentacion> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Presentacion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Presentacion> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Presentacion> findAllById(Iterable<Serializable> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Presentacion getOne(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Presentacion> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void delete(Presentacion entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Presentacion> entities) {
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
	public Optional<Presentacion> findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Presentacion> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Presentacion> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Presentacion> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
}

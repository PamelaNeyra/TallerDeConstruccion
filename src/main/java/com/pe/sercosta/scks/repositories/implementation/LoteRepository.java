package com.pe.sercosta.scks.repositories.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.repositories.ILoteRepository;
import com.pe.sercosta.scks.util.HibernateUtil;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "registrarLotesProcedimientoAlmacenado",
	query = "CALL registrarLotes()",//falta revisar que parametros se le manda
	resultClass = Lote.class
		)
})
public class LoteRepository implements ILoteRepository{
	Session session;
	@Override
	public void registrarLote(Lote lote) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Lote miLote = null; Falta revisar lo de la validación
		 try {
			 session.save(lote);
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
	public void deleteInBatch(Iterable<Lote> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Lote> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Lote> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Lote> findAllById(Iterable<Serializable> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Lote getOne(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<Lote> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void delete(Lote entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends Lote> entities) {
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
	public Optional<Lote> findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends Lote> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <S extends Lote> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Lote> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
}

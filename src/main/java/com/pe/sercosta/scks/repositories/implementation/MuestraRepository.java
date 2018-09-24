package com.pe.sercosta.scks.repositories.implementation;

//TODO Adecuar a las interfaces necesarias


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pe.sercosta.scks.entities.Muestra;
//import com.pe.sercosta.scks.repositories.IMuestraRepository; 
import com.pe.sercosta.scks.util.HibernateUtil;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "registrarMuestraProcedimientoAlmacenado",
	query = "CALL registrarMuestra()",
	resultClass = Muestra.class
			)
})

// falta el implements IMuestraRepository
public class MuestraRepository {
	
	//@Override
	public void registrarMuestra(Muestra muestra) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Muestra miMuestra = null; Falta ver lo de las validaciones
		 try {
			 
			 session.save(muestra);
			 tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
}

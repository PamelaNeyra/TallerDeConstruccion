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

import com.pe.sercosta.scks.entities.Muestreo;
//import com.pe.sercosta.scks.repositories.IMuestraRepository; 
import com.pe.sercosta.scks.util.HibernateUtil;



@NamedNativeQueries({
	@NamedNativeQuery(
	name = "registrarMuestreoProcedimientoAlmacenado",
	query = "CALL registrarMuestreo()",
	resultClass = Muestreo.class
			)
})

// falta el implements IMuestreoRepository
public class MuestreoRepository {
	
	//@Override
	public void registrarMuestreo(Muestreo muestreo) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Muestreo miMuestreo = null; Falta ver lo de las validaciones
		 try {
			 
			 session.save(muestreo);
			 tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
}
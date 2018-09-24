package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.util.HibernateUtil;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "registrarAsignacionProcedimientoAlmacenado",
	query = "CALL registrarAsignacion()",//falta revisar que parametros se le manda
	resultClass = Asignacion.class
		)
})
public class AsignacionRepository { /*Falta el implements*/
	Session session;
	
	public void registrarAsignacion(Asignacion asignacion) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Asignacion asignacioTemporal = null; /*Falta revisar lo de la validación*/
		 try {
			 session.save(asignacion);
			 tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
}

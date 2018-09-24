package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.util.HibernateUtil;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "registrarOrdenVentaProcedimientoAlmacenado",
	query = "CALL registrarOrdenVenta()",//falta revisar que parametros se le manda
	resultClass = OrdenVenta.class
		)
})
public class OrdenVentaRepository { /*Falta el implements*/
	Session session;
	
	public void registrarOrdenVenta(OrdenVenta ordenVenta) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//OrdenVenta ordenVentaTemporal = null; /*Falta revisar lo de la validación*/
		 try {
			 session.save(ordenVenta);
			 tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
}

package com.pe.sercosta.scks.repositories.implementation;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.repositories.ILoteRepository;
import com.pe.sercosta.scks.util.HibernateUtil;

//falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarLotesProcedimientoAlmacenado", query = "CALL registrarLotes()", resultClass = Lote.class) })
public class LoteRepository implements ILoteRepository {

	Session session;

	@Override
	public void registrarLote(Lote lote) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		// Lote miLote = null; Falta revisar lo de la validación
		try {
			session.save(lote);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}

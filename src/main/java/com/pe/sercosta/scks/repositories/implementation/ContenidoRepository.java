package com.pe.sercosta.scks.repositories.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.util.HibernateUtil;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarContenidoProcedimientoAlmacenado", query = "CALL registrarContenido()", resultClass = Contenido.class) })
public class ContenidoRepository implements IContenidoRepository {

	@Override
	public void registrarContenido(Contenido contenido) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		// Contenido miContenido = null; Falta ver lo de las validaciones
		try {
			session.save(contenido);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}

	}

}

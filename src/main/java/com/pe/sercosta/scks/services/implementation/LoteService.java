package com.pe.sercosta.scks.services.implementation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.repositories.ILoteRepository;
import com.pe.sercosta.scks.services.ILoteService;
import com.pe.sercosta.scks.util.HibernateUtil;

public class LoteService implements ILoteService {

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Lote] -> ";
	
	private ILoteRepository loteRepository;
	private IContenidoRepository contenidoRepository;

	@Override
	public void registrarLote(Lote lote) {
		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();
		try {
			//TODO: Validaciones de O1 - Registrar Lote
			loteRepository.registrarLote(sesion, lote);
			lote.getContenidoList().forEach(c -> contenidoRepository.registrarContenido(sesion, c));
			tx.commit();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}

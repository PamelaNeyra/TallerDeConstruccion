package com.pe.sercosta.scks.repositories.implementation;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.repositories.ILoteRepository;

//TODO: falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarLotesProcedimientoAlmacenado", query = "CALL registrarLotes()", resultClass = Lote.class) })
public class LoteRepository implements ILoteRepository {

	private static final Log LOG = LogFactory.getLog(LoteRepository.class);
	private static final String CAPA = "[Repository : Lote] -> ";
	
	@Override
	public void registrarLote(Session sesion, Lote lote) {
		try {
			// TODO: CALL PA_RegistrarLote
			sesion.save(lote);
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
	}

}

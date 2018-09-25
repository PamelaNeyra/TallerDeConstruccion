package com.pe.sercosta.scks.repositories.implementation;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.repositories.ILoteRepository;

//TODO: falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarLotesProcedimientoAlmacenado", query = "CALL registrarLotes()", resultClass = Lote.class) })
public class LoteRepository implements ILoteRepository {

	@Override
	public void registrarLote(Session sesion, Lote lote) {
		try {
			// TODO: CALL PA_RegistrarLote
			sesion.save(lote);
		} catch (Exception ex) {
			throw ex;
		}
	}

}

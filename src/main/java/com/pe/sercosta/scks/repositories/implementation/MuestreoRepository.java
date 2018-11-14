package com.pe.sercosta.scks.repositories.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.SaldoOtModel;
import com.pe.sercosta.scks.repositories.IMuestreoRepository;

@Repository("muestreoRepository")
public class MuestreoRepository implements IMuestreoRepository {

	private static final Log LOG = LogFactory.getLog(MuestreoRepository.class);
	private static final String CAPA = "[Repository : Muestreo] -> ";	
	
	@Override
	public void registrarMuestreo(EntityManager sesion, Muestreo muestreo) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_muestreo");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Double.class, ParameterMode.IN);
			myquery.setParameter(1, muestreo.getMuestreoPK().getIdLote())
					.setParameter(2, muestreo.getMuestreoPK().getIdPresentacion())
					.setParameter(3, muestreo.getCantidad());
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el muestreo", ex.getMessage());
		}
	}

	@Override
	public List<Muestreo> listarMuestreos(EntityManager sesion) {
		List<Muestreo> listaMuestreo = new ArrayList<Muestreo>();
		try {
			//StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_muestreo");
			//Falta determinar el procedure
			/*myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			myquery.setParameter(1, asignacion.getOrdenVenta().getIdOrdenVenta())
					.setParameter(2, asignacion.getContenido().getContenidoPK().getIdPresentacion())
					.setParameter(3, asignacion.getContenido().getContenidoPK().getIdLote());
			myquery.execute();*/
		} catch(Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los Muestreos", ex.getMessage());
		}
		return listaMuestreo;
	}

	@Override
	public List<SaldoOtModel> listarSaldoOt(EntityManager sesion, Planta planta) {
		List<SaldoOtModel> listaSaldoOt = new ArrayList<SaldoOtModel>();
		try {
			StoredProcedureQuery myquery= sesion.createStoredProcedureQuery("sp_saldos_por_ot");
			
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1,planta.getIdPlanta());
			myquery.execute();
			
			@SuppressWarnings("unchecked")
			List<Object []> lista = myquery.getResultList();
			lista.forEach(o ->{
				SaldoOtModel saldoOtModel = new SaldoOtModel();
				saldoOtModel.setOt((String) o[0]);
				saldoOtModel.setFecha_muestreado((LocalDate) o[1]);
				saldoOtModel.setNombre_laboratorio((String) o[2]);
				saldoOtModel.setCantidad((double) o[3]);
			});
		} catch(Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al lista Saldos por OT", ex.getMessage());
		}
		return listaSaldoOt;
	}

}
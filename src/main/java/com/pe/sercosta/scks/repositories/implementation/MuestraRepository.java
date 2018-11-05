package com.pe.sercosta.scks.repositories.implementation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IMuestraRepository;

@Repository("muestraRepository")
public class MuestraRepository implements IMuestraRepository {

	private static final Log LOG = LogFactory.getLog(MuestraRepository.class);
	private static final String CAPA = "[Repository : Muestra] -> ";

	@Override
	public void registrarMuestra(EntityManager sesion, Muestra muestra) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_muestra");
			myquery.registerStoredProcedureParameter(1, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, muestra.getFechaCreacion())
					.setParameter(2, muestra.getIdLaboratorio().getIdLaboratorio())
					.setParameter(3, muestra.getIdPlanta().getIdPlanta());
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la muestra", ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Muestra> listarMuestraOt(EntityManager sesion, Planta planta) {
		List<Muestra> listaMuestraOt = new ArrayList<Muestra>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_muestra");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Muestra aux = new Muestra();
				aux.setIdMuestra((Integer) o[0]);
				aux.setOt((String) o[1]);
				aux.setFechaCreacion(((Date) o[2]).toLocalDate());
				aux.setFechaMuestreado(((Date) o[3]).toLocalDate());
				Laboratorio lab = new Laboratorio();
				lab.setNombreLaboratorio((String) o[4]);
				aux.setIdLaboratorio(lab);
				aux.setCantidadTotal((Double) o[5]);
				aux.setEstaMuestreado(((Boolean) o[6]));
				listaMuestraOt.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las muestras", ex.getMessage());
		}
		return listaMuestraOt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Muestra obtenerMuestraOt(EntityManager sesion, Muestra muestra) {
		try {
			//OrdenVentaModel ordenVenta = new OrdenVentaModel() ;
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_informacion_muestra");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1, muestra.getIdMuestra());
			myquery.execute();
			Muestra muest = new Muestra();
			List<Object[]> rsMuestra = myquery.getResultList();
			if(rsMuestra.size() == 1) {
				Object[] ordenRow = rsMuestra.get(0);
				muest.setIdMuestra((Integer) ordenRow[0]);
				muest.setFechaCreacion(((Date) ordenRow[1]).toLocalDate());
				Laboratorio lab = new Laboratorio();
				lab.setNombreLaboratorio((String) ordenRow[2]);
				muest.setIdLaboratorio(lab);
				muest.setEstaMuestreado(((Boolean) ordenRow[3]));
				
			}
			return muest;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar la muestra", ex.getMessage());
		}
	}

	@Override
	public void actualizarMuestarOt(EntityManager sesion, Muestra muestra) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_ot");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalDate.class, ParameterMode.IN);
			myquery.setParameter(1, muestra.getIdMuestra())
					.setParameter(2, muestra.getOt())
					.setParameter(3,muestra.getFechaMuestreado());
			myquery.execute();			
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el ot", ex.getMessage());
		}
		
	}

}

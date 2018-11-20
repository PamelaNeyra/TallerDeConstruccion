package com.pe.sercosta.scks.repositories.implementation;

import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.LoteOtModel;
import com.pe.sercosta.scks.repositories.ILoteRepository;

@Repository("loteRepository")
public class LoteRepository implements ILoteRepository {

	private static final Log LOG = LogFactory.getLog(LoteRepository.class);
	private static final String CAPA = "[Repository : Lote] -> ";
	
	@Override
	public void registrarLote(EntityManager sesion, Lote lote) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_lote");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN)
					.registerStoredProcedureParameter(5, Boolean.class, ParameterMode.IN);
			myquery.setParameter(1, lote.getIdLote())
					.setParameter(2, lote.getIdPlanta().getIdPlanta())
					.setParameter(3, lote.getFechaProduccion())
					.setParameter(4, lote.getCantidadRecepcion())
					.setParameter(5, lote.getEsReempaque());
			myquery.execute();			
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el lote", ex.getMessage());
		}
	}

	@Override
	public void actualizarLote(EntityManager sesion, Lote lote) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_actualizar_lote");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN);
			myquery.setParameter(1, lote.getIdLote())
					.setParameter(2, lote.getIdPlanta().getIdPlanta())
					.setParameter(3, lote.getFechaCaptura())
					.setParameter(4, lote.getCantidadTotal());
					
			myquery.execute();			
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al actualizar el lote", ex.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lote> listarLotes(EntityManager sesion) {
		List<Lote> listaBD = new ArrayList<Lote>();
		List<Lote> listaLotes = new ArrayList<Lote>();
		Lote loteTemporal = new Lote();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_lote");
			// TODO: Falta actualizar el nombre del procedimiento
			listaBD = myquery.getResultList();
			for (Lote lote : listaBD) {
				loteTemporal.setCantidadTotal(lote.getCantidadTotal());
				loteTemporal.setIdLote(lote.getIdLote());
				loteTemporal.setFechaCaptura(lote.getFechaCaptura());
				listaLotes.add(loteTemporal);
			}
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los contenidos", ex.getMessage());
		}
		return listaLotes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lote> listarLotesPlanta(EntityManager sesion, Planta planta) {
		List<Lote> listaLotes = new ArrayList<Lote>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_lotes");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Lote aux = new Lote();
				aux.setIdLote((String) o[0]);
				aux.setFechaProduccion(((Date) o[1]).toLocalDate());
				aux.setFechaVencimiento(((Date) o[2]).toLocalDate());
				aux.setCantidadTotal((Double) o[3]);
				aux.setComprometidoTotal((Double) o[4]);
				listaLotes.add(aux);
			});	
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los lotes", ex.getMessage());
		}
		return listaLotes;
	}
	
	@Override
	public void eliminarLote(EntityManager sesion, Lote lote) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoteOtModel> listarLoteOt(EntityManager sesion, Muestra muestra) {
		List<LoteOtModel> listaLoteOt = new ArrayList<LoteOtModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_lotes_muestra");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, muestra.getIdMuestra());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				LoteOtModel aux = new LoteOtModel();
				aux.setIdLote((String) o[0]);
				aux.setIdPresentacion((String) o[1]);
				aux.setCantidadMuestreado((double) o[2]);
			    listaLoteOt.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
		return listaLoteOt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lote> listarLotePorPresentacion(EntityManager sesion, Presentacion presentacion, Planta planta) {
		List<Lote> listaLotePorPresentacion = new ArrayList<Lote>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_lotes_por_presentacion");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, presentacion.getIdPresentacion())
					.setParameter(2, planta.getIdPlanta());
			myquery.execute();

			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Lote aux = new Lote();
				aux.setIdLote((String) o[0]);
				if(!o[1].getClass().getName().equals("String")) 
				     aux.setFechaProduccion(((Date) o[1]).toLocalDate());
				aux.setCantidadTotal((double) o[2]);
				aux.setComprometidoTotal((double) o[3]);
				listaLotePorPresentacion.add(aux);
			});
		}catch(Exception ex){
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los lotes por Presentacion", ex.getMessage());
		}
		return listaLotePorPresentacion;
	}
}

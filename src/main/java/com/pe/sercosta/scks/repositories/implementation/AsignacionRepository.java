package com.pe.sercosta.scks.repositories.implementation;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.repositories.IAsignacionRepository;

@Repository("asignacionRepository")
public class AsignacionRepository implements IAsignacionRepository{

	private static final Log LOG = LogFactory.getLog(AsignacionRepository.class);
	private static final String CAPA = "[Repository : Asignacion] -> ";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AsignacionModel> listarAsignacion(EntityManager sesion, OrdenVenta orden) {
		List<AsignacionModel> listaAsignacion = new ArrayList<AsignacionModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_informacion_asignacion");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1, orden.getIdOrdenVenta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				AsignacionModel aux = new AsignacionModel();
				aux.setIdLote((String) o[0]);
				aux.setOT((String) o[1]);
				aux.setIdPresentacion((String) o[2]);
				aux.setDescripcion((String) o[3]);
				aux.setCantidad((double) o[4]);
                aux.setSaldo((double) o[5]);
			    listaAsignacion.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
		return listaAsignacion;
	}

	@Override
	public void registrarAsignacion(EntityManager sesion, Asignacion asignacion) {
		try {
			//Nombre de PA por verificar
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_asignacion");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN);
			myquery.setParameter(1, asignacion.getAsignacionPK().getIdLote())
					.setParameter(2, asignacion.getAsignacionPK().getIdPresentacion())
					.setParameter(3, asignacion.getAsignacionPK().getIdOrdenVenta())
					.setParameter(4, asignacion.getCantidad());
			myquery.execute();	
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de venta", ex.getMessage());
		}
		
	}

	@Override
	public void embarcarAsignacion(EntityManager sesion, Asignacion asignacion) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_embarcar_asignacion");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			myquery.setParameter(1, asignacion.getOrdenVenta().getIdOrdenVenta())
					.setParameter(2, asignacion.getContenido().getContenidoPK().getIdPresentacion())
					.setParameter(3, asignacion.getContenido().getContenidoPK().getIdLote());
			myquery.execute();
		} catch(Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la asignación", ex.getMessage());
		}
	}

	@Override
	public List<Asignacion> listarAsignaciones(EntityManager sesion) {
		List<Asignacion> listaAsignacion = new ArrayList<Asignacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_asignacion");
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
			throw new SercostaException("Hubo un error al registrar la asignación", ex.getMessage());
		}
		return listaAsignacion;
	}	
	
}

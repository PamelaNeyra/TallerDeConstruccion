package com.pe.sercosta.scks.repositories.implementation;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.models.AsignacionSaldoGrupoModel;
import com.pe.sercosta.scks.models.AsignacionSaldoModel;
import com.pe.sercosta.scks.models.InfoAsignacionModel;
import com.pe.sercosta.scks.models.OrdenVentaClienteModel;
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
	public List<Asignacion> listarAsignaciones(EntityManager sesion, Planta planta) {
		List<Asignacion> listaAsignacion = new ArrayList<Asignacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_asignaciones");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
		} catch(Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error listar las asignaciones", ex.getMessage());
		}
		return listaAsignacion;
	}

	@Override
	public List<AsignacionSaldoModel> listarAsignacionesSaldo(EntityManager sesion, Laboratorio labo, Planta planta, Muestra muestra) {
		List<AsignacionSaldoModel> listaAsignacionSaldo = new ArrayList<AsignacionSaldoModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_asignaciones_saldos");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN);
			myquery.setParameter(1, labo.getNombreLaboratorio());
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.setParameter(1, muestra.getOt());
			myquery.setParameter(1, muestra.getFechaMuestreado());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				AsignacionSaldoModel aux = new AsignacionSaldoModel();
				aux.setId_Lote((String) o[0]);
				aux.setId_Presentacion((String) o[1]);
				aux.setId_orden_venta((String) o[2]);
				aux.setCantidad((double) o[3]);
				listaAsignacionSaldo.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
		return listaAsignacionSaldo;
	}

	@Override
	public List<AsignacionSaldoGrupoModel> listarAsignacionesSaldoGrupo(EntityManager sesion, Laboratorio laboratorio,
			Planta planta, Muestra muestra) {
		List<AsignacionSaldoGrupoModel> listaAsignacionSaldoGrupo = new ArrayList<AsignacionSaldoGrupoModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_grupo_asignaciones_saldos");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			myquery.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN);
			myquery.setParameter(1, laboratorio.getNombreLaboratorio());
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.setParameter(1, muestra.getOt());
			myquery.setParameter(1, muestra.getFechaMuestreado());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				AsignacionSaldoGrupoModel aux = new AsignacionSaldoGrupoModel();
				aux.setId_orden_venta((String) o[0]);
				aux.setCantidad((double) o[1]);
				listaAsignacionSaldoGrupo.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
		return listaAsignacionSaldoGrupo;
	}

	@Override
	public List<OrdenVentaClienteModel> listarOrdenVentaCliente(EntityManager sesion, Planta planta) {
		List<OrdenVentaClienteModel> listaOrdenVentaCliente = new ArrayList<OrdenVentaClienteModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_asignaciones");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				OrdenVentaClienteModel aux = new OrdenVentaClienteModel();
				aux.setId_ordenVenta((String) o[0]);
				aux.setNombreCliente((String) o[1]);
				aux.setFechaAsignacion((LocalDate) o[2]);
				aux.setFechaEmbarque((LocalDate) o[3]);
				aux.setCantidadTotal((double) o[4]);
				aux.setEstaEmbarcado((boolean) o[5]);
                listaOrdenVentaCliente.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
		return listaOrdenVentaCliente;
	}

	@Override
	public List<InfoAsignacionModel> listarInfoAsignacion(EntityManager sesion, Planta planta, OrdenVenta orden) {
		List<InfoAsignacionModel> listaInfoAsignacion = new ArrayList<InfoAsignacionModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_info_asig");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalDate.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta())
					.setParameter(2, orden.getIdOrdenVenta())
					.setParameter(3, orden.getFechaAsignacion());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				InfoAsignacionModel aux = new InfoAsignacionModel();
				aux.setId_presentacion((String) o[0]);
				aux.setDescripcion((String) o[1]);
				aux.setBloque((double) o[2]);
				aux.setFechaAsignacion((LocalDate) o[3]);
				aux.setCodigoTrazabilidad((String) o[4]);
				aux.setNombrePlanta((String) o[5]);
				aux.setOt((String) o[6]);
				listaInfoAsignacion.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los Info Asignacion Model", ex.getMessage());
		}
		return listaInfoAsignacion;
	}	
	
}

package com.pe.sercosta.scks.repositories.implementation;

import org.springframework.stereotype.Repository;
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
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IOrdenVentaRepository;

@Repository("ordenVentaRepository")
public class OrdenVentaRepository implements IOrdenVentaRepository{

	private static final Log LOG = LogFactory.getLog(OrdenVentaRepository.class);
	private static final String CAPA = "[Repository : OrdenVenta] -> ";
	
	public void registrarOrdenVenta(Session sesion, OrdenVenta ordenVenta) {
		try {
			//TODO: Nombre de PA por verificar
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_orden_venta");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
				/*.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);*/
			myquery.setParameter(1, ordenVenta.getIdOrdenVenta())
					.setParameter(2, ordenVenta.getIdPlanta().getIdPlanta())
					.setParameter(3, ordenVenta.getIdCliente().getIdCliente())
					.setParameter(4, ordenVenta.getFechaAsignacion())
					.setParameter(5, ordenVenta.getCertificado())
					.setParameter(6, ordenVenta.getPaisDestino());
			/*		.setParameter(7, ordenVenta.getPaisDestino()); */
			myquery.execute();	
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de venta", ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenVenta> listarOrdenVenta(EntityManager sesion, Planta planta) {
		List<OrdenVenta> listaOrdenVenta = new ArrayList<OrdenVenta>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_orden_venta");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				OrdenVenta aux = new OrdenVenta();
				aux.setIdOrdenVenta((String) o[0]);
				Cliente cliente = new Cliente();
				cliente.setNombreCliente((String) o[1]);
				aux.setIdCliente(cliente);
				aux.setFechaAsignacion(((Date) o[2]).toLocalDate());
				aux.setCantidadTotal((Double) o[3]);
				aux.setEstaEmbarcado(((Boolean) o[4]));
				listaOrdenVenta.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
		return listaOrdenVenta;
	}
	
	@SuppressWarnings("unchecked")
	public OrdenVenta obtenerOrdenVenta(EntityManager sesion, OrdenVenta orden) {
		
		try {
			//OrdenVentaModel ordenVenta = new OrdenVentaModel() ;
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_informacion_orden");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1, orden.getIdOrdenVenta());
			myquery.execute();
			OrdenVenta ordenVen = new OrdenVenta();
			List<Object[]> rsOrden = myquery.getResultList();
			if(rsOrden.size() == 1) {
				Object[] ordenRow = rsOrden.get(0);
				ordenVen.setIdOrdenVenta((String) ordenRow[0]);
				Cliente cliente = new Cliente();
				cliente.setNombreCliente((String) ordenRow[1]);
				ordenVen.setIdCliente(cliente);
				ordenVen.setFechaAsignacion(((Date) ordenRow[2]).toLocalDate());
				ordenVen.setCertificado((String) ordenRow[3]);
				Planta planta = new Planta();
				planta.setNombrePlanta(((String) ordenRow[4]));
				ordenVen.setIdPlanta(planta);
				ordenVen.setCantidadTotal((Double) ordenRow[5]);
			}
			return ordenVen;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de venta", ex.getMessage());
		}
	}

	@Override
	public List<OrdenVenta> buscarOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_embarcar_orden_venta");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalTime.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
			myquery.setParameter(1, ordenVenta.getIdOrdenVenta())
					.setParameter(2, ordenVenta.getFechaEmbarque())
					.setParameter(3,ordenVenta.getHoraEmbarque())
					.setParameter(4,ordenVenta.getPaisDestino());
			myquery.execute();			
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el lote", ex.getMessage());
		}
		
	}

	@Override
	public void registrarOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		
	}

}

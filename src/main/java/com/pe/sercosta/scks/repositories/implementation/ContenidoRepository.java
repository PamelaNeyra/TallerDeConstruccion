package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;

@Repository("contenidoRepository")
public class ContenidoRepository implements IContenidoRepository {

	private static final Log LOG = LogFactory.getLog(ContenidoRepository.class);
	private static final String CAPA = "[Repository : Contenido] -> ";
	
	@SuppressWarnings("unchecked")
	public List<Contenido> listarContenidos(EntityManager sesion) {
		List<Contenido> listaBD = new ArrayList<Contenido>();
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Contenido contenidoTemporal = new Contenido();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_contenido");
			// TODO: Falta actualizar el nombre del procedimiento
			listaBD = myquery.getResultList();
			for (Contenido contenido : listaBD) {
				contenidoTemporal.setCantidad(contenido.getCantidad());
				contenidoTemporal.setCodigoTrazabilidad(contenido.getCodigoTrazabilidad());
				contenidoTemporal.setComprometido(contenido.getComprometido());
				contenidoTemporal.setEstaMuestreado(contenido.getEstaMuestreado());
				contenidoTemporal.setLote(contenido.getLote());
				contenidoTemporal.setPresentacion(contenido.getPresentacion());
				listaContenidos.add(contenidoTemporal);
			}
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los contenidos", ex.getMessage());
		}
		return listaContenidos;
	}

	@Override
	public void registrarContenido(EntityManager sesion, Contenido contenido) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_contenido");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Double.class, ParameterMode.IN);
			myquery.setParameter(1, contenido.getLote().getIdLote())
					.setParameter(2, contenido.getPresentacion().getIdPresentacion())
					.setParameter(3, contenido.getCantidad());
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el contenido", ex.getMessage());
		}
	}

	@Override
	public void actualizarContenido(EntityManager sesion, Contenido contenido) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_actualizar_contenido");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Double.class, ParameterMode.IN);
			myquery.setParameter(1, contenido.getLote().getIdLote())
					.setParameter(2, contenido.getPresentacion().getIdPresentacion())
					.setParameter(3, contenido.getCantidad());
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al actualizar el contenido", ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contenido> listarContenidos(EntityManager sesion, Planta planta, Presentacion presentacion) {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_contenidos_presentacion");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta())
					.setParameter(2, presentacion.getIdPresentacion());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Contenido aux = new Contenido();
				aux.setPresentacion(new Presentacion((String) o[0]));
				aux.setLote(new Lote((String) o[1]));
				aux.setCantidad((Double) o[2]);
				aux.setComprometido((Double) o[3]);
				listaContenidos.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los contenidos", ex.getMessage());
		}
		return listaContenidos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contenido> listarContenidos(EntityManager sesion, Planta planta, ProductoTerminado producto) {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_contenidos_producto_t");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta())
					.setParameter(2, producto.getIdProductoTerminado());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Contenido aux = new Contenido();
				aux.setPresentacion(new Presentacion((String) o[0]));
				aux.setLote(new Lote((String) o[1]));
				aux.setCantidad((Double) o[2]);
				listaContenidos.add(aux);
			});			
		} catch(Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los contenidos", ex.getMessage());			
		}
		return listaContenidos;
	}
	
	

}

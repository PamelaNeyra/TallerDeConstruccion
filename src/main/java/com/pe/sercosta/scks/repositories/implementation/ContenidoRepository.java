package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarContenidoProcedimientoAlmacenado", query = "CALL sp_registrar_contenido()", resultClass = Contenido.class),
		@NamedNativeQuery(name = "listarContenidoProcedimientoAlmacenado", query = "CALL listarContenido()", resultClass = Contenido.class) })
@Repository("contenidoRepository")
public class ContenidoRepository implements IContenidoRepository {

	private static final Log LOG = LogFactory.getLog(ContenidoRepository.class);
	private static final String CAPA = "[Repository : Contenido] -> ";
	
	/* Falta un metodo en la interfaz para sobreescribir el listado de contenidos */

	public List<Contenido> listarContenidos(Session sesion) {
		List<Contenido> listaBD = new ArrayList<Contenido>();
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Contenido contenidoTemporal = new Contenido();
		try {
			@SuppressWarnings("unchecked")
			Query<Contenido> myquery = sesion.getNamedQuery("listarContenidoProcedimientoAlmacenado");
			// TODO: Falta actualizar el nombre del procedimiento
			listaBD = myquery.list();
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
	public List<Contenido> listarContenidos(EntityManager sesion) {
		// TODO Auto-generated method stub
		return null;
	}

}

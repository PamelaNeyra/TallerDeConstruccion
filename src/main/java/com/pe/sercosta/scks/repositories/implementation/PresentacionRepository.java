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
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ProductoAsignacionModel;
import com.pe.sercosta.scks.repositories.IPresentacionRepository;

@Repository("presentacionRepository")
public class PresentacionRepository implements IPresentacionRepository {

	private static final Log LOG = LogFactory.getLog(PresentacionRepository.class);
	private static final String CAPA = "[Repository : Presentacion] -> ";

	@SuppressWarnings("unchecked")
	@Override
	public List<Presentacion> listarPresentacion(EntityManager sesion) {
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_presentaciones");
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Presentacion aux = new Presentacion();
				aux.setIdPresentacion((String) o[0]);
				aux.setDescripcion((String) o[1]);
				listaPresentacion.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las presentaciones", ex.getMessage());
		}
		return listaPresentacion;
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<Presentacion> buscarPresentacion(EntityManager sesion, Presentacion presentacion) {
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_informacion_presentacion");
			myquery.execute();
			listaPresentacion = myquery.getResultList();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al buscar la presentación", ex.getMessage());
		}
		return listaPresentacion;
	}

	@Override
	public void registrarPresentacion(EntityManager sesion, Presentacion presentacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarPresentacion(EntityManager sesion, Presentacion presentacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPresentacion(EntityManager sesion, Presentacion presentacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductoAsignacionModel> listarDetalleProductoAsignacion(EntityManager sesion, Planta planta) {
		List<ProductoAsignacionModel> listaProductoAsignacion = new ArrayList<ProductoAsignacionModel>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_detalle_producto_asignacion");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				ProductoAsignacionModel aux = new ProductoAsignacionModel();
				aux.setId_ordenventa((String) o[0]);
				aux.setNombre_cliente((String) o[1]);
				aux.setId_lote((String) o[2]);
				aux.setOt((String) o[3]);
				aux.setCantidad((double) o[4]);
				aux.setFecha_asignacion((LocalDate) o[5]);
				listaProductoAsignacion.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los detalles de producto asignación", ex.getMessage());
		}
		return listaProductoAsignacion;
		
	}

}

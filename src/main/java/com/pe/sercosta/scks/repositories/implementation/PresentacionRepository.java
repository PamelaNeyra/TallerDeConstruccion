package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.repositories.IPresentacionRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "listarPresentacionProcedimientoAlmacenado", query = "CALL listarPresentacion()", resultClass = Presentacion.class),
		@NamedNativeQuery(name = "buscarPresentacionProcedimientoAlmacenado", query = "CALL buscarPresentacion(:id_presentacion)", resultClass = Presentacion.class) })
public class PresentacionRepository implements IPresentacionRepository {

	@Override
	public List<Presentacion> listarPresentacion(Session sesion) {
		List<Presentacion> listaBD = new ArrayList<Presentacion>();
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		Presentacion presentacionTmp = new Presentacion();
		try {
			@SuppressWarnings("unchecked")
			Query<Presentacion> myquery = sesion.getNamedQuery("listarPresentacionProcedimientoAlmacenado");
			listaBD = myquery.list();
			for (Presentacion presentacion : listaBD) {
				presentacionTmp.setIdPresentacion(presentacion.getIdPresentacion());
				presentacionTmp.setIdProductoTerminado(presentacion.getIdProductoTerminado());
				presentacionTmp.setDescripcion(presentacion.getDescripcion());
				presentacionTmp.setCantidadTotal(presentacion.getCantidadTotal());
				presentacionTmp.setComprometidoTotal(presentacion.getComprometidoTotal());
				presentacionTmp.setBloque(presentacion.getBloque());
				presentacionTmp.setIdTipoContenedor(presentacion.getIdTipoContenedor());
				presentacionTmp.setContenedor(presentacion.getContenedor());
				listaPresentacion.add(presentacionTmp);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return listaPresentacion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Presentacion> buscarPresentacion(Session sesion, Presentacion presentacion) {
		List<Presentacion> listaBD = new ArrayList<Presentacion>();
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		Presentacion presentacionTmp = new Presentacion();
		try {
			Query<?> myquery = sesion.getNamedQuery("buscarPresentacionProcedimientoAlmacenado")
					.setParameter("id_presentacion", presentacion.getIdPresentacion());
			listaBD = (List<Presentacion>) myquery.list();
			for (Presentacion prstnIte : listaBD) {
				presentacionTmp.setIdPresentacion(prstnIte.getIdPresentacion());
				presentacionTmp.setIdProductoTerminado(prstnIte.getIdProductoTerminado());
				presentacionTmp.setDescripcion(prstnIte.getDescripcion());
				presentacionTmp.setCantidadTotal(prstnIte.getCantidadTotal());
				presentacionTmp.setComprometidoTotal(prstnIte.getComprometidoTotal());
				presentacionTmp.setBloque(prstnIte.getBloque());
				presentacionTmp.setIdTipoContenedor(prstnIte.getIdTipoContenedor());
				presentacionTmp.setContenedor(prstnIte.getContenedor());
				listaPresentacion.add(presentacionTmp);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return listaPresentacion;
	}

}

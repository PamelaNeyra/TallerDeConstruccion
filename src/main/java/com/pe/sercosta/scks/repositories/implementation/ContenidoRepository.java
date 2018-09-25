package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.repositories.IContenidoRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarContenidoProcedimientoAlmacenado", query = "CALL registrarContenido()", resultClass = Contenido.class),
		@NamedNativeQuery(name = "listarContenidoProcedimientoAlmacenado", query = "CALL listarContenido()", resultClass = Contenido.class) })
public class ContenidoRepository implements IContenidoRepository {

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
			throw ex;
		}
		return listaContenidos;
	}

	@Override
	public void registrarContenido(Session sesion, Contenido contenido) {
		try {
			// TODO: CALL PA_RegistrarContenido()
			sesion.save(contenido);
		} catch (Exception ex) {
			throw ex;
		}
	}

}

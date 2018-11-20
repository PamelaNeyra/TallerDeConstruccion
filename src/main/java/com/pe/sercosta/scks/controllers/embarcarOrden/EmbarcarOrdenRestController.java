package com.pe.sercosta.scks.controllers.embarcarOrden;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pe.sercosta.scks.converter.implementation.OrdenVentaConverter;
import com.pe.sercosta.scks.converter.implementation.AsignacionConverter;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.services.IOrdenVentaService;
import com.pe.sercosta.scks.services.IUsuarioService;
import com.pe.sercosta.scks.services.IAsignacionService;

@RestController
@PreAuthorize("hasAnyAuthority('DEV','ADMIN','ASSIGN')")
public class EmbarcarOrdenRestController {

	private static final Log LOG = LogFactory.getLog(EmbarcarOrdenRestController.class);
	private static final String CAPA = "[RestController : EmbarcarOrden] -> ";

	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("ordenVentaConverter")
	private OrdenVentaConverter ordenVentaConverter;

	@Autowired
	@Qualifier("ordenVentaService")
	private IOrdenVentaService ordenVentaService;

	@Autowired
	@Qualifier("asignacionConverter")
	private AsignacionConverter asignacionConverter;

	@Autowired
	@Qualifier("asignacionService")
	private IAsignacionService asignacionService;
	
	@RequestMapping(path = "EmbarcarOrden/listarOrdenVenta", method = RequestMethod.GET)
	public List<OrdenVentaModel> listarOrdenVenta() {
		List<OrdenVentaModel> listaOrdenVenta = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaOrdenVenta = ordenVentaService.listarOrdenVenta(usuario.getIdPlanta())
								.stream()
								.map(entity -> ordenVentaConverter.convertToModel(entity))
								.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaOrdenVenta;
	}
	
	@RequestMapping(path = "EmbarcarOrden/embarcarOrden", method = RequestMethod.POST)
	public void actualizarOrden(@RequestBody(required = true) OrdenVentaModel orden) {
		try {
			ordenVentaService.actualizarOrdenVenta(ordenVentaConverter.convertToEntity(orden));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
	}
	
	@RequestMapping(path = "EmbarcarOrden/obtenerOrdenVenta", method = RequestMethod.POST)
	public OrdenVentaModel obtenerOrdenVenta(@RequestBody(required = true) OrdenVentaModel orden) {
		try {
			return ordenVentaConverter.convertToModel(
					ordenVentaService.obtenerOrdenVenta(ordenVentaConverter.convertToEntity(orden)));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
	}

	@RequestMapping(path = "EmbarcarOrden/listarAsignacion", method = RequestMethod.POST)
	public List<AsignacionModel> listarAsignacion(@RequestBody(required = false) OrdenVentaModel orden) {
		List<AsignacionModel> listaAsignacion = new ArrayList<>();
		try {
			listaAsignacion = asignacionService.listarAsignacion(ordenVentaConverter.convertToEntity(orden));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaAsignacion;
	}
}

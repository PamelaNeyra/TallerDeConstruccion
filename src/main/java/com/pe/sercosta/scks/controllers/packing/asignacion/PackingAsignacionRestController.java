package com.pe.sercosta.scks.controllers.packing.asignacion;

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
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.InfoAsignacionModel;
import com.pe.sercosta.scks.models.InfoOrdenVentaModel;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.services.IAsignacionService;
import com.pe.sercosta.scks.services.IOrdenVentaService;
import com.pe.sercosta.scks.services.IUsuarioService;

@RestController
@PreAuthorize("isAuthenticated()")
public class PackingAsignacionRestController {

	private static final Log LOG = LogFactory.getLog(PackingAsignacionRestController.class);
	private static final String CAPA = "[RestController : PackingAsignacion] -> ";

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
	@Qualifier("asignacionService")
	private IAsignacionService asignacionService;
	
	@RequestMapping(path = "Packing/Asignacion/listarOrdenVenta", method = RequestMethod.GET)
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
	
	@RequestMapping(path = "Packing/Asignacion/listarAsignacion", method = RequestMethod.POST)
	public List<InfoAsignacionModel> listarAsignacion(@RequestBody(required = true) OrdenVentaModel ordenVenta) {
		List<InfoAsignacionModel> listaAsignacion = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaAsignacion = asignacionService.listarInfoAsignacion(usuario.getIdPlanta(), ordenVentaConverter.convertToEntity(ordenVenta));
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
	
	@RequestMapping(path = "Packing/Asignacion/obtenerOrdenVenta", method = RequestMethod.POST)
	public InfoOrdenVentaModel obtenerOrdenVenta(@RequestBody(required = true) OrdenVentaModel ordenVenta) {
		try {
			return ordenVentaService.obtenerOrdenVentaPacking(ordenVentaConverter.convertToEntity(ordenVenta));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}	
	}
	
}

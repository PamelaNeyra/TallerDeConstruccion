package com.pe.sercosta.scks.controllers.ver.ordenRetiro;

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
import com.pe.sercosta.scks.converter.implementation.OrdenRetiroConverter;
import com.pe.sercosta.scks.converter.implementation.RetiroConverter;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenRetiroModel;
import com.pe.sercosta.scks.models.RetiroModel;
import com.pe.sercosta.scks.services.IOrdenRetiroService;
import com.pe.sercosta.scks.services.IRetiroService;
import com.pe.sercosta.scks.services.IUsuarioService;

@RestController
@PreAuthorize("isAuthenticated()")
public class VerOrdenRetiroRestController {

	private static final Log LOG = LogFactory.getLog(VerOrdenRetiroRestController.class);
	private static final String CAPA = "[RestController : VerOrdenRetiro] -> ";
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("ordenRetiroService")
	private IOrdenRetiroService ordenRetiroService;
	
	@Autowired
	@Qualifier("retiroService")
	private IRetiroService retiroService;
	
	@Autowired
	@Qualifier("ordenRetiroConverter")
	private OrdenRetiroConverter ordenRetiroConverter;
	
	@Autowired
	@Qualifier("retiroConverter")
	private RetiroConverter retiroConverter;
	
	@RequestMapping(path = "Ver/OrdenRetiro/listarOrdenRetiro", method = RequestMethod.GET)
	public List<OrdenRetiroModel> listarLote() {
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			return ordenRetiroService.listarOrdenRetiro(usuario.getIdPlanta())
								.stream()
								.map(entity -> ordenRetiroConverter.convertToModel(entity))
								.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las ordenes de retiro", ex.getMessage());
		}
	}
	
	@RequestMapping(path = "Ver/OrdenRetiro/listarRetiro", method = RequestMethod.POST)
	public List<RetiroModel> listarRetiro(@RequestBody(required = true) OrdenRetiroModel orden) {
		try {
			return retiroService.listarRetiroOrdenRetiro(ordenRetiroConverter.convertToEntity(orden))
									.stream()
									.map(entity -> retiroConverter.convertToModel(entity))
									.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los retiros", ex.getMessage());
		}
	}	
}

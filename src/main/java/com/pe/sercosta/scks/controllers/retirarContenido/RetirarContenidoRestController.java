package com.pe.sercosta.scks.controllers.retirarContenido;

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
import com.pe.sercosta.scks.converter.implementation.ContenidoConverter;
import com.pe.sercosta.scks.converter.implementation.LoteViewConverter;
import com.pe.sercosta.scks.converter.implementation.OrdenRetiroConverter;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ContenidoModel;
import com.pe.sercosta.scks.models.OrdenRetiroModel;
import com.pe.sercosta.scks.models.views.LoteView;
import com.pe.sercosta.scks.services.IContenidoService;
import com.pe.sercosta.scks.services.ILoteService;
import com.pe.sercosta.scks.services.IOrdenRetiroService;
import com.pe.sercosta.scks.services.IUsuarioService;

@RestController
@PreAuthorize("hasAnyAuthority('DEV','ADMIN','ENTER')")
public class RetirarContenidoRestController {

	private static final Log LOG = LogFactory.getLog(RetirarContenidoRestController.class);
	private static final String CAPA = "[RestController : RetirarContenido] -> ";
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("loteViewConverter")
	private LoteViewConverter loteViewConverter;
	
	@Autowired
	@Qualifier("contenidoConverter")
	private ContenidoConverter contenidoConverter;
	
	@Autowired
	@Qualifier("ordenRetiroConverter")
	private OrdenRetiroConverter ordenRetiroConverter;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("contenidoService")
	private IContenidoService contenidoService;
	
	@Autowired
	@Qualifier("ordenRetiroService")
	private IOrdenRetiroService ordenRetiroService;
	
	@RequestMapping(path = "RetirarContenido/listarLote", method = RequestMethod.GET)
	public List<LoteView> listarLote() {
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			return loteService.listarLotesPlanta(usuario.getIdPlanta())
								.stream()
								.map(entity -> loteViewConverter.convertToModel(entity))
								.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los lotes", ex.getMessage());
		}
	}
	
	@RequestMapping(path = "RetirarContenido/listarContenido", method = RequestMethod.POST)
	public List<ContenidoModel> listarContenido(@RequestBody(required = true) LoteView lote) {
		try {
			return contenidoService.listarContenidosPorLote(loteViewConverter.convertToEntity(lote))
									.stream()
									.map(entity -> contenidoConverter.convertToModel(entity))
									.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los contenidos", ex.getMessage());
		}
	}

	
	@RequestMapping(path = "RetirarContenido/registrarOrdenRetiro", method = RequestMethod.POST)
	public void registrarOrdenRetiro(@RequestBody(required = true) OrdenRetiroModel ordenRetiro) {
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			ordenRetiro.setIdPlanta(usuario.getIdPlanta().getIdPlanta());
			ordenRetiroService.registrarOrdenRetiro(ordenRetiroConverter.convertToEntity(ordenRetiro));			
		} catch(SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de retiro", ex.getMessage());
		}
	}
	
}

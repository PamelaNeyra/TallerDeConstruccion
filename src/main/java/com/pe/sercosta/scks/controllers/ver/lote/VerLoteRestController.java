package com.pe.sercosta.scks.controllers.ver.lote;

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
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ContenidoModel;
import com.pe.sercosta.scks.models.views.LoteView;
import com.pe.sercosta.scks.services.IContenidoService;
import com.pe.sercosta.scks.services.ILoteService;
import com.pe.sercosta.scks.services.IUsuarioService;

@RestController
@PreAuthorize("isAuthenticated()")
public class VerLoteRestController {
	
	private static final Log LOG = LogFactory.getLog(VerLoteRestController.class);
	private static final String CAPA = "[RestController : VerLote] -> ";
	
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
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("contenidoService")
	private IContenidoService contenidoService;
	
	@RequestMapping(path = "Ver/Lote/listarLote", method = RequestMethod.GET)
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
	
	@RequestMapping(path = "Ver/Lote/listarContenido", method = RequestMethod.POST)
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

}

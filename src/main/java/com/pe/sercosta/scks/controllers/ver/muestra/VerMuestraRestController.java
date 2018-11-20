package com.pe.sercosta.scks.controllers.ver.muestra;

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
import com.pe.sercosta.scks.converter.implementation.LoteConverter;
import com.pe.sercosta.scks.converter.implementation.OtConverter;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.LoteOtModel;
import com.pe.sercosta.scks.models.OtModel;
import com.pe.sercosta.scks.services.ILoteService;
import com.pe.sercosta.scks.services.IOtService;
import com.pe.sercosta.scks.services.IUsuarioService;

@RestController
@PreAuthorize("isAuthenticated()")
public class VerMuestraRestController {

	private static final Log LOG = LogFactory.getLog(VerMuestraRestController.class);
	private static final String CAPA = "[RestController : VerMuestra] -> ";
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;	

	@Autowired
	@Qualifier("otService")
	private IOtService otService;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("otConverter")
	private OtConverter otConverter;
	
	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	
	@RequestMapping(path = "Ver/Muestra/listarMuestra", method = RequestMethod.GET)
	public List<OtModel> listarMuestra() {
		List<OtModel> listaMuestraOt = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaMuestraOt = otService.listarMuestraOt(usuario.getIdPlanta())
								.stream()
								.map(entity -> otConverter.convertToModel(entity))
								.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaMuestraOt;
	}
	
	@RequestMapping(path = "Ver/Muestra/listarMuestreo", method = RequestMethod.POST)
	public List<LoteOtModel> listarMuestreo(@RequestBody(required = false) OtModel muestra) {
		List<LoteOtModel> listarLotesOt = new ArrayList<>();
		try {
			listarLotesOt = loteService.listarLotesOt(otConverter.convertToEntity(muestra));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listarLotesOt;
	}
	
}

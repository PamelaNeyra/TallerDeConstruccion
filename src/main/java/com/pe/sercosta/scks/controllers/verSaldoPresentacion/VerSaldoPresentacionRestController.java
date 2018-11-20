package com.pe.sercosta.scks.controllers.verSaldoPresentacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.pe.sercosta.scks.converter.implementation.AsignacionSaldoPresentacionConverter;
import com.pe.sercosta.scks.converter.implementation.LoteSaldoViewConverter;
import com.pe.sercosta.scks.converter.implementation.PresentacionViewConverter;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.AsignacionSaldoPresentacionModel;
import com.pe.sercosta.scks.models.views.LoteSaldoView;
import com.pe.sercosta.scks.models.views.PresentacionView;
import com.pe.sercosta.scks.services.IAsignacionService;
import com.pe.sercosta.scks.services.ILoteService;
import com.pe.sercosta.scks.services.IPlantaPresentacionService;
import com.pe.sercosta.scks.services.IUsuarioService;


@RestController
public class VerSaldoPresentacionRestController {

	private static final Log LOG = LogFactory.getLog(VerSaldoPresentacionRestController.class);
	private static final String CAPA = "[RestController : VerSaldoPresentacion] -> ";
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;



	@Autowired
	@Qualifier("presentacionViewConverter")
	private PresentacionViewConverter presentacionViewConverter;
	
	@Autowired
	@Qualifier("plantaPresentacionService")
	private IPlantaPresentacionService plantaPresentacionService;
	

	@Autowired
	@Qualifier("loteSaldoViewConverter")
	private LoteSaldoViewConverter loteSaldoViewConverter;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	
	@Autowired
	@Qualifier("asignacionSaldoPresentacionConverter")
	private AsignacionSaldoPresentacionConverter asigancionConverter;
	
	@Autowired
	@Qualifier("asignacionService")
	private IAsignacionService asignacionService;
	
	
		
	
	@RequestMapping(path = "/VerSaldoPresentacion/listarPresentacion", method = RequestMethod.GET)
	public List<PresentacionView> listarPresentacion() {
		List<PresentacionView> listaPresentacion = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaPresentacion =  plantaPresentacionService.listarPresentacion(usuario.getIdPlanta())
								 .stream()
								 .map(entity -> presentacionViewConverter.convertToModel(entity))
								 .collect(Collectors.toList());				 
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaPresentacion;
	}
	
	
	@RequestMapping(path = "/VerSaldoPresentacion/listarLoteSaldo", method = RequestMethod.POST)
	public List<LoteSaldoView> listarLotesPresentacion(@RequestBody(required = false) Presentacion pre) {
		List<LoteSaldoView> listaLotes = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaLotes =  loteService.listarLotePorPresentacion(pre, usuario.getIdPlanta())
					 .stream()
					 .map(entity -> loteSaldoViewConverter.convertToModel(entity))
					 .collect(Collectors.toList());				 
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaLotes;
	}
	
	@RequestMapping(path = "/VerSaldoPresentacion/listarAsignacionSaldo", method = RequestMethod.POST)
	public List<AsignacionSaldoPresentacionModel> listarAsignacionPresentacion(@RequestBody(required = false) Presentacion pre) {
		List<AsignacionSaldoPresentacionModel> listaPresentacion = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaPresentacion = asignacionService.listarAsignacionPorPresentacion(pre, usuario.getIdPlanta())
					 .stream()
					 .map(entity -> asigancionConverter.convertToModel(entity))
					 .collect(Collectors.toList());				 
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaPresentacion;
	}
	

	
}

package com.pe.sercosta.scks.controllers.verSaldoOt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pe.sercosta.scks.converter.implementation.PlantaConverter;
import com.pe.sercosta.scks.converter.implementation.SaldoOtConverter;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.SaldoOtModel;
import com.pe.sercosta.scks.services.IUsuarioService;
import com.pe.sercosta.scks.services.IMuestreoService;

@RestController
public class VerSaldoOtRestController {

	private static final Log LOG = LogFactory.getLog(VerSaldoOtRestController.class);
	private static final String CAPA = "[RestController : VerSaldoOt] -> ";
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("plantaConverter")
	private PlantaConverter plantaConverter;
	
	@Autowired
	@Qualifier("otSaldoConverter")
	private SaldoOtConverter otSaldoConverter;
	
	
	@Autowired
	@Qualifier("muestreoService")
	private IMuestreoService muestreoService;
	

	@RequestMapping(path = "/VerSaldoOt/listarMuestreoOt", method = RequestMethod.GET)
	public List<SaldoOtModel> listarMuestraOt() {
		List<SaldoOtModel> listaMuestreoOt = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
		
			listaMuestreoOt= muestreoService.listarMuestreoOt(usuario.getIdPlanta())
					.stream()
					.map(entity -> otSaldoConverter.convertToModel(entity))
					.collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaMuestreoOt;
	}
	
}

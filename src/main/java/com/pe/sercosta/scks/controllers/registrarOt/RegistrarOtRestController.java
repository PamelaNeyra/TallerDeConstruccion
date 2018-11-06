package com.pe.sercosta.scks.controllers.registrarOt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pe.sercosta.scks.converter.implementation.OtConverter;
import com.pe.sercosta.scks.converter.implementation.LoteConverter;
import com.pe.sercosta.scks.converter.implementation.PlantaConverter;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OtModel;
import com.pe.sercosta.scks.models.LoteOtModel;
import com.pe.sercosta.scks.services.IOtService;
import com.pe.sercosta.scks.services.ILoteService;


@RestController
public class RegistrarOtRestController {

	private static final Log LOG = LogFactory.getLog(RegistrarOtRestController.class);
	private static final String CAPA = "[RestController : RegistrarOt] -> ";
	
	@Autowired
	@Qualifier("plantaConverter")
	private PlantaConverter plantaConverter;
	
	@Autowired
	@Qualifier("otConverter")
	private OtConverter otConverter;
	
	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	
	@Autowired
	@Qualifier("otService")
	private IOtService otService;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
		
	@RequestMapping(path = "/RegistrarOt/listarMuestraOt", method = RequestMethod.GET)
	public List<OtModel> listarMuestraOt() {
		List<OtModel> listaMuestraOt = new ArrayList<>();
		try {
			//TODO: Obtener Planta de Sesión
			listaMuestraOt = otService.listarMuestraOt(new Planta(1))
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
	
	@RequestMapping(path = "/RegistrarOt/obtenerMuestraOt", method = RequestMethod.POST)
	public OtModel obtenerMuestraOt(@RequestBody(required = true) OtModel muestra) {
		try {
			return otConverter.convertToModel(
					otService.obtenerMuestraOt(otConverter.convertToEntity(muestra)));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
	}
	
	@RequestMapping(path = "/RegistrarOt/listarLotesOt", method = RequestMethod.POST)
	public List<LoteOtModel> listarLotesOt(@RequestBody(required = false) OtModel muestra) {
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
	
	
	@RequestMapping(path = "/RegistrarOt/actualizarMuestraOt", method = RequestMethod.POST)
	public void actualizarOrden(@RequestBody(required = true) Muestra muestra) {
		try {
			otService.actualizarMuestraOt(muestra);
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

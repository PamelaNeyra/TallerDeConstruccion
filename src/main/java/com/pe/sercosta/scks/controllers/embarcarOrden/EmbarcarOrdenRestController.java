package com.pe.sercosta.scks.controllers.embarcarOrden;

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
import com.pe.sercosta.scks.converter.implementation.OrdenVentaConverter;
import com.pe.sercosta.scks.converter.implementation.AsignacionConverter;
import com.pe.sercosta.scks.converter.implementation.PlantaConverter;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.services.IOrdenVentaService;
import com.pe.sercosta.scks.services.IAsignacionService;

@RestController
public class EmbarcarOrdenRestController {

	private static final Log LOG = LogFactory.getLog(EmbarcarOrdenRestController.class);
	private static final String CAPA = "[RestController : EmbarcarOrden] -> ";

	@Autowired
	@Qualifier("plantaConverter")
	private PlantaConverter plantaConverter;
	
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

	
	@RequestMapping(path = "/EmbarcarOrden/listarOrdenVenta", method = RequestMethod.GET)
	public List<OrdenVentaModel> listarOrdenVenta() {
		List<OrdenVentaModel> listaOrdenVenta = new ArrayList<>();
		try {
			listaOrdenVenta = ordenVentaService.listarOrdenVenta()
								.stream()
								.map(entity -> ordenVentaConverter.convertToModel(entity)).collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
		}
		return listaOrdenVenta;
	}
	
	@RequestMapping(path = "/EmbarcarOrden/actualizarOrden", method = RequestMethod.POST)
	public void actualizarOrden(@RequestBody(required = true) OrdenVentaModel orden) {
		try {
			// TODO: Poner planta del usuario al lote
			ordenVentaService.actualizarOrdenVenta(ordenVentaConverter.convertToEntity(orden));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
		}
	}
	
	@RequestMapping(path = "/EmbarcarOrden/listarAsignacion", method = RequestMethod.GET)
	public List<AsignacionModel> listarAsignacion(@RequestBody(required = true) OrdenVentaModel orden) {
		List<AsignacionModel> listaAsignacion = new ArrayList<>();
		try {
			listaAsignacion = asignacionService.listarAsignacion(ordenVentaConverter.convertToEntity(orden))
								.stream()
								.map(entity -> asignacionConverter.convertToModel(entity)).collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
		}
		return listaAsignacion;
	}
	
	@RequestMapping(path = "/EmbarcarOrden/listarOrdenVentaE", method = RequestMethod.GET)
	public OrdenVentaModel listarOrdenVentaE(@RequestBody(required = true) OrdenVentaModel orden) {
		OrdenVenta listaOrden = new OrdenVenta();
		try {
			listaOrden = ordenVentaService.listarOrdenVenta(ordenVentaConverter.convertToEntity(orden));
						
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
		}
		return ordenVentaConverter.convertToModel(listaOrden);
	}
}

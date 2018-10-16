package com.pe.sercosta.scks.controllers.RegistrarOrdenVenta;

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
import com.pe.sercosta.scks.converter.implementation.ClienteConverter;
import com.pe.sercosta.scks.converter.implementation.OrdenVentaViewConverter;
import com.pe.sercosta.scks.converter.implementation.PresentacionConverter;
import com.pe.sercosta.scks.converter.implementation.PresentacionViewConverter;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ClienteModel;
import com.pe.sercosta.scks.models.multiple.OrdenVentaMultiple;
import com.pe.sercosta.scks.models.views.OrdenVentaView;
import com.pe.sercosta.scks.models.views.PresentacionView;
import com.pe.sercosta.scks.services.IClienteService;
import com.pe.sercosta.scks.services.IOrdenVentaService;
import com.pe.sercosta.scks.services.IPlantaPresentacionService;

@RestController
public class RegistrarOrdenVentaRestController {

	private static final Log LOG = LogFactory.getLog(RegistrarOrdenVentaRestController.class);
	private static final String CAPA = "[RestController : RegistrarOrdenVenta] -> ";

	@Autowired
	@Qualifier("clienteConverter")
	private ClienteConverter clienteConverter;
	
	@Autowired
	@Qualifier("ordenVentaViewConverter")
	private OrdenVentaViewConverter ordenVentaConverter;
	
	@Autowired
	@Qualifier("presentacionConverter")
	private PresentacionConverter presentacionConverter;	

	@Autowired
	@Qualifier("presentacionViewConverter")
	private PresentacionViewConverter presentacionViewConverter;

	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;

	@Autowired
	@Qualifier("ordenVentaService")
	private IOrdenVentaService ordenVentaService;
	
	@Autowired
	@Qualifier("plantaPresentacionService")
	private IPlantaPresentacionService plantaPresentacionService;

	@RequestMapping(path = "/RegistrarOrdenVenta/listarClientes", method = RequestMethod.GET)
	public List<ClienteModel> listarClientes() {
		List<ClienteModel> listaClientes = new ArrayList<>();
		try {
			listaClientes = clienteService.listarClientes()
								.stream()
								.map(entity -> clienteConverter.convertToModel(entity)).collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaClientes;
	}
	
	@RequestMapping(path = "/RegistrarOrdenVenta/listarPresentacion", method = RequestMethod.GET)
	public List<PresentacionView> listarPresentacion() {
		List<PresentacionView> listaPresentacion = new ArrayList<>();
		try {
			//TODO: La planta se obtendrá de la sesión de usuario :D
			listaPresentacion =  plantaPresentacionService.listarPresentacion(new Planta(1))
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
	
	@RequestMapping(path = "/RegistrarOrdenVenta/registrarOrdenVenta", method = RequestMethod.POST)
	public void registrarOrdenVenta(@RequestBody(required = true) OrdenVentaMultiple ordenVentaMultiple) {
		try {
			// TODO: Poner planta del usuario al lote
			OrdenVentaView ordenVenta = ordenVentaMultiple.getOrdenVenta();
			ordenVenta.setIdPlanta(1);
			ordenVentaService.registrarOrdenVenta(ordenVentaConverter.convertToEntity(ordenVenta),
													ordenVentaMultiple.getListaPresentacion().stream()
													.map(p -> presentacionConverter.convertToEntity(p))
													.collect(Collectors.toList()));
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

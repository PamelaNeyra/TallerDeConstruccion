package com.pe.sercosta.scks.controllers.RegistrarOrdenVenta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pe.sercosta.scks.converter.implementation.ClienteConverter;
import com.pe.sercosta.scks.converter.implementation.PresentacionConverter;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ClienteModel;
import com.pe.sercosta.scks.models.PresentacionModel;
import com.pe.sercosta.scks.services.IClienteService;
import com.pe.sercosta.scks.services.IPresentacionService;

@RestController
public class RegistrarOrdenVentaRestController {

	private static final Log LOG = LogFactory.getLog(RegistrarOrdenVentaRestController.class);
	private static final String CAPA = "[RestController : RegistrarOrdenVenta] -> ";

	@Autowired
	@Qualifier("clienteConverter")
	private ClienteConverter clienteConverter;

	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;

	@Autowired
	@Qualifier("presentacionConverter")
	private PresentacionConverter presentacionConverter;

	@Autowired
	@Qualifier("presentacionService")
	private IPresentacionService presentacionService;

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
	public List<PresentacionModel> listarPresentacion() {
		List<PresentacionModel> listaPresentacion = new ArrayList<>();
		try {
			listaPresentacion = presentacionService.listarPresentacion()
								.stream()
								.map(entity -> presentacionConverter.convertToModel(entity)).collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
		}
		return listaPresentacion;
	}

}

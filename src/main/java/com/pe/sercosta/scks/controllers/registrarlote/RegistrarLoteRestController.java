package com.pe.sercosta.scks.controllers.registrarlote;

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
import com.pe.sercosta.scks.converter.implementation.LoteConverter;
import com.pe.sercosta.scks.converter.implementation.PresentacionConverter;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.LoteModel;
import com.pe.sercosta.scks.models.PresentacionModel;
import com.pe.sercosta.scks.services.ILoteService;
import com.pe.sercosta.scks.services.IPresentacionService;

@RestController
public class RegistrarLoteRestController {

	private static final Log LOG = LogFactory.getLog(RegistrarLoteRestController.class);
	private static final String CAPA = "[RestController : RegistrarLote] -> ";

	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;

	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@Autowired
	@Qualifier("presentacionConverter")
	private PresentacionConverter presentacionConverter;

	@Autowired
	@Qualifier("presentacionService")
	private IPresentacionService presentacionService;

	@RequestMapping(path = "/RegistrarLote/registrarLote", method = RequestMethod.POST)
	public void registrarLote(@RequestBody(required = true) LoteModel lote) {
		try {
			// TODO: Poner planta del usuario al lote
			loteService.registrarLote(loteConverter.convertToEntity(lote));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
		}
	}

	@RequestMapping(path = "/RegistrarLote/listarPresentacion", method = RequestMethod.GET)
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

package com.pe.sercosta.scks.controllers.registrarMuestra;

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
import com.pe.sercosta.scks.converter.implementation.MuestraConverter;
import com.pe.sercosta.scks.converter.implementation.ProductoTerminadoConverter;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.MuestraModel;
import com.pe.sercosta.scks.models.ProductoTerminadoModel;
import com.pe.sercosta.scks.models.multiple.MuestraMultiple;
import com.pe.sercosta.scks.services.IMuestraService;
import com.pe.sercosta.scks.services.IProductoTerminadoService;
import com.pe.sercosta.scks.services.IUsuarioService;

@RestController
@PreAuthorize("hasAnyAuthority('DEV','ADMIN','ENTER')")
public class RegistrarMuestraRestController {

	private static final Log LOG = LogFactory.getLog(RegistrarMuestraRestController.class);
	private static final String CAPA = "[RestController : RegistrarMuestra] -> ";

	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("muestraConverter")
	private MuestraConverter muestraConverter;

	@Autowired
	@Qualifier("muestraService")
	private IMuestraService muestraService;
	
	@Autowired
	@Qualifier("productoTerminadoConverter")
	private ProductoTerminadoConverter productoTerminadoConverter;

	@Autowired
	@Qualifier("productoTerminadoService")
	private IProductoTerminadoService productoTerminadoService;

	@RequestMapping(path = "RegistrarMuestra/registrarMuestra", method = RequestMethod.POST)
	public void registrarLote(@RequestBody(required = true) MuestraMultiple muestraMultiple) {
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			MuestraModel muestra = muestraMultiple.getMuestra();
			//TODO: Laboratorio se Obtiene de Combo
			muestra.setIdLaboratorio(1);
			muestra.setIdPlanta(usuario.getIdPlanta().getIdPlanta());
			muestraService.registrarMuestra(muestraConverter.convertToEntity(muestra)
											, muestraMultiple.getProductoTerminadoList().stream()
												.map(p -> productoTerminadoConverter.convertToEntity(p))
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

	@RequestMapping(path = "RegistrarMuestra/listarProductoTerminado", method = RequestMethod.GET)
	public List<ProductoTerminadoModel> listarProducto() {
		List<ProductoTerminadoModel> listaProducto = new ArrayList<>();
		try {
			User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
			Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
			listaProducto = productoTerminadoService.listarProducto(usuario.getIdPlanta())
					       .stream()
				        	.map(entity -> productoTerminadoConverter.convertToModel(entity)).collect(Collectors.toList());
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
		return listaProducto;
	}

}


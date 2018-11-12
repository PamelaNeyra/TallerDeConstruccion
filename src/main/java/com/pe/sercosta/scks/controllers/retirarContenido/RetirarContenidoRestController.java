package com.pe.sercosta.scks.controllers.retirarContenido;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import com.pe.sercosta.scks.converter.implementation.LoteViewConverter;
import com.pe.sercosta.scks.converter.implementation.PlantaConverter;
import com.pe.sercosta.scks.services.ILoteService;

@RestController
public class RetirarContenidoRestController {

	//private static final Log LOG = LogFactory.getLog(RetirarContenidoRestController.class);
	//private static final String CAPA = "[RestController : RetirarContenido] -> ";
	
	@Autowired
	@Qualifier("plantaConverter")
	private PlantaConverter plantaConverter;
	
	@Autowired
	@Qualifier("loteViewConverter")
	private LoteViewConverter loteViewConverter;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	
}

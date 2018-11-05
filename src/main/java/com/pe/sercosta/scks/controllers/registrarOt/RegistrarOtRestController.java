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
import com.pe.sercosta.scks.converter.implementation.OrdenVentaConverter;
import com.pe.sercosta.scks.converter.implementation.AsignacionConverter;
import com.pe.sercosta.scks.converter.implementation.PlantaConverter;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.services.IOrdenVentaService;
import com.pe.sercosta.scks.services.IAsignacionService;

@RestController
public class RegistrarOtRestController {

	private static final Log LOG = LogFactory.getLog(RegistrarOtRestController.class);
	private static final String CAPA = "[RestController : RegistrarOt] -> ";
	
	
}

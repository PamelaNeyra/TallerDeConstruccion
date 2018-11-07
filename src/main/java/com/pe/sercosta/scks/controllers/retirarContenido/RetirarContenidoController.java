package com.pe.sercosta.scks.controllers.retirarContenido;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/RetirarContenido")
public class RetirarContenidoController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/RetirarContenido/index";
	}

}

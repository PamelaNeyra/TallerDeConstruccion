package com.pe.sercosta.scks.controllers.registrarlote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/RegistrarLote")
public class RegistrarLoteController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/RegistrarLote/index";
	}

}

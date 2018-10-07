package com.pe.sercosta.scks.controllers.registrarmuestra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/RegistrarMuestra")
public class RegistrarMuestraController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/RegistrarMuestra/index";
	}
}

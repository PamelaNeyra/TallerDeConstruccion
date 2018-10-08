package com.pe.sercosta.scks.controllers.embarcarOrden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/EmbarcarOrden")
public class EmbarcarOrdenController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/EmbarcarOrden/index";
	}

}

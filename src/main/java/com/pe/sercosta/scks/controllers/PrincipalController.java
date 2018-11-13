package com.pe.sercosta.scks.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/Principal")
@PreAuthorize("permitAll()")
public class PrincipalController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/Principal/index";
	}
	
}

package com.pe.sercosta.scks.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

	private static final Log LOG = LogFactory.getLog(ExampleController.class);
		
	@RequestMapping(value = "/example", 
			method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		try {
			
		} catch (Exception e) {
		}
		return "Ejemplo";
	}
}

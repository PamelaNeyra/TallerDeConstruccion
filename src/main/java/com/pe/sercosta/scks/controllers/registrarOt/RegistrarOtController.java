package com.pe.sercosta.scks.controllers.registrarOt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/RegistrarOt")
public class RegistrarOtController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/RegistrarOt/index";
	}
	
	@RequestMapping(path = "/{idMuestra}", method = RequestMethod.GET)
	public ModelAndView index2(@PathVariable("idMuestra") String idMuestra) {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/RegistrarOt/index2");
		mav.addObject("idMuestra", idMuestra);
		return mav;
	}

}

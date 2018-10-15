package com.pe.sercosta.scks.controllers.embarcarOrden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/EmbarcarOrden")
public class EmbarcarOrdenController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/EmbarcarOrden/index";
	}
	
	@RequestMapping(path = "/{idOrden}", method = RequestMethod.GET)
	public ModelAndView index2(@PathVariable("idOrden") String idOrden) {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/EmbarcarOrden/index2");
		mav.addObject("idOrden", idOrden);
		return mav;
	}

}

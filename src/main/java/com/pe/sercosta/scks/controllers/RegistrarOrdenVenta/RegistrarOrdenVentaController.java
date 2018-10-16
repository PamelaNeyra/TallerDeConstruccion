package com.pe.sercosta.scks.controllers.RegistrarOrdenVenta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/RegistrarOrdenVenta")
public class RegistrarOrdenVentaController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index() {		
		return "/RegistrarOrdenVenta/index";
	}
	
	@RequestMapping(path = "/Orden/{nombreCliente}/{idCliente}", method = RequestMethod.GET)
	public ModelAndView index2(@PathVariable("nombreCliente") String nombreCliente,@PathVariable("idCliente") String idCliente) {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/RegistrarOrdenVenta/index2");
		mav.addObject("nombreCliente", nombreCliente);
		mav.addObject("idCliente", idCliente);
		return mav;
	}
}	


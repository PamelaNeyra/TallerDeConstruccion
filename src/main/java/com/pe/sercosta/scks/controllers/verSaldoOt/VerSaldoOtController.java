package com.pe.sercosta.scks.controllers.verSaldoOt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.models.SaldoOtModel;
import com.pe.sercosta.scks.services.IUsuarioService;

@Controller
@RequestMapping("VerSaldoOt")
public class VerSaldoOtController {

	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView index() {	
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.
					getContext().
					getAuthentication().
						getPrincipal();
		Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
		mav.setViewName("/VerSaldoOt/index");
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		mav.addObject("saldo", new SaldoOtModel());
		return mav;
	}
	
	@RequestMapping(path = "/Muestra", method = RequestMethod.POST)	
	public ModelAndView index2(@ModelAttribute(name = "saldo") SaldoOtModel saldo) {
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.
				getContext().
				getAuthentication().
					getPrincipal();
		Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
		mav.setViewName("VerSaldoOt/index2");
		mav.addObject("ot", saldo.getOt());
	    mav.addObject("fechaMuestreado", saldo.getFechaMuestreado());
		mav.addObject("nombreLaboratorio", saldo.getNombreLaboratorio());
     	mav.addObject("cantidad", saldo.getCantidad());
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		return mav;
	}	

}

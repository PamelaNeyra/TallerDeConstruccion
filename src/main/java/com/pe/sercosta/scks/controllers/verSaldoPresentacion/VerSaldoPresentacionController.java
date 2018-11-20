package com.pe.sercosta.scks.controllers.verSaldoPresentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.services.IUsuarioService;

@Controller()
@RequestMapping("/VerSaldoPresentacion")
public class VerSaldoPresentacionController {

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
		mav.setViewName("/VerSaldoPresentacion/index");
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		return mav;
	}
	
	@RequestMapping(path = "/{idPresentacion}", method = RequestMethod.GET)
	public ModelAndView index2(@PathVariable("idPresentacion") String idPresentacion) {		
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.
				getContext().
				getAuthentication().
					getPrincipal();
		Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
		mav.setViewName("/VerSaldoPresentacion/index2");
		mav.addObject("idPresentacion", idPresentacion);
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		return mav;
	}
	
	
	@RequestMapping(path = "index3/{idPresentacion}", method = RequestMethod.GET)
	public ModelAndView index3(@PathVariable("idPresentacion") String idPresentacion) {		
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.
				getContext().
				getAuthentication().
					getPrincipal();
		Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
		mav.setViewName("/VerSaldoPresentacion/index3");
		mav.addObject("idPresentacion", idPresentacion);
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		return mav;
	}

}

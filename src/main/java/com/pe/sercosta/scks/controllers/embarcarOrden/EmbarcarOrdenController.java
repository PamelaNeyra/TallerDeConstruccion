package com.pe.sercosta.scks.controllers.embarcarOrden;

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
@RequestMapping("/EmbarcarOrden")
public class EmbarcarOrdenController {

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
		mav.setViewName("/EmbarcarOrden/index");
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		return mav;
	}
	
	@RequestMapping(path = "/{idOrden}", method = RequestMethod.GET)
	public ModelAndView index2(@PathVariable("idOrden") String idOrden) {		
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.
				getContext().
				getAuthentication().
					getPrincipal();
		Usuario usuario = usuarioService.obtenerUsuario(user.getUsername(), user.getPassword());
		mav.setViewName("/EmbarcarOrden/index2");
		mav.addObject("idOrden", idOrden);
		mav.addObject("planta", usuario.getIdPlanta().getNombrePlanta());
		mav.addObject("nombre", usuario.getNombreUsuario());
		return mav;
	}

}

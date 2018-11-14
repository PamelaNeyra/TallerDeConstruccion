package com.pe.sercosta.scks.services;

import com.pe.sercosta.scks.entities.Usuario;

public interface IUsuarioService {

	public abstract Usuario obtenerUsuario (String username, String password);
	
}

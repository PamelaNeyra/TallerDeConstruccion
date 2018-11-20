package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Usuario;

public interface IUsuarioService {

	public abstract Usuario obtenerUsuario (String username, String password);
	
	public abstract List<Usuario> listarUsuarioPlanta (Planta planta);
	
	public abstract void registrarUsuario (Usuario usuario);
	
}

package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.repositories.admin.IUsuarioRepository;
import com.pe.sercosta.scks.services.IUsuarioService;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService{

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public Usuario obtenerUsuario(String username, String password) {
		Usuario usuario = usuarioRepository.findByUsername(username);
		return usuario;
	}

	@Override
	public List<Usuario> listarUsuarioPlanta(Planta planta) {
		return usuarioRepository.findByIdPlanta(planta);
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

}

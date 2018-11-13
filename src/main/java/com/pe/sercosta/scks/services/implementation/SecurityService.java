package com.pe.sercosta.scks.services.implementation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Rol;
import com.pe.sercosta.scks.entities.Usuario;
import com.pe.sercosta.scks.repositories.IUsuarioRepository;

@Service("securityService")
public class SecurityService implements UserDetailsService {
	
	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		List<GrantedAuthority> autorizaciones = buildAuthorities(usuario.getRolList());
		return buildUser(usuario, autorizaciones);
	}

	private User buildUser(Usuario user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.getActivo(), 
				true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(List<Rol> rolList) {
		List<GrantedAuthority> auths = new ArrayList<>();
		for(Rol rol : rolList) {
			auths.add(new SimpleGrantedAuthority(rol.getDescripcionRol()));
		}
		return auths;
	}
	
}

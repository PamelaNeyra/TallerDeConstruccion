package com.pe.sercosta.scks.repositories.admin;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Usuario;

@Repository("usuarioRepository")
public interface IUsuarioRepository extends JpaRepository<Usuario, Serializable>{

	public abstract Usuario findByUsername(String username);
	
	public abstract List<Usuario> findByIdPlanta(Planta planta);
	
}

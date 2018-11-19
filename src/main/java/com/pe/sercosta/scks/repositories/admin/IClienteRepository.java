package com.pe.sercosta.scks.repositories.admin;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Cliente;

@Repository("clienteAdminRepository")
public interface IClienteRepository extends JpaRepository<Cliente, Serializable>{

	public abstract List<Cliente> findAll();
		
}

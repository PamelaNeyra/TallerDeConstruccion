package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Cliente;

public interface IClienteService {

	/**
	 * Lógica de negocios para
	 * - O5 - Listar Clientes
	 * : Lista los clientes
	 * @return List<Cliente>
	 */
	public abstract List<Cliente> listarClientes();
	
	/**
	 * Lógica de negocios para
	 * - O5 - Listar Clientes
	 * : Lista los clientes con filtro
	 * @return List<Cliente>
	 */
	public abstract List<Cliente> buscarCliente(Cliente cliente);
	
}

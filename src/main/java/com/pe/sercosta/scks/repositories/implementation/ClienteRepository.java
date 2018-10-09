package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IClienteRepository;

@Repository("clienteRepository")
public class ClienteRepository implements IClienteRepository {

	private static final Log LOG = LogFactory.getLog(ClienteRepository.class);
	private static final String CAPA = "[Repository : Cliente] -> ";
	

	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientes(EntityManager sesion) {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_clientes");
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Cliente aux = new Cliente();
				aux.setIdCliente((Integer) o[0]);
				aux.setNombreCliente((String) o[1]);
				listaClientes.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los clientes", ex.getMessage());
		}
		return listaClientes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscarCliente(EntityManager sesion, Cliente cliente) {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_informacion_cliente");
			myquery.execute();
			listaCliente = myquery.getResultList();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al buscar el cliente", ex.getMessage());
		}
		return listaCliente;
	}


	@Override
	public void registrarCliente(EntityManager sesion, Cliente cliente) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_cliente");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			myquery.setParameter(1, cliente.getIdCliente())
					.setParameter(2, cliente.getNombreCliente());
			myquery.execute();			
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el cliente", ex.getMessage());
		}
		
	}

}

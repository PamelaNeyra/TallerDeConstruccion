package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IClienteRepository;
import com.pe.sercosta.scks.services.IClienteService;

@Service("clienteService")
public class ClienteService implements IClienteService {

	private static final Log LOG = LogFactory.getLog(ClienteService.class);
	private static final String CAPA = "[Service : Cliente] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("clienteRepository")
	private IClienteRepository clienteRepository;
	

	@Override
	public List<Cliente> listarClientes() {
		try {
			return clienteRepository.listarClientes(sesion);									
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las presentaciones", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	@Override
	public List<Cliente> buscarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
}

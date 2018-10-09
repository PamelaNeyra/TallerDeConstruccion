package com.pe.sercosta.scks.repositories.implementation;

//import java.io.Serializable; /*Descomentar*/
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IClienteRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "listarClientesProcedimientoAlmacenado", query = "CALL listarClientes()", resultClass = Cliente.class) })
@Repository("clienteRepository")
public class ClienteRepository implements IClienteRepository {

	private static final Log LOG = LogFactory.getLog(ClienteRepository.class);
	private static final String CAPA = "[Repository : Cliente] -> ";
	

	public List<Cliente> listarClientes(Session sesion) {
		List<Cliente> listaBD = new ArrayList<Cliente>();
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente clienteTemporal = new Cliente();
		try {
			@SuppressWarnings("unchecked")
			Query<Cliente> myquery = sesion.getNamedQuery(
					"sp_listar_clientes"); /* Falta actualizar el nombre del procedimiento */
			listaBD = myquery.list();
			for (Cliente cliente : listaBD) {
				clienteTemporal.setIdCliente(cliente.getIdCliente());
				clienteTemporal.setNombreCliente(cliente.getNombreCliente());
				listaClientes.add(clienteTemporal);
			}
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los clientes", ex.getMessage());
		}
		return listaClientes;
	}


	@Override
	public List<Cliente> listarClientes(EntityManager sesion) {
		// TODO Auto-generated method stub
		return null;
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

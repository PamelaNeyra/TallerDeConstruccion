package com.pe.sercosta.scks.repositories.implementation;

//import java.io.Serializable; /*Descomentar*/
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;
import com.pe.sercosta.scks.entities.Cliente;

@NamedNativeQueries({
		@NamedNativeQuery(name = "listarClientesProcedimientoAlmacenado", query = "CALL listarClientes()", resultClass = Cliente.class) })
// TODO: Falta el implements
public class ClienteRepository {

	public List<Cliente> listarClientes(Session sesion) {
		List<Cliente> listaBD = new ArrayList<Cliente>();
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente clienteTemporal = new Cliente();
		try {
			@SuppressWarnings("unchecked")
			Query<Cliente> myquery = sesion.getNamedQuery(
					"listarClientesProcedimientoAlmacenado"); /* Falta actualizar el nombre del procedimiento */
			listaBD = myquery.list();
			for (Cliente cliente : listaBD) {
				clienteTemporal.setIdCliente(cliente.getIdCliente());
				clienteTemporal.setNombreCliente(cliente.getNombreCliente());
				clienteTemporal.setOrdenVentaList(cliente.getOrdenVentaList());
				listaClientes.add(clienteTemporal);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return listaClientes;
	}

}

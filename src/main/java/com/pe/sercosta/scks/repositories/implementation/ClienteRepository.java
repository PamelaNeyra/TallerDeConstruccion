package com.pe.sercosta.scks.repositories.implementation;

//import java.io.Serializable; /*Descomentar*/
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.query.Query;


import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.util.HibernateUtil;

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "listarClientesProcedimientoAlmacenado",
	query = "CALL listarClientes()",
	resultClass = Cliente.class
			)
})
public class ClienteRepository { /* Falta el implements*/
	
	public List<Cliente> listarClientes() {
		
		List<Cliente> listaBD = new ArrayList<Cliente>();
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente clienteTemporal = new Cliente();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
		
			@SuppressWarnings("unchecked")
			Query<Cliente> myquery= session.getNamedQuery("listarClientesProcedimientoAlmacenado"); /*Falta actualizar el nombre del procedimiento*/
			listaBD = myquery.list();
			
			for (Cliente cliente : listaBD) {
				clienteTemporal.setIdCliente(cliente.getIdCliente());
				clienteTemporal.setNombreCliente(cliente.getNombreCliente());
				clienteTemporal.setOrdenVentaList(cliente.getOrdenVentaList());
	
				listaClientes.add(clienteTemporal);
			}
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return listaClientes;
	}
	
}

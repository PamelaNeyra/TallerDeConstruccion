package com.pe.sercosta.scks.repositories.implementation;

<<<<<<< HEAD
=======
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

>>>>>>> 64b178b0c368982ffd3669ce25eadc7e8cdc2f18
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
<<<<<<< HEAD
=======
import org.hibernate.query.Query;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

>>>>>>> 64b178b0c368982ffd3669ce25eadc7e8cdc2f18
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.util.HibernateUtil;

@NamedNativeQueries({
<<<<<<< HEAD
		@NamedNativeQuery(name = "registrarContenidoProcedimientoAlmacenado", query = "CALL registrarContenido()", resultClass = Contenido.class) })
public class ContenidoRepository implements IContenidoRepository {

=======
	@NamedNativeQuery(
	name = "registrarContenidoProcedimientoAlmacenado",
	query = "CALL registrarContenido()",
	resultClass = Contenido.class
			),
	@NamedNativeQuery(
			name = "listarContenidoProcedimientoAlmacenado",
			query = "CALL listarContenido()",
			resultClass = Contenido.class
					)
	
})
public class ContenidoRepository implements IContenidoRepository{
	
	/*Falta un metodo en la interfaz para sobreescribir el listado de contenidos*/
	
	public List<Contenido> listarContenidos() {
		
		List<Contenido> listaBD = new ArrayList<Contenido>();
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Contenido contenidoTemporal = new Contenido();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
		
			@SuppressWarnings("unchecked")
			Query<Contenido> myquery= session.getNamedQuery("listarContenidoProcedimientoAlmacenado"); /*Falta actualizar el nombre del procedimiento*/
			listaBD = myquery.list();
			
			for (Contenido contenido : listaBD) {
				contenidoTemporal.setCantidad(contenido.getCantidad());
				contenidoTemporal.setCodigoTrazabilidad(contenido.getCodigoTrazabilidad());
				contenidoTemporal.setComprometido(contenido.getComprometido());
				contenidoTemporal.setEstaMuestreado(contenido.getEstaMuestreado());
				contenidoTemporal.setLote(contenido.getLote());
				contenidoTemporal.setPresentacion(contenido.getPresentacion());
	
				listaContenidos.add(contenidoTemporal);
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
		return listaContenidos;
	}
	
	
>>>>>>> 64b178b0c368982ffd3669ce25eadc7e8cdc2f18
	@Override
	public void registrarContenido(Contenido contenido) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		// Contenido miContenido = null; Falta ver lo de las validaciones
		try {
			session.save(contenido);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}

	}

}

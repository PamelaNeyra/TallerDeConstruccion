package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.ILaboratorioRepository;

@Repository("laboratorioRepository")
public class LaboratorioRepository implements ILaboratorioRepository {

	private static final Log LOG = LogFactory.getLog(LaboratorioRepository.class);
	private static final String CAPA = "[Repository : Laboratorio] -> ";

	@SuppressWarnings("unchecked")
	@Override
	public List<Laboratorio> listarLaboratorios(EntityManager sesion) {
		List<Laboratorio> listaLaboratorio = new ArrayList<Laboratorio>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_laboratorios");
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Laboratorio aux = new Laboratorio();
				aux.setIdLaboratorio((Integer) o[0]);
				aux.setNombreLaboratorio((String) o[1]);
				listaLaboratorio.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las presentaciones", ex.getMessage());
		}
		return listaLaboratorio;
	}

	@Override
	public void registrarLaboratorio(EntityManager sesion, Laboratorio labo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarLaboratorio(EntityManager sesion, Laboratorio labo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarLaboratorio(EntityManager sesion, Laboratorio labo) {
		// TODO Auto-generated method stub
		
	}

}
package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.ILoteRepository;
import com.pe.sercosta.scks.repositories.IMuestreoRepository;
import com.pe.sercosta.scks.services.IMuestreoService;

@Service("muestreoService")
public class MuestreoService implements IMuestreoService{
	
	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Contenido] -> ";

	private IMuestreoRepository muestreoRepository;
	private ILoteRepository loteRepository;
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Override
	public void registrarMuestreo(Muestreo muestreo) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			//TODO: Validaciones de O2 - Muestrear Contenido
			List<Lote> listaLotes = null;
			
			//Listamos los lotes y actualizamos
			listaLotes = loteRepository.listarLotes(sesion);
			for (Lote l : listaLotes) {
				if(muestreo.getMuestreoPK().getIdLote() == l.getIdLote()) {
					loteRepository.actualizarLote(sesion, l);
					}
			}
			
			validarRegistrarMuestreo(muestreo);
			muestreoRepository.registrarMuestreo(sesion, muestreo);
			
			//tx.commit();
					
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al registrar el Muestreo", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	public void validarRegistrarMuestreo(Muestreo muestreo) throws Exception {
		if(muestreo.getMuestreoPK().getIdLote() == null)
			throw new Exception("El idLote es requerido.");
		if(muestreo.getMuestreoPK().getIdPresentacion() == null)
			throw new Exception("El idPresentacion es requerido.");
		if(muestreo.getMuestreoPK().getIdMuestra() == 0)
			throw new Exception("El idMuestra es requerido.");
		if(muestreo.getCantidad() <= 0.0)
			throw new Exception("La cantidad de muestreo debe ser mayor a 0.0.");
			
	}

}

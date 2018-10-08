package com.pe.sercosta.scks.services.implementation;

import com.pe.sercosta.scks.services.ILaboratorioService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.repositories.ILaboratorioRepository;

public class LaboratorioService implements ILaboratorioService{

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Contenido] -> ";
	
	private ILaboratorioRepository laboratorioRepository;
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Override
	public List<Laboratorio> listarLaboratorios() {
		EntityTransaction tx = sesion.getTransaction();
		tx.begin();
		try {
			return laboratorioRepository.listarLaboratorios(sesion);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			tx.rollback();
			throw new SercostaException("Hubo un error al listar laboratorios", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}

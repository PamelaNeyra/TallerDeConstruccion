package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Laboratorio;

public interface ILaboratorioService {

	/**
	 * Lógica de negocios para
	 * - O2 - Muestrear Contenido
	 * : Lista los laboratorios
	 * @return List<Laboratorio>
	 */
	public List<Laboratorio> listarLaboratorios();
}

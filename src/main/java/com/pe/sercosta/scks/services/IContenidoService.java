package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Presentacion;

public interface IContenidoService {
	
	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Contenido
	 * : Registra un contenido
	 * @param contenido
	 */
	public abstract void registrarContenido(Contenido contenido);
	
	/**
	 * Lógica de negocios para
	 * - O1 - Listar Contenido
	 * : Lista un contenido
	 * @param contenido
	 */
	public abstract List<Contenido> listarContenidos();

	/**
	 * Lógica de negocios para
	 * - O2 - Actualizar Contenido
	 * : Actualiza un contenido
	 * @param contenido
	 */
	public abstract void actualizarContenido(Contenido contenido);
	
	/**
	 * Lógica de negocios para
	 * -O8 - Ver Saldos
	 * :Lista los Lotes por Presentación mediante los Contenidos
	 */
	public abstract List<Lote> listarLotePorPresentacion(Presentacion presentacion);
	
	/**
	 * Lógica de negocios para
	 * -O4
	 * :Lista los Contenidos por Lote
	 */
	public abstract List<Contenido> listarContenidosPorLote(Lote lote);
	
	/**
	 * Lógica de negocios para
	 * -O9 - Ver Packings
	 * :Lista los contenidos por Asiganacion
	 */
	public abstract List<Contenido> listarContenidosPorAsignacion(Asignacion asignacion);
}

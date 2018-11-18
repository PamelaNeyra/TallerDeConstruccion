package com.pe.sercosta.scks.services;

import java.util.List;

import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.models.ProductoAsignacionModel;

public interface IPresentacionService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Lista las presentaciones
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> listarPresentacion();
	
	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Lista las presentaciones con filtro
	 * @param presentacion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> buscarPresentacion(Presentacion presentacion);
	
	/**
	 * Logica de negocios para 
	 * -O8 - Listar Detalle Producto Asigancion
	 * :Lista los ProductoAsignacionModel
	 * 
	 * 
	 */
	public abstract List<ProductoAsignacionModel> listarDetalleProductoAsignacion(Planta planta);

}

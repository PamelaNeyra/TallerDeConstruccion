package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.ProductoTerminado;

public interface IProductoTerminadoService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Muestra
	 * : Lista de productos terminados
	 * @return List<ProdcutosTerminados>
	 */
	public abstract List<ProductoTerminado> listarProducto();
	
	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Lista de productos terminados
	 * @param productos terminados
	 * @return List<ProductoTerminado>
	 */
	public abstract List<ProductoTerminado> buscarProducto(ProductoTerminado producto);

}

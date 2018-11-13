package com.pe.sercosta.scks.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.models.ProductoTerminadoSaldoModel;

public interface IProductoTerminadoService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Muestra
	 * : Lista de productos terminados
	 * @return List<ProdcutosTerminados>
	 */
	public abstract List<ProductoTerminado> listarProducto(Planta planta);
	
	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Lista de productos terminados
	 * @param productos terminados
	 * @return List<ProductoTerminado>
	 */
	public abstract List<ProductoTerminado> buscarProducto(ProductoTerminado producto);
	
	/**
	 * Lógica de negocios para
	 * -O8 - Ver Saldos
	 * :Lista los saldos de producto terminado en grupo
	 */
	public abstract List<ProductoTerminadoSaldoModel> listarProductosTerminadosSaldos(EntityManager sesion, Laboratorio laboratorio, Planta planta,
														Muestra muestra);

}

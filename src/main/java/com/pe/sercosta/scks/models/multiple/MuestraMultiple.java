  package com.pe.sercosta.scks.models.multiple;

import java.util.List;

import com.pe.sercosta.scks.models.MuestraModel;
import com.pe.sercosta.scks.models.ProductoTerminadoModel;

public class MuestraMultiple {

	private MuestraModel muestra;
	private List<ProductoTerminadoModel> productoTerminadoList;
	
	public MuestraMultiple() {
		
	}

	public MuestraModel getMuestra() {
		return muestra;
	}

	public void setMuestra(MuestraModel muestra) {
		this.muestra = muestra;
	}

	public List<ProductoTerminadoModel> getProductoTerminadoList() {
		return productoTerminadoList;
	}

	public void setProductoTerminadoList(List<ProductoTerminadoModel> productoTerminadoList) {
		this.productoTerminadoList = productoTerminadoList;
	}
	
}

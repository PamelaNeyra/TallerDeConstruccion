package com.pe.sercosta.scks.models.multiple;

import java.util.List;
import com.pe.sercosta.scks.models.PresentacionModel;
import com.pe.sercosta.scks.models.views.OrdenVentaView;

public class OrdenVentaMultiple {

	private OrdenVentaView ordenVenta;
	private List<PresentacionModel> listaPresentacion;
	
	public OrdenVentaMultiple() {
		
	}

	public OrdenVentaView getOrdenVenta() {
		return ordenVenta;
	}

	public void setOrdenVenta(OrdenVentaView ordenVenta) {
		this.ordenVenta = ordenVenta;
	}

	public List<PresentacionModel> getListaPresentacion() {
		return listaPresentacion;
	}

	public void setListaPresentacion(List<PresentacionModel> listaPresentacion) {
		this.listaPresentacion = listaPresentacion;
	}
	
}

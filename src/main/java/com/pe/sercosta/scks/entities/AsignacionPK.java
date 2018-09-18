package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AsignacionPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Column(name = "id_lote")
    private String idLote;
    
    @Column(name = "id_presentacion")
    private String idPresentacion;
    
    @Column(name = "id_orden_venta")
    private String idOrdenVenta;

    public AsignacionPK() {
    }

    public AsignacionPK(String idLote, String idPresentacion, String idOrdenVenta) {
        this.idLote = idLote;
        this.idPresentacion = idPresentacion;
        this.idOrdenVenta = idOrdenVenta;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public String getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(String idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public String getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(String idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }
    
}

package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RetiroPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_lote")
    private String idLote;

    @Column(name = "id_presentacion")
    private String idPresentacion;

    @Column(name = "id_orden_retiro")
    private int idOrdenRetiro;

    public RetiroPK() {
    }

    public RetiroPK(String idLote, String idPresentacion, int idOrdenRetiro) {
        this.idLote = idLote;
        this.idPresentacion = idPresentacion;
        this.idOrdenRetiro = idOrdenRetiro;
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

    public int getIdOrdenRetiro() {
        return idOrdenRetiro;
    }

    public void setIdOrdenRetiro(int idOrdenRetiro) {
        this.idOrdenRetiro = idOrdenRetiro;
    }
    
}

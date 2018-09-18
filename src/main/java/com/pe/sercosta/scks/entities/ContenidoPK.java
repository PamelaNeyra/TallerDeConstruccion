package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContenidoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_lote")
    private String idLote;

    @Column(name = "id_presentacion")
    private String idPresentacion;

    public ContenidoPK() {
    }

    public ContenidoPK(String idLote, String idPresentacion) {
        this.idLote = idLote;
        this.idPresentacion = idPresentacion;
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
    
}

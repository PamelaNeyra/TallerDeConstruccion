package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MuestreoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Column(name = "id_lote")
    private String idLote;

    @Column(name = "id_presentacion")
    private String idPresentacion;

    @Column(name = "id_muestra")
    private int idMuestra;

    public MuestreoPK() {
    }

    public MuestreoPK(String idLote, String idPresentacion, int idMuestra) {
        this.idLote = idLote;
        this.idPresentacion = idPresentacion;
        this.idMuestra = idMuestra;
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

    public int getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(int idMuestra) {
        this.idMuestra = idMuestra;
    }
    
}

package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlantaPresentacionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_presentacion")
    private String idPresentacion;
    
    @Column(name = "id_planta")
    private int idPlanta;

    public PlantaPresentacionPK() {
    }

    public PlantaPresentacionPK(String idPresentacion, int idPlanta) {
        this.idPresentacion = idPresentacion;
        this.idPlanta = idPlanta;
    }

    public String getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(String idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

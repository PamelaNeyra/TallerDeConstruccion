package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlantaProductoTerminadoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_producto_terminado")
    private String idProductoTerminado;

    @Column(name = "id_planta")
    private int idPlanta;

    public PlantaProductoTerminadoPK() {
    }

    public PlantaProductoTerminadoPK(String idProductoTerminado, int idPlanta) {
        this.idProductoTerminado = idProductoTerminado;
        this.idPlanta = idPlanta;
    }

    public String getIdProductoTerminado() {
        return idProductoTerminado;
    }

    public void setIdProductoTerminado(String idProductoTerminado) {
        this.idProductoTerminado = idProductoTerminado;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

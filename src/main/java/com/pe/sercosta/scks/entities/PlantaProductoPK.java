package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlantaProductoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_planta")
    private int idPlanta;

    @Column(name = "id_producto")
    private String idProducto;

    public PlantaProductoPK() {
    }

    public PlantaProductoPK(int idPlanta, String idProducto) {
        this.idPlanta = idPlanta;
        this.idProducto = idProducto;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    
}

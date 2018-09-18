package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlantaSubproductoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_subproducto")
    private String idSubproducto;

    @Column(name = "id_planta")
    private int idPlanta;

    public PlantaSubproductoPK() {
    }

    public PlantaSubproductoPK(String idSubproducto, int idPlanta) {
        this.idSubproducto = idSubproducto;
        this.idPlanta = idPlanta;
    }

    public String getIdSubproducto() {
        return idSubproducto;
    }

    public void setIdSubproducto(String idSubproducto) {
        this.idSubproducto = idSubproducto;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

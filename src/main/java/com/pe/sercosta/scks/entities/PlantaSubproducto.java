package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planta_subproducto")
public class PlantaSubproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PlantaSubproductoPK plantaSubproductoPK;

    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planta planta;
    
    @JoinColumn(name = "id_subproducto", referencedColumnName = "id_subproducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subproducto subproducto;

    public PlantaSubproducto() {
    }

    public PlantaSubproducto(PlantaSubproductoPK plantaSubproductoPK) {
        this.plantaSubproductoPK = plantaSubproductoPK;
    }

    public PlantaSubproducto(String idSubproducto, int idPlanta) {
        this.plantaSubproductoPK = new PlantaSubproductoPK(idSubproducto, idPlanta);
    }

    public PlantaSubproductoPK getPlantaSubproductoPK() {
        return plantaSubproductoPK;
    }

    public void setPlantaSubproductoPK(PlantaSubproductoPK plantaSubproductoPK) {
        this.plantaSubproductoPK = plantaSubproductoPK;
    }

    public Double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Double getComprometidoTotal() {
        return comprometidoTotal;
    }

    public void setComprometidoTotal(Double comprometidoTotal) {
        this.comprometidoTotal = comprometidoTotal;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Subproducto getSubproducto() {
        return subproducto;
    }

    public void setSubproducto(Subproducto subproducto) {
        this.subproducto = subproducto;
    }
    
}

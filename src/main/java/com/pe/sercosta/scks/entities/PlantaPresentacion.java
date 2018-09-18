package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planta_presentacion")
public class PlantaPresentacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PlantaPresentacionPK plantaPresentacionPK;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planta planta;
    
    @JoinColumn(name = "id_presentacion", referencedColumnName = "id_presentacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Presentacion presentacion;

    public PlantaPresentacion() {
    }

    public PlantaPresentacion(PlantaPresentacionPK plantaPresentacionPK) {
        this.plantaPresentacionPK = plantaPresentacionPK;
    }

    public PlantaPresentacion(String idPresentacion, int idPlanta) {
        this.plantaPresentacionPK = new PlantaPresentacionPK(idPresentacion, idPlanta);
    }

    public PlantaPresentacionPK getPlantaPresentacionPK() {
        return plantaPresentacionPK;
    }

    public void setPlantaPresentacionPK(PlantaPresentacionPK plantaPresentacionPK) {
        this.plantaPresentacionPK = plantaPresentacionPK;
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

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }
    
}

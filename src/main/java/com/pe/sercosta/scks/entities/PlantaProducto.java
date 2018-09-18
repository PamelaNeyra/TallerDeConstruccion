package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planta_producto")
public class PlantaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PlantaProductoPK plantaProductoPK;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planta planta;
    
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public PlantaProducto() {
    }

    public PlantaProducto(PlantaProductoPK plantaProductoPK) {
        this.plantaProductoPK = plantaProductoPK;
    }

    public PlantaProducto(int idPlanta, String idProducto) {
        this.plantaProductoPK = new PlantaProductoPK(idPlanta, idProducto);
    }

    public PlantaProductoPK getPlantaProductoPK() {
        return plantaProductoPK;
    }

    public void setPlantaProductoPK(PlantaProductoPK plantaProductoPK) {
        this.plantaProductoPK = plantaProductoPK;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}

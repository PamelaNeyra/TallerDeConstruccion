package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planta_producto_terminado")
public class PlantaProductoTerminado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PlantaProductoTerminadoPK plantaProductoTerminadoPK;

    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @Column(name = "abreviatura")
    private String abreviatura;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planta planta;
    
    @JoinColumn(name = "id_producto_terminado", referencedColumnName = "id_producto_terminado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProductoTerminado productoTerminado;

    public PlantaProductoTerminado() {
    }

    public PlantaProductoTerminado(PlantaProductoTerminadoPK plantaProductoTerminadoPK) {
        this.plantaProductoTerminadoPK = plantaProductoTerminadoPK;
    }

    public PlantaProductoTerminado(PlantaProductoTerminadoPK plantaProductoTerminadoPK, String abreviatura) {
        this.plantaProductoTerminadoPK = plantaProductoTerminadoPK;
        this.abreviatura = abreviatura;
    }

    public PlantaProductoTerminado(String idProductoTerminado, int idPlanta) {
        this.plantaProductoTerminadoPK = new PlantaProductoTerminadoPK(idProductoTerminado, idPlanta);
    }

    public PlantaProductoTerminadoPK getPlantaProductoTerminadoPK() {
        return plantaProductoTerminadoPK;
    }

    public void setPlantaProductoTerminadoPK(PlantaProductoTerminadoPK plantaProductoTerminadoPK) {
        this.plantaProductoTerminadoPK = plantaProductoTerminadoPK;
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public ProductoTerminado getProductoTerminado() {
        return productoTerminado;
    }

    public void setProductoTerminado(ProductoTerminado productoTerminado) {
        this.productoTerminado = productoTerminado;
    }
    
}

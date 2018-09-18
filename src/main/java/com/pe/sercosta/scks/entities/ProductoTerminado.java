package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "producto_terminado")
public class ProductoTerminado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_producto_terminado")
    private String idProductoTerminado;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoTerminado")
    private List<PlantaProductoTerminado> plantaProductoTerminadoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProductoTerminado")
    private List<Presentacion> presentacionList;
    
    @JoinColumn(name = "id_subproducto", referencedColumnName = "id_subproducto")
    @ManyToOne(optional = false)
    private Subproducto idSubproducto;

    public ProductoTerminado() {
    }

    public ProductoTerminado(String idProductoTerminado) {
        this.idProductoTerminado = idProductoTerminado;
    }

    public ProductoTerminado(String idProductoTerminado, String descripcion) {
        this.idProductoTerminado = idProductoTerminado;
        this.descripcion = descripcion;
    }

    public String getIdProductoTerminado() {
        return idProductoTerminado;
    }

    public void setIdProductoTerminado(String idProductoTerminado) {
        this.idProductoTerminado = idProductoTerminado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<PlantaProductoTerminado> getPlantaProductoTerminadoList() {
        return plantaProductoTerminadoList;
    }

    public void setPlantaProductoTerminadoList(List<PlantaProductoTerminado> plantaProductoTerminadoList) {
        this.plantaProductoTerminadoList = plantaProductoTerminadoList;
    }

    public List<Presentacion> getPresentacionList() {
        return presentacionList;
    }

    public void setPresentacionList(List<Presentacion> presentacionList) {
        this.presentacionList = presentacionList;
    }

    public Subproducto getIdSubproducto() {
        return idSubproducto;
    }

    public void setIdSubproducto(Subproducto idSubproducto) {
        this.idSubproducto = idSubproducto;
    }
    
}

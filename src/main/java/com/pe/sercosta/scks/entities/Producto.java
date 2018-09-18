package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_producto")
    private String idProducto;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<Subproducto> subproductoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<PlantaProducto> plantaProductoList;

    public Producto() {
    }

    public Producto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(String idProducto, String descripcion) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
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

    public List<Subproducto> getSubproductoList() {
        return subproductoList;
    }

    public void setSubproductoList(List<Subproducto> subproductoList) {
        this.subproductoList = subproductoList;
    }

    public List<PlantaProducto> getPlantaProductoList() {
        return plantaProductoList;
    }

    public void setPlantaProductoList(List<PlantaProducto> plantaProductoList) {
        this.plantaProductoList = plantaProductoList;
    }
    
}

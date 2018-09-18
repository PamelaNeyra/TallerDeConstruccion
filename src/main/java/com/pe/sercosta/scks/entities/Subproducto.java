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
@Table(name = "subproducto")
public class Subproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_subproducto")
    private String idSubproducto;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto idProducto;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproducto")
    private List<ProductoTerminado> productoTerminadoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subproducto")
    private List<PlantaSubproducto> plantaSubproductoList;

    public Subproducto() {
    }

    public Subproducto(String idSubproducto) {
        this.idSubproducto = idSubproducto;
    }

    public Subproducto(String idSubproducto, String descripcion) {
        this.idSubproducto = idSubproducto;
        this.descripcion = descripcion;
    }

    public String getIdSubproducto() {
        return idSubproducto;
    }

    public void setIdSubproducto(String idSubproducto) {
        this.idSubproducto = idSubproducto;
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

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public List<ProductoTerminado> getProductoTerminadoList() {
        return productoTerminadoList;
    }

    public void setProductoTerminadoList(List<ProductoTerminado> productoTerminadoList) {
        this.productoTerminadoList = productoTerminadoList;
    }

    public List<PlantaSubproducto> getPlantaSubproductoList() {
        return plantaSubproductoList;
    }

    public void setPlantaSubproductoList(List<PlantaSubproducto> plantaSubproductoList) {
        this.plantaSubproductoList = plantaSubproductoList;
    }
    
}

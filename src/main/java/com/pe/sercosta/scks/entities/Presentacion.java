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
@Table(name = "presentacion")
public class Presentacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_presentacion")
    private String idPresentacion;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @Column(name = "bloque")
    private Double bloque;
    
    @Column(name = "contenedor")
    private Double contenedor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentacion")
    private List<Contenido> contenidoList;
    
    @JoinColumn(name = "id_producto_terminado", referencedColumnName = "id_producto_terminado")
    @ManyToOne(optional = false)
    private ProductoTerminado idProductoTerminado;
    
    @JoinColumn(name = "id_tipo_contenedor", referencedColumnName = "id_tipo_contenedor")
    @ManyToOne
    private TipoContenedor idTipoContenedor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentacion")
    private List<PlantaPresentacion> plantaPresentacionList;

    public Presentacion() {
    }

    public Presentacion(String idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public Presentacion(String idPresentacion, String descripcion) {
        this.idPresentacion = idPresentacion;
        this.descripcion = descripcion;
    }

    public String getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(String idPresentacion) {
        this.idPresentacion = idPresentacion;
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

    public Double getBloque() {
        return bloque;
    }

    public void setBloque(Double bloque) {
        this.bloque = bloque;
    }

    public Double getContenedor() {
        return contenedor;
    }

    public void setContenedor(Double contenedor) {
        this.contenedor = contenedor;
    }

    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    public ProductoTerminado getIdProductoTerminado() {
        return idProductoTerminado;
    }

    public void setIdProductoTerminado(ProductoTerminado idProductoTerminado) {
        this.idProductoTerminado = idProductoTerminado;
    }

    public TipoContenedor getIdTipoContenedor() {
        return idTipoContenedor;
    }

    public void setIdTipoContenedor(TipoContenedor idTipoContenedor) {
        this.idTipoContenedor = idTipoContenedor;
    }

    public List<PlantaPresentacion> getPlantaPresentacionList() {
        return plantaPresentacionList;
    }

    public void setPlantaPresentacionList(List<PlantaPresentacion> plantaPresentacionList) {
        this.plantaPresentacionList = plantaPresentacionList;
    }
    
}

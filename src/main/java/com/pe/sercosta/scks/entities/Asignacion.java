package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asignacion")
public class Asignacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected AsignacionPK asignacionPK;
    
    @Column(name = "cantidad")
    private double cantidad;
    
    @JoinColumns({
        @JoinColumn(name = "id_lote", referencedColumnName = "id_lote", insertable = false, updatable = false)
        , @JoinColumn(name = "id_presentacion", referencedColumnName = "id_presentacion", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Contenido contenido;
    
    @JoinColumn(name = "id_orden_venta", referencedColumnName = "id_orden_venta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrdenVenta ordenVenta;

    public Asignacion() {
    }

    public Asignacion(AsignacionPK asignacionPK) {
        this.asignacionPK = asignacionPK;
    }

    public Asignacion(AsignacionPK asignacionPK, double cantidad) {
        this.asignacionPK = asignacionPK;
        this.cantidad = cantidad;
    }

    public Asignacion(String idLote, String idPresentacion, String idOrdenVenta) {
        this.asignacionPK = new AsignacionPK(idLote, idPresentacion, idOrdenVenta);
    }

    public AsignacionPK getAsignacionPK() {
        return asignacionPK;
    }

    public void setAsignacionPK(AsignacionPK asignacionPK) {
        this.asignacionPK = asignacionPK;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }
    
}

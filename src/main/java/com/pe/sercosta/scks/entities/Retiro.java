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
@Table(name = "retiro")
public class Retiro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected RetiroPK retiroPK;
    
    @Column(name = "cantidad")
    private double cantidad;
    
    @JoinColumns({
        @JoinColumn(name = "id_lote", referencedColumnName = "id_lote", insertable = false, updatable = false)
        , @JoinColumn(name = "id_presentacion", referencedColumnName = "id_presentacion", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Contenido contenido;
    
    @JoinColumn(name = "id_orden_retiro", referencedColumnName = "id_orden_retiro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrdenRetiro ordenRetiro;

    public Retiro() {
    }

    public Retiro(RetiroPK retiroPK) {
        this.retiroPK = retiroPK;
    }

    public Retiro(RetiroPK retiroPK, double cantidad) {
        this.retiroPK = retiroPK;
        this.cantidad = cantidad;
    }

    public Retiro(String idLote, String idPresentacion, int idOrdenRetiro) {
        this.retiroPK = new RetiroPK(idLote, idPresentacion, idOrdenRetiro);
    }

    public RetiroPK getRetiroPK() {
        return retiroPK;
    }

    public void setRetiroPK(RetiroPK retiroPK) {
        this.retiroPK = retiroPK;
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

    public OrdenRetiro getOrdenRetiro() {
        return ordenRetiro;
    }

    public void setOrdenRetiro(OrdenRetiro ordenRetiro) {
        this.ordenRetiro = ordenRetiro;
    }
    
}

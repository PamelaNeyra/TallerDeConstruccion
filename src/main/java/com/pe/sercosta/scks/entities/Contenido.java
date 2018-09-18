package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "contenido")
public class Contenido implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ContenidoPK contenidoPK;
    
    @Column(name = "cantidad")
    private double cantidad;
    
    @Column(name = "comprometido")
    private double comprometido;

    @Column(name = "codigo_trazabilidad")
    private String codigoTrazabilidad;

    @Column(name = "esta_muestreado")
    private boolean estaMuestreado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contenido")
    private List<Asignacion> asignacionList;
    
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lote lote;
    
    @JoinColumn(name = "id_presentacion", referencedColumnName = "id_presentacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Presentacion presentacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contenido")
    private List<Muestreo> muestreoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contenido")
    private List<Retiro> retiroList;

    public Contenido() {
    }

    public Contenido(ContenidoPK contenidoPK) {
        this.contenidoPK = contenidoPK;
    }

    public Contenido(ContenidoPK contenidoPK, double cantidad, double comprometido, String codigoTrazabilidad, boolean estaMuestreado) {
        this.contenidoPK = contenidoPK;
        this.cantidad = cantidad;
        this.comprometido = comprometido;
        this.codigoTrazabilidad = codigoTrazabilidad;
        this.estaMuestreado = estaMuestreado;
    }

    public Contenido(String idLote, String idPresentacion) {
        this.contenidoPK = new ContenidoPK(idLote, idPresentacion);
    }

    public ContenidoPK getContenidoPK() {
        return contenidoPK;
    }

    public void setContenidoPK(ContenidoPK contenidoPK) {
        this.contenidoPK = contenidoPK;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getComprometido() {
        return comprometido;
    }

    public void setComprometido(double comprometido) {
        this.comprometido = comprometido;
    }

    public String getCodigoTrazabilidad() {
        return codigoTrazabilidad;
    }

    public void setCodigoTrazabilidad(String codigoTrazabilidad) {
        this.codigoTrazabilidad = codigoTrazabilidad;
    }

    public boolean getEstaMuestreado() {
        return estaMuestreado;
    }

    public void setEstaMuestreado(boolean estaMuestreado) {
        this.estaMuestreado = estaMuestreado;
    }

    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    public List<Muestreo> getMuestreoList() {
        return muestreoList;
    }

    public void setMuestreoList(List<Muestreo> muestreoList) {
        this.muestreoList = muestreoList;
    }

    public List<Retiro> getRetiroList() {
        return retiroList;
    }

    public void setRetiroList(List<Retiro> retiroList) {
        this.retiroList = retiroList;
    }
    
}

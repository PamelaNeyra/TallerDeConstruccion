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
@Table(name = "muestreo")
public class Muestreo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected MuestreoPK muestreoPK;
    
    @Column(name = "cantidad")
    private double cantidad;
    
    @JoinColumns({
        @JoinColumn(name = "id_lote", referencedColumnName = "id_lote", insertable = false, updatable = false)
        , @JoinColumn(name = "id_presentacion", referencedColumnName = "id_presentacion", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Contenido contenido;
    
    @JoinColumn(name = "id_muestra", referencedColumnName = "id_muestra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Muestra muestra;

    public Muestreo() {
    }

    public Muestreo(MuestreoPK muestreoPK) {
        this.muestreoPK = muestreoPK;
    }

    public Muestreo(MuestreoPK muestreoPK, double cantidad) {
        this.muestreoPK = muestreoPK;
        this.cantidad = cantidad;
    }

    public Muestreo(String idLote, String idPresentacion, int idMuestra) {
        this.muestreoPK = new MuestreoPK(idLote, idPresentacion, idMuestra);
    }

    public MuestreoPK getMuestreoPK() {
        return muestreoPK;
    }

    public void setMuestreoPK(MuestreoPK muestreoPK) {
        this.muestreoPK = muestreoPK;
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

    public Muestra getMuestra() {
        return muestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (muestreoPK != null ? muestreoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Muestreo)) {
            return false;
        }
        Muestreo other = (Muestreo) object;
        if ((this.muestreoPK == null && other.muestreoPK != null) || (this.muestreoPK != null && !this.muestreoPK.equals(other.muestreoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Muestreo[ muestreoPK=" + muestreoPK + " ]";
    }
    
}

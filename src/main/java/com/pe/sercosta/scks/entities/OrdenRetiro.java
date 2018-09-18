package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "orden_retiro")
public class OrdenRetiro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_retiro")
    private Integer idOrdenRetiro;
    
    @Column(name = "fecha_retiro")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaRetiro;

    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenRetiro")
    private List<Retiro> retiroList;
    
    @JoinColumn(name = "id_motivo", referencedColumnName = "id_motivo")
    @ManyToOne
    private Motivo idMotivo;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta")
    @ManyToOne
    private Planta idPlanta;
    
    @OneToMany(mappedBy = "idOrdenRetiro")
    private List<Muestra> muestraList;

    public OrdenRetiro() {
    }

    public OrdenRetiro(Integer idOrdenRetiro) {
        this.idOrdenRetiro = idOrdenRetiro;
    }

    public OrdenRetiro(Integer idOrdenRetiro, LocalDate fechaRetiro) {
        this.idOrdenRetiro = idOrdenRetiro;
        this.fechaRetiro = fechaRetiro;
    }

    public Integer getIdOrdenRetiro() {
        return idOrdenRetiro;
    }

    public void setIdOrdenRetiro(Integer idOrdenRetiro) {
        this.idOrdenRetiro = idOrdenRetiro;
    }

    public LocalDate getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(LocalDate fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public Double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public List<Retiro> getRetiroList() {
        return retiroList;
    }

    public void setRetiroList(List<Retiro> retiroList) {
        this.retiroList = retiroList;
    }

    public Motivo getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Motivo idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Planta getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Planta idPlanta) {
        this.idPlanta = idPlanta;
    }

    public List<Muestra> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra> muestraList) {
        this.muestraList = muestraList;
    }
    
}

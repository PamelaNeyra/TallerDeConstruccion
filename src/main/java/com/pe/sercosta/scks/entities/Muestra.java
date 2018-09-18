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
@Table(name = "muestra")
public class Muestra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_muestra")
    private Integer idMuestra;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaCreacion;
    
    @Column(name = "fecha_muestreado")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaMuestreado;
    
    @Column(name = "ot")
    private String ot;

    @Column(name = "cantidad_total")
    private Double cantidadTotal;

    @Column(name = "esta_muestreado")
    private boolean estaMuestreado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestra")
    private List<Muestreo> muestreoList;
    
    @JoinColumn(name = "id_laboratorio", referencedColumnName = "id_laboratorio")
    @ManyToOne
    private Laboratorio idLaboratorio;
    
    @JoinColumn(name = "id_orden_retiro", referencedColumnName = "id_orden_retiro")
    @ManyToOne
    private OrdenRetiro idOrdenRetiro;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta")
    @ManyToOne
    private Planta idPlanta;

    public Muestra() {
    }

    public Muestra(Integer idMuestra) {
        this.idMuestra = idMuestra;
    }

    public Muestra(Integer idMuestra, LocalDate fechaCreacion, boolean estaMuestreado) {
        this.idMuestra = idMuestra;
        this.fechaCreacion = fechaCreacion;
        this.estaMuestreado = estaMuestreado;
    }

    public Integer getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(Integer idMuestra) {
        this.idMuestra = idMuestra;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaMuestreado() {
        return fechaMuestreado;
    }

    public void setFechaMuestreado(LocalDate fechaMuestreado) {
        this.fechaMuestreado = fechaMuestreado;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public Double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public boolean getEstaMuestreado() {
        return estaMuestreado;
    }

    public void setEstaMuestreado(boolean estaMuestreado) {
        this.estaMuestreado = estaMuestreado;
    }

    public List<Muestreo> getMuestreoList() {
        return muestreoList;
    }

    public void setMuestreoList(List<Muestreo> muestreoList) {
        this.muestreoList = muestreoList;
    }

    public Laboratorio getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Laboratorio idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public OrdenRetiro getIdOrdenRetiro() {
        return idOrdenRetiro;
    }

    public void setIdOrdenRetiro(OrdenRetiro idOrdenRetiro) {
        this.idOrdenRetiro = idOrdenRetiro;
    }

    public Planta getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Planta idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

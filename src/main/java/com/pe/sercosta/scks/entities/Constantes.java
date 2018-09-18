package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "constantes")
public class Constantes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planta")
    private Integer idPlanta;
    
    @Column(name = "codigo_trazabilidad")
    private int codigoTrazabilidad;
    
    @Column(name = "maximo_muestreo")
    private double maximoMuestreo;
    
    @Column(name = "maximo_asignacion")
    private double maximoAsignacion;
    
    @Column(name = "anios_vencimiento")
    private int aniosVencimiento;
    
    @Column(name = "dias_expira")
    private int diasExpira;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Planta planta;

    public Constantes() {
    }

    public Constantes(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public Constantes(Integer idPlanta, int codigoTrazabilidad, double maximoMuestreo, double maximoAsignacion, int aniosVencimiento, int diasExpira) {
        this.idPlanta = idPlanta;
        this.codigoTrazabilidad = codigoTrazabilidad;
        this.maximoMuestreo = maximoMuestreo;
        this.maximoAsignacion = maximoAsignacion;
        this.aniosVencimiento = aniosVencimiento;
        this.diasExpira = diasExpira;
    }

    public Integer getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public int getCodigoTrazabilidad() {
        return codigoTrazabilidad;
    }

    public void setCodigoTrazabilidad(int codigoTrazabilidad) {
        this.codigoTrazabilidad = codigoTrazabilidad;
    }

    public double getMaximoMuestreo() {
        return maximoMuestreo;
    }

    public void setMaximoMuestreo(double maximoMuestreo) {
        this.maximoMuestreo = maximoMuestreo;
    }

    public double getMaximoAsignacion() {
        return maximoAsignacion;
    }

    public void setMaximoAsignacion(double maximoAsignacion) {
        this.maximoAsignacion = maximoAsignacion;
    }

    public int getAniosVencimiento() {
        return aniosVencimiento;
    }

    public void setAniosVencimiento(int aniosVencimiento) {
        this.aniosVencimiento = aniosVencimiento;
    }

    public int getDiasExpira() {
        return diasExpira;
    }

    public void setDiasExpira(int diasExpira) {
        this.diasExpira = diasExpira;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
    
}

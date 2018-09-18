package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "lote")
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_lote")
    private String idLote;
    
    @Column(name = "fecha_produccion")
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate fechaProduccion;
    
    @Column(name = "fecha_captura")
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate fechaCaptura;

    @Column(name = "fecha_vencimiento")
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate fechaVencimiento;
    
    @Column(name = "cantidad_recepcion")
    private double cantidadRecepcion;

    @Column(name = "rendimiento")
    private double rendimiento;
    
    @Column(name = "cantidad_total")
    private Double cantidadTotal;
    
    @Column(name = "comprometido_total")
    private Double comprometidoTotal;
    
    @Column(name = "es_reempaque")
    private Boolean esReempaque;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lote")
    private List<Contenido> contenidoList;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta")
    @ManyToOne
    private Planta idPlanta;

    public Lote() {
    }

    public Lote(String idLote) {
        this.idLote = idLote;
    }

    public Lote(String idLote, LocalDate fechaProduccion, LocalDate fechaCaptura, LocalDate fechaVencimiento, double cantidadRecepcion, double rendimiento) {
        this.idLote = idLote;
        this.fechaProduccion = fechaProduccion;
        this.fechaCaptura = fechaCaptura;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadRecepcion = cantidadRecepcion;
        this.rendimiento = rendimiento;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public LocalDate getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(LocalDate fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public LocalDate getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(LocalDate fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getCantidadRecepcion() {
        return cantidadRecepcion;
    }

    public void setCantidadRecepcion(double cantidadRecepcion) {
        this.cantidadRecepcion = cantidadRecepcion;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
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

    public Boolean getEsReempaque() {
        return esReempaque;
    }

    public void setEsReempaque(Boolean esReempaque) {
        this.esReempaque = esReempaque;
    }

    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    public Planta getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Planta idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

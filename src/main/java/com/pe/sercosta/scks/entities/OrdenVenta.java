package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "orden_venta")
public class OrdenVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id_orden_venta")
    private String idOrdenVenta;
    
    @Column(name = "fecha_asignacion")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaAsignacion;
    
    @Column(name = "fecha_embarque")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaEmbarque;
    
    @Column(name = "hora_embarque")
    @DateTimeFormat(iso = ISO.TIME)
    private LocalTime horaEmbarque;
    
    @Column(name = "certificado")
    private String certificado;
    
    @Column(name = "pais_destino")
    private String paisDestino;
    
    @Column(name = "esta_embarcado")
    private boolean estaEmbarcado;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenVenta")
    private List<Asignacion> asignacionList;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta")
    @ManyToOne
    private Planta idPlanta;

    public OrdenVenta() {
    }

    public OrdenVenta(String idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public OrdenVenta(String idOrdenVenta, LocalDate fechaAsignacion, String certificado, boolean estaEmbarcado) {
        this.idOrdenVenta = idOrdenVenta;
        this.fechaAsignacion = fechaAsignacion;
        this.certificado = certificado;
        this.estaEmbarcado = estaEmbarcado;
    }

    public String getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(String idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDate getFechaEmbarque() {
        return fechaEmbarque;
    }

    public void setFechaEmbarque(LocalDate fechaEmbarque) {
        this.fechaEmbarque = fechaEmbarque;
    }

    public LocalTime getHoraEmbarque() {
        return horaEmbarque;
    }

    public void setHoraEmbarque(LocalTime horaEmbarque) {
        this.horaEmbarque = horaEmbarque;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public boolean getEstaEmbarcado() {
        return estaEmbarcado;
    }

    public void setEstaEmbarcado(boolean estaEmbarcado) {
        this.estaEmbarcado = estaEmbarcado;
    }

    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Planta getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Planta idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

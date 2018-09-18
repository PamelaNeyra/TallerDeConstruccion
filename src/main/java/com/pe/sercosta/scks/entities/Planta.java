package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planta")
public class Planta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planta")
    private Integer idPlanta;
    
    @Column(name = "nombre_planta")
    private String nombrePlanta;
    
    @Column(name = "ubicacion_planta")
    private String ubicacionPlanta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planta")
    private List<PlantaProductoTerminado> plantaProductoTerminadoList;
    
    @OneToMany(mappedBy = "idPlanta")
    private List<Lote> loteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planta")
    private List<PlantaProducto> plantaProductoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planta")
    private List<PlantaPresentacion> plantaPresentacionList;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "planta")
    private Constantes constantes;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planta")
    private List<PlantaSubproducto> plantaSubproductoList;
    
    @OneToMany(mappedBy = "idPlanta")
    private List<OrdenRetiro> ordenRetiroList;
    
    @OneToMany(mappedBy = "idPlanta")
    private List<Usuario> usuarioList;
    
    @OneToMany(mappedBy = "idPlanta")
    private List<Muestra> muestraList;
   
    @OneToMany(mappedBy = "idPlanta")
    private List<OrdenVenta> ordenVentaList;

    public Planta() {
    }

    public Planta(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public Planta(Integer idPlanta, String nombrePlanta, String ubicacionPlanta) {
        this.idPlanta = idPlanta;
        this.nombrePlanta = nombrePlanta;
        this.ubicacionPlanta = ubicacionPlanta;
    }

    public Integer getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public void setNombrePlanta(String nombrePlanta) {
        this.nombrePlanta = nombrePlanta;
    }

    public String getUbicacionPlanta() {
        return ubicacionPlanta;
    }

    public void setUbicacionPlanta(String ubicacionPlanta) {
        this.ubicacionPlanta = ubicacionPlanta;
    }

    public List<PlantaProductoTerminado> getPlantaProductoTerminadoList() {
        return plantaProductoTerminadoList;
    }

    public void setPlantaProductoTerminadoList(List<PlantaProductoTerminado> plantaProductoTerminadoList) {
        this.plantaProductoTerminadoList = plantaProductoTerminadoList;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    public List<PlantaProducto> getPlantaProductoList() {
        return plantaProductoList;
    }

    public void setPlantaProductoList(List<PlantaProducto> plantaProductoList) {
        this.plantaProductoList = plantaProductoList;
    }

    public List<PlantaPresentacion> getPlantaPresentacionList() {
        return plantaPresentacionList;
    }

    public void setPlantaPresentacionList(List<PlantaPresentacion> plantaPresentacionList) {
        this.plantaPresentacionList = plantaPresentacionList;
    }

    public Constantes getConstantes() {
        return constantes;
    }

    public void setConstantes(Constantes constantes) {
        this.constantes = constantes;
    }

    public List<PlantaSubproducto> getPlantaSubproductoList() {
        return plantaSubproductoList;
    }

    public void setPlantaSubproductoList(List<PlantaSubproducto> plantaSubproductoList) {
        this.plantaSubproductoList = plantaSubproductoList;
    }

    public List<OrdenRetiro> getOrdenRetiroList() {
        return ordenRetiroList;
    }

    public void setOrdenRetiroList(List<OrdenRetiro> ordenRetiroList) {
        this.ordenRetiroList = ordenRetiroList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Muestra> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra> muestraList) {
        this.muestraList = muestraList;
    }

    public List<OrdenVenta> getOrdenVentaList() {
        return ordenVentaList;
    }

    public void setOrdenVentaList(List<OrdenVenta> ordenVentaList) {
        this.ordenVentaList = ordenVentaList;
    }
    
}

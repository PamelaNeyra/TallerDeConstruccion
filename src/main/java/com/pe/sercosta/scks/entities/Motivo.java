package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "motivo")
public class Motivo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivo")
    private Integer idMotivo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "idMotivo")
    private List<OrdenRetiro> ordenRetiroList;

    public Motivo() {
    }

    public Motivo(Integer idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Motivo(Integer idMotivo, String descripcion) {
        this.idMotivo = idMotivo;
        this.descripcion = descripcion;
    }

    public Integer getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Integer idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<OrdenRetiro> getOrdenRetiroList() {
        return ordenRetiroList;
    }

    public void setOrdenRetiroList(List<OrdenRetiro> ordenRetiroList) {
        this.ordenRetiroList = ordenRetiroList;
    }
    
}

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
@Table(name = "tipo_contenedor")
public class TipoContenedor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contenedor")
    private Integer idTipoContenedor;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "idTipoContenedor")
    private List<Presentacion> presentacionList;

    public TipoContenedor() {
    }

    public TipoContenedor(Integer idTipoContenedor) {
        this.idTipoContenedor = idTipoContenedor;
    }

    public TipoContenedor(Integer idTipoContenedor, String descripcion) {
        this.idTipoContenedor = idTipoContenedor;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoContenedor() {
        return idTipoContenedor;
    }

    public void setIdTipoContenedor(Integer idTipoContenedor) {
        this.idTipoContenedor = idTipoContenedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Presentacion> getPresentacionList() {
        return presentacionList;
    }

    public void setPresentacionList(List<Presentacion> presentacionList) {
        this.presentacionList = presentacionList;
    }
    
}

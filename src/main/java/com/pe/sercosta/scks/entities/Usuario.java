package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "activo")
    private boolean activo;
    
    @ManyToMany(mappedBy = "usuarioList", fetch = FetchType.EAGER)
    private List<Rol> rolList;
    
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta")
    @ManyToOne
    private Planta idPlanta;

    public Usuario() {
    }

    public Usuario(String username) {
        this.username = username;
    }

    public Usuario(String username, String password, boolean activo) {
        this.username = username;
        this.password = password;
        this.activo = activo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public Planta getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Planta idPlanta) {
        this.idPlanta = idPlanta;
    }
    
}

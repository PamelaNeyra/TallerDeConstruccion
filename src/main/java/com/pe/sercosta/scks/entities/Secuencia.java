package com.pe.sercosta.scks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secuencia")
public class Secuencia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "nombre_tabla")
    private String nombreTabla;

    @Column(name = "numero_actual")
    private int numeroActual;

    public Secuencia() {
    }

    public Secuencia(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public Secuencia(String nombreTabla, int numeroActual) {
        this.nombreTabla = nombreTabla;
        this.numeroActual = numeroActual;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numeroActual) {
        this.numeroActual = numeroActual;
    }
    
}

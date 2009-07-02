package com.losalpes.persistence.entity;

import java.io.Serializable;

/**
 * POJO de Experiencia
 * @author Camilo Alvarez Duran
 */
public class ExperienciaVendendor implements Serializable {

    private Integer id;
    private String nombreEmpesa;
    private String cargo;
    private String descripcion;
    private int anio;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreEmpesa() {
        return nombreEmpesa;
    }

    public void setNombreEmpesa(String nombreEmpesa) {
        this.nombreEmpesa = nombreEmpesa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
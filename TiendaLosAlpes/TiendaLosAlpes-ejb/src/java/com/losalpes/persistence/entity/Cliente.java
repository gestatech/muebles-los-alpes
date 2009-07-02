package com.losalpes.persistence.entity;

/**
 * POJO de Cliente
 * @author Memo Toro
 */
public class Cliente {

    private String nombres;
    private TipoDocumento tipoDocumento;
    private int numeroDocumento;
    private String pais;
    private String departamento;
    private String direccion;
    private String ciudadResidencia;
    private String email;
    private String profesion;
    private int telefonoResidencia;
    private long telefonoCelular;

    /** Crea una nueva instancia de Cliente */
    public Cliente() {
    }

    public Cliente(String nombres, TipoDocumento tipoDocumento, int numeroDocumento, String pais, String departamento, String direccion, String ciudadResidencia, String email, String profesion, int telefonoResidencia, long telefonoCelular) {
        this.nombres = nombres;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.pais = pais;
        this.departamento = departamento;
        this.direccion = direccion;
        this.ciudadResidencia = ciudadResidencia;
        this.email = email;
        this.profesion = profesion;
        this.telefonoResidencia = telefonoResidencia;
        this.telefonoCelular = telefonoCelular;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public long getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(long telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public int getTelefonoResidencia() {
        return telefonoResidencia;
    }

    public void setTelefonoResidencia(int telefonoResidencia) {
        this.telefonoResidencia = telefonoResidencia;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
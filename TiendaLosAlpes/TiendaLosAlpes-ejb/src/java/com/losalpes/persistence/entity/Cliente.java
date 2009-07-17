package com.losalpes.persistence.entity;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/**
 * POJO de Cliente
 * @author Memo Toro
 */
@Entity
public class Cliente implements Serializable{

    private String nombres;
    private String tipoDocumento;
    private int numeroDocumento;
    private String direccion;
    private TipoPais pais;
    private TipoDepartamento departamento;
    private TipoCiudad ciudadResidencia;
    private String email;
    private String profesion;
    private int telefonoResidencia;
    private long telefonoCelular;
    private Usuario usuario;

    /** Crea una nueva instancia de Cliente */
    public Cliente() {}
    
    @Id
    @Column(name = "clienudo")
    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    @OneToOne(cascade={CascadeType.ALL},mappedBy="cliente")
    @JoinColumn(name="clieusu")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="cliecire")
    public TipoCiudad getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(TipoCiudad ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="cliedept")
    public TipoDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="cliepais")
    public TipoPais getPais() {
        return pais;
    }

    public void setPais(TipoPais pais) {
        this.pais = pais;
    }

    @Column(name="cliedire")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name="clieemai")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="clienomb")
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Column(name="clieprof")
    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Column(name="cliecelu")
    public long getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(long telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    @Column(name="clietere")
    public int getTelefonoResidencia() {
        return telefonoResidencia;
    }

    public void setTelefonoResidencia(int telefonoResidencia) {
        this.telefonoResidencia = telefonoResidencia;
    }

    @Column(name="clietido")
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
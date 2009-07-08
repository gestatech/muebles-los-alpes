package com.losalpes.persistence.entity;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoDocumento;
import com.losalpes.enums.TipoPais;
import java.util.List;
/**
 * POJO de Cliente
 * @author Memo Toro
 */
public class Cliente {

    private String nombres;
    private TipoDocumento tipoDocumento;
    private int numeroDocumento;
    private String direccion;
    private TipoPais pais;
    private TipoDepartamento departamento;
    private TipoCiudad ciudadResidencia;
    private String email;
    private String profesion;
    private int telefonoResidencia;
    private long telefonoCelular;
    private List<Venta> ventas;
    /** Crea una nueva instancia de Cliente */
    public Cliente() {
    }
    /** Crea una nueva instancia de Cliente pasandole valores */
    public Cliente(String nombres, TipoDocumento tipoDocumento, int numeroDocumento, String direccion, TipoPais pais, TipoDepartamento departamento, TipoCiudad ciudadResidencia, String email, String profesion, int telefonoResidencia, long telefonoCelular, List<Venta> ventas) {
        this.nombres = nombres;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.direccion = direccion;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudadResidencia = ciudadResidencia;
        this.email = email;
        this.profesion = profesion;
        this.telefonoResidencia = telefonoResidencia;
        this.telefonoCelular = telefonoCelular;
        this.ventas = ventas;
    }

    public TipoCiudad getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(TipoCiudad ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public TipoDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }

    public TipoPais getPais() {
        return pais;
    }

    public void setPais(TipoPais pais) {
        this.pais = pais;
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

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    
    public void setAsignarVenta(Venta venta){
        this.ventas.add(venta);
    }
}
package com.losalpes.persistence.entity;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * POJO de Venta
 * @author Memo Toro
 */
@Entity
@Table(name="venta")
public class Venta implements Serializable{
    
    private String referencia;
    private double valor;
    private String descripcion;
    private String numeroTarjeta;
    private int codigoSeguridad;
    private String fechaExpiracionTarjeta;
    private int cuotas;
    private String fechaGeneracion;
    private List<DetalleVenta> detalleVenta;
    private TipoPais pais;
    private TipoDepartamento departamento;
    private TipoCiudad ciudad;
    private int idCliente;
    /** Crea una nueva instancia de Venta */
    public Venta() {}
    
    @Id
    @Column(name="ventrefe")
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @OneToMany(mappedBy="venta",cascade={CascadeType.PERSIST})
    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    @Column(name="ventcose")
    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    @Column(name="ventcuot")
    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    @Column(name="ventdesc")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="ventfeex")
    public String getFechaExpiracionTarjeta() {
        return fechaExpiracionTarjeta;
    }

    public void setFechaExpiracionTarjeta(String fechaExpiracionTarjeta) {
        this.fechaExpiracionTarjeta = fechaExpiracionTarjeta;
    }

    @Column(name="ventfege")
    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    @Column(name="ventnuta")
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Column(name="ventvalo")
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="ventciud")
    public TipoCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(TipoCiudad ciudad) {
        this.ciudad = ciudad;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="ventdept")
    public TipoDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="ventpais")
    public TipoPais getPais() {
        return pais;
    }

    public void setPais(TipoPais pais) {
        this.pais = pais;
    }
    
    @Column(name="ventidcl")
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
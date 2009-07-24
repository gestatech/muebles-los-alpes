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
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Memo Toro
 */
@Entity
@Table(name="venta")
public class Venta implements Serializable{
    /**
     * String referencia de la venta
     */
    private String referencia;
    /**
     * Double con el valor de venta.
     */
    private double valor;
    /**
     * String con la descripción de venta.
     */
    private String descripcion;
    /**
     * String con el numero de tarjeta
     */
    private String numeroTarjeta;
    /**
     * Int con el codigo de seguridad.
     */
    private int codigoSeguridad;
    /**
     * String con la fecha de expiración de la tarjeta.
     */
    private String fechaExpiracionTarjeta;
    /**
     * Int con las cuotas de pago.
     */
    private int cuotas;
    /**
     * String con la fecha de generación de compra.
     */
    private String fechaGeneracion;
    /**
     * List Listado de ventas asociado a la venta.
     */
    private List<DetalleVenta> detalleVenta;
    /**
     * TipoPais para el pais de entrega.
     */
    private TipoPais pais;
    /**
     * TIpoDepartamento con el departamento de entrega.
     */
    private TipoDepartamento departamento;
    /**
     * TipoCiudad con la ciudad de entrega.
     */
    private TipoCiudad ciudad;
    /**
     * Int Identificador del cliente.
     */
    private int idCliente;
    /** Crea una nueva instancia de Venta */
    public Venta() {}
    /**
     * Método que retorna el identificador de la venta.
     * Anotado con @Id para ser la llave primaria al persistir.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con identificador de venta.
     */
    @Id
    @Column(name="ventrefe")
    public String getReferencia() {
        return referencia;
    }
    /**
     * Mëtodo para asignar la referencia.
     * @param referencia Referencia de venta.
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @OneToMany(mappedBy="venta",cascade={CascadeType.PERSIST})
    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }
    /**
     * Mëtodo para asignar el listado de detalles
     * @param detalleVenta Listado de detalles de venta.
     */
    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    @Column(name="ventcose")
    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }
    /**
     * Mëtodo para asignar el código de seguridad de la tarjeta.
     * @param codigoSeguridad Codigo de seguridad de la tarjeta
     */
    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
    /**
     * Método para obtener las cuotas de la venta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return int Cuotas de pago.
     */
    @Column(name="ventcuot")
    public int getCuotas() {
        return cuotas;
    }
    /**
     * Mëtodo para asignar las cuotas de pago.
     * @param cuotas Cuotas de Pago.
     */
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }
    /**
     * Método para obtener la descripción de la venta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Descripción de la Venta.
     */
    @Column(name="ventdesc")
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Mëtodo para asignar la descripción
     * @param descripcion Descripción de la venta.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Método para obtener la fecha de expiración de la tarjeta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String fecha de expiración de la tarjeta.
     */
    @Column(name="ventfeex")
    public String getFechaExpiracionTarjeta() {
        return fechaExpiracionTarjeta;
    }
    /**
     * Mëtodo para asignar la fecha de expiración de la tarjeta.
     * @param fechaExpiracionTarjeta Fecha de expiración de la tarjeta.
     */
    public void setFechaExpiracionTarjeta(String fechaExpiracionTarjeta) {
        this.fechaExpiracionTarjeta = fechaExpiracionTarjeta;
    }
    /**
     * Método para obtener la fecha de compra
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String fecha de compra
     */
    @Column(name="ventfege")
    public String getFechaGeneracion() {
        return fechaGeneracion;
    }
    /**
     * Mëtodo para asignar la fecha de generacion de venta.
     * @param fechaGeneracion Fecha de generacion de venta.
     */
    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    /**
     * Método para obtener el numero de tarjeta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Numero de tarjeta.
     */
    @Column(name="ventnuta")
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    /**
     * Mëtodo para asignar el numero de tarjetaio.
     * @param numeroTarjeta Número de tarjetaio.
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    /**
     * Método para obtener el valor de la venta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return double Valor de la venta.
     */
    @Column(name="ventvalo")
    public double getValor() {
        return valor;
    }
    /**
     * Mëtodo para asignar el valor de la venta.
     * @param valor Valor de la venta.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    /**
     * Método para obtener la ciudad de entrega.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoCiudad Tipo de ciudad de entrega.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="ventciud")
    public TipoCiudad getCiudad() {
        return ciudad;
    }
    /**
     * Mëtodo para asignar la ciudad de entrega.
     * @param ciudad Ciudad de entrega.
     */
    public void setCiudad(TipoCiudad ciudad) {
        this.ciudad = ciudad;
    }
    /**
     * Método para obtener el departamento de entrega.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoDepartamento Tipo de departamentode entrega.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="ventdept")
    public TipoDepartamento getDepartamento() {
        return departamento;
    }
    /**
     * Mëtodo para asignar el departamento de entrega.
     * @param departamento Departamento de entrega.
     */
    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }
    /**
     * Método para obtener el pais de entrega.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoPais Tipo de pais de entrega.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="ventpais")
    public TipoPais getPais() {
        return pais;
    }
    /**
     * Mëtodo para asignar el Pais de entrega.
     * @param pais Pais de entrega.
     */
    public void setPais(TipoPais pais) {
        this.pais = pais;
    }
    /**
     * Método para obtener el identificador del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return int Id del cliente.
     */
    @Column(name="ventidcl")
    public int getIdCliente() {
        return idCliente;
    }
    /**
     * Mëtodo para asignar el id del cliente.
     * @param idCliente Identificador del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
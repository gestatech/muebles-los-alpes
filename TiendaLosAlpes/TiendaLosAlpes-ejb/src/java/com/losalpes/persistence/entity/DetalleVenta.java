package com.losalpes.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * POJO de Detalle Venta.
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Memo Toro
 */
@Entity
@Table(name="detallventa")
public class DetalleVenta implements Serializable{
    /**
     * Int para el id del detalle de la venta.
     */
    private int idDetalleVenta;
    /**
     * Mueble indicando el mueble vendido.
     */
    private Mueble muebleVendido;
    /**
     * Int con la cantidad de muebles asignados.
     */
    private int cantidadVenta;
    /**
     * Double con la cantidad de precio de venta.
     */
    private double precioVenta;
    /**
     * Venta asociada al detalle de venta.
     */
    private Venta venta;
    /** Crea una nueva instancia de DetalleVenta */
    public DetalleVenta() {}
    /**
     * Método para obtener el id del detalle de venta.
     * Anotado con @Id para ser la llave primaria al persistir.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * Anotado con @GeneratedValue y @SequenceGenerator para crear la secuencia de valores.
     * @return int Identificador del detalle de venta.
     */
    @Id
    @Column(name="deveidde")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_DETALLE")
    @SequenceGenerator(name="SEQ_DETALLE",allocationSize=1,sequenceName="detalle_seq")
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }
    /**
     * Método para asignar el detalle de venta.
     * @param idDetalleVenta
     */
    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }
    /**
     * Método para obtener el mueble vendido.
     * Anotado con @OneToOne al ser el atributo de relación entre mueble y detalle venta.
     * Anotado con @JoinColumn para indicar la columna de enlace de la relación.
     * @return Mueble Mueble vendido.
     */
    @OneToOne
    @JoinColumn(name="muebrefe")
    public Mueble getMuebleVendido() {
        return muebleVendido;
    }
    /**
     * Método para asignar el mueble vendido
     * @param muebleVendido Mueble vendido
     */
    public void setMuebleVendido(Mueble muebleVendido) {
        this.muebleVendido = muebleVendido;
    }
    /**
     * Método que retrona la venta del detalle de venta.
     * Anotado con @ManyToOne para indicar la relación bidireccional enter venta y detalles.
     * Anotado con @JoinColumn para indicar la columna de enlace de la relación.
     * @return Venta la venta asociada al mueble.
     */
    @ManyToOne(optional=false)
    @JoinColumn(name="devevent")
    public Venta getVenta() {
        return venta;
    }
    /**
     * Método para asignar la venta del detalle del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @param venta Venta del detalle de venta.
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    /**
     * Método para retornar la cantidad de venta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return int Cantidad de venta.
     */
    @Column(name="devecave")
    public int getCantidadVenta() {
        return cantidadVenta;
    }
    /**
     * Método para asignar la cantidad de venta.
     * @param cantidadVenta Cantidad de Venta.
     */
    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
    /**
     * Método para obtener el precio de venta.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return double Precio de la venta.
     */
    @Column(name="deveprve")
    public double getPrecioVenta() {
        return precioVenta;
    }
    /**
     * Método para asignar el precio de la venta.
     * @param precioVenta Valor a precio de venta.
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
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
 * @author Memo Toro
 */
@Entity
@Table(name="detallventa")
public class DetalleVenta implements Serializable{

    private int idDetalleVenta;
    private Mueble muebleVendido;
    private int cantidadVenta;
    private double precioVenta;
    private Venta venta;
    /** Crea una nueva instancia de DetalleVenta */
    public DetalleVenta() {}

    @Id
    @Column(name="deveidde")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_DETALLE")
    @SequenceGenerator(name="SEQ_DETALLE",allocationSize=1,sequenceName="detalle_seq")
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    @OneToOne
    @JoinColumn(name="muebrefe")
    public Mueble getMuebleVendido() {
        return muebleVendido;
    }

    public void setMuebleVendido(Mueble muebleVendido) {
        this.muebleVendido = muebleVendido;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="devevent")
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Column(name="devecave")
    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
    
    @Column(name="deveprve")
    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
package com.losalpes.persistence.entity;
/**
 * POJO de Detalle Venta.
 * @author Memo Toro
 */
public class DetalleVenta {

    private static int idVenta;
    private Mueble muebleVendido;
    private int cantidadVenta;
    private double precioVenta;
    /** Crea una nueva instancia de DetalleVenta */
    public DetalleVenta() {
    }

    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Mueble getMuebleVendido() {
        return muebleVendido;
    }

    public void setMuebleVendido(Mueble muebleVendido) {
        this.muebleVendido = muebleVendido;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setId(){
        idVenta++;
    }
}
package com.losalpes.reportes.pojos;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.persistence.entity.Mueble;
/**
 * POJO de reporte de producto mas vendido.
 * @author Memo Toro
 */
public class ReporteMasVendido {
    /**
     * TipoPais para almacenar el pais.
     */
    private TipoPais pais;
    /**
     * TipoDepartamento para el departamento de entrega.
     */
    private TipoDepartamento depto;
    /**
     * TipoCiudad para la ciudad d eentrega.
     */
    private TipoCiudad ciudad;
    /**
     * Integer cantidad para la cantidad asignada en la venta como maxima.
     */
    private int cantidad;
    /**
     * Mueble para relacionar el mueble mas vendido.
     */
    private Mueble mueble;
    /** Crea una nueva instancia de ReporteMasVendido */
    public ReporteMasVendido() {}
    /**
     * Método para retornar la cantidad de venta.
     * @return int Cantidad de venta.
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Método para asignar la cantidad de venta.
     * @param cantidad Cantidad de Venta.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * Método para obtener el mueble vendido.
     * @return Mueble Mueble vendido.
     */
    public Mueble getMueble() {
        return mueble;
    }
    /**
     * Método para asignar el mueble vendido
     * @param mueble Mueble vendido
     */
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }
    /**
     * Método para obtener la ciudad de entrega.
     * @return TipoCiudad Tipo de ciudad de entrega.
     */
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
     * @return TipoDepartamento Tipo de departamentode entrega.
     */
    public TipoDepartamento getDepto() {
        return depto;
    }
    /**
     * Mëtodo para asignar el departamento de entrega.
     * @param depto Departamento de entrega.
     */
    public void setDepto(TipoDepartamento depto) {
        this.depto = depto;
    }
    /**
     * Método para obtener el pais de entrega.
     * @return TipoPais Tipo de pais de entrega.
     */
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
}
package com.losalpes.persistence.entity;
/**
 * POJO de Promociones
 * @author Kerlyn Hans
 * @author Memo Toro
 */
public class Promocion {
    private String nombre;

    private String muebleRef;

    private String fechaInicio;

    private String fechaFin;
    /** Crea una nueva instancia de Promocion vacia*/
    public void Promocion(){
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getMueble() {
        return muebleRef;
    }

    public void setMueble(String muebleRef) {
        this.muebleRef = muebleRef;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
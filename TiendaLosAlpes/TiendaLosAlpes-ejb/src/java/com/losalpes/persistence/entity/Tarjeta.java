/**
 * POJO de Cliente
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Hans Escallon
 */
package com.losalpes.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

public class Tarjeta implements Serializable {   

    /**
     * String con el numero de tarjeta
     */
    private String numeroTarjeta;
    /**
     * Cliente dueño de la tarjeta
     */
    private int clienudo;
    /**
    * Int con el codigo de seguridad.
     */    
    private double monto;

    /**
     * Int con el codigo de seguridad.
     */    
    private int codigoSeguridad;
    /**
     * String con la fecha de expiración de la tarjeta.
     */    
    private String fechaExpiracionTarjeta;

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getFechaExpiracionTarjeta() {
        return fechaExpiracionTarjeta;
    }

    public void setFechaExpiracionTarjeta(String fechaExpiracionTarjeta) {
        this.fechaExpiracionTarjeta = fechaExpiracionTarjeta;
    }

    public int getClienudo() {
        return clienudo;
    }

    public void setClienudo(int clienudo) {
        this.clienudo = clienudo;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Mëtodo para obtener el monto de la tarjeta de credito
     */
        public double getMonto() {
        return monto;
    }
     /**
     * Mëtodo para asignar el monto a la tarjeta de credito
     * @param monto Variable de tipo double.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }


}
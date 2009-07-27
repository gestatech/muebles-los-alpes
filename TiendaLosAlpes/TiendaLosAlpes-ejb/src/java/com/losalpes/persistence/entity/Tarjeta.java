package com.losalpes.persistence.entity;

import java.io.Serializable;
/**
 * POJO de Cliente
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Hans Escallon
 */
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
    /** Crea una nueva instancia de Tarjeta */
    public Tarjeta() {}
    /**
     * Método para retornar el Codigo de Seguridad
     * @return int Codito de seguridad
     */
    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }
    /**
     * Método para asignar el código de seguridad.
     * @param codigoSeguridad codigo de seguridad.
     */
    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
    /**
     * Método para obtener la fecha de expiracion de la tarjeta.
     * @return String fecha de expiracion.
     */
    public String getFechaExpiracionTarjeta() {
        return fechaExpiracionTarjeta;
    }
    /**
     * Método para asignar la fecha de expiracion.
     * @param fechaExpiracionTarjeta fecha de expiracion de la tarjeta.
     */
    public void setFechaExpiracionTarjeta(String fechaExpiracionTarjeta) {
        this.fechaExpiracionTarjeta = fechaExpiracionTarjeta;
    }
    /**
     * Método para obtener el identificador del cliente.
     * @return int id del cliente.
     */
    public int getClienudo() {
        return clienudo;
    }
    /**
     * Método para asignar el id del cliente.
     * @param clienudo id del cliente.
     */
    public void setClienudo(int clienudo) {
        this.clienudo = clienudo;
    }
    /**
     * Método para obtener el numero de tarjeta.
     * @return String numero de tarjeta.
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    /**
     * Método asignar el numero de tarjeta.
     * @param numeroTarjeta numero de tarjeta.
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    /**
     * Mëtodo para obtener el monto de la tarjeta de credito
     * @return double con el monto de la tarjeta.
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
package com.losalpes.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * POJO de Promociones.
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Kerlyn Hans
 * @author Memo Toro
 */
@Entity
@Table(name="promocion")
public class Promocion implements Serializable{
    /**
     * Strinf con el identificador de la promocion.
     */
    private String idPromo;
    /**
     * String con el nombre de la promoción.
     */
    private String nombre;
    /**
     * String con la referencia y el nombre del mueble.
     */
    private String muebleReferencia;
    /**
     * String con la fecha de inicio.
     */
    private String fechaInicio;
    /**
     * String con la fecha de fin.
     */
    private String fechaFin;
    /** Crea una nueva instancia de Promocion vacia*/
    public void Promocion(){}
    /**
     * Método que retorna el id de la promocion.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * Anotado con @GeneratedValue y @SequenceGenerator para crear la secuencia de valores.
     * @return String con el identificador.
     */
    @Id
    @Column(name="promidpr")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PROMO")
    @SequenceGenerator(name="SEQ_PROMO",allocationSize=1,sequenceName="promocion_seq")
    public String getIdPromo() {
        return idPromo;
    }
    /**
     * Mëtodo para asignar el id de promocion.
     * @param idPromo Identificador promocion.
     */
    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }
    /**
     * Método para obtener la referencia del mueble
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Referencia del Mueble.
     */
    @Column(name="prommure")
    public String getMuebleReferencia() {
        return muebleReferencia;
    }
    /**
     * Mëtodo para asignar el mueble y su referencia.
     * @param muebleReferencia Referencia del mueble.
     */
    public void setMuebleReferencia(String muebleReferencia) {
        this.muebleReferencia = muebleReferencia;
    }
    /**
     * Método para obtener la fecha de fin
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Fecha fin de la promocion..
     */
    @Column(name="promfefi")
    public String getFechaFin() {
        return fechaFin;
    }
    /**
     * Mëtodo para asignar la fecha de fin.
     * @param fechaFin Fecha de finalizacion de la promocion.
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    /**
     * Método para obtener la fecha de inicio.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String fecha de inicio de la promoción.
     */
    @Column(name="promfein")
    public String getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Mëtodo para asignar la fehca de inicion.
     * @param fechaInicio Fecha de inicio de la promocion.
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Método para obtener el nombre de la promoción.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Nombre de la promoción.
     */
    @Column(name="promnomb")
    public String getNombre() {
        return nombre;
    }
    /**
     * Mëtodo para asignar el nombre de la promocion.
     * @param nombre Nombre de la promocion.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
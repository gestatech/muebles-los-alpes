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
 * POJO de Promociones
 * @author Kerlyn Hans
 * @author Memo Toro
 */
@Entity
@Table(name="promocion")
public class Promocion implements Serializable{

    private String idPromo;
    private String nombre;
    private String muebleReferencia;
    private String fechaInicio;
    private String fechaFin;
    /** Crea una nueva instancia de Promocion vacia*/
    public void Promocion(){}
    @Id
    @Column(name="promidpr")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PROMO")
    @SequenceGenerator(name="SEQ_PROMO",allocationSize=1,sequenceName="promocion_seq")
    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }
    @Column(name="prommure")
    public String getMuebleReferencia() {
        return muebleReferencia;
    }

    public void setMuebleReferencia(String muebleReferencia) {
        this.muebleReferencia = muebleReferencia;
    }
    @Column(name="promfefi")
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    @Column(name="promfein")
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    @Column(name="promnomb")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
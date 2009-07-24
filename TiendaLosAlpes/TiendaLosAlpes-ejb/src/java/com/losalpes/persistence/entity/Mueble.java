package com.losalpes.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * POJO de Mueble
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Memo Toro
 */
@Entity
@Table(name="mueble")
public class Mueble implements Serializable {
    /**
     * String para la referencia del mueble
     */
    private String referencia;
    /**
     * String para el nombre del mueble.
     */
    private String nombre;
    /**
     * String para la descripción del mueble.
     */
    private String descripcion;
    /**
     * String con el tipo de de mueble.
     */
    private String tipo;
    /**
     * String con el material del mueble.
     */
    private String material;
    /**
     * Double con el alto del mueble.
     */
    private double alto;
    /**
     * Double con el ancho del mueble.
     */
    private double ancho;
    /**
     * Double con la profundidad del mueble.
     */
    private double profundidad;
    /**
     * String con el color del mueble.
     */
    private String color;
    /**
     * Double con el peso del mueble.
     */
    private double peso;
    /**
     * String con la ruta de la foto.
     */
    private String foto;
    /**
     * Double con el precio del mueble.
     */
    private double precio;
    /**
     * Int con el numero de unidades del mueble.
     */
    private int cantidad;

    /** Crea una nueva instancia de Mueble */
    public Mueble() {}
    /**
     * Método que retorna la referencia del mueble.
     * Anotado con @Id para ser la llave primaria al persistir.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con la referencia.
     */
    @Id
    @Column(name="muebrefe",length=4)
    public String getReferencia() {
        return referencia;
    }
    /**
     * Mëtodo para asignar la referencia.
     * @param referencia Referencia del mueble.
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    /**
     * Método para obtener el alto del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return double Alto del mueble.
     */
    @Column(name="muebalto")
    public double getAlto() {
        return alto;
    }
    /**
     * Método para asignar el alto del mueble.
     * @param alto Altor del mueble.
     */
    public void setAlto(double alto) {
        this.alto = alto;
    }
    /**
     * Método para obtener el ancho del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return double Ancho del mueble.
     */
    @Column(name="muebanch")
    public double getAncho() {
        return ancho;
    }
    /**
     * Método para asignar el ancho del mueble.
     * @param ancho Ancho del mueble.
     */
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    /**
     * Método para obtener el color del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con el color del mueble.
     */
    @Column(name="muebcolo")
    public String getColor() {
        return color;
    }
    /**
     * Método para asignar el color del mueble.
     * @param color Color del mueble.
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * Método para obtener la descripción del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con la descripción del mueble.
     */
    @Column(name="muebdesc")
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Método para asignar la descripción del mueble.
     * @param descripcion Descripción del mueble.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Método para retornar el material del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con el material del mueble.
     */
    @Column(name="muebmate")
    public String getMaterial() {
        return material;
    }
    /**
     * Método para asignar el material del mueble.
     * @param material Material del mueble.
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    /**
     * Método para retornar el nombre del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Nombre del Mueble.
     */
    @Column(name="muebnomb")
    public String getNombre() {
        return nombre;
    }
    /**
     * Método para asignar el nombre del mueble.
     * @param nombre Material del mueble.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método para retornar el peso del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return Double Peso del Mueble.
     */
    @Column(name="muebpeso")
    public double getPeso() {
        return peso;
    }
    /**
     * Método para asignar el peso del mueble.
     * @param peso Peso del mueble.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }
    /**
     * Método para retornar la produndidad del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return Double Profundidad del Mueble.
     */
    @Column(name="muebprof")
    public double getProfundidad() {
        return profundidad;
    }
    /**
     * Método para asignar la produnidad del mueble.
     * @param produnidad Produnidad del mueble.
     */
    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }
    /**
     * Método para retornar el tipo del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Tipo del Mueble.
     */
    @Column(name="muebtipo")
    public String getTipo() {
        return tipo;
    }
    /**
     * Método para asignar el tipo del mueble.
     * @param tipo Tipo de mueble.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Método para retornar la foto del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String Foto del Mueble.
     */
    @Column(name="muebfoto")
    public String getFoto() {
        return foto;
    }
    /**
     * Método para asignar la foto del mueble.
     * @param foto Foto del mueble.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }
    /**
     * Método para retornar la cantidad del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return Int Cantidad del Muebles.
     */
    @Column(name="muebcant")
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Método para asignar la cantidad del mueble
     * @param cantidad Cantidad del mueble.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * Método para retornar el precio del mueble.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return Double Precio del Mueble.
     */
    @Column(name="muebprec")
    public double getPrecio() {
        return precio;
    }
    /**
     * Método para asignar el precio del mueble.
     * @param precio Precio del mueble.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
package com.losalpes.persistence.entity;

/**
 * POJO de Mueble
 * @author Memo Toro
 */
public class Mueble {

    private String referencia;
    private String nombre;
    private String descripcion;
    private TipoMueble tipo;
    private String material;
    private double alto;
    private double ancho;
    private double profundidad;
    private String color;
    private double peso;
    private String foto;
    private double precio;
    private int cantidad;

    /** Crea una nueva instancia de Mueble */
    public Mueble() {
    }

    /** Crea una nueva instancia de Mueble pasandole valores */
    public Mueble(String referencia, String nombre, String descripcion, TipoMueble tipo, String material, double alto, double ancho, double profundidad, String color, double peso, String foto, double precio, int cantidad ) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.material = material;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.color = color;
        this.peso = peso;
        this.foto = "/img/muebles/" + foto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public TipoMueble getTipo() {
        return tipo;
    }

    public void setTipo(TipoMueble tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = "/img/muebles/" + foto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
package com.losalpes.persistence.entity;

import com.losalpes.enums.TipoUsuario;
/**
 * POJO de Usuario
 * @author Memo Toro
 * @author Hans Escallon
 */
public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private TipoUsuario tipoUsuario;
    private Cliente cliente;
    /** Crea una nueva instancia de Usuario vacia*/
    public Usuario() {
    }
    /** Crea una nueva instancia de Usuario pasandole los datos al constructor*/
    public Usuario(String nombreUsuario,String contrasenia, TipoUsuario tipoUsuario, Cliente cliente) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
        this.cliente = cliente;
    }
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
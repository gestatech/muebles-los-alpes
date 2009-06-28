package com.losalpes.persistence.entity;

/**
 * POJO de Usuario
 * @author Memo Toro
 * @author Camilo Alvarez
 */
public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private TipoUsuario tipoUsuario;

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
}
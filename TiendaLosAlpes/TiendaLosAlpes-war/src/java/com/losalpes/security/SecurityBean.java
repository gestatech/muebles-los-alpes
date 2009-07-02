package com.losalpes.security;

import com.losalpes.persistence.entity.TipoUsuario;
import com.losalpes.persistence.entity.Usuario;
import javax.ejb.EJB;

/**
 * Managed Bean para controlar la autenticación de usuarios. 
 * Dicho carrito conoce las interfaces de varios Mocl para interactuar con Ventas, Catalogo
 * @author Memo Toro
 */
public class SecurityBean {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz ISecurityService para los usuarios.
     */
    @EJB
    private ISecurityService securityService;
    /**
     * Variable String para el nombre de usuario.
     */
    private String nombreUsuario;
    /**
     * Variable String para el password del usuario.
     */
    private String contrasenia;

    /** Crea una nueva instancia de SecurityBean */
    public SecurityBean() {
    }
    /**
     * Método para obtener el tipo de usuario. El rol de usuario y poder redireccionar.
     * @return String Variable de tipo String con el redireccionamiento.
     */
     public String login(){
        Usuario usuario = securityService.login(nombreUsuario, contrasenia);
        if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
            return "admin";
        }
        if(usuario.getTipoUsuario() == TipoUsuario.CLIENTE){
            return "cliente";
        }
        else{
            return "login";
        }
    }
    /**
     * Método par obtener el password del usuario.
     * @return String variable tipo String con el password.
     */
    public String getContrasenia() {
        return contrasenia;
    }
   /**
     * Método para asignar el password de usuario.
     * @param String con la contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
   /**
     * Método para obtener el nombre de usuario.
     * @return String Variable de tipo String para el Nombre de Usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
   /**
     * Método para asignar el nombre de usuario.
     * @param String con el nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
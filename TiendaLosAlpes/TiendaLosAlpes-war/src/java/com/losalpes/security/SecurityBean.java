package com.losalpes.security;

import com.losalpes.enums.TipoUsuario;
import com.losalpes.persistence.entity.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 * Backing Bean para controlar la autenticación de usuarios.
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
        String usua = "";
        if(usuario == null){
            usua = "login";
        }
        else{
            if(usuario.getTipoUsuario().equals(TipoUsuario.CLIENTE)){
                usua = "cliente";
            }
       }
       return usua;
    }
    /**
     * Método para obtener el password del usuario.
     * @return String variable tipo String con el password.
     */
    public String getContrasenia() {
        return contrasenia;
    }
   /**
     * Método para asignar el password de usuario.
     * @param String con la contrasenia.
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
    /**
     * Método para salir de la aplicación de como clientes y como administradores y gerentes y destruiri la session.
     * @return String valor de redireccionamiento.
     */
    public void cerrarSession() {
        try {
            // Toma la sesión con el contexto Faces.
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            // Vuelve no valida la sesión.
            session.invalidate();
            // redirecciona a la pagina de admin.
            FacesContext.getCurrentInstance().getExternalContext().redirect("./menuAdmin.jsf");
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException ex) {
            Logger.getLogger(SecurityBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
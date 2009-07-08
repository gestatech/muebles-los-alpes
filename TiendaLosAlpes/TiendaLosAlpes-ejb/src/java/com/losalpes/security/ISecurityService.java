package com.losalpes.security;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Usuario;
import javax.ejb.Local;
/**
 * Interfaz con métodos de Seguridad anotada con @local para indicar la forma como acceden al bean.
 * @author Memo Toro
 */
@Local
public interface ISecurityService {
    /**
     * Método para autenticar usuarios y establecer su rol.
     * @param nombreUsuario Variable con el nombre de usuario nombreUsuario
     * @param contrasenia Variable con la contraseñia contrasenia
     * @return Usuario variable tipo Usuario
     */
    Usuario login(String usuario, String pwd);
    /** Método para asignar el cliente actualizado al usuario
     * @param cliente Variable tipo ClienteString.
    */
    public void editarUsuario(Cliente cliente);

    /**
     * Método para registrar en la sesión el cliente y usuario que realizo el login
     * @param objeto Variable tipo Object.
    */
    public void setObjetoSesion(Object objeto);
    /** Método para obtener el cliente o usuario de la sessión
     * @param clave Variable tipo String, que identifica el objeto a recuperar.
    */
    public Object getObjetoSesion(String clave);
}
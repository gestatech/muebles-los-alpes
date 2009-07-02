package com.losalpes.security;

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
}
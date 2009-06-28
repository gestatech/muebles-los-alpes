package com.losalpes.security;

import com.losalpes.persistence.entity.Usuario;

/**
 * Interfaz con métodos de Seguridad
 * @author Memo Toro
 */
public interface ISecurityService {
    /**
     * Método para autenticar usuarios y establecer su rol.
     * @param Variable con el nombre de usuario nombreUsuario
     * @param Variable con la contraseñia contrasenia
     * @return variable tipo Usuario
     */
    Usuario login(String usuario, String pwd);
}
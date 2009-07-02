package com.losalpes.security;

import com.losalpes.cliente.IClienteService;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import com.losalpes.persistence.entity.TipoUsuario;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.ventas.ICarritoService;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Session Bean que implementa la interfaz con los métodos del Autenticacion. Abnotado con @Statelss para indicar que no guarda ningun estado.
 * @author Memo Toro
 */
@Stateless
public class SecurityServiceBean implements ISecurityService {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IClienteService para los clientes.
     */
    @EJB
    private IClienteService clienteService;
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para las ventas.
     */
    @EJB
    private ICarritoService carritoService;
    /**
     * Método para autenticar usuarios y establecer su rol.
     * @param nombreUsuario Variable con el nombre de usuario nombreUsuario
     * @param contrasenia Variable con la contraseñia contrasenia
     * @return Usuario variable tipo Usuario
     */
    public Usuario login(String nombreUsuario, String contrasenia) {
        // Verifica si el nombre de usuario y la contraseñia son admin, si son, crea un usuario con perfil de administrador.
        if (nombreUsuario.equalsIgnoreCase("admin") && contrasenia.equalsIgnoreCase("admin")) {
            Usuario usAdmin = new Usuario();
            usAdmin.setNombreUsuario(nombreUsuario);
            usAdmin.setContrasenia(contrasenia);
            // Usuariocon rol de administador.
            usAdmin.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
            return usAdmin;
        }
        else {
            // Obtiene el cliente con la información de cédula del cliente colocada como nombre de usuario.
            Cliente cliente = clienteService.consultar(TipoConsultaCliente.NUMERO_DOCUMENTO, nombreUsuario);
            // Si el cliente no esta registrado.
            if(cliente==null){
                Usuario noUser = new Usuario();
                return noUser;
            }
            // Si el cliente obtenido existe y es igual al numero de cedula ingresado, se devuelve un usuario con rol de cliente.
            if(cliente.getNumeroDocumento()==(Integer.valueOf(nombreUsuario).intValue())){
                Usuario usCliente = new Usuario();
                usCliente.setNombreUsuario(nombreUsuario);
                usCliente.setContrasenia(contrasenia);
                usCliente.setTipoUsuario(TipoUsuario.CLIENTE);
                // Asigana el valor del id del cliente al carrito de compras.
                carritoService.clienteAutenticado(Integer.valueOf(nombreUsuario).intValue());                
                return usCliente;
            }
            return null;
        }
    }
}
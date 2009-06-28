package com.losalpes.security;

import com.losalpes.cliente.ClienteServiceMock;
import com.losalpes.cliente.IClienteService;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import com.losalpes.persistence.entity.TipoUsuario;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.ventas.IVentaService;
import com.losalpes.ventas.VentaServiceMock;

/**
 * Servicio Mock que implementa la interfaz con los métodos del Autenticacion.
 * @author Memo Toro
 * @author Camilo Alvarez
 */
public class SecurityServiceMock implements ISecurityService {
    /**
     * Variable de Tipo Interfaz Cliente para poder hacer la consulta del cliente en el listado al autenticarse.
     */
    private IClienteService clienteService = new ClienteServiceMock();
    /**
     * Variable de Tipo Interfaz Venta para poder obtener estableer el Id del cliente en la venta.
     */
    private IVentaService ventaService = new VentaServiceMock();
    /**
     * Método para autenticar usuarios y establecer su rol.
     * @param Variable con el nombre de usuario nombreUsuario
     * @param Variable con la contraseñia contrasenia
     * @return variable tipo Usuario
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
                // Obtiene una venta y le asigna el id del cliente como el numero de identificacion del cliente
                ventaService.obtenerVenta().setClienteId((Integer.valueOf(nombreUsuario).intValue()));
                ventaService.obtenerVenta().setNombreCompleto(cliente.getNombres());
                return usCliente;
            }
            return null;
        }
    }
}
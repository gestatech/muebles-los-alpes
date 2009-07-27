package com.losalpes.cliente.ws.client;

import org.junit.Test;
import com.losalpes.cliente.Cliente;
import com.losalpes.cliente.Tarjeta;
import com.losalpes.cliente.TipoPais;
import com.losalpes.cliente.TipoDepartamento;
import com.losalpes.cliente.TipoCiudad;
import com.losalpes.cliente.TipoUsuario;
/**
 * Clase JUnit para probar el servicio de cliente.
 * Ingresa un cliente.
 * @author Memo Toro
 */
public class ClienteWSTest {
    /** Crea una nueva instancia de ClienteWSTest */
    public ClienteWSTest() {
    }
    /**
     * Método con la lógica de probar la creación de un cliente por el web service.
     * Anotado con @Test para definir el metodo a probar.
     */
    @Test
    public void crearCliente(){
        try {
            // Invocación del web Service.
            com.losalpes.cliente.ClienteWS service = new com.losalpes.cliente.ClienteWS();
            // Llamado de la operación del Web Service
            com.losalpes.cliente.ClienteWebService port = service.getClienteWebServicePort();
            // Crea un usuario de la clase generada por el Web Service.
            com.losalpes.cliente.Usuario usuario = new com.losalpes.cliente.Usuario();
            // Creación de Tarjeta de Crédito para que se almacene y se genere el cliente.
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setNumeroTarjeta("999999999999");
            tarjeta.setCodigoSeguridad(2233);
            tarjeta.setFechaExpiracionTarjeta("02/02/2999");
            tarjeta.setMonto(100000000);
            tarjeta.setClienudo(90900903);
            // Creación del cliente para almacenarlo con su respectivo usuario y tarjeta.
            Cliente cliente = new Cliente();
            cliente.setNumeroDocumento(99999999);
            cliente.setTipoDocumento("CEDULA");
            cliente.setNombres("CLIENTE 99999999");
            cliente.setPais(TipoPais.COLOMBIA);
            cliente.setDepartamento(TipoDepartamento.DEPARTAMENTO_1);
            cliente.setCiudadResidencia(TipoCiudad.CIUDAD_1);
            cliente.setDireccion("CARRERA 9");
            cliente.setEmail("usuario9@uniandes.edu.co");
            cliente.setProfesion("INGENIERO");
            cliente.setTelefonoCelular(311999999);
            cliente.setTelefonoResidencia(9999999);
            cliente.setTarjeta(tarjeta);
            // Creación del usuario con su cliente asociado. Dicho cliente se almacena por efecto cascada de la relacion entre cliente y usuario.
            usuario.setNombreUsuario("99999999");
            usuario.setContrasenia("usuario9");
            usuario.setTipoUsuario(TipoUsuario.CLIENTE);
            usuario.setCliente(cliente);
            // Invocacion de la operacion del Web Service.
            port.crearCliente(usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
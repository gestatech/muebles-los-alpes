package com.losalpes.ventas.ws.client;

import com.losalpes.ventas.TipoCiudad;
import com.losalpes.ventas.TipoDepartamento;
import com.losalpes.ventas.TipoPais;

import org.junit.Test;

/**
 * Clase JUnit para probar el servicio de ventas..
 * Ingresa un cliente.
 * @author Memo Toro
 */
public class VentaWSTest {
    /** Crea una nueva instancia de ClienteWSTest */
    public VentaWSTest() {}
     /**
     * Método con la lógica de probar la creación de un cliente por el web service.
     * Anotado con @Test para definir el metodo a probar.
     */
    @Test
    public void comprarMueble(){
        try {
            // Invocación del web Service.
            com.losalpes.ventas.VentaWS service = new com.losalpes.ventas.VentaWS();
            // Llamado de la operación del Web Service
            com.losalpes.ventas.VentaWebService port = service.getVentaWebServicePort();
            // Crea una venta de mueble de la clase generada por el Web Service.
            com.losalpes.ventas.Venta venta = new com.losalpes.ventas.Venta();
            // Creación de la venta con sus datos.
            venta.setReferencia("999-99999999");
            venta.setPais(TipoPais.COLOMBIA);
            venta.setDepartamento(TipoDepartamento.DEPARTAMENTO_5);
            venta.setCiudad(TipoCiudad.CIUDAD_5);
            venta.setDescripcion("Descripción de la Venta Demo por JUnit");
            venta.setFechaGeneracion("09/09/2099");
            venta.setValor(30000);
            // Creación de los datos asociados al cliente y de la tarjeta de crédito de la venta.
            venta.setIdCliente(80800904);
            venta.setNumeroTarjeta("999999999999");
            venta.setCodigoSeguridad(1234);
            venta.setCuotas(24);
            venta.setFechaExpiracionTarjeta("02/02/2999");
            // Invocacion de la operacion del Web Service.
            port.comprarMueble(venta);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
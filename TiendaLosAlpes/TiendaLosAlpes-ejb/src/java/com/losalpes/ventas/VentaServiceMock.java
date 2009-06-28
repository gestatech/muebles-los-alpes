package com.losalpes.ventas;

import com.losalpes.persistence.entity.Venta;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.mail.SimpleEmail;

/**
 * Servicio Mock que implementa la interfaz con los métodos de Venta.
 * @author Memo Toro
 */
public class VentaServiceMock implements IVentaService{
    /**
     * Variable Static con el Listado de Ventas de la Tienda.
     */
    private static List<Venta> ventas = new ArrayList<Venta>();
    /**
     * Variable Satic con la Venta que se esta realizando.
     */
    private static Venta venta = new Venta();

    /** Crea una nueva instancia de VentaServiceMock */
    public VentaServiceMock() {        
    }
    /**
     * Método para crear una Venta
     * @param Variable tipo Venta para crear.
     */
    public void crear(Venta ventaNueva){
        venta = ventaNueva;
    }
    /**
     * Método para almacenar una Venta a la venta actual y al listado de ventas.
     * @param ventaNueva
     */
    public void almacenar(Venta ventaNueva){
        venta = ventaNueva;
        ventas.add(venta);
    }
    /**
     * Método para obtener el listado de ventas de la tienda.
     * @return List con las ventas de la tienda.
     */
    public List obtener(){
        return ventas;
    }
    /**
     * Método para obtener la venta actual.
     * @return Variable tipo Venta.
     */
    public Venta obtenerVenta() {
        return venta;
    }
    /**
     * Método para enviar correos electrónicos con las librerias de Apache
     */
    public void enviarCorreo(){
        // Logica de envio de mensajes por email.
        try{
            // Variable para envio de correo electrónico
            SimpleEmail email = new SimpleEmail();
            email.setHostName("mail1.igac.gov.co");
            email.addTo("memo.toro@gmail.com", "memo.toro@gmail.com");
            email.addTo("memotoro83@hotmail.com", "memotoro83@hotmail.com");
            email.setAuthentication("gtoro", "password");
            email.setFrom("gtoro@igac.gov.co", "Muebles Los Alpes");
            email.setSubject("Confirmación de Compra");
            email.setMsg(   "Apreciado Cliente:\n" +
                            "Su compra ha sido registrada.\n" +
                            "En un par de días le llegará su mercancia a la dirección registrada.\n\n" +
                            "Gracias !!! \n\n" +
                            "Guillermo Antonio Toro Bayona");
            email.send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
package com.losalpes.ventas;

import com.losalpes.persistence.entity.Venta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import org.apache.commons.mail.SimpleEmail;
/**
 * Servicio Bean que implementa la interfaz con los métodos de Venta. Anotada con @Stateless.
 * @author Memo Toro
 */
@Stateful
public class VentaServiceBean implements IVentaService{
    /**
     * Variable Static con el Listado de Ventas de la Tienda.
     */
    private static List<Venta> ventas;
    /**
     * Variable Satic con la Venta que se esta realizando.
     */
    private static Venta venta;
    /** Crea una nueva instancia de VentaServiceMock */
    public VentaServiceBean() {
    }
    /**
     * Método anotado con @PostConstruct para iniciar la variable de arreglo de ventas.
     */
    @PostConstruct
    public void iniciar(){
//       ventas = new ArrayList<Venta>();
//       venta = new Venta();
    }
    /**
     * Método anotado con @PreDestroy para anunciar la destrucción del carrito.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("VENTA-SERVICE-BEAN HA SIDO DESTRUIDA SATISFACTORIAMENTE !!!");
    }
    /**
     * Método para crear una Venta
     * @param ventaNueva Variable tipo Venta para crear.
     */
    public void crear(Venta ventaNueva){
        venta = new Venta();
        ventas  = new ArrayList<Venta>();
        venta = ventaNueva;
    }
    /**
     * Método para almacenar una Venta a la venta actual y al listado de ventas.
     * @param ventaNueva
     */
    public void almacenar(Venta ventaNueva){
        ventas.add(ventaNueva);
    }
    /**
     * Método para obtener el listado de ventas de la tienda.
     * @return List con las ventas de la tienda.
     */
    public List obtenerVentas(){
        return ventas;
    }
    /**
     * Método para obtener la venta actual.
     * @return Venta Variable tipo Venta.
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
            email.setAuthentication("gtoro", "password");
            email.setFrom("gtoro@igac.gov.co", "Muebles Los Alpes");
            email.setSubject("Confirmación de Compra");
            email.setMsg(   "Apreciado Cliente:\n" +
                            "Su compra ha sido registrada.\n" +
                            "En un par de días le llegará su mercancia a la dirección registrada.\n\n" +
                            "Gracias !!! \n\n" +
                            "Guillermo Antonio Toro Bayona");
 //           email.send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Método para obtener las ventas a partir de una fecha.
     * @param valor Valor de la consulta por fecha.
     * @return List Listado de Ventas.
     */
    public List<Venta> obtenerVentasConsultadas(String valor){
        List<Venta> ventasConsultadas = new ArrayList<Venta>();
        Venta venta = new Venta();
        Iterator it = ventas.iterator();
        while(it.hasNext()){
            venta = (Venta) it.next();
            if(venta.getFechaGeneracion().equalsIgnoreCase(valor))
                ventasConsultadas.add(venta);
        }
        return ventasConsultadas;
    }
}
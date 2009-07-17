package com.losalpes.ventas;

import com.losalpes.persistence.IPersistenceServices;
import com.losalpes.persistence.entity.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.mail.SimpleEmail;
/**
 * Session Bean que implementa la interfaz con los métodos de Venta. Anotada con @Stateless
 * @author Memo Toro
 */
@Stateless
public class VentaServiceBean implements IVentaService{
    /**
     * Interfaz anotada como @EJB para que haga referencia e inyección con el Bean de la Persistencia.
     */
    @EJB
    private IPersistenceServices persistencia;
    /** Crea una nueva instancia de VentaServiceBean */
    public VentaServiceBean() {}
    /**
     * Método anotado con @PostConstruct para iniciar la variable de arreglo de ventas.
     */
    @PostConstruct
    public void iniciar(){
        System.out.println("VENTA-SERVICE-BEAN HA SIDO INICIALIZADO !!!");
    }
    /**
     * Método anotado con @PreDestroy para anunciar la destrucción del carrito.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("VENTA-SERVICE-BEAN HA SIDO DESTRUIDA SATISFACTORIAMENTE !!!");
    }
    /**
     * Método para almacenar una Venta a la venta actual y al listado de ventas.
     * @param ventaNueva
     */
    public void almacenar(Venta ventaNueva){
        persistencia.create(ventaNueva);
    }
    /**
     * Método para obtener el listado de ventas de la tienda.
     * @return List con las ventas de la tienda.
     */
    public List obtenerVentas(){
        return persistencia.findAll(Venta.class);
    }
    /**
     * Método para consultar las ventas y dtellaes del cliente
     * @param idCliente Identificador del cliente
     * @return List de Ventas de cliente
     */
    public List<Venta> consultarVentas(int valor) {
        List<String> valores = new ArrayList<String>();
        List<Venta> ventas = new ArrayList<Venta>();
        valores.add("idCliente|" + valor);
        ventas = persistencia.findObjects("findVentas",valores);
/*        Iterator it = ventas.iterator();
        while(it.hasNext()){
            valores.clear();
            Venta venta = (Venta)it.next();
            valores.add("venta|" + venta.getReferencia());
            List<DetalleVenta> detalles = persistencia.findObjects("findDetalles", valores);
            venta.setDetalleVenta(detalles);
        }
 */
        return ventas;
    }
    /**
     * Método para enviar correos electrónicos con las librerias de Apache.
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
}
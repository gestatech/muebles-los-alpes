package com.losalpes.ventas;

import com.losalpes.persistence.IPersistenceServices;
import com.losalpes.persistence.entity.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.commons.mail.SimpleEmail;
/**
 * Session Bean que implementa la interfaz con los métodos de Venta. Anotada con @Stateless
 * @author Memo Toro
 */
@WebService(name="VentaWebService",serviceName="VentaWS")
@Stateless
@DeclareRoles({"Gerente"})
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
     * Anotadocon @PermitAll para no restringir el acceso a este metodo.
     * @param ventaNueva
     */
    @WebMethod(operationName="ComprarMueble")
    @PermitAll
    public void almacenar(Venta ventaNueva){
        persistencia.createVenta(ventaNueva);
    }
    /**
     * Método para obtener el listado de ventas de la tienda.
     * Anotado con @RolesAllowed para que pueda solo acceder el Gerente a la funcionalidad*
     * @return List con las ventas de la tienda.
     */
    @RolesAllowed({"Gerente"})
    public List obtenerVentas(){
        return persistencia.findAll(Venta.class);
    }
    /**
     * Método para consultar las ventas y dtellaes del cliente
     * Anotado con @RolesAllowed para que pueda solo acceder el Gerente a la funcionalidad
     * @param idCliente Identificador del cliente
     * @return List de Ventas de cliente
     */
    @RolesAllowed({"Gerente"})
    public List<Venta> consultarVentas(int valor) {
        List<String> valores = new ArrayList<String>();
        List<Venta> ventas = new ArrayList<Venta>();
        valores.add("idCliente|" + valor);
        ventas = persistencia.findObjects("findVentas",valores);
        return ventas;
    }
    /**
     * Método para enviar correos electrónicos con las librerias de Apache.
     * Anotadocon @PermitAll para no restringir el acceso a este metodo.
     */
    @PermitAll
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
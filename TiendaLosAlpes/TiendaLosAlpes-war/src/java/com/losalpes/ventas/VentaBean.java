package com.losalpes.ventas;

import com.losalpes.persistence.entity.Venta;
import javax.ejb.EJB;
/**
 * Managed Bean para acceder la Venta y Enviar la notificación final
 * por correo electrónico sobre la confirmación de la venta.
 * @author Memo Toro
 */
public class VentaBean {
    /**
     * Interfaz con la referencia a la interfaz IVentaService para los muebles.
     */
    private IVentaService ventaService = new VentaServiceBean();
    /**
     * Variable tipo Venta para ser desplegada y asignada.
     */
    private Venta venta;
    /** Crea una nueva instancia de VentaBean */
    public VentaBean() {
       venta = new Venta();
       setVenta(ventaService.obtenerVenta());
    }
    /**
     * Método para obtener la venta activa en la compra.
     * @return Venta Variable de Tipo Venta
     */
    public Venta getVenta() {
        return venta;
    }
    /**
     * Método para asignar la Venta que se esta manipulando.
     * @param venta Variable de tipo Venta.
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    /**
     * Método para Almacenar la venta en el Listado de Ventas para efectos de reportes.
     * Se alamacena en el Mock de Venta
     * @return Variable tipo String con el direccionamiento al Reporte.
     */
    public String getComprarMuebles(){
        // Cargar la venta al arreglo del Mock de Venta para reportes.
        ventaService.almacenar(getVenta());
        // Envío de correo electrónico de notificación.
        ventaService.enviarCorreo();
        // String que redirecciona a la pagina de Confirmación de Compra de muebles.
        return "confirmacion";
    }
}
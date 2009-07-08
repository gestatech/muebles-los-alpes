package com.losalpes.ventas;

import com.losalpes.cliente.IClienteService;
import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.security.ISecurityService;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
/**
 * Backing Bean para acceder la Venta y Enviar la notificación final
 * por correo electrónico sobre la confirmación de la venta.
 * @author Memo Toro
 */
public class VentaBean {
    /**
     * Interfaz con la referencia a la interfaz IVentaService para los muebles.
     */
    //@EJB
    private IVentaService ventaService  = new VentaServiceBean();
    /**
     * Intefaz anotada con @EJB para inyectar codigo de interfaz IClienteService.
     */
    @EJB
    private IClienteService clienteService;
    /**
     * Interfaz anotada con @EJB que inyecta referencia a la interfaz ISecurityService
     */
    @EJB
    private ISecurityService securityService;
    /**
     * Variable tipo Venta para ser desplegada y asignada.
     */
    private Venta venta;
    /** Crea una nueva instancia de VentaBean */
    public VentaBean() {
       venta = new Venta();
        venta = ventaService.obtenerVenta();
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
        // Obtener el cliente que se autentico y se guardo en la sesión de la aplicación
        Cliente cliente = (Cliente)securityService.getObjetoSesion("cliente");
        // Asignar al cliente la venta creada.
        cliente.setAsignarVenta(getVenta());
        // Actualizar el cliente en el listado con sus ventas asigandas.
        clienteService.editar(cliente);
        // Cargar la venta al arreglo del Mock de Venta para reportes.
        ventaService.almacenar(getVenta());
        // Envío de correo electrónico de notificación.
        ventaService.enviarCorreo();
        // String que redirecciona a la pagina de Confirmación de Compra de muebles.
        return "confirmacion";
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getPaises() {
        TipoPais[] tipos = TipoPais.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getDepartamentos() {
        TipoDepartamento[] tipos = TipoDepartamento.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getCiudadResidencia() {
        TipoCiudad[] tipos = TipoCiudad.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
}
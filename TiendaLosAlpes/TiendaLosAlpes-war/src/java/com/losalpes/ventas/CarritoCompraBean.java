package com.losalpes.ventas;

import com.losalpes.catalog.ICatalogService;
import com.losalpes.cliente.IClienteService;
import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.persistence.entity.Tarjeta;
import com.losalpes.security.ISecurityService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
/**
 * Backing Bean para controlar el Carrito de Compras y las Ventas.
 * Dicho carrito conoce las interfaces de varios services para interactuar con Ventas y Catalogo
 * @author Memo Toro
 */
public class CarritoCompraBean {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz ICarritoService para los muebles.
     */
    @EJB
    private ICarritoService carritoService;
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz ICatalogService para los muebles.
     */
    @EJB
    private ICatalogService catalogService;
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para las ventas.
     */
    @EJB
    private IVentaService ventaService;
    /**
     * Interfaz anotada con @EJB que inyecta referencia a la interfaz ISecurityService
     */
    @EJB
    private ISecurityService securityService;
    /**
     * Intefaz anotada con @EJB para inyectar codigo de interfaz IClienteService.
     */
    @EJB
    private IClienteService clienteService;
    /**
     * Variable de tipo Mueble a comprar.
     */
    private Mueble mueble;
    /**
     * Variable tipo Venta con la venta del carrito.
     */
    private Venta venta;
    /**
     * Variable tipo Venta con la venta del carrito.
     */
    private Tarjeta tarjeta;

    /**
     * Variable para el valor de la compra.
     */
    private double valorCompra;
    /**
     * Variable para la cantidad por tipo de productos a agregar al carrito.
     */
    private int cantidad;
    /**
     * Variable de fecha de expiracion
     */
    private Date fechaExpiracion;
    /** Crea una nueva instancia de CarritoCompraBean */
    public CarritoCompraBean() {
        // Inicializa las variables
        mueble = new Mueble();
        venta = new Venta();
    }
    /**
     * Método que obtiene el mueble.
     * @param Mueble Variable tipo Mueble.
     */
    public Mueble getMueble() {
        return mueble;
    }
    /**
     * Método que asigna el mueble a la variable local de mueble.
     * @param mueble Variable tipo Mueble.
     */
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }
    /**
     * Método que retorna el valor de la compra.
     * @return double Variable tipo Double con el valor de la compra.
     */
    public double getValorCompra() {
        return valorCompra;
    }
    /**
     * Método que asigna el valor de la compra.
     * @param valorCompra Variable tipo Double con el valor de la compra.
     */
    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
    /**
     * Método para obtener la cantidad de unidades de cada mueble cargado al carrito.
     * @return int Variable tipo Int con la cantidad de productos cargados en el carrito por tipo de mueble.
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Método para asignar la cantidad de unidades.
     * @param cantidad Variable tipo Int con la cantidad de unidades.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
     * Método para retornar la fecha de expiracion
     * @return Date con fecha de expiracion
     */
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }
    /**
     * Método para asignar la fehca de expiracion
     * @param fechaExpiracion
     */
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    /**
     * Método para retornar la tarjeta de crédito.
     * @return Tarjeta de crédito.
     */
    public Tarjeta getTarjeta() {
        return tarjeta;
    }
    /**
     * Método para asignar la tarjeta de crédito.
     * @param tarjeta tarjeta de crédito.
     */
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
    /**
     * Método que retorna un List con todos los muebles agregados al carrito de compras.
     * @return List Variable tipo List con los muebles cargados al carrito.
     */
    public List getVerCarrito(){
        return carritoService.verDetallesCarrito();
    }
    /**
     * Método para agregar un mueble al carrito de compras.
     * Se dispara a partir de un evento de clic sobre el boton de agregar mueble.
     * @param evento Variable tipo evento como clic.
     */
    public void getAgregarMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("referencia");
        String valor = componente.getValue().toString();
        String criterio = "REFERENCIA";
        // Busqueda del mueble por medio de la interfaz de Catálogo para obtener el mueble
        List<Mueble> muebles = catalogService.consultar(criterio, valor);
        if(muebles.size()>0){
            setMueble(muebles.get(0));
            DetalleVenta nuevoDetalle = new DetalleVenta();
            nuevoDetalle.setMuebleVendido(getMueble());
            nuevoDetalle.setCantidadVenta(getCantidad());
            nuevoDetalle.setPrecioVenta(getMueble().getPrecio());
            // Carga el detalle de compra al carrito de compras.
            carritoService.agregar(nuevoDetalle);
            // Cálcula el valor de la compra acumulada con el mueble agregado.
            setValorCompra(getValorCompra()+getCantidad()*nuevoDetalle.getPrecioVenta());
        }
    }
    /**
     * Método para eliminar un mueble del carrito de compras.
     * Se dispara a partir de un evento de clic sobre el boton de eliminar mueble.
     * @param Variable tipo evento como clic.
     */
    public void getEliminarMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("referencia");
        String referencia = componente.getValue().toString();
        // Busqueda del mueble por medio de la interfaz de Carrito para obtener el mueble
        DetalleVenta detalle = carritoService.obtenerDetalle(referencia);
        if(detalle!=null){
            // Cálcula el valor de la compra acumulada sin el mueble eliminado.
            setValorCompra(getValorCompra()-detalle.getCantidadVenta()*detalle.getPrecioVenta());
            // Borrar el mueble al carrito de compras.
            carritoService.eliminar(detalle);
        }
    }
    /**
     * Método para crear la primera parte de la Venta.
     * La primera parte de la venta se inicia con el numero de referencia, el valor de la compra, la descripción de la venta.
     * @return Variable de tipo String para redireccionar a la autenticación.
     */
    public String getComprarMuebles(){
        // Crea un aleatorio para simlar un numero de referencia.
        Random rand = new Random();
        String referenciaCompra = (String.valueOf(rand.nextInt(1000)))+"-"+(String.valueOf(rand.nextInt(100000000)));
        getVenta().setReferencia(referenciaCompra);
        getVenta().setValor(getValorCompra());
        Iterator it = carritoService.verDetallesCarrito().iterator();
        String referencias = "";
        int contadorMuebles = 0;
        String descripcion;
        // Bucle para contar los muebles y obtener su referencia.
        while(it.hasNext()){
            DetalleVenta actualizar = (DetalleVenta)it.next();
            String ref = actualizar.getMuebleVendido().getReferencia();
            contadorMuebles += actualizar.getCantidadVenta();
            referencias = referencias+"--"+ ref;
            // Asigna la venta a cada detalle
            actualizar.setVenta(getVenta());
            carritoService.actualizar(actualizar);
        }
        descripcion = "("+contadorMuebles+") tipos de muebles con referencia(s): "+referencias;
        getVenta().setDescripcion(descripcion);
        // Asigna la fecha a la compra la fecha actual.
        Calendar fechaActual = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        getVenta().setFechaGeneracion(df.format(fechaActual.getTime()));
        getVenta().setDetalleVenta(carritoService.verDetallesCarrito());
        // Obtener el cliente que se autentico y se guardo en la sesión de la aplicación
        Cliente cliente = (Cliente)securityService.getObjetoSesion("cliente");
        // Asignar idcliente a venta
        getVenta().setIdCliente(cliente.getNumeroDocumento());
        //obtiene la tarjeta del cliente
        if(!(cliente == null) && cliente.getNumeroDocumento() != 0){
            tarjeta = clienteService.consultarTarjeta(cliente.getNumeroDocumento());
            //asigna los valores de la tarjeta a la venta
            getVenta().setNumeroTarjeta(tarjeta.getNumeroTarjeta());
            getVenta().setCodigoSeguridad(tarjeta.getCodigoSeguridad());
            getVenta().setFechaExpiracionTarjeta(tarjeta.getFechaExpiracionTarjeta());
        }
        // String para redireccionar a la pagina de autenticación.
        return "pagar";
    }
    /**
     * Método para Almacenar la venta para efectos de reportes.
     * Se alamacena en persistencia de Venta.
     * @return Variable tipo String con el direccionamiento al Reporte.
     */
    public String getPagarMuebles(){
        // Cargar la venta a la persistencia de Venta para reportes.
        ventaService.almacenar(getVenta());
        // Envío de correo electrónico de notificación.
        ventaService.enviarCorreo();
        // String que redirecciona a la pagina de Confirmación de Compra de muebles.
        return "confirmacion";
    }
    /**
     * Método para limpiar las variables del Backing Bean.
     */
    public String getLimpiar(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CarritoCompraBean");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CatalogBean");
        return "cliente";
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
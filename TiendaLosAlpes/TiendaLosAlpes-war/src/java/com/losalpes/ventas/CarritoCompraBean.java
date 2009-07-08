package com.losalpes.ventas;

import com.losalpes.catalog.ICatalogService;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.enums.TipoConsultaMueble;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.security.ISecurityService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
/**
 * Backing Bean para controlar el Carrito de Compras y las Ventas.
 * Dicho carrito conoce las interfaces de varios Mock para interactuar con Ventas, Catalogo
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
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para los muebles.
     */
    @EJB
    private IVentaService ventaService;
    /**
     * Interfaz anotada con @EJB que inyecta referencia a la interfaz ISecurityService
     */
    @EJB
    private ISecurityService securityService;
    /**
     * Variable de tipo Mueble a comprar.
     */
    private Mueble mueble;
    /**
     * Variable para el valor de la compra.
     */
    private double valorCompra;
    /**
     * Variable para la cantidad por tipo de productos a agregar al carrito.
     */
    private int cantidad;
    /** Crea una nueva instancia de CarritoCompraBean */
    public CarritoCompraBean() {
        mueble = new Mueble();
    }
    /**
     * Método que obtiene el mueble .
     * @param Mueble Variable tipo Mueble
     */
    public Mueble getMueble() {
        return mueble;
    }
    /**
     * Método que asigna el mueble a la variable local de mueble.
     * @param mueble Variable tipo Mueble
     */
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }
    /**
     * Método que retorna el valor de la compra
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
     * Método para agregar un mueble al carrito de compras.
     * Se dispara a partir de un evento de clic sobre el boton de agregar mueble.
     * @param evento Variable tipo evento como clic.
     */
    public void getAgregarMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("referencia");
        String valor = componente.getValue().toString();
        TipoConsultaMueble criterio = TipoConsultaMueble.REFERENCIA;
        // Busqueda del mueble por medio de la interfaz de Catálogo para obtener el mueble
        List<Mueble> muebles = catalogService.consultar(criterio, valor);
        if(muebles.size()>0){
            setMueble(muebles.get(0));
            DetalleVenta nuevoDetalle = new DetalleVenta();
            nuevoDetalle.setId();
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
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("idVenta");
        String idVenta = componente.getValue().toString();
        // Busqueda del mueble por medio de la interfaz de Carrito para obtener el mueble
        DetalleVenta detalle = carritoService.obtenerDetalle(Integer.valueOf(idVenta).intValue());
        if(detalle!=null){
            // Cálcula el valor de la compra acumulada sin el mueble eliminado.
            setValorCompra(getValorCompra()-detalle.getCantidadVenta()*detalle.getPrecioVenta());
            // Borrar el mueble al carrito de compras.
            carritoService.eliminar(detalle);
        }
    }
    /**
     * Método para crear la primera parte de la Venta.
     * La primera parte de la venta se inicia con el numero de referencia, el valor de la compra,
     * la descripción de la venta. El resto de la venta se obtiene una vez se ha autenticado el usuario.
     * @return Variable de tipo String para redireccionar a la autenticación.
     */
    public String getComprarMuebles(){
        // Crea un aleatorio para simlar un numero de referencia.
        Random rand = new Random();
        String referenciaCompra = (String.valueOf(rand.nextInt(1000)))+"-"+(String.valueOf(rand.nextInt(100000000)));
        Venta miVenta = new Venta();
        miVenta.setReferencia(referenciaCompra);
        miVenta.setValor(getValorCompra());
        Iterator it = carritoService.verDetallesCarrito().iterator();
        String referencias = "";
        int contadorMuebles = 0;
        String descripcion;
        // Bucle para contar los muebles y obtener su referencia.
        while(it.hasNext()){
            DetalleVenta temp = (DetalleVenta)it.next();
            String ref = temp.getMuebleVendido().getReferencia();
            contadorMuebles += temp.getCantidadVenta();
            referencias = referencias+"--"+ ref;
        }
        descripcion = "("+contadorMuebles+") tipos de muebles con referencia(s): "+referencias;
        miVenta.setDescripcion(descripcion);
        // Asigna la fecha a la compra la fecha actual.
        Calendar fechaActual = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        miVenta.setFechaGeneracion(df.format(fechaActual.getTime()));
        miVenta.setDetalleVenta(carritoService.verDetallesCarrito());
        // Obtener el cliente que se autentico y se guardo en la sesión de la aplicación
        Cliente cliente = (Cliente)securityService.getObjetoSesion("cliente");
        // Asignar idcliente a venta
        miVenta.setIdCliente(cliente.getNumeroDocumento());
        // Asigna la venta creada al servicio de ventas.
        ventaService.crear(miVenta);
        // String para redireccionar a la pagina de autenticación.
        return "pagar";
    }
    /**
     * Método que retorna un List con todos los muebles agregados al carrito de compras.
     * @return List Variable tipo List con los muebles cargados al carrito.
     */
    public List getVerCarrito(){
        return carritoService.verDetallesCarrito();
    }
    /**
     * Método para limpiar las variables del Backng Bean.
     */
    public String getLimpiar(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CarritoCompraBean");
        return "cliente";
    }
}
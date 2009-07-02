package com.losalpes.ventas;

import com.losalpes.catalog.ICatalogService;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.persistence.entity.TipoConsultaMueble;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Managed Bean para controlar el Carrito de Compras y las Ventas.
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
     * Variable de tipo Mueble a comprar.
     */
    private Mueble mueble;
    /**
     * Variable Venta para ser relacionada con el carrito de compras.
     */
    private Venta venta;
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
        venta = new Venta();
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
     * Método para obtener la venta que se esta manipulando con el carrito.
     * @return Venta Variable tipo Venta.
     */
    public Venta getVenta() {
        return venta;
    }
    /**
     * Método para asignar la Venta.
     * @param venta Variable tipo venta.
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    /**
     * Método para agregar un mueble al carrito de compras.
     * Se dispara a partir de un evento de clic sobre el boton de agregar mueble.
     * @param evento Variable tipo evento como clic.
     */
    public void getAgregarMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        FacesContext contexto = FacesContext.getCurrentInstance();
        String id = evento.getComponent().getClientId(contexto);
        Map parametros = contexto.getExternalContext().getRequestParameterMap();
        String valor = (String) parametros.get(id);
        TipoConsultaMueble criterio = TipoConsultaMueble.REFERENCIA;
        // Busqueda del mueble por medio de la interfaz Mock de Catálogo para obtener el mueble
        List<Mueble> muebles = catalogService.consultar(criterio, valor);
        if(muebles.size()>0)
            setMueble(muebles.get(0));
        getMueble().setCantidad(getCantidad());
        // Carga el mueble al carrito de compras.
        carritoService.agregar(getMueble());
        // Cálcula el valor de la compra acumulada con el mueble agregado.
        setValorCompra(getValorCompra()+getCantidad()*getMueble().getPrecio());
    }
    /**
     * Método para eliminar un mueble del carrito de compras.
     * Se dispara a partir de un evento de clic sobre el boton de eliminar mueble.
     * @param Variable tipo evento como clic.
     */
    public void getEliminarMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        FacesContext contexto = FacesContext.getCurrentInstance();
        String id = evento.getComponent().getClientId(contexto);
        Map parametros = contexto.getExternalContext().getRequestParameterMap();
        String valor = (String) parametros.get(id);
        TipoConsultaMueble criterio = TipoConsultaMueble.REFERENCIA;
        // Busqueda del mueble por medio de la interfaz Mock de Catálogo para obtener el mueble
        List<Mueble> muebles = catalogService.consultar(criterio, valor);
        if(muebles.size()>0)
            setMueble(muebles.get(0));
        // Borrar el mueble al carrito de compras.
        carritoService.eliminar(getMueble());
        // Cálcula el valor de la compra acumulada sin el mueble eliminado.
        setValorCompra(getValorCompra()-getMueble().getCantidad()*getMueble().getPrecio());
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
        setVenta(new Venta());
        getVenta().setReferencia(referenciaCompra);
        getVenta().setValor(getValorCompra());
        Iterator it = carritoService.verMueblesCarrito().iterator();
        String referencias = "";
        int contadorMuebles = 0;
        String descripcion;
        List<String> refMuebles = new ArrayList<String>();
        // Bucle para contar los muebles y obtener su referencia.
        while(it.hasNext()){
            String ref = ((Mueble)it.next()).getReferencia();
            referencias = referencias+"--"+ ref;
            refMuebles.add(ref);
            contadorMuebles++;
        }
        descripcion = "("+contadorMuebles+") muebles con referencia(s): "+referencias;
        getVenta().setDescripcion(descripcion);
        // Asigna el identificador del cliente con el cliente autenticado y asignado al carrito.
        getVenta().setClienteId(carritoService.obtenerClienteAutenticado());
        // Asigna la fecha a la compra la fecha actual.
        Calendar fechaActual = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        getVenta().setFechaGeneracion(df.format(fechaActual.getTime()));
        getVenta().setReferenciasMuebles(refMuebles);
       // Asigna la venta creada al servicio Mock de ventas.
        ventaService.crear(getVenta());      
        // String para redireccionar a la pagina de autenticación.
        return "pagar";
    }
    /**
     * Método que retorna un List con todos los muebles agregados al carrito de compras.
     * @return List Variable tipo List con los muebles cargados al carrito.
     */
    public List getVerCarrito(){
        return carritoService.verMueblesCarrito();
    }
}
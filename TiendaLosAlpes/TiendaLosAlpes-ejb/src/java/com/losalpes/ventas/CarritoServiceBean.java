package com.losalpes.ventas;

import com.losalpes.persistence.entity.DetalleVenta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
/**
 * Session Bean que implementa la interfaz con los métodos del Carrito de Compras.
 * Bean anotado con @Stateful para guardar los datos del carrito de compras.
 * @author Memo Toro
 */
@Stateful
public class CarritoServiceBean implements ICarritoService{
    /**
     * Listado de Muebles Static con los muebles del carrito.
     */
    private List<DetalleVenta> detallesCarrito;
    /**
     * Identificador del usuario autenticado en el carrito.
     */
    private static int idClienteAutenticado;
    /** Crea una nueva instancia de CarritoServiceMock */
    public CarritoServiceBean() {
    }
    /**
     * Método anotado con @PostConstruct para iniciar la variable de arreglo de productos del carrito.
     */
    @PostConstruct
    public void iniciar(){
        detallesCarrito = new ArrayList<DetalleVenta>();
    }
    /**
     * Método anotado con @PreDestroy para anunciar la destrucción del carrito.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("CARRITO-SERVICE-BEAN SE HA DESTRUIDO SATISFACTORIAMENTE !!!");
    }
    /**
     * Método para gregar un detalle de mueble al carrito de compras.
     * @param detalle Vatiable tipo detalle de mueble a agregar al listado.
     */
    public void agregar(DetalleVenta nuevoDetalle) {
        DetalleVenta viejo = new DetalleVenta();
        Iterator it = detallesCarrito.iterator();
        boolean existe = false;
        while(it.hasNext()){
            viejo = (DetalleVenta) it.next();
            if(viejo.getMuebleVendido().equals(nuevoDetalle.getMuebleVendido())){
                existe = true;
                break;
            }
        }
        if(existe == true){
            nuevoDetalle.setCantidadVenta(viejo.getCantidadVenta()+nuevoDetalle.getCantidadVenta());
            detallesCarrito.remove(viejo);
            detallesCarrito.add(nuevoDetalle);
        }
        else
            detallesCarrito.add(nuevoDetalle);
    }
    /**
     * Método para eliminar un detalle de mueble del carrito de compras.
     * @param detalle Vatiable tipo detalle de mueble a eliminar del listado.
     */
    public void eliminar(DetalleVenta detalle) {
        DetalleVenta eliminada = new DetalleVenta();
        Iterator it;
        it = detallesCarrito.iterator();
        // Recorre todo el listado comparando el detalle de mueble del listado con el detalle de mueble que llego.
        while(it.hasNext()){
            eliminada = (DetalleVenta) it.next();
            if(eliminada.getMuebleVendido().equals(detalle.getMuebleVendido()))
                break;
        }
        // Elimina el detalle de mueble del listado del carrito.
        detallesCarrito.remove(detalle);
    }
    /**
     * Método para obtener todos el listado de detalle de muebles del carrito
     * @return List Variable tipo List con detalle de muebles del carrito.
     */
    public List<DetalleVenta> verDetallesCarrito(){
        return detallesCarrito;
    }
    /**
     * Método para obtener todos el listado de detalles del carrito
     * @return Variable tipo List con detalles del carrito.
     */
    public DetalleVenta obtenerDetalle(int idDetalle){
        DetalleVenta consultado = new DetalleVenta();
        Iterator it;
        it = detallesCarrito.iterator();
        // Recorre todo el listado comparando el mueble del listado con el mueble que llego.
        while(it.hasNext()){
            consultado = (DetalleVenta) it.next();
            if(consultado.getIdVenta()==idDetalle)
                return consultado;
        }
        return null;
    }
    /**
    * Métoro para asignar el Id del cliente autenticado a la variable del carrito.
    * @param idCliente Identificador del cliente.
    */
    public void clienteAutenticado(int idCliente){
        idClienteAutenticado = idCliente;
    }
    /**
     * Método para obtener el id del cliente autenticado
     * @return int con el Id del cliente autenticado en la aplicación
     */
    public int obtenerClienteAutenticado() {
        return idClienteAutenticado;
    }
}
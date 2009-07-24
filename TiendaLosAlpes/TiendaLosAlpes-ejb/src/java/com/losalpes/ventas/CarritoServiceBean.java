package com.losalpes.ventas;

import com.losalpes.persistence.entity.DetalleVenta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.PermitAll;
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
    /** Crea una nueva instancia de CarritoServiceMock */
    public CarritoServiceBean() {
    }
    /**
     * Método anotado con @PostConstruct para iniciar la variable de arreglo de productos del carrito.
     */
    @PostConstruct
    public void iniciar(){
        System.out.println("CARRITO-SERVICE-BEAN HA SIDO INICIALIZADO !!!");
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
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * @param detalle Vatiable tipo detalle de mueble a agregar al listado.
     */
    @PermitAll
    public void agregar(DetalleVenta nuevoDetalle) {
        DetalleVenta viejo = new DetalleVenta();
        Iterator it = detallesCarrito.iterator();
        boolean existe = false;
        while(it.hasNext()){
            viejo = (DetalleVenta) it.next();
            if(viejo.getMuebleVendido().getReferencia().equalsIgnoreCase(nuevoDetalle.getMuebleVendido().getReferencia())){
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
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * @param detalle Vatiable tipo detalle de mueble a eliminar del listado.
     */
    @PermitAll
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
     * Método para obtener todos el listado de detalle de muebles del carrito.
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * @return List Variable tipo List con detalle de muebles del carrito.
     */
    @PermitAll
    public List<DetalleVenta> verDetallesCarrito(){
        return detallesCarrito;
    }
    /**
     * Método para obtener todos el listado de detalles del carrito.
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * @param referencia String con la referencia.
     * @return DetalleVenta detalle de venta del carrito
     */
    @PermitAll
    public DetalleVenta obtenerDetalle(String referencia){
        DetalleVenta consultado = new DetalleVenta();
        Iterator it;
        it = detallesCarrito.iterator();
        // Recorre todo el listado comparando el mueble del listado con el mueble que llego.
        while(it.hasNext()){
            consultado = (DetalleVenta) it.next();
            if(consultado.getMuebleVendido().getReferencia().equalsIgnoreCase(referencia))
                return consultado;
        }
        return null;
    }
    /**
     * Método que actualiza los detalles de la venta.
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * @param detalle Detalle a actualizar.
     */
    @PermitAll
    public void actualizar(DetalleVenta detalle){
        detallesCarrito.set(detallesCarrito.indexOf(detalle), detalle);
    }
}
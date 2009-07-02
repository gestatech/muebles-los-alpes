package com.losalpes.ventas;

import com.losalpes.persistence.entity.Mueble;
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
    private List<Mueble> mueblesCarrito;
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
        mueblesCarrito = new ArrayList<Mueble>();
    }
    /**
     * Método anotado con @PreDestroy para anunciar la destrucción del carrito.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("CarritoServiceBean ha sido eliminado con exito !!!");
    }
    /**
     * Método para gregar un mueble al carrito de compras.
     * @param mueble Vatiable tipo mueble a agregar al listado.
     */
    public void agregar(Mueble mueble) {
        /*Mueble comparar = new Mueble();
        Iterator it;
        it = mueblesCarrito.iterator();
        // Variable boolean para verificar la existencia del mueble.
        boolean existencia = false;
        // Recorre todo el listado comparando el mueble del listado con el mueble que llego.
        while(it.hasNext()){
            comparar = (Mueble) it.next();
            if(comparar.getNombre().equalsIgnoreCase(mueble.getNombre())){
                existencia = true;
                break;
            }
        }
        // Verifica si el mueble no existe en el carrito, lo agrega inmediatamente al listado
        if(existencia == false)
            mueblesCarrito.add(mueble);
        // Si el mueble existe en el carrito, actualiza su cantidad y actualiza sus datos.
        else{
            int a = comparar.getCantidad();
            int b = mueble.getCantidad();
            int nuevaCantidad = comparar.getCantidad() + mueble.getCantidad();
            mueble.setCantidad(nuevaCantidad);
            mueblesCarrito.remove(comparar);
            mueblesCarrito.add(mueble);
            //mueblesCarrito.set(index, comparar);
        }*/
        mueblesCarrito.add(mueble);
    }
    /**
     * Método para eliminar un mueble del carrito de compras.
     * @param mueble Vatiable tipo mueble a eliminar del listado.
     */
    public void eliminar(Mueble mueble) {
        Mueble eliminado = new Mueble();
        Iterator it;
        it = mueblesCarrito.iterator();
        // Recorre todo el listado comparando el mueble del listado con el mueble que llego.
        while(it.hasNext()){
            eliminado = (Mueble) it.next();
            if(eliminado.getReferencia().equalsIgnoreCase(mueble.getReferencia()))
                break;
        }
        // Elimina el mueble del listado del carrito.
        mueblesCarrito.remove(eliminado);
    }
    /**
     * Método para obtener todos el listado de muebles del carrito
     * @return List Variable tipo List con muebles del carrito.
     */
    public List<Mueble> verMueblesCarrito(){
        return mueblesCarrito;
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
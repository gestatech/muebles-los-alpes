package com.losalpes.ventas;

import com.losalpes.persistence.entity.Mueble;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Servicio Mock que implementa la interfaz con los métodos del Carrito de Compras.
 * @author Memo Toro
 */
public class CarritoServiceMock implements ICarritoService{
    /**
     * Listado de Muebles Static con los muebles del carrito.
     */
    private static List<Mueble> mueblesCarrito = new ArrayList<Mueble>();

    /** Crea una nueva instancia de CarritoServiceMock */
    public CarritoServiceMock() {
    }
    /**
     * Método para gregar un mueble al carrito de compras.
     * @param Vatiable tipo mueble a agregar al listado.
     */
    public void agregar(Mueble mueble) {
        mueblesCarrito.add(mueble);
    }
    /**
     * Método para eliminar un mueble del carrito de compras.
     * @param Vatiable tipo mueble a eliminar del listado.
     */
    public void eliminar(Mueble mueble) {
        Mueble eliminado = new Mueble();
        Iterator it;
        it = mueblesCarrito.iterator();
        // Recorre todo el listado comparando el mueble del listado con el mueble que llego.
        while(it.hasNext()){
            eliminado = (Mueble) it.next();
            if(eliminado.getNombre().equalsIgnoreCase(mueble.getNombre()))
                break;
        }
        // Elimina el mueble del listado del carrito.
        mueblesCarrito.remove(eliminado);
    }
    /**
     * Método para obtener todos el listado de muebles del carrito
     * @return Variable tipo List con muebles del carrito.
     */
    public List<Mueble> ver(){
        return mueblesCarrito;
    }
}
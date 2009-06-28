package com.losalpes.ventas;

import com.losalpes.persistence.entity.Mueble;
import java.util.List;

/**
 * Interfaz con métodos de Carrito de Compras
 * @author Memo Toro
 */
public interface ICarritoService {
    /**
     * Método para gregar un mueble al carrito de compras.
     * @param Vatiable tipo mueble a agregar al listado.
     */
    void agregar(Mueble mueble);
    /**
     * Método para eliminar un mueble del carrito de compras.
     * @param Vatiable tipo mueble a eliminar del listado.
     */
    void eliminar(Mueble mueble);
    /**
     * Método para obtener todos el listado de muebles del carrito
     * @return Variable tipo List con muebles del carrito.
     */
    List<Mueble> ver();
}
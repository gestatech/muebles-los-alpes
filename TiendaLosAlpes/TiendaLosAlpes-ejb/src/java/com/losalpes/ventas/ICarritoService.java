package com.losalpes.ventas;

import com.losalpes.persistence.entity.Mueble;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz con métodos de Carrito de Compras anotada con @Local para definir la forma de acceso de los Bean a la interfaz.
 * @author Memo Toro
 */
@Local
public interface ICarritoService {
    /**
     * Método para gregar un mueble al carrito de compras.
     * @param mueble Vatiable tipo mueble a agregar al listado.
     */
    void agregar(Mueble mueble);
    /**
     * Método para eliminar un mueble del carrito de compras.
     * @param mueble Vatiable tipo mueble a eliminar del listado.
     */
    void eliminar(Mueble mueble);
    /**
     * Método para obtener todos el listado de muebles del carrito
     * @return Variable tipo List con muebles del carrito.
     */
    List<Mueble> verMueblesCarrito();
    /**
     * Método para asignar al carrito el Identificador del cliente autenticado en la aplicación.
     * @param  int Identificador del cliente autenticado.
     */
    void clienteAutenticado(int idCliente);
    /**
     * Método para obtener el id del cliente autenticado
     * @return int con el Id del cliente autenticado en la aplicación
     */
    int obtenerClienteAutenticado();
}
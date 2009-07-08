package com.losalpes.ventas;

import com.losalpes.persistence.entity.DetalleVenta;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz con métodos de Carrito de Compras anotada con @Local para definir la forma de acceso de los Bean a la interfaz.
 * @author Memo Toro
 */
@Local
public interface ICarritoService {
    /**
     * Método para gregar un detalle al carrito de compras.
     * @param detalle Variable tipo detalle a agregar al listado.
     */
    void agregar(DetalleVenta detalle);
    /**
     * Método para eliminar un detalle del carrito de compras.
     * @param detalle Variable tipo detalle a eliminar del listado.
     */
    void eliminar(DetalleVenta detalle);
    /**
     * Método para obtener todos el listado de detalles del carrito
     * @return Variable tipo List con detalles del carrito.
     */
    List<DetalleVenta> verDetallesCarrito();
    /**
     * Método para obtener todos el listado de detalles del carrito
     * @return Variable tipo List con detalles del carrito.
     */
    DetalleVenta obtenerDetalle(int idDetalle);
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
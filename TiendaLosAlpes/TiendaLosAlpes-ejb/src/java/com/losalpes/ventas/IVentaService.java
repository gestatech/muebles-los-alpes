package com.losalpes.ventas;

import com.losalpes.persistence.entity.Venta;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz con métodos de Venta, Anotada con @Local para acceso al Bean.
 * @author Memo Toro
 */
@Local
public interface IVentaService {
    /**
     * Método para crear una Venta
     * @param Variable tipo Venta para crear.
     */
    void crear(Venta venta);
    /**
     * Método para almacenar una Venta a la venta actual y al listado de ventas.
     * @param ventaNueva
     */
    void almacenar(Venta venta);
    /**
     * Método para obtener el listado de ventas de la tienda.
     * @return List con las ventas de la tienda.
     */
    List<Venta> obtenerVentas();
    /**
     * Método para obtener la venta actual.
     * @return Variable tipo Venta.
     */
    Venta obtenerVenta();
    /**
     * Método para enviar correos electrónicos con las librerias de Apache
     */    
    void enviarCorreo();

    List<Venta> obtenerVentasConsultadas(String valor);
}
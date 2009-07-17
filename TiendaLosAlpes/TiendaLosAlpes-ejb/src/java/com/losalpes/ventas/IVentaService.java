package com.losalpes.ventas;

import com.losalpes.persistence.entity.Venta;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz con métodos de Venta, Anotada con @Local para acceso al Session Bean.
 * @author Memo Toro
 */
@Local
public interface IVentaService {
    /**
     * Método para almacenar una Venta a la venta actual y al listado de ventas.
     * @param ventaNueva.
     */
    void almacenar(Venta venta);
    /**
     * Método para obtener el listado de ventas .
     * @return List con las ventas.
     */
    List<Venta> obtenerVentas();
    /**
     * Método para enviar correos electrónicos con las librerias de Apache
     */    
    void enviarCorreo();
    /**
     * Método para consultar las ventas y sus detalles
     */
    List<Venta> consultarVentas(int valor);
}
package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.TipoConsultaMueble;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz con métodos de Catálogo anotada con @Local para indicar su acceso desde los Bean.
 * @author Memo Toro
 */
@Local
public interface ICatalogService {
    /**
     * Método para registrar mueble al catálogo.
     * @param mueble Variable tipo mueble.
     */
    void registrar(Mueble mueble);
    /**
     * Método para eliminar un mueble del catálogo
     * @param mueble Variable tipo mueble
     */
    void eliminar(Mueble mueble);
    /**
     * Método para actualizar los valores de un mueble
     * @param mueble Variable mueble actualizado.
     */
    void actualizar(Mueble mueble);
    /**
     * Método para obtener los muebles consulados como Listado
     * @param criterio Variable tipo TipoConsultaCliente criterio
     * @param consula Variable String para el valor de la consula
     * @return List Variable tipo List de muebles.
     */
    List<Mueble> consultar (TipoConsultaMueble criterio, String valor);
    /**
     * Método que retorna el listado de todos los muebles del catálogo.
     * @return List con los muebles.
     */
    List<Mueble> consultarTodos();
}
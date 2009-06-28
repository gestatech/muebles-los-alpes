package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.TipoConsultaMueble;
import java.util.List;

/**
 * Interfaz con métodos de Catálogo
 * @author Memo Toro
 */
public interface ICatalogService {
    /**
     * Método para registrar mueble al catálogo.
     * @param Variable tipo mueble.
     */
    void registrar(Mueble mueble);
    /**
     * Método que retorna el listado de todos los muebles del catálogo.
     * @return List con los muebles.
     */
    List<Mueble> consultarTodos();
    /**
     * Método para obtener los muebles consulados como Listado
     * @param Variable tipo TipoConsultaCliente criterio
     * @param Variable String para el valor de la consula
     * @return Variable tipo List de muebles.
     */
    List<Mueble> consultar (TipoConsultaMueble criterio, String valor);
    /**
     * Método para eliminar un mueble del catálogo
     * @param Variable tipo mueble
     */
    void eliminar(Mueble mueble);
    /**
     * Método para actualizar los valores de un mueble
     * @param Variable mueble actualizado.
     */
    void actualizar(Mueble mueble);
}
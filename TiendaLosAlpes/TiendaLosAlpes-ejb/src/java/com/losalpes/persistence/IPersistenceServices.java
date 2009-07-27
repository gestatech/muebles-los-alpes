package com.losalpes.persistence;

import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz para acceder a los metodos del Entity Manager para la persistencia.
 * Anotado con @Local para indicar la manera de acceder al Session Bean de persistencia.
 * @author Memo Toro
 */
@Local
public interface IPersistenceServices {
    /**
     * Método para crear objetos en la base de datos.
     * @param obj Variable tipo Object
     */
    public void create(Object obj);
    /**
     * Método para actualizar un objeto en la base de datos.
     * @param obj Variable tipo Object
     */
    public void update(Object obj);
    /**
     * Método para eliminar un objeto de la base de datos.
     * @param obj Variable tipo Object
     */
    public void delete(Object obj);
    /**
     * Método para retornar todos los objetos de una tabla de la base de datos.
     * @param c Clase para consultar.
     * @return List Listado con todos los osbjetos de la tabla.
     */
    public List findAll(Class c);
    /**
     * Método para consultar y retornar objetos de una tabla por medio del Id.
     * @param c Clase para consultar.
     * @param id String Id del Objeto
     * @return Object Variable tipo Object
     */
    public Object findById(Class c, Object id);
    /**
     * Método para cosultar por varios criterios y retornar listas de objetos
     * @param consulta String Criterio de consulta
     * @param valores List con la configuracion de columna, valor para la consulta
     * @return List Listado con los objetos que satisfacen la consulta.
     */
    public List findObjects(String consulta, List<String> valores);
    /**
     * Método para crear el cliente
     * @param objeto parametro de tipo Object
     */
    public void createCliente(Object obj);
    /**
     * Método para realizar la busqueda de la tarjeta de credito del cliente
     * @param id variable de tipo int ue corresponde al numero de documento del cliente
     */
    public Object findRemoteDatabase(int id);
    /**
     * Método para crear una venta con transaccionalidad
     * @param obj de tipo Venta
     */
    public void createVenta(Object obj);
}
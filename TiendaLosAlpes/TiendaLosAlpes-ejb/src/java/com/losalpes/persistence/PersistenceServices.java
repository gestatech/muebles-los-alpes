package com.losalpes.persistence;

import com.losalpes.utils.SQLService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * Sessión Bean con lógica de persistencia. Implementa un Entity Manager para realizar las
 * operaciones sobre la base de datos a nivel de object. Todos los session bean que quieren persistir
 * una entidad invocan este session bean.
 * @author Memo Toro
 */
@Stateless
public class PersistenceServices implements IPersistenceServices {
    /**
     * Variable de Persistence Context para realizar las operaciones de persistencia de entidades @Entity
     */
    @PersistenceContext
    private EntityManager em;
    /**  Crea una nueva instacia de PersistenceServices */
    public PersistenceServices() { }
    /**
     * Método para almacenar entidades.
     * @param obj Object de una entidad anotada con @Entity
     */
    public void create(Object obj) {
        em.persist(obj);
    }
    /**
     * Método para actualizar entidades
     * @param obj Object de una entidad anotada con @Entity
     */
    public void update(Object obj) {
        em.merge(obj);
    }
    /**
     * Método para eliminar entidades
     * @param obj Object de una entidad anotada con @Entity
     */
    public void delete(Object obj) {
        em.remove(obj);
    }
    /**
     * Método para retornar el listado de toda una entidad
     * @param c Class con el tipo de clase a consultar
     * @return List Listado con las instancias de la entidad
     */
    public List findAll(Class c) {
        return em.createQuery("SELECT o FROM "+c.getSimpleName()+" AS o").getResultList();
    }
    /**
     * Método para encontrar un objeto por su Id
     * @param c Class con el tipo de clase a consultar
     * @param id Object como Id para buscar el objeto
     * @return Object Objeto retornado y consultado
     */
    public Object findById(Class c, Object id) {
        return em.find(c, id);
    }
    /**
     * Método para cosultar por varios criterios y retornar listas de objetos
     * @param consulta String Criterio de consulta
     * @param valores List con la configuracion de columna, valor para la consulta
     * @return List Listado con los objetos que satisfacen la consulta.
     */
    public List findObjects(String consulta, List<String> valores){
        // Crea la sentencia con el String que le retorna la clase SQLService
        Query qry = em.createQuery(SQLService.buscarObjetos(consulta, valores));
        // Ejecuta el query
        List temp = qry.getResultList();
        return temp;
    }
}
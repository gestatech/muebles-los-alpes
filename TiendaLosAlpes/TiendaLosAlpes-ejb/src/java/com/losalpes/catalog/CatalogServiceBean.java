package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.IPersistenceServices;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 * Session Bean que implementa la interfaz con los métodos del Catálogo.
 * Bean anotado con @Stateless por no ser necesario guardar los datos del catálogo en sesion.
 * @author Memo Toro
 */
@Stateless
public class CatalogServiceBean implements ICatalogService {
    /**
     * Interfaz anotada como @EJB para que haga referencia e inyección con el Bean de la Persistencia.
     */
    @EJB
    private IPersistenceServices persistencia;
    /** Crea una nueva instancia de CatalogServiceMock */
    public CatalogServiceBean() {}
    /**
     * Método anotado con @PostConstructor para que inicialice los muebles de prueba.
     */
    @PostConstruct
    public void iniciar(){
        System.out.println("CATALOG-SERVICE-BEAN HA SIDO INICIALIZADO !!!");
    }
    /**
     * Método anotado como @PreDestroy para poder avisar antes de la destrucción.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("CATALOG-SERVICE-BEAN DESTRUIDO SATISFACTORIAMENTE !!!");
    }
    /**
     * Método para registrar mueble al catálogo.
     * @param mueble Variable tipo mueble.
     */
    public void registrar(Mueble mueble) {
        persistencia.create(mueble);
    }
    /**
     * Método para obtener los muebles consulados como Listado.
     * @param criterio Variable tipo String.
     * @param consula Variable String para el valor de la consula.
     * @return List Variable tipo List de muebles.
     */
    public List<Mueble> consultar(String criterio, String valor) {
        List<String> valores = new ArrayList<String>();
        List<Mueble> mueblesConsultados = new ArrayList<Mueble>();
        // Creación de arreglos para la consulta con parametros de consulta y valor.
        if(criterio.equalsIgnoreCase("NOMBRE")){
            valores.add("nombre"+"|"+valor);
        }
        else if(criterio.equalsIgnoreCase("REFERENCIA")){
            valores.add("referencia"+"|"+valor);
        }
        else if(criterio.equalsIgnoreCase("TIPO")){
            valores.add("tipo"+"|"+valor.toUpperCase());
        }
        // Llamar al servicio de persistencia de consulta por criterios.
        mueblesConsultados = persistencia.findObjects("findMueble",valores);
        return mueblesConsultados;
    }
    /**
     * Método que retorna el listado de todos los muebles del catálogo.
     * @return List con los muebles.
     */
    public List<Mueble> consultarTodos() {
        return persistencia.findAll(Mueble.class);
    }
    /**
     * Método para eliminar un mueble del catálogo.
     * @param mueble Variable tipo mueble.
     */
    public void eliminar(Mueble mueble) {
        persistencia.delete((Mueble)persistencia.findById(Mueble.class, mueble.getReferencia()));
    }
    /**
     * Método para actualizar los valores de un mueble.
     * @param mueble Variable mueble actualizado.
     */
    public void actualizar(Mueble mueble){
        persistencia.update(mueble);
    }
}
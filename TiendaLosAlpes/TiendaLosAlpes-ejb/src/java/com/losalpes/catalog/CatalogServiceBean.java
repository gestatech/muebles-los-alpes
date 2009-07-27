package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.IPersistenceServices;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
/**
 * Session Bean que implementa la interfaz con los métodos del Catálogo.
 * Bean anotado con @Stateless por no ser necesario guardar los datos del catálogo en sesion.
 * Bean anotado con @WebService para poderlo exponer como servicio web.
 * Bean anotado con @DeclareRoles para asignar los rolesque pueden utilizar este bean.
 * @author Memo Toro
 */
@WebService(name="TiendaWebService",serviceName="TiendaWS")
@Stateless
@DeclareRoles({"Administrador"})
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
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param mueble Variable tipo mueble.
     */
    @RolesAllowed({"Administrador"})
    public void registrar(Mueble mueble) {
        persistencia.create(mueble);
    }
    /**
     * Método para obtener los muebles consulados como Listado.
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * @param criterio Variable tipo String.
     * @param consula Variable String para el valor de la consula.
     * @return List Variable tipo List de muebles.
     */
    @PermitAll
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
     * Anotado con @PermitAll para que puedan a la funcionalidad cualquier rol.
     * Anotado con @WebMethod como metodo publico para operacion de servicio web.
     * @return List con los muebles.
     */
    @WebMethod(operationName="VerMueblesTienda")
    @PermitAll
    public List<Mueble> consultarTodos() {
        return persistencia.findAll(Mueble.class);
    }
    /**
     * Método para eliminar un mueble del catálogo.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param mueble Variable tipo mueble.
     */
    @RolesAllowed({"Administrador"})
    public void eliminar(Mueble mueble) {
        persistencia.delete((Mueble)persistencia.findById(Mueble.class, mueble.getReferencia()));
    }
    /**
     * Método para actualizar los valores de un mueble.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param mueble Variable mueble actualizado.
     */
    @RolesAllowed({"Administrador"})
    public void actualizar(Mueble mueble){
        persistencia.update(mueble);
    }
}
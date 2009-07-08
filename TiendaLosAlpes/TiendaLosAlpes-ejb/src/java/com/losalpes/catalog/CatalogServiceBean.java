package com.losalpes.catalog;

import com.losalpes.persistencia.ITiendaService;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.enums.TipoConsultaMueble;
import java.util.ArrayList;
import java.util.Iterator;
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
     * Interfaz anotada como @EJB para que haga referencia e inyección con el Bean Mock de de la TiendaService.
     */
    @EJB
    private ITiendaService tienda;
    /** Crea una nueva instancia de CatalogServiceMock */
    public CatalogServiceBean() {
    }
    /**
     * Método anotado con @PostConstructor para que inicialice los muebles de prueba.
     */
    @PostConstruct
    public void iniciar(){
        // Llama al contructor de tienda para el llenado de muebles iniciales.
        tienda.crearMuebles();
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
        tienda.registrarMueble(mueble);
    }
    /**
     * Método para obtener los muebles consulados como Listado
     * @param criterio Variable tipo TipoConsultaMueble
     * @param consula Variable String para el valor de la consula
     * @return List Variable tipo List de muebles.
     */
    public List<Mueble> consultar(TipoConsultaMueble criterio, String valor) {
        // Arreglo para alamcenar los muebles que se consulten.
        List<Mueble> mueblesConsultados = new ArrayList<Mueble>();
        // Copia de todo el listado de muebles.
        List<Mueble> muebles = tienda.retornarMuebles();
        Mueble consultado = new Mueble();
        String valorMueble = null;
        Iterator it;
        it = muebles.iterator();
        // Bucle para recorrer el listado de muebles y obtener el mueble
        while(it.hasNext()){
            consultado = (Mueble) it.next();
            if(criterio.equals(TipoConsultaMueble.NOMBRE))
                valorMueble = consultado.getNombre();
            if(criterio.equals(TipoConsultaMueble.REFERENCIA))
                valorMueble = consultado.getReferencia();
            if(criterio.equals(TipoConsultaMueble.TIPO))
                valorMueble = consultado.getTipo().toString();
            if(valorMueble.equalsIgnoreCase(valor))
                // Adiciona los muebles obtenidos al listado de consulta.
                mueblesConsultados.add(consultado);
        }
        // Retorna el listado de muebles de consulta.
        return mueblesConsultados;
    }
    /**
     * Método que retorna el listado de todos los muebles del catálogo.
     * @return List con los muebles.
     */
    public List<Mueble> consultarTodos() {
        return tienda.retornarMuebles();
    }
    /**
     * Método para eliminar un mueble del catálogo
     * @param mueble Variable tipo mueble
     */
    public void eliminar(Mueble mueble) {
        Mueble eliminado = new Mueble();
        // Copia de todo el listado de muebles.
        List<Mueble> muebles = tienda.retornarMuebles();
        Iterator it;
        it = muebles.iterator();
        // Variable boolean para verificar la existencia del cliente.
        boolean existencia = false;
         // Bucle para recorrer el listado de muebles y obtener el mueble
        while(it.hasNext()){
            eliminado = (Mueble) it.next();
            if(eliminado.getReferencia().equalsIgnoreCase(mueble.getReferencia())){
                existencia = true;
                break;
            }
        }
        if(existencia==true){
            System.out.println("MUEBLE EXISTE Y SE BORRARÁ !!!");
            // Elimina el mueble del listado.
            tienda.eliminarMueble(eliminado);
        }
    }
    /**
     * Método para actualizar los valores de un mueble
     * @param mueble Variable mueble actualizado.
     */
    public void actualizar(Mueble mueble){
        tienda.actualizarMueble(mueble);
    }
}
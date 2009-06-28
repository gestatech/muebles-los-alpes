package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.TipoConsultaMueble;
import com.losalpes.persistence.entity.TipoMueble;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Servicio Mock que implementa la interfaz con los métodos del Catálogo.
 * @author Memo Toro
 */
public class CatalogServiceMock implements ICatalogService {
    /**
     * Listado de Muebles Static con los muebles
     */
    private static List<Mueble> muebles = new ArrayList<Mueble>();
    /**
     * Interfaz Mock para operaciones con la tienda.
     */
    private ITiendaService tienda;
    /**
     * Variable  contador para tener control sobre las instancias de esta clase.
     */
    private static int contador;

    /** Crea una nueva instancia de CatalogServiceMock */
    public CatalogServiceMock() {
        contador++;
        // Se crean muebles solo si es una vez que se invoca.
        if(contador==1){
            tienda = new TiendaServiceMock();
            muebles = tienda.crearMuebles();
        }        
    }
    /**
     * Método para registrar mueble al catálogo.
     * @param Variable tipo mueble.
     */
    public void registrar(Mueble mueble) {
        muebles.add(mueble);
    }
    /**
     * Método para obtener los muebles consulados como Listado
     * @param Variable tipo TipoConsultaCliente criterio
     * @param Variable String para el valor de la consula
     * @return Variable tipo List de muebles.
     */
    public List<Mueble> consultar(TipoConsultaMueble criterio, String valor) {
        List<Mueble> mueblesConsultados = new ArrayList<Mueble>();
        Mueble consultado = new Mueble();
        String valorMueble = null;
        TipoMueble tipo;
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
        return muebles;
    }
    /**
     * Método para eliminar un mueble del catálogo
     * @param Variable tipo mueble
     */
    public void eliminar(Mueble mueble) {
        Mueble eliminado = new Mueble();
        Iterator it;
        it = muebles.iterator();
         // Bucle para recorrer el listado de muebles y obtener el mueble
        while(it.hasNext()){
            eliminado = (Mueble) it.next();
            if(eliminado.getNombre().equalsIgnoreCase(mueble.getNombre()))
                break;
        }
        // Elimina el mueble del listado.
        muebles.remove(eliminado);
    }
    /**
     * Método para actualizar los valores de un mueble
     * @param Variable mueble actualizado.
     */
    public void actualizar(Mueble mueble){
        muebles.set(muebles.indexOf(mueble), mueble);
    }
}
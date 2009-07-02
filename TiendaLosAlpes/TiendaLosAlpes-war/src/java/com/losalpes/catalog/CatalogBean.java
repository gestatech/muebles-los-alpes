package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.TipoConsultaMueble;
import com.losalpes.persistence.entity.TipoMueble;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * Managed Bean para controlar el Catalogo de Productos y sus Muebles.
 * @author Memo Toro
 */
public class CatalogBean {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz ICatalogService para los muebles.
     */
    @EJB
    private ICatalogService catalogService;
    /**
     * Listado de mueles del catálogo.
     */
    private List<Mueble> muebles;
    /**
     * Mueble del catálogo
     */
    private Mueble mueble;
    /**
     * TIpo de Consulta de muebles.
     */
    private TipoConsultaMueble criterio;
    /**
     * Valor de la consulta de los muebles.
     */
    private String valor;
    /** Crea una nueva instancia de CatalogBean */
    public CatalogBean() {        
        mueble = new Mueble();
        muebles = new ArrayList<Mueble>();
    }
    /**
     * Método para obtener el valor de la consulta.
     * @return String con el valor de la consulta.
     */
    public String getValor() {
        return valor;
    }
    /**
     * Método para asignar el valor de la consulta.
     * @param String con el valor de la consulta.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }    
    /**
     * Método para obtener el criterio de consulta de muebles.
     * @return TipoConsultaMueble Variable TipoConsultaMueble
     */
    public TipoConsultaMueble getCriterio() {
        return criterio;
    }
    /**
     * Método para establecer el criterio de consulta de muebles.
     * @param criterio Variable tipo TipoConsultaMueble.
     */
    public void setCriterio(TipoConsultaMueble criterio) {
        this.criterio = criterio;
    }
    /**
     * Método para obtener el mueble.
     * @return Mueble Variable de tipo Mueble.
     */
    public Mueble getMueble() {
        return mueble;
    }
    /**
     * Método para asignar el mueble.
     * @param mueble Variable de tipo Mueble .
     */
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }
    /**
     * Método para obtener todo el listado de muebles del catálogo.
     * @return List Variable tipo List con los muebles.
     */
    public List<Mueble> getMuebles() {
        return muebles;
    }
    /**
     * Método para asignar el listado de muebles.
     * @param muebles Variable tipo List de muebles.
     */
    public void setMuebles(List<Mueble> muebles) {
        this.muebles = muebles;
    }
     /**
     * Método para cargar el Listado de la interfaz con los posibles tipos de muebles.
     * @return SelectItem Variable Enum con el TipoMeble.
     */
    public SelectItem[] getTiposMuebles() {
        TipoMueble[] tipos =  TipoMueble.values();
        SelectItem[] sitems = new SelectItem[tipos.length];
        for (int i = 0; i < sitems.length; i++) {
             sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }
    /**
     * Método para cargar el Listado de la interfaz con los posibles criterios de consulta.
     * @return SelectItem Variable Enum con el TipoCriterio
     */
    public SelectItem[] getTipoCriterio() {
        TipoConsultaMueble[] tipos =  TipoConsultaMueble.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
             sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para obtener el listado de los muebles consultados.
     * Adicionalmente asigna el primer mueble cuando la consulta arroja solo un mueble.
     */
    public void getMueblesConsultados(){
        setMuebles(catalogService.consultar(criterio, valor));
        if(getMuebles().size()>0)
            setMueble(getMuebles().get(0));
    }
    /**
     * Método para obtener todos los muebles del catálogo.
     * @return List con todos los muebles de la tienda.
     */
    public List getMueblesTienda() {
        return catalogService.consultarTodos();
    }
    /**
     * Método que registra el mueble en el listado del catálogo.
     * @return String para el redireccionamiento.
     */
    public String getRegistrar() {
        catalogService.registrar(getMueble());
        return getLimpiar();
    }
    /**
     * Método que actualiza un mueble en el catálogo.
     * @return String para el redireccionamiento.
     */
    public String getActualizar(){
        catalogService.actualizar(getMueble());
        return getLimpiar();
    }
    /**
     * Método para limpiar las variables del Managed Bean.
     */
    public String getLimpiar(){
        setMueble(new Mueble());
        setMuebles(null);
        setValor(null);
        return "admin";
    }
    /**
     * Método para Redireccionamiento luego de actualizar los precios del catálogo.
     * @return String de redireccion al menu de administrador
     */
    public String getActualizarPrecios(){
        return getLimpiar();
    }
    /**
     * Método para obtiene un mueble para ser desplegado en detalle.
     * Este método se dispara con un evento gráfico de clic.
     * @param evento Variable tipo evento con un clic.
     */
    public void getDetalleMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        FacesContext contexto = FacesContext.getCurrentInstance();
        String id = evento.getComponent().getClientId(contexto);
        Map parametros = contexto.getExternalContext().getRequestParameterMap();
        setValor((String) parametros.get(id));
        setCriterio(TipoConsultaMueble.REFERENCIA);
        // Busqueda del mueble por medio de la interfaz Mock de Catalogo para obtener el mueble
        setMuebles(catalogService.consultar(criterio, valor));
        if(getMuebles().size()>0)
            // Asigna el primer mueble de la consulta.
            setMueble(getMuebles().get(0));
    }
    /**
     * Método para borrar el mueble por medio del administrador.
     * Este método se dispara con un evento gráfico de clic.
     * @param evento Variable tipo evento con un clic.
     */
    public void getBorrarMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        FacesContext contexto = FacesContext.getCurrentInstance();
        String id = evento.getComponent().getClientId(contexto);
        Map parametros = contexto.getExternalContext().getRequestParameterMap();
        setValor((String) parametros.get(id));
        setCriterio(TipoConsultaMueble.REFERENCIA);
        // Busqueda del mueble por medio de la interfaz Mock de Catalogo para obtener el mueble
        setMuebles(catalogService.consultar(criterio, valor));
        if(getMuebles().size()>0)
            setMueble(getMuebles().get(0));
        // Elimina el mueble del listado de mueble del catálogo.
        catalogService.eliminar(getMueble());
    }
}
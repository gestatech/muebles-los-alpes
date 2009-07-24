package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
/**
 * Backing Bean para controlar el Catalogo de Productos y sus Muebles.
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
     * Mueble del catálogo.
     */
    private Mueble mueble;
    /**
     * String de Consulta de muebles.
     */
    private String criterio;
    /**
     * Valor de la consulta de los muebles.
     */
    private String valor;
    /** Crea una nueva instancia de CatalogBean */
    public CatalogBean() {
        // Inicializa variables.
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
     * Método para obtener el criterio de consulta.
     * @return String Variable con el criterio.
     */
    public String getCriterio() {
        return criterio;
    }
    /**
     * Método para establecer el criterio de consulta.
     * @param criterio String con el criterio.
     */
    public void setCriterio(String criterio) {
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
     * Método para obtener el listado de muebles.
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
     * Método para obtener el listado de los muebles consultados.
     */
    public void getMueblesConsultados(){
        setMuebles(catalogService.consultar(criterio, valor));
    }
    /**
     * Método para obtener todos los muebles del catálogo.
     * @return List con todos los muebles de la tienda.
     */
    public List getMueblesTienda() {
        return catalogService.consultarTodos();
    }
    /**
     * Método que registra el mueble en el catálogo.
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
     * Método para limpiar las variables del Backing Bean de los procesos y las paginas del admin.
     */
    public String getLimpiar(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CatalogBean");
        return "admin";
    }
    /**
     * Método para limpiar las variables del Backing Bean desde la compra y las paginas del cliente.
     */
    public String getLimpiarCompra(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CarritoCompraBean");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CatalogBean");
        return "login";
    }
    /**
     * Método para actualizar los precios del catálogo.
     * Este método se dispara con un evento gráfico de clic.
     * @param evento Variable de tipo evento para capturar los parametros.
     */
    public void getActualizarPrecios(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("referencia");
        String referencia = componente.getValue().toString();
        componente = (UIParameter) evento.getComponent().findComponent("cantidad");
        int cantidad = Integer.valueOf(componente.getValue().toString()).intValue();
        componente = (UIParameter) evento.getComponent().findComponent("precio");
        double precio = Double.valueOf(componente.getValue().toString()).doubleValue();
        setValor(referencia);
        setCriterio("REFERENCIA");
        // Busqueda del mueble por medio de la interfaz de Catalogo para obtener el mueble
        List<Mueble> mueblePrecio = catalogService.consultar(getCriterio(),getValor());
        if(mueblePrecio.size()>0){
            // Asigna el primer mueble de la consulta.
            setMueble(mueblePrecio.get(0));
            getMueble().setPrecio(precio);
            getMueble().setCantidad(cantidad);
            catalogService.actualizar(getMueble());
        }
    }
    /**
     * Método para obtiene un mueble para ser desplegado en detalle.
     * Este método se dispara con un evento gráfico de clic.
     * @param evento Variable tipo evento con un clic.
     */
    public void getDetalleMueble(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("referencia");
        String referencia = componente.getValue().toString();
        setValor(referencia);
        setCriterio("REFERENCIA");
        // Busqueda del mueble por medio de la interfaz de Catalogo para obtener el mueble
        setMuebles(catalogService.consultar(getCriterio(),getValor()));
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
        UIParameter componente = (UIParameter) evento.getComponent().findComponent("referencia");
        String referencia = componente.getValue().toString();
        setValor(referencia);
        setCriterio("REFERENCIA");
        // Busqueda del mueble por medio de la interfaz de Catalogo para obtener el mueble
        setMuebles(catalogService.consultar(getCriterio(),getValor()));
        if(getMuebles().size()>0){
            setMueble(getMuebles().get(0));
            // Elimina el mueble del listado de mueble del catálogo.
            catalogService.eliminar(getMueble());
        }
    }
}
package com.losalpes.catalog;

import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.Promocion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
/**
 * Backing Bean para el manejo de promociones.
 * @author Kerlyn Hans
 * @author Memo Toro
 */
public class PromocionBean {
    /**
     * Interfaz anotada con @EJB para inyeccion de dependencias de IPromocionServices.
     */
    @EJB
    private IPromocionServices promocionServices;
    /**
     * Interfaz anotada con @EJB para inyeccion de dependencias de ICatalogService.
     */
    @EJB
    private ICatalogService catalogService;
    /**
     * List de promociones.
     */
    private List<Promocion> promociones;
    /**
     * Promocion.
     */
    private Promocion promocion;
    /**
     * Variable para la fecha inicial
     */
    private Date fechainicial;
    /**
     * Variable para la fecha fin
     */
    private Date fechafinal;
    /** Crea una nueva instancia de PromocionBean */
    public PromocionBean() {}
    /**
     * Métdo anotado como @PostConstruc para inicializar variables de backing bean.
     */
    @PostConstruct
    public void init() {
        // Inicializa las variables
        promociones = promocionServices.findAll();
        promocion = new Promocion();
    }
    /**
     * Método para crear la promocion.
     */
    public String create() {
        // Parseo de fechas a formato string de dia mes año
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // Asignacion a la promocion de la fecha en el formato indicado.
        promocion.setFechaInicio(df.format(fechainicial.getTime()));
        promocion.setFechaFin(df.format(fechafinal.getTime()));
        promocionServices.setPromocion(promocion);
        promocionServices.create();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("PromocionBean");
        return "admin";
    }
    /**
     * Método para asignar la promocion.
     * @return Promocion Variable de tipo Promocion.
     */
    public Promocion getPromocion(){
        return(promocion);
    }
    /**
     * Método para signar la promocion.
     * @param promocion Tipo Promocion.
     */
    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
    /**
     * Método para obtener la variable fecha final
     * @return Date con la fecha inicial
     */
    public Date getFechafinal() {
        return fechafinal;
    }
    /**
     * Método para asignar la fecha final
     * @param fechafinal
     */
    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }
    /**
     * Método para obtener la variable fecha inicial
     * @return Date con la fecha inicial
     */
    public Date getFechainicial() {
        return fechainicial;
    }
    /**
     * Método para asignar la fecha inicial
     * @param fechainicial
     */
    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }
     /**
     * Método para obtener todas las promociones.
     * @return List con todas las promociones de la tienda.
     */
    public List getAllPromotions(){
        return promociones;
    }
    /**
     * Método para cargar el Listado de la interfaz con los muebles.
     * @return List SelectItem Variable List con los Muebles.
     */
    public List<SelectItem> getMuebles() {
        List<SelectItem> sItems = new ArrayList<SelectItem>();
        Iterator it = catalogService.consultarTodos().iterator();
        while(it.hasNext()){
            Mueble temp = (Mueble) it.next();
            sItems.add(new SelectItem((String)temp.getNombre()+"|"+String.valueOf(temp.getTipo().toString()),(String)temp.getNombre()+" de Tipo: "+(String)temp.getTipo()));
        }
        return sItems;
    }
}
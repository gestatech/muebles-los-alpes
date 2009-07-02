package com.losalpes.ventas;

import com.losalpes.catalog.ICatalogService;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.TipoConsultaMueble;
import com.losalpes.persistence.entity.Venta;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;

/**
 * Managed Bean para manejar las ventas y su despliegue en los reportes
 * @author Memo Toro
 */
public class ReporteVentas {
    /**
     * Interfaz anotada con @EJB para hacer referencia a la interfaz IVentaService
     */
    @EJB
    private IVentaService ventaService;
    /**
     * Interfaz anotada con @EJB para hacer referencia a la interfaz ICatalogService
     */
    @EJB
    private ICatalogService catalogService;
    /**
     * Variable para el valor de la consulta
     */
    private String valor;
    /**
     * Variable tipo venta
     */
    private Venta venta;
    /**
     * List de las ventas obtenidas
     */
    private List<Venta> ventas;
    /**
     * List de los muebles de cada venta
     */
    private List<Mueble> muebles;
    /** Crea una nueva instancia de ReporteVentas*/
    public ReporteVentas() {
    }
    /**
     * Método para obtener la venta
     * @return Venta Variable de tipo venta.
     */
    public Venta getVenta() {
        return venta;
    }
    /**
     * Método para asiganar el valor de la consulta.
     * @return String variable de tipo String
     */
    public String getValor() {
        return valor;
    }
    /**
     * Método  para asignar el valor de la consulta
     * @param valor Valor de la consulta.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    /**
     * Método para asignar la venta
     * @param venta Variable tipo venta.
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    /**
     * Método para obtener el listado de ventas
     * @return List Listado de ventas
     */
    public List<Venta> getVentas() {
        return ventas;
    }
    /**
     * Método para asignar las ventas
     * @param ventas Variable tipo venta en List.
     */
    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    /**
     * Método para obtener el listado de muebles de una venta.
     * @return List de Muebles
     */
    public List<Mueble> getMuebles() {
        return muebles;
    }
    /**
     * Método par asignar los muebles
     * @param muebles Listo de muebles
     */
    public void setMuebles(List<Mueble> muebles) {
        this.muebles = muebles;
    }
    /**
     * Método para obtener la consulta de ventas a partir de una fecha.
     */
    public void getConsultarFecha(){
        // Asigna las ventas el valor de la venta consultada por la fecha asignada
       setVentas(ventaService.obtenerVentasConsultadas(valor));
       Iterator it = getVentas().iterator();
       // Recorre el Listado con un bucle para cargar los diferentes muebles de cada venta.
       while(it.hasNext()){
            List<String> refs = ((Venta)it.next()).getReferenciasMuebles();
            Iterator itRefs = refs.iterator();
            while(itRefs.hasNext()){
                muebles.add(catalogService.consultar(TipoConsultaMueble.REFERENCIA,((String)itRefs.next())).get(0));
            }
       }
    }
    /**
     * Método que obtiene todas las ventas
     * @return List contodas las ventas.
     */
    public List<Venta> getTodasVentas(){
        return ventaService.obtenerVentas();
    }    
}
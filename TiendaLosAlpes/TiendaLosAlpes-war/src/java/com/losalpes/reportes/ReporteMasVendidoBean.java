package com.losalpes.reportes;

import java.util.Date;
import java.util.List;
import com.losalpes.reportes.pojos.ReporteMasVendido;
import javax.ejb.EJB;
/**
 * Baacking Bean para el manejo y despliegue del reporte de producto mas vendido.
 * @author Memo Toro
 */
public class ReporteMasVendidoBean {
    /**
     * Interfaz anotada con @EJB para inyeccion de dependencia de IReporteMasVendidoService.
     */
    @EJB
    private IReporteMasVendidoService reporteMasVendidoService;
    /**
     * Date para la fecha de inicio.
     */
    private Date fechaInicio;
    /**
     * Date para la fecha de fin.
     */
    private Date fechaFin;
    /**
     * List para el reporte de muebles mas vendidos.
     */
    private List<ReporteMasVendido> mueblesMasVendidos;
    /**
     * Reporte para el reporte mas vendido individualmente.
     */
    private ReporteMasVendido reporte;
    /** Crea una nueva instancia de ReporteMasVendidoBean */
    public ReporteMasVendidoBean() {
    }
    /**
     * Método para retornar el mueble mas vendido.
     * @return ReporteMasVendido Reporte del mueble mas vendido.
     */
    public ReporteMasVendido getMasVendido() {
        return reporte;
    }
    /**
     * Método par asignar el mueble mas vendido.
     * @param reporte El mueble mas vendido.
     */
    public void setMasVendido(ReporteMasVendido reporte) {
        this.reporte = reporte;
    }
    /**
     * Método para retornar el listado de reportes de ventas de mas vendidos.
     * @return List con Reportes de Ventas de mas Vendidos.
     */
    public List<ReporteMasVendido> getMueblesMasVendidos() {
        return mueblesMasVendidos;
    }
    /**
     * Método para asignar el listado de muebles mas vendidos.
     * @param mueblesMasVendidos Listado de muebles mas vendidos.
     */
    public void setMueblesMasVendidos(List<ReporteMasVendido> mueblesMasVendidos) {
        this.mueblesMasVendidos = mueblesMasVendidos;
    }
    /**
     * Método para retornar la fecha de fin.
     * @return Date con fecha de fin.
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    /**
     * Método par asignar la fecha de fin.
     * @param fechaFin Date con fecha de fin.
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    /**
     * Método para obtener la fecha de inicio.
     * @return Date con la fecha de inicio.
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Método para signar la fecha de inicio.
     * @param fechaInicio Fecha de inicio.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Método para retornar e invocar la consulta del producto mas vendido.
     * @return List con los reportes de productos mas vendidos.
     */
    public List<ReporteMasVendido> getMasVendidos(){
        // Asignarción al Listado de muebles mas vendidos.
        mueblesMasVendidos = reporteMasVendidoService.obtenerProductoMasVendido(fechaInicio, fechaFin);
        return mueblesMasVendidos;
    }
}
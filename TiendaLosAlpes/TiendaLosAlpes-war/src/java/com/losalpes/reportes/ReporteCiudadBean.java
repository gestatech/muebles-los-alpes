package com.losalpes.reportes;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.reportes.pojos.ReporteCiudad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
/**
 * Backing Bean para los reportes de Ciudad.
 * @author Hans Escallon
 * @author Memo Toro
 */
public class ReporteCiudadBean {
    /**
     * Intefaz anotada con @EJB para inyectar codigo de interfaz IReporteCiudadService.
     */
    @EJB
    private IReporteCiudadService reporteCiudadService;
    /**
     * Variable de tipo reporte ciudad.
     */
    private ReporteCiudad reporteCiudad;
    /**
     * Variable Date para la fecha.
     */
    private Date fechaCalendario;
    /**
     * Variable String para la fecha.
     */
    private String fecha;
    /**
     * List con el listado de los reportes de ciudad.
     */
    private List<ReporteCiudad> lreporteCiudad = new ArrayList<ReporteCiudad>();

    /** Crea una nueva instancia de ReporteCiudadBean */
    public ReporteCiudadBean() {
       reporteCiudad = new ReporteCiudad();
       fechaCalendario = new Date();
    }
    /**
     * Método para obtener la fecha
     * @return String con la fecha
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Método para asignar la fecha.
     * @param fecha String para la fecha.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Método para obtener la fecha del calendario
     * @return
     */
    public Date getFechaCalendario() {
        return fechaCalendario;
    }
    /**
     * Método para asignar la fecha del calendario
     * @param fechaCalendario
     */
    public void setFechaCalendario(Date fechaCalendario) {
        this.fechaCalendario = fechaCalendario;
    }

    /**
     * M;etodo para obtener el reporte
     * @return ReporteCiudad Variable de tipo reporte.
     */
    public ReporteCiudad getReporteCiudad() {
        return reporteCiudad;
    }
    /**
     * Método para asignar el reporte ciudad
     * @param reporteCiudad Reporte de la ciudad
     */
    public void setReporteCiudad(ReporteCiudad reporteCiudad) {
        this.reporteCiudad = reporteCiudad;
    }
    /**
     * Método para obtener las ventas diarias por ciudad.
     * @return List con todas las ventas diarias por ciudad.
     */
    public List<ReporteCiudad> getReporteDiario(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        fecha = df.format(fechaCalendario.getTime());
        lreporteCiudad = reporteCiudadService.obtenerVentasDiarias(reporteCiudad.getPais(),reporteCiudad.getDepartamento(),
                                                                   reporteCiudad.getCiudadResidencia(),fecha);
        return lreporteCiudad;
    }
     /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getPaises() {
        TipoPais[] tipos = TipoPais.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getDepartamentos() {
        TipoDepartamento[] tipos = TipoDepartamento.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getCiudadResidencia() {
        TipoCiudad[] tipos = TipoCiudad.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
}
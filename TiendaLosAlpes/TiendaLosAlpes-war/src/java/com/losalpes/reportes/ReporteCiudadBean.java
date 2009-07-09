package com.losalpes.reportes;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.reportes.pojos.ReporteCiudad;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

/**
 *
 * @author usuario
 */

public class ReporteCiudadBean {
    /**
     * Intefaz anotada con @EJB para inyectar codigo de interfaz IReporteCiudadService.
     */
    @EJB
    private IReporteCiudadService reporteCiudadService;

    private ReporteCiudad reporteCiudad;
    private String fecha;
    private List<ReporteCiudad> lreporteCiudad = new ArrayList<ReporteCiudad>();

    /** Crea una nueva instancia de ReporteCiudadBean */
    public ReporteCiudadBean() {
       reporteCiudad = new ReporteCiudad();
        //venta = ventaService.obtenerVenta();
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ReporteCiudad getReporteCiudad() {
        return reporteCiudad;
    }

    public void setReporteCiudad(ReporteCiudad reporteCiudad) {
        this.reporteCiudad = reporteCiudad;
    }
    /**
     * Método para obtener las ventas diarias por ciudad
     * @return List con todas las ventas diarias por ciudad.
     */
    public List<ReporteCiudad> getReporteDiario(){        
        lreporteCiudad = reporteCiudadService.obtenerVentasDiarias(reporteCiudad.getPais(),reporteCiudad.getDepartamento(),
                                                                   reporteCiudad.getCiudadResidencia(),fecha);
        Iterator it = lreporteCiudad.iterator();
        while(it.hasNext()){
            ReporteCiudad rc = (ReporteCiudad)it.next();
            System.out.println(rc.getCiudadResidencia());
        }
      return lreporteCiudad;//reporteCiudadService.obtenerVentasDiarias(reporteCiudad.getPais(),reporteCiudad.getDepartamento(),reporteCiudad.getCiudadResidencia());

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
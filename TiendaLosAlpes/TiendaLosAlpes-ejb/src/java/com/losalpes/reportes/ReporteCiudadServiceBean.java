package com.losalpes.reportes;

import com.losalpes.reportes.IReporteCiudadService;
import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.reportes.pojos.*;
import com.losalpes.ventas.IVentaService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 *
 * @author usuario
 */
@Stateless
public class ReporteCiudadServiceBean implements IReporteCiudadService {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para los ventas.
     */
    @EJB
    private IVentaService ventaService;
    private List ventas;
    private List<ReporteCiudad> reporteCiudad;
    /**
     * Método para obtener el reporte con las ventas diarias por ciudad
     * @param ventaNueva Variable tipo Venta para crear.
     */
    public List<ReporteCiudad> obtenerVentasDiarias(TipoPais pais,TipoDepartamento departamento,TipoCiudad ciudadResidencia,String fecha) {
        reporteCiudad = new ArrayList<ReporteCiudad>();
        ventas = ventaService.obtenerVentas();
        ReporteCiudad reporteNuevo = null;
        Iterator itVentas = ventas.iterator();
        List<DetalleVenta> detallesVenta;
        Venta venta;
        DetalleVenta detalleVenta;
        while(itVentas.hasNext()){
            venta = (Venta)itVentas.next();
            if(venta.getPais().equals(pais) && venta.getDepartamento().equals(departamento) && venta.getCiudad().equals(ciudadResidencia)
               && venta.getFechaGeneracion().equals(fecha)){
                ReporteCiudad report = existeVentaCiudad(pais, departamento, ciudadResidencia);
                if(report == null){
                    reporteNuevo = new ReporteCiudad();
                    reporteNuevo.setPais(pais);
                    reporteNuevo.setDepartamento(departamento);
                    reporteNuevo.setCiudadResidencia(ciudadResidencia);
                }
                else{
                    reporteNuevo = report;
                }
                /**
                * Obtiene la lista de detalles de venta y las almacena en el reporte.
                */
                detallesVenta = venta.getDetalleVenta();
                Iterator itDetalle = detallesVenta.iterator();
                while(itDetalle.hasNext()){
                    detalleVenta = (DetalleVenta)itDetalle.next();
                    reporteNuevo.setAdicionarDetalleVenta(detalleVenta);
                }
                reporteCiudad.add(reporteNuevo);
            }
        }
        return reporteCiudad;
    }
    /**
     * Método para verificar si la ciudad ya tiene un registro en el reporte de ventas diarias por ciudadd
     * @param pais, departamento y ciudad para buscar la ciudad en el reporte de ventas diarias.
     */
    public ReporteCiudad existeVentaCiudad(TipoPais pais,TipoDepartamento departamento,TipoCiudad ciudadResidencia){
        Iterator itReporte = reporteCiudad.iterator();
        while(itReporte.hasNext()){
            ReporteCiudad reporte = (ReporteCiudad)itReporte.next();
            if(reporte.getPais().equals(pais) && reporte.getDepartamento().equals(departamento) && reporte.getCiudadResidencia().equals(ciudadResidencia)){
                return reporte;
            }
        }
        return null;
    }
    public List<ReporteCiudad> obtenerProductoMasVendido(TipoPais pais,TipoDepartamento departamento,TipoCiudad ciudadResidencia,String fecha) {
        return null;
    }
}

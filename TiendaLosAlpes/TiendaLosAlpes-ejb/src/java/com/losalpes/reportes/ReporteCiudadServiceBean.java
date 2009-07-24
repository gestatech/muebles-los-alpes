package com.losalpes.reportes;

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
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 * Session Bean sin estado @Stateless para indicar la no persistena de datos de reporte.
 * @author Hans Escallon
 */
@Stateless
@DeclareRoles({"Gerente"})
public class ReporteCiudadServiceBean implements IReporteCiudadService {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para los ventas.
     */
    @EJB
    private IVentaService ventaService;
    /**
     * Listado de ventas.
     */
    private List ventas;
    /**
     * Listado de reportes de ciudad.
     */
    private List<ReporteCiudad> reporteCiudad;
    /**
     * Método para obtener el reporte con las ventas diarias por ciudad
     * Anotado con @RolesAllowed para que pueda solo acceder el Gerente a la funcionalidad
     * @param ventaNueva Variable tipo Venta para crear.
     */
    @RolesAllowed({"Gerente"})
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
                // Obtiene la lista de detalles de venta y las almacena en el reporte.
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
     * Método para verificar si la ciudad ya tiene un registro en el reporte de ventas diarias por ciudad.
     * Anotado con @RolesAllowed para que pueda solo acceder el Gerente a la funcionalidad
     * @param pais, departamento y ciudad para buscar la ciudad en el reporte de ventas diarias.
     */
    @RolesAllowed({"Gerente"})
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
}
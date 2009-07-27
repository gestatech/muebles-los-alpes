package com.losalpes.reportes;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.persistence.entity.Venta;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import com.losalpes.reportes.pojos.ReporteMasVendido;
import com.losalpes.ventas.IVentaService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 * Session Bean anotado con @Stateless al no mantener información para la lógica de consulta de
 * producto mas vendido en un rango de fechas determinado.
 * Bean anotado con @DeclareRoles para asignar los rolesque pueden utilizar este bean.
 * @author Memo Toro
 */
@Stateless
@DeclareRoles({"Gerente"})
public class ReporteMasVendidoServiceBean  implements IReporteMasVendidoService {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para los ventas.
     */
    @EJB
    private IVentaService ventaService;
    /**
     * Método que se encarga de retornar un objeto POJO de este tipo de reporte.
     * Anotado con @RolesAllowed para que pueda solo acceder el Gerente a la funcionalidad
     * @param fechaInicial Fecha inicial de consulta
     * @param fechaFinal Fecha Final de consulta
     * @return List Listado de reporte de mas vendidos por ciudad para un rango de fechas.
     */
    @RolesAllowed({"Gerente"})
    public List<ReporteMasVendido> obtenerProductoMasVendido(Date fechaInicial, Date fechaFinal) {
        // Creación de variables necesarias para el reporte.
        List<ReporteMasVendido> reporte = new ArrayList<ReporteMasVendido>();
        List<DetalleVenta> detallesVenta;
        DetalleVenta detalleVenta;
        Venta venta;
        List<Venta> ventas = ventaService.obtenerVentas();
        Iterator itVentas = ventas.iterator();
        // Variable controladora de la canitidad maxima de muebles vendidos.
        int cantidadMaxima = 0;
        // Variable controladora del recorrido de las diferentes ciudades.
        int contadorCiudad = 0;
        // Arreglo de ciudades.
        TipoCiudad ciudad[] = TipoCiudad.values();
        // Bucle que recorrerá todas las ciudades una a una.
        while(contadorCiudad<ciudad.length){
            // Variable de reporte mas vendido.
            ReporteMasVendido reporteMasVendido = new ReporteMasVendido();
            // Variable que recorrerá todas las ventas por ciudad.
            while(itVentas.hasNext()){
                venta = (Venta)itVentas.next();
                // Verifica que sea la ciudad de interes para cada venta.
                if(venta.getCiudad().equals(ciudad[contadorCiudad])){
                    // Parser de fecha.
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaConsulta = new Date();
                    try {
                        fechaConsulta = df.parse(venta.getFechaGeneracion());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    // Verifica si la fecha de la venta está en el rango establecido como minimo y maximo para la consulta.
                    if(fechaConsulta.getTime()-fechaInicial.getTime()>0 && fechaFinal.getTime()-fechaConsulta.getTime()>0){
                        // Inicializa el reporte 
                        reporteMasVendido.setPais(venta.getPais());
                        reporteMasVendido.setDepto(venta.getDepartamento());
                        reporteMasVendido.setCiudad(venta.getCiudad());
                        detallesVenta = venta.getDetalleVenta();
                        Iterator itDetalle = detallesVenta.iterator();
                        // Bucle que recorre todos los detalles de la venta de interés.
                        while(itDetalle.hasNext()){
                            detalleVenta = (DetalleVenta) itDetalle.next();
                            // Verifica la cantidad de muebles vendidos en la venta.
                            if(cantidadMaxima<=detalleVenta.getCantidadVenta()){
                                cantidadMaxima = detalleVenta.getCantidadVenta();
                                // Asignar la cantidad y el mueble al objeto reporte
                                reporteMasVendido.setCantidad(cantidadMaxima);
                                reporteMasVendido.setMueble(detalleVenta.getMuebleVendido());
                            }
                        }
                    }
                }
            }
            // Verifica que no se cargue reportes vacios
            if(reporteMasVendido.getMueble()!=null)
                reporte.add(reporteMasVendido);
            // Aumenta la variable de ciudad para cambiar de ciudad
            contadorCiudad++;
            // Inicializa el contador de maximos muebles para la siguiente ciudad.
            cantidadMaxima = 0;
            // Reasigna el iterador de ventas para que recoorra nuevamente todas las ventas, pero ahora con la siguiente ciudad.
            itVentas = ventas.iterator();
        }
        return reporte;
    }
}
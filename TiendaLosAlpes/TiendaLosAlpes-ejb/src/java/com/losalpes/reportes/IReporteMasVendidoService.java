package com.losalpes.reportes;

import java.util.List;
import javax.ejb.Local;
import java.util.Date;
import com.losalpes.reportes.pojos.ReporteMasVendido;
/**
 * Interfaz anotada con @Local para dar accedo al Session Bean de consultas de este tipo de reporte.
 * @author Memo Toro
 */
@Local
public interface IReporteMasVendidoService {
    /**
     * Método que se encarga de retornar un objeto POJO de este tipo de reporte.
     * @param fechaInicial Fecha inicial de consulta
     * @param fechaFinal Fecha Final de consulta
     * @return List Listado de reporte de mas vendidos por ciudad para un rango de fechas.
     */
    public List<ReporteMasVendido> obtenerProductoMasVendido(Date fechaInicial, Date fechaFinal);
}
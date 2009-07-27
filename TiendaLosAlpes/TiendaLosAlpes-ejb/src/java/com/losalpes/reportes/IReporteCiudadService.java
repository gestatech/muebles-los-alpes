package com.losalpes.reportes;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.reportes.pojos.ReporteCiudad;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz anotada con @Local para definir el acceso al Session Bean de reporte ciudad.
 * @author Hans Escallon
 */
@Local
public interface IReporteCiudadService {
    /**
     * Método para obtener el reporte con las ventas diarias por ciudad
     * @param pais, departamento y ciudad para buscar las ventas de la ciudad y generar el reporte.
     */
    public List<ReporteCiudad> obtenerVentasDiarias(TipoPais pais,TipoDepartamento departamento,TipoCiudad ciudadResidencia,String fecha);
    /**
     * Método para verificar si la ciudad ya tiene un registro en el reporte de ventas diarias por ciudadd
     * @param pais, departamento y ciudad para buscar la ciudad en el reporte de ventas diarias.
     */
    public ReporteCiudad existeVentaCiudad(TipoPais pais,TipoDepartamento departamento,TipoCiudad ciudadResidencia);
}
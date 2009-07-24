package com.losalpes.reportes.pojos;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.persistence.entity.DetalleVenta;
import java.util.ArrayList;
import java.util.List;
/**
 * POJO de Reportes Por Ciudad.
 * @author Hans Escallon
 */
public class ReporteCiudad {
    /**
     * TipoPais para el pais
     */
    private TipoPais pais;
    /**
     * TipoDepartamento para el departamento.
     */
    private TipoDepartamento departamento;
    /**
     * TipoCiudad para el tipo de ciudad.
     */
    private TipoCiudad ciudadResidencia;
    /**
     * String para el tipo de mueble.
     */
    private String tipoMueble;
    /**
     * List para los detalles de venta.
     */
    List<DetalleVenta> detallesVenta = new ArrayList<DetalleVenta>();
    /** Crea una nueva instancia de ReporteCiudad */
    public ReporteCiudad() {}
    /**
     * Método para obtener la ciudad de residencia.
     * @return TipoCiudad ciudad de residencia.
     */
    public TipoCiudad getCiudadResidencia() {
        return ciudadResidencia;
    }
    /**
     * Método para asignar la ciudad de residencia.
     * @param ciudadResidencia TipoCiudad para la ciudad de residencia.
     */
    public void setCiudadResidencia(TipoCiudad ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }
    /**
     * Método para obtener el departamento.
     * @return TipoDepartamento Deparamento.
     */
    public TipoDepartamento getDepartamento() {
        return departamento;
    }
    /**
     * Método para asignar el el tipo de departamento.
     * @param departamento Tipo de Departamento.
     */
    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }
    /**
     * Método para el Listado de detalles de venta.
     * @return List Detalles de Venta.
     */
    public List<DetalleVenta> getDetallesVenta() {
        return detallesVenta;
    }
    /**
     * Método para asignar el detalle de venta.
     * @param detallesVenta Listado de detalle de venta.
     */
    public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }
    /**
     * Método para asignar los detalles de venta.
     * @param detalleVenta Detalles de Venta.
     */
    public void setAdicionarDetalleVenta(DetalleVenta detalleVenta) {
        detallesVenta.add(detalleVenta);
    }
    /**
     * Método para retornar el tipo de pais.
     * @return TipoPais con el Pais.
     */
    public TipoPais getPais() {
        return pais;
    }
    /**
     * Método para asignar el TIpoPais.
     * @param pais Tipo Pais.
     */
    public void setPais(TipoPais pais) {
        this.pais = pais;
    }
    /**
     * Método para obtener el tipo de mueble
     * @return String Tipo de mueble.
     */
    public String getTipoMueble() {
        return tipoMueble;
    }
    /**
     * Método para asignar el tipo de mueble.
     * @param tipoMueble Tipo de Mueble.
     */
    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }
}
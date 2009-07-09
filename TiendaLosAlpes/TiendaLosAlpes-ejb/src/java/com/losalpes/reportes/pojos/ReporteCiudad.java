/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.losalpes.reportes.pojos;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoMueble;
import com.losalpes.enums.TipoPais;
import com.losalpes.persistence.entity.DetalleVenta;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO de Reportes Por Ciudad
 * @author Hans Escallon
 */
public class ReporteCiudad {
    private TipoPais pais;
    private TipoDepartamento departamento;
    private TipoCiudad ciudadResidencia;
    private TipoMueble tipoMueble;
    List<DetalleVenta> detallesVenta = new ArrayList<DetalleVenta>();

    public TipoCiudad getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(TipoCiudad ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public TipoDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }

    public List<DetalleVenta> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }
    public void setAdicionarDetalleVenta(DetalleVenta detalleVenta) {
        detallesVenta.add(detalleVenta);
    }

    public TipoPais getPais() {
        return pais;
    }

    public void setPais(TipoPais pais) {
        this.pais = pais;
    }

    public TipoMueble getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(TipoMueble tipoMueble) {
        this.tipoMueble = tipoMueble;
    }
}






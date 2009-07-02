/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.losalpes.ventas;

import com.losalpes.persistence.entity.Venta;
import javax.ejb.EJB;

/**
 *
 * @author Memo Toro
 */
public class ReporteVentas {
    /**
     *
     */
    @EJB
    private IVentaService ventaService;
    /** Crea una nueva instancia de ReporteVentas*/
    private Venta venta;

    public ReporteVentas() {
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
}

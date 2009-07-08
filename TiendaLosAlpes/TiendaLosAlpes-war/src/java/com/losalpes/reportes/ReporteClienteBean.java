package com.losalpes.reportes;

import com.losalpes.cliente.IClienteService;
import com.losalpes.enums.TipoConsultaCliente;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.ventas.IVentaService;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

/**
 * Backing Bean asociado para la parte de reportes por cliente y sus ventas.
 * @author Memo Toro
 */

public class ReporteClienteBean {
    private TipoConsultaCliente criterio;
    private String valor;
    private String fecha;
    private List<Venta> ventas;
    private List<DetalleVenta> detalles;
    private DetalleVenta detalleVenta;
    private Cliente cliente;
    @EJB
    private IClienteService clienteService;
    /** Crea una nueva instancia de ReporteClienteBean */
    public ReporteClienteBean() {
    }

    public TipoConsultaCliente getCriterio() {
        return criterio;
    }

    public void setCriterio(TipoConsultaCliente criterio) {
        this.criterio = criterio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }    
    /**
     * Método para cargar el Listado de la interfaz con los posibles criterios de consulta.
     * @return SelectItem Variable Enum con el TipoCriterio
     */
    public SelectItem[] getTipoCriterio() {
        TipoConsultaCliente[] tipos =  TipoConsultaCliente.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
             sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }

    public void getConsultarVentas(){
        cliente = clienteService.consultar(getCriterio(),getValor());
        if(cliente!=null){
            if(cliente.getVentas()==null)
                ventas=null;
            else{
                ventas = cliente.getVentas();
                if(ventas.size()>0){
                    Iterator it = ventas.iterator();
                    while(it.hasNext())
                        detalles = ((Venta)it.next()).getDetalleVenta();
                }
                else
                    detalles = null;
            }
        }
    }
}
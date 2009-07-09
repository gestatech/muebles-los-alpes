package com.losalpes.reportes;

import com.losalpes.cliente.IClienteService;
import com.losalpes.enums.TipoConsultaCliente;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.persistence.entity.Venta;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
/**
 * Backing Bean asociado para la parte de reportes por cliente y sus ventas.
 * @author Memo Toro
 */
public class ReporteClienteBean {
    /**
     * Interfaz anotada con@EJB para inyectar la referencia a IClienteservice
     */
    @EJB
    private IClienteService clienteService;
    /**
     * Variable de tipo de cirterio de consulta
     */
    private TipoConsultaCliente criterio;
    /**
     * String para el valor de la consulta
     */
    private String valor;
    /**
     * String para la fecha de compra.
     */
    private String fecha;
    /**
     * Listado de ventas que tenga asociadas el cliente.
     */
    private List<Venta> ventas;
    /**
     * Listado de Detalles de venta de una venta.
     */
    private List<DetalleVenta> detalles;
    /**
     * Detalle de venta de una venta.
     */
    private DetalleVenta detalleVenta;
    /**
     * Variable de tipo Cliente.
     */
    private Cliente cliente;
    /** Crea una nueva instancia de ReporteClienteBean */
    public ReporteClienteBean() {
    }
    /**
     * Método para obtener el tipo de criterio
     * @return TipoConsultaCliente
     */
    public TipoConsultaCliente getCriterio() {
        return criterio;
    }
    /**
     * Método para asignar el tipo de criterio
     * @param criterio Tipo de Criterio de consulta
     */
    public void setCriterio(TipoConsultaCliente criterio) {
        this.criterio = criterio;
    }
    /**
     * Método para obtener la fecha
     * @return String fecha
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Método para asignar la fecha
     * @param fecha tipo String
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Método para obtener el valor de la consulta
     * @return String con el valor a consultar.
     */
    public String getValor() {
        return valor;
    }
    /**
     * Método para asignar el valor a la consulta
     * @param valor Valor de la consulta.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    /**
     * Método para obtener el detalle de venta
     * @return DetalleVenta
     */
    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }
    /**
     * Mëtodo para asignar el detalle de venta
     * @param detalleVenta Detalle de Venta
     */
    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
    /**
     * Método para obtener el listado de ventas de un cliente
     * @return
     */
    public List<Venta> getVentas() {
        return ventas;
    }
    /**
     * Método para asignar valores a ese listado de ventas de cliente.
     * @param ventas List con el lsitado de ventas
     */
    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    /**
     * Método para la obtencion de un cliente
     * @return Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Método para asignar los clientes.
     * @param cliente Variable tipo clliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Metodo para asignar la lista de detalles
     * @return List de detalles
     */
    public List<DetalleVenta> getDetalles() {
        return detalles;
    }
    /**
     * Método para asignar los detalles de venta
     * @param detalles
     */
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
    /**
     * Mëtodo para consulta el cliente,obtener de él el listado de ventas que tiene y el detallde de venta de cada una.
     */
    public void getConsultarVentas(){
        // COnsulta el cliente por criterio y valor
        cliente = clienteService.consultar(getCriterio(),getValor());
        // Si cliente existe
        if(cliente!=null){
            if(cliente.getVentas()==null)
                // Si no tiene ventas se asigna null para evitar errores de despiegue.
                ventas=null;
            else{
                // Obtiene las ventas del cliente
                ventas = cliente.getVentas();
                // Si existen detalles de venta.
                if(ventas.size()>0){
                    Iterator it = ventas.iterator();
                    // Bucle que asigna los detalles de venta.
                    while(it.hasNext())
                        detalles = ((Venta)it.next()).getDetalleVenta();
                }
                else
                    detalles = null;
            }
        }
    }
}
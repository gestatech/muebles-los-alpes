package com.losalpes.reportes;

import com.losalpes.cliente.IClienteService;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.ventas.IVentaService;
import java.util.List;
import javax.ejb.EJB;
/**
 * Backing Bean asociado para la parte de reportes por cliente y sus ventas.
 * @author Memo Toro
 */
public class ReporteClienteBean {
    /**
     * Interfaz anotada con @EJB para inyectar la referencia a IClienteservice
     */
    @EJB
    private IClienteService clienteService;
    /**
     * Interfaz anotada con @EJB para inyectar la referencia a IVentaService
     */
    @EJB
    private IVentaService ventaService;
    /**
     * Variable de tipo de cirterio de consulta
     */
    private String criterio;
    /**
     * String para el valor de la consulta
     */
    private String valor;
    /**
     * Listado de ventas que tenga asociadas el cliente.
     */
    private List<Venta> ventas;
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
    public String getCriterio() {
        return criterio;
    }
    /**
     * Método para asignar el tipo de criterio
     * @param criterio Tipo de Criterio de consulta
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
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
     * Mëtodo para consulta el cliente,obtener de él el listado de ventas que tiene y el detallde de venta de cada una.
     */
    public void getConsultarVentas(){
        // Consulta el cliente por criterio y valor
        cliente = clienteService.consultar(getCriterio(),getValor());
        // Si cliente existe
        if(cliente!=null){
           ventas = ventaService.consultarVentas(cliente.getNumeroDocumento());
        }
    }
}
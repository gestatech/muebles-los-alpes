package com.losalpes.cliente;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoDocumento;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * Managed Bean para controlar los Clientes de la Tienda.
 * @author Memo Toro
 */
public class ClienteBean {
    /**
     *
     * Variable Tipo Cliente
     */
    private Cliente cliente;
    /**
     * Interfaz Mock para mantener el listado de clientes.
     */
    private IClienteService clienteService = new ClienteServiceMock();
    /**
     * Variable tipo Enum para establecer los criterios de seleccion de clientes.
     */
    private TipoConsultaCliente criterio;
    /**
     * Variable tipos String para el valor de la consulta.
     */
    private String valor;

    /** Crea una nueva instancia de ClienteBean */
    public ClienteBean() {
        cliente = new Cliente();
    }
    /**
     * Método para obtener el c liente
     * @return Variable TIpo Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Método para asignar el cliente
     * @param Variable tipo Cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Método para obtener el valor de la consulta.
     * @return Variable de tipo Strinc con el valor de la consulta.
     */
    public String getValor() {
        return valor;
    }
    /**
     * Método para asignar el valor a la consulta.
     * @param String con el valor de la consulta.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    /**
     * Método para obtener el criterio de consulta de cliente.
     * @return Variable TipoConsultaCliente
     */
    public TipoConsultaCliente getCriterio() {
        return criterio;
    }
    /**
     * Método para establecer el criterio de consulta de clientes.
     * @param Variable tipo TipoConsultaCliente.
     */
    public void setCriterio(TipoConsultaCliente criterio) {
        this.criterio = criterio;
    }
    /**
     * Método para cargar el Listado de la interfaz con los posibles criterios de consulta.
     * @return Variable Enum con el TipoCriterio
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
     * Método para cargar el Listado de la interfaz con los posibles criterios de tipo de documento.
     * @return Variable Enum con el TipoDocumento
     */
    public SelectItem[] getTipoDocumento() {
        TipoDocumento[] tipos =  TipoDocumento.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
             sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     *Método para obtener los clientes listados. Asigna a su vez la variable Cliente con el consultado.
     */
    public void getClienteConsultado(){
        setCliente(clienteService.consultar(criterio, valor));
    }
    /**
     * Método para obtener todo el listado de clientes de la tienda.
     * @return Variable tipo List con los clientes.
     */
    public List getClientesTienda(){
        return clienteService.consultarTodos();
    }
    /**
     * Método para registrar un nuevo cliente en la tienda.
     * @return Variable tipo String con el redireccionamiento.
     */
    public String getRegistrar(){
        clienteService.registrar(getCliente());
        return "admin";
    }
    /**
     * Método para borrar el cliente por medio del administrador.
     * Este método se dispara con un evento gráfico de clic.
     * @param Variable tipo evento con un clic.
     */
    public void getBorrarCliente(ActionEvent evento){
        // Lógica de captura del evento y del valor del parametro pasado.
        FacesContext contexto = FacesContext.getCurrentInstance();
        String id = evento.getComponent().getClientId(contexto);
        Map parametros = contexto.getExternalContext().getRequestParameterMap();
        setValor((String) parametros.get(id));
        setCriterio(TipoConsultaCliente.NUMERO_DOCUMENTO);
        // Busqueda del cliente por medio de la interfaz Mock de Cliente para obtener el cliente
        setCliente(clienteService.consultar(criterio, valor));
        // Elimina el cliente del listado de clientes.
        clienteService.eliminar(getCliente());
    }
    /**
     * Método para redirecciona posterior al borrado.
     * @return Variable de tipo String para el redireccionamiento al menu de administador.
     */
    public String getBorrar(){
        return "admin";
    }
}
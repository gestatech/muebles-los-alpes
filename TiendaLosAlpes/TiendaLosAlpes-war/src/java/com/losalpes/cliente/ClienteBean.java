package com.losalpes.cliente;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoDocumento;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * Managed Bean para controlar los Clientes de la Tienda.
 * @author Memo Toro
 */
public class ClienteBean {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IClienteService para los clientes.
     */
    @EJB
    private IClienteService clienteService;
    /**
     * Variable Tipo Cliente
     */
    private Cliente cliente;
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
     * Método para obtener el cliente
     * @return Cliente Variable Tipo Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Método para asignar el cliente
     * @param cliente Variable tipo Cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Método para obtener el valor de la consulta.
     * @return String Variable de tipo String con el valor de la consulta.
     */
    public String getValor() {
        return valor;
    }
    /**
     * Método para asignar el valor a la consulta.
     * @param valor String con el valor de la consulta.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    /**
     * Método para obtener el criterio de consulta de cliente.
     * @return TipoConsultaCliente Variable TipoConsultaCliente
     */
    public TipoConsultaCliente getCriterio() {
        return criterio;
    }
    /**
     * Método para establecer el criterio de consulta de clientes.
     * @param TipoConsultaCliente Variable tipo TipoConsultaCliente.
     */
    public void setCriterio(TipoConsultaCliente criterio) {
        this.criterio = criterio;
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
     * Método para cargar el Listado de la interfaz con los posibles criterios de tipo de documento.
     * @return SelectItem Variable Enum con el TipoDocumento
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
     * @return List Variable tipo List con los clientes.
     */
    public List getClientesTienda(){
        return clienteService.consultarTodos();
    }
    /**
     * Método para registrar un nuevo cliente en la tienda.
     * @return String Variable tipo String con el redireccionamiento.
     */
    public String getRegistrar(){
        clienteService.registrar(getCliente());
        return "admin";
    }
    /**
     * Método para borrar el cliente por medio del administrador.
     * Este método se dispara con un evento gráfico de clic.
     * @param evento Variable tipo evento con un clic.
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
        if(getCliente() != null)
            // Elimina el cliente del listado de clientes.
            clienteService.eliminar(getCliente());
    }
    /**
     * Método que consulta por el numero de identificación del cliente que es el usuario y password para editarlo.
     */
    public void getClienteEditar(){
        setCliente(clienteService.consultar(TipoConsultaCliente.NUMERO_DOCUMENTO, valor));
    }
    /**
     * Método para editar los clientes. Lo ejecutan los Clientes.
     * @return String con redireccionamiento al login
     */
    public String getEditarCliente(){
        clienteService.editar(cliente);
        return "login";
    }
}
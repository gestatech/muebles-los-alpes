package com.losalpes.cliente;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import com.losalpes.enums.TipoUsuario;
import com.losalpes.persistence.entity.Usuario;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
/**
 * Backing Bean para controlar los Clientes de la Tienda.
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
     * Variable Tipo Usuario
     */
    private Usuario usuario;
    /**
     * Variable tipo Enum para establecer los criterios de seleccion de clientes.
     */
    private String criterio;
    /**
     * Variable tipos String para el valor de la consulta.
     */
    private String valor;
    /** Crea una nueva instancia de ClienteBean */
    public ClienteBean() {
        cliente = new Cliente();
        usuario = new Usuario();
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
     * Método para obtener el usuario
     * @return Usuario Variable Tipo Usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Método para asignar el cliente
     * @param cliente Variable tipo Cliente
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public String getCriterio() {
        return criterio;
    }
    /**
     * Método para establecer el criterio de consulta de clientes.
     * @param TipoConsultaCliente Variable tipo TipoConsultaCliente.
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getPaises() {
        TipoPais[] tipos = TipoPais.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getDepartamentos() {
        TipoDepartamento[] tipos = TipoDepartamento.values();
        SelectItem[] sItems = new SelectItem[tipos.length];
        for (int i = 0; i < sItems.length; i++) {
            sItems[i] = new SelectItem(tipos[i]);
        }
        return sItems;
    }
    /**
     * Método para cargar el Listado de la interfaz con las ciudades registradas en el sistema.
     * @return SelectItem Variable Enum con el listado de ciudades.
     */
    public SelectItem[] getCiudadResidencia() {
        TipoCiudad[] tipos = TipoCiudad.values();
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
        // clienteService.registrar(getCliente());
        // Registra el nuevo usuario.
        Usuario usua = new Usuario();
        usua.setNombreUsuario(Integer.valueOf(cliente.getNumeroDocumento()).toString());
        usua.setContrasenia(usuario.getContrasenia());
        usua.setTipoUsuario(TipoUsuario.CLIENTE);
        usua.setCliente(getCliente());
        clienteService.registrarUsuario(usua);
        return "login";
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
        setCriterio("NUMERO_DOCUMENTO");
        // Busqueda del cliente por medio de la interfaz Mock de Cliente para obtener el cliente
        setCliente(clienteService.consultar(criterio, valor));
        if(getCliente()!= null)
            // Elimina el cliente del listado de clientes.
            clienteService.eliminar(getCliente());
    }
    /**
     * Método que consulta por el numero de identificación del cliente que es el usuario y password para editarlo.
     */
    public void getClienteEditar(){
        Cliente clien = clienteService.consultarPorUsuario(usuario.getNombreUsuario(),usuario.getContrasenia());
        setCliente(clien);
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
package com.losalpes.cliente;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.IPersistenceServices;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.security.ISecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 * Session Bean que implementa la interfaz con los métodos del Cliente.
 * Esta anotado como @Stateless por ser un bien sin estado.
 * @author Memo Toro
 */
@Stateless
public class ClienteServiceBean implements IClienteService {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IClienteService para los clientes.
     */
    @EJB
    private ISecurityService securityService;
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IPersistenceServices para la persistencia de los clientes.
     */
    @EJB
    private IPersistenceServices persistenceServices;
    /** Crea una nueva instancia de ClienteServiceMock */
    public ClienteServiceBean() {
    }
    /**
     * Método anotado con PostConstructor para que inicialice los clientes de prueba.
     */
    @PostConstruct
    public void iniciar(){
    }
    /**
     * Método anotado como PreDestroy para poder avisar antes de la destrucción.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("CLIENTE-SERVICE-BEAN DESTRUIDO SATISFACTORIAMENTE !!!");
    }
    /**
     * Método para registrar clientes. Verifica si el cliente existe a partir del número de identificación. Si existe, no lo ingresa.
     * @param cliente Variable tipo Cliente
     */
    public void registrar(Cliente cliente) {
        // Cliente creado para compararlo con el cliente que se va a registrar.
        Cliente comparar = null;
        // Copia de todo el listado de clientes.
        comparar = consultarCliente(cliente.getNumeroDocumento());
        if(comparar == null){
            // Registra el nuevo cliente si no esta en la lista.
            persistenceServices.create(cliente);
        }
        else
            System.out.println("CLIENTE EXISTE Y NO SE REGISTRARÁ !!!");
    }
    /**
     * Método para eliminar clientes a partir de un cliente seleccionado.
     * @param cliente Variable cliente seleccionado para eliminar.
     */
    public void eliminar(Cliente cliente) {
        persistenceServices.delete((Cliente)persistenceServices.findById(Cliente.class, cliente.getNumeroDocumento()));
    }
    /**
     * Método para editar Cliente
     * @param Cliente Variable cliente
     */
    public void editar(Cliente cliente){
        persistenceServices.update(cliente);
        // Asigna el cliente con sus nuevos datos al usuario y actualiza la lista de usuarios
        Usuario usua = (Usuario)securityService.getObjetoSesion("usuario");
        usua.setCliente(cliente);
        persistenceServices.update(usua);
    }
    /**
     * Método para consulta clientes por criterios establecidos
     * @param criterio Variable tipo TipoConsultaCliente
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    public Cliente consultar(String criterio, String valor) {
        List<String> valores = new ArrayList<String>();
        Cliente cliente = new Cliente();
        // Determinación de criterio de consulta y valor del cliente a obtener.
        if(criterio.equalsIgnoreCase("NUMERO_DOCUMENTO")){
            valores.add("numeroDocumento|" + valor);
        }
        else if(criterio.equalsIgnoreCase("NOMBRES")){
            valores.add("nombres|" + valor);
        }
        else if(criterio.equalsIgnoreCase("EMAIL")){
            valores.add("email|" + valor);
        }
        cliente = (Cliente)persistenceServices.findObjects("findCliente",valores).get(0);
        return cliente;
    }
    /**
     * Método para consulta clientes por criterios establecidos
     * @param criterio Variable tipo TipoConsultaCliente
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    public Cliente consultarCliente(int numeroDocumento) {
        return (Cliente)persistenceServices.findById(Cliente.class,new Integer(numeroDocumento));
    }
    /**
     * Método para obtener el listado de clientes de la tienda.
     * @return List con los clientes
     */
    public List<Cliente> consultarTodos(){
        // Retorna todos los clientes de la tienda.
        return (List<Cliente>)persistenceServices.findAll(Cliente.class);
    }
    /**
     * Método para registrar usuarios.
     * @param usuario Variable tipo Usuario
     */
    public void registrarUsuario(Usuario usuario) {
        persistenceServices.create(usuario);
    }
    /**
     * Método para obtener el Cliente a partir del usuario ingresado.
     * @return cliente  variable de tipo Cliente
     */
    public Cliente consultarPorUsuario(String nombreUsuario,String contrasenia){
        // Retorna todos los clientes de la tienda.
        Usuario usuario = securityService.login(nombreUsuario, contrasenia);
        if(usuario == null){
            return null;
        }
        else{
           return usuario.getCliente();
        }
    }
}
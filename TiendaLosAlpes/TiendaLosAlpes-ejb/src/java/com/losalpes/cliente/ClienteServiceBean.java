package com.losalpes.cliente;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.IPersistenceServices;
import com.losalpes.persistence.entity.Tarjeta;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.security.ISecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
/**
 * Session Bean que implementa la interfaz con los métodos del Cliente.
 * Bean anotado con @Stateless por no ser necesario guardar los datos del cliente en sesion.
 * Bean anotado con @WebService para poderlo exponer como servicio web.
 * Bean anotado con @DeclareRoles para asignar los rolesque pueden utilizar este bean.
 * @author Memo Toro
 */
@WebService(name="ClienteWebService",serviceName="ClienteWS")
@Stateless
@DeclareRoles({"Administrador","Gerente"})
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
     * Método para registrar clientes. Verifica si el cliente existe a partir del número de identificación.
     * Si existe, no lo ingresa.
     * Anotadocon @PermitAll para no restringir el acceso a este metodo.
     * @param cliente Variable tipo Cliente
     */
    @PermitAll
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
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param cliente Variable cliente seleccionado para eliminar.
     */
    @RolesAllowed({"Administrador"})
    public void eliminar(Cliente cliente) {
        persistenceServices.delete((Cliente)persistenceServices.findById(Cliente.class, cliente.getNumeroDocumento()));
    }
    /**
     * Método para editar Cliente.
     * Anotadocon @PermitAll para no restringir el acceso a este metodo.
     * @param Cliente Variable cliente
     */
    @PermitAll
    public void editar(Cliente cliente){
        persistenceServices.update(cliente);
        // Asigna el cliente con sus nuevos datos al usuario y actualiza la lista de usuarios
        Usuario usua = (Usuario)securityService.getObjetoSesion("usuario");
        usua.setCliente(cliente);
        persistenceServices.update(usua);
    }
    /**
     * Método para consulta clientes por criterios establecidos
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param criterio Variable tipo TipoConsultaCliente
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    @RolesAllowed({"Administrador","Gerente"})
    public Cliente consultar(String criterio, String valor) {
        List<String> valores = new ArrayList<String>();
        List<Cliente> clientes = new ArrayList<Cliente>();
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
        clientes = persistenceServices.findObjects("findCliente",valores);
        if(clientes.size()>0)
            cliente = clientes.get(0);
        return cliente;
    }
    /**
     * Método para consulta clientes por criterios establecidos
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param criterio Variable tipo TipoConsultaCliente
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    @RolesAllowed({"Administrador"})
    public Cliente consultarCliente(int numeroDocumento) {
        return (Cliente)persistenceServices.findById(Cliente.class,new Integer(numeroDocumento));
    }
    /**
     * Método para obtener el listado de clientes de la tienda.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @return List con los clientes
     */
    @RolesAllowed({"Administrador"})
    public List<Cliente> consultarTodos(){
        // Retorna todos los clientes de la tienda.
        return (List<Cliente>)persistenceServices.findAll(Cliente.class);
    }
     /**
     * Método para consultar la tarjeta de credito asociada al cliente
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param criterio Variable tipo TipoConsultaCliente
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    @PermitAll
    public Tarjeta consultarTarjeta(int numeroDocumento) {
        return (Tarjeta)persistenceServices.findRemoteDatabase(numeroDocumento);
    }
    /**
     * Método para registrar usuarios.
     * Anotado con @PermitAll para no restringir el acceso a este metodo.
     * Anotado con @WebMethod como metodo publico para operacion de servicio web.
     * PermitAll@param usuario Variable tipo Usuario
     */
    @WebMethod(operationName="CrearCliente")
    @PermitAll
    public void registrarUsuario(Usuario usuario) {
        persistenceServices.createCliente(usuario);
    }
    /**
     * Método para obtener el Cliente a partir del usuario ingresado.
     * Anotadocon @PermitAll para no restringir el acceso a este metodo.
     * @return cliente  variable de tipo Cliente
     */
    @PermitAll
    public Cliente consultarPorUsuario(String nombreUsuario,String contrasenia){
        // Retorna todos los clientes de la tienda.
        Usuario usuario = securityService.login(nombreUsuario, contrasenia);
        if(usuario == null)
            return null;
        else
           return usuario.getCliente();
    }
}
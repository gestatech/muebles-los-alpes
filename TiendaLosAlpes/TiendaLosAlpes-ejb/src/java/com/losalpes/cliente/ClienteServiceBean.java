package com.losalpes.cliente;

import com.losalpes.persistencia.ITiendaService;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import java.util.List;
import java.util.Iterator;
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
     * Interfaz anotada como @EJB para que haga referencia e inyección con el Bean Mock de de la TiendaService.
     */
    @EJB
    private ITiendaService tienda;
    /** Crea una nueva instancia de ClienteServiceMock */
    public ClienteServiceBean() {
    }
    /**
     * Método anotado con PostConstructor para que inicialice los clientes de prueba.
     */
    @PostConstruct
    public void iniciar(){
        // Llama al contructor de tienda para el llenado de clientes iniciales.
        tienda.crearClientes();
    }
    /**
     * Método anotado como PreDestroy para poder avisar antes de la destrucción.
     */
    @PreDestroy
    public void finalizar(){
        System.out.println("ClienteServiceBean destruido satisfactoriamente !!!");
    }
    /**
     * Método para registrar clientes. Verifica si el cliente existe a partir del número de identificación. Si existe, no lo ingresa.
     * @param cliente Variable tipo Cliente
     */
    public void registrar(Cliente cliente) {
        // Cliente creado para compararlo con el cliente que se va a registrar.
        Cliente comparar = new Cliente();
        // Copia de todo el listado de clientes.
        List<Cliente> clientes = tienda.retornarClientes();
        Iterator it;
        it = clientes.iterator();
        // Variable boolean para verificar la existencia del cliente.
        boolean existencia = false;
        // Bucle para recorrer el listado de clientes y obtener el cliente a eliminar.
        while(it.hasNext()){
            comparar = (Cliente) it.next();
            if(comparar.getNumeroDocumento()==cliente.getNumeroDocumento()){
                existencia = true;
                break;
            }
        }
        if(existencia==true)
            System.out.println("Cliente existente y NO se registrará !!!");
        else
            // Registra el nuevo cliente si no esta en la lista.
            tienda.registrarCliente(cliente);
    }
    /**
     * Método para eliminar clientes a partir de un cliente seleccionado.
     * @param cliente Variable cliente seleccionado para eliminar.
     */
    public void eliminar(Cliente cliente) {
        // Cliente para comparar con el cliente que se va a eliminar.
        Cliente eliminado = new Cliente();
        // Copia de todo el listado de clientes.
        List<Cliente> clientes = tienda.retornarClientes();
        Iterator it;
        it = clientes.iterator();
        // Variable boolean para verificar la existencia del cliente.
        boolean existencia = false;
        // Bucle para recorrer el listado de clientes y obtener el cliente a eliminar.
        while(it.hasNext()){
            eliminado = (Cliente) it.next();
            if(eliminado.getNumeroDocumento()==cliente.getNumeroDocumento()){
                existencia = true;
                break;
            }
        }
        if(existencia==true){
            System.out.println("Cliente existente y se borrará !!!");
            // Elimina el cliente de la lista.
            tienda.eliminarCliente(eliminado);
        }
    }
    /**
     * Método para editar Cliente
     * @param Cliente Variable cliente
     */
    public void editar(Cliente cliente){
        // Consulta el cliente a eliminar por el Id
        tienda.eliminarCliente(consultar(TipoConsultaCliente.NUMERO_DOCUMENTO, String.valueOf(cliente.getNumeroDocumento()).toString()));
        // Lo envia al metodo de actualizar.
        tienda.actualizarCliente(cliente);
    }
    /**
     * Método para consulta clientes por criterios establecidos
     * @param criterio Variable tipo TipoConsultaCliente
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    public Cliente consultar(TipoConsultaCliente criterio, String valor) {
        Cliente consultado = new Cliente();
        // Copia de todo el listado de clientes.
        List<Cliente> clientes = tienda.retornarClientes();
        String valorCliente = null;
        Iterator it;
        it = clientes.iterator();
        // Bucle para recorrer el listado de clientes y obtener el cliente
        while(it.hasNext()){
            consultado = (Cliente) it.next();
            // Determinación de criterio de consulta y valor del cliente a obtener.
            if(criterio.equals(TipoConsultaCliente.NUMERO_DOCUMENTO))
                valorCliente = (Integer.valueOf(consultado.getNumeroDocumento()).toString());
            if(criterio.equals(TipoConsultaCliente.NOMBRES))
                valorCliente = consultado.getNombres();
            if(criterio.equals(TipoConsultaCliente.EMAIL))
                valorCliente = consultado.getEmail();
            // Comparación entre el atributo del cliente y el valor de consulta.
            if(valorCliente.equalsIgnoreCase(valor))
                // Retorna el cliente que se buscaba
                return consultado;
        }
        return null;
    }
    /**
     * Método para obtener el listado de clientes de la tienda.
     * @return List con los clientes
     */
    public List<Cliente> consultarTodos(){
        // Retorna todos los clientes de la tienda.
        return tienda.retornarClientes();
    }
}
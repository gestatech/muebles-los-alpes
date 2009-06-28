package com.losalpes.cliente;

import com.losalpes.catalog.ITiendaService;
import com.losalpes.catalog.TiendaServiceMock;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Servicio Mock que implementa la interfaz con los métodos del Cliente.
 * @author Memo Toro
 */
public class ClienteServiceMock implements IClienteService {
    /**
     * Variable Static con el Listado de Clientes de la Tienda.
     */
    private static List<Cliente> clientes = new ArrayList<Cliente>();;
    /**
     * Interfaz Mock para operaciones con la tienda.
     */
    private ITiendaService tienda;
    /**
     * Variable  contador para tener control sobre las instancias de esta clase.
     */
    private static int contador;

    /** Crea una nueva instancia de ClienteServiceMock */
    public ClienteServiceMock() {
        contador++;
        // Se crean clientes solo si es una vez que se invoca.
        if(contador==1){
            tienda = new TiendaServiceMock();
            clientes = tienda.crearClientes();
        }
    }
    /**
     * Método para registrar clientes
     * @param Variable tipo cliente
     */
    public void registrar(Cliente cliente) {
        clientes.add(cliente);
    }
    /**
     * Método para consulta clientes por criterios establecidos
     * @param Variable tipo TipoConsultaCliente criterio
     * @param Variable String para el valor de la consula
     * @return Variable tipo Cliente.
     */
    public Cliente consultar(TipoConsultaCliente criterio, String valor) {
        Cliente consultado = new Cliente();
        String valorCliente = null;
        Iterator it;
        it = clientes.iterator();
        // Bucle para recorrer el listado de clientes y obtener el cliente
        while(it.hasNext()){
            consultado = (Cliente) it.next();
            if(criterio.equals(TipoConsultaCliente.NUMERO_DOCUMENTO))
                valorCliente = (Integer.valueOf(consultado.getNumeroDocumento()).toString());
            if(criterio.equals(TipoConsultaCliente.NOMBRES))
                valorCliente = consultado.getNombres();
            if(criterio.equals(TipoConsultaCliente.EMAIL))
                valorCliente = consultado.getEmail();
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
        return clientes;
    }
    /**
     * Método para eliminar clientes a partir de un cliente seleccionado.
     * @param Variable cliente a eliminar
     */
    public void eliminar(Cliente cliente) {
        Cliente eliminado = new Cliente();
        Iterator it;
        it = clientes.iterator();
        // Bucle para recorrer el listado de clientes y obtener el cliente
        while(it.hasNext()){
            eliminado = (Cliente) it.next();
            if(eliminado.getNombres().equalsIgnoreCase(cliente.getNombres()))
                break;
        }
        // Elimina el cliente de la lista.
        clientes.remove(eliminado);
    }
}
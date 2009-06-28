package com.losalpes.cliente;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.TipoConsultaCliente;
import java.util.List;

/**
 * Interfaz con métodos de Cliente
 * @author Memo Toro
 */
public interface IClienteService {
    /**
     * Método para registrar clientes
     * @param Variable tipo cliente
     */
    void registrar(Cliente cliente);
    /**
     * Método para eliminar clientes a partir de un cliente seleccionado.
     * @param Variable cliente a eliminar
     */
    void eliminar(Cliente cliente);
    /**
     * Método para consulta clientes por criterios establecidos
     * @param Variable tipo TipoConsultaCliente criterio
     * @param Variable String para el valor de la consula
     * @return Variable tipo Cliente.
     */
    Cliente consultar(TipoConsultaCliente criterio, String valor);
    /**
     * Método para obtener el listado de clientes de la tienda.
     * @return List con los clientes
     */
    List<Cliente> consultarTodos();
}
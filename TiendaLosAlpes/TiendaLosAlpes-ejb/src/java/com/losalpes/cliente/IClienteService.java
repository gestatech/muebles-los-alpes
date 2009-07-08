package com.losalpes.cliente;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.enums.TipoConsultaCliente;
import com.losalpes.persistence.entity.Usuario;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz con métodos de Cliente
 * Anotada con @Local para indicar que su acceso a través de los SessionBean seran de manera local.
 * @author Memo Toro
 */
@Local
public interface IClienteService {
    /**
     * Método para registrar clientes
     * @param cliente Variable tipo cliente
     */
    void registrar(Cliente cliente);
    /**
     * Método para eliminar clientes a partir de un cliente seleccionado.
     * @param cliente Variable cliente a eliminar
     */
    void eliminar(Cliente cliente);
    /**
     * Método para editar Cliente
     * @param Cliente Variable cliente*
     */
    void editar(Cliente cliente);
    /**
     * Método para consulta clientes por criterios establecidos
     * @param criterio Variable tipo TipoConsultaCliente criterio
     * @param consula Variable String para el valor de la consula
     * @return Cliente Variable tipo Cliente.
     */
    Cliente consultar(TipoConsultaCliente criterio, String valor);
    /**
     * Método para obtener el listado de clientes de la tienda.
     * @return List con los clientes
     */
    List<Cliente> consultarTodos();
    /**
     * Método para registrar usuarios
     * @param usuario Variable tipo Usuario
     */
    public void registrarUsuario(Usuario usuario);
    /**
     * Método para obtener el cliente del usuarios especificado.
     * @return Cliente asociado al usuario
     */
    public Cliente consultarPorUsuario(String nombreUsuario,String contrasenia);
}
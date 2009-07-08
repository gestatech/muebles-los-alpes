package com.losalpes.persistencia;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.Usuario;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz con métodos de Tienda.
 * Anotada con @Local para indicar el acceso Local por medio de un SessionBean.
 * @author Memo Toro
 * @author Hans Escallon
 */
@Local
public interface ITiendaService {
    /**
     * Método para crear clientes a partir de un bucle.
     */
    void crearClientes();
    /**
     * Método para crear muebles a partir de un bucle.
     */
    void crearMuebles();
    /**
     * Método para registrar un nuevo cliente en el listado.
     * @param cliente Variable tipo cliente.
     */
    void registrarCliente(Cliente cliente);
    /**
     * Método para eliminar un cliente en el listado.
     * @param cliente Variable tipo cliente.
     */
    void eliminarCliente(Cliente eliminado);
    /**
     * Método para actualizar cliente.
     * @param cliente Variable de tipo Cliente.
     */
    void actualizarCliente(Cliente cliente);
    /**
     * Método para retornar todos los clientes del listado de la tienda.
     * @return List con los clientes de la tienda.
     */
    List<Cliente> retornarClientes();
    /**
     * Método para registrar un nuevo usuario en el listado.
     * @param usuario Variable tipo Usuario.
     */
    public void registrarUsuario(Usuario usuario);
    /**
     * Método para retornar todos los usuarios del listado de la tienda.
     * @return List con los usuarios de la tienda.
     */
    public List<Usuario> retornarUsuarios();
    /**
     * Método para registrar mueble al catálogo.
     * @param mueble Variable tipo mueble.
     */
    void registrarMueble(Mueble mueble);
    /**
     * Método para eliminar un mueble del catálogo
     * @param mueble Variable tipo mueble
     */
    void eliminarMueble(Mueble mueble);
    /**
     * Método para actualizar los valores de un mueble
     * @param mueble Variable mueble actualizado.
     */
    void actualizarMueble(Mueble mueble);
     /**
     * Método que retorna el listado de todos los muebles del catálogo.
     * @return List con los muebles.
     */
    List<Mueble> retornarMuebles();
    /**
     * Método para actualizar los datos del usuario.
     * @param usuario Variable tipo Usuario.
     */
    public void actualizarUsuario(Usuario usuario);

}


















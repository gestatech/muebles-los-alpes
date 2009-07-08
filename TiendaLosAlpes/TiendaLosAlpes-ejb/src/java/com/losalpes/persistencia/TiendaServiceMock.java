package com.losalpes.persistencia;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.enums.TipoDocumento;
import com.losalpes.enums.TipoMueble;
import com.losalpes.enums.TipoPais;
import com.losalpes.enums.TipoUsuario;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.security.ISecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
/**
 * Servicio Mock que implementa la interfaz con los métodos del TiendaSeriveMocl para simular la persistencia.
 * Bean anotado con @Stateful para que guarde en memoria de sesion los valores de los muebles y clientes de la tienda.
 * @author Memo Toro
 */
@Stateful
public class TiendaServiceMock implements ITiendaService{
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IClienteService para los clientes.
     */
    @EJB
    private ISecurityService securityService;

    /**
     * Listado de clientes de la tienda.
     */
    private static List<Cliente> clientes;
    /**
     * Listado de usuarios de la tienda.
     */
    private static List<Usuario> usuarios;
    /**
     * Listado de Muebles de la Tienda.
     */
    private List<Mueble> muebles;
    /**
     * Variable contador para tener control sobre las instancias de esta clase y los clientes.
     */
    private static int contadorCliente;
    /**
     * Variable contador para tener control sobre las instancias de esta clase y los muebles.
     */
    private static int contadorMueble;
    /**
     * Variable contador para tener control sobre las instancias de esta clase y los usuarios.
     */
    private static int contadorUsuario;
    /** Crea una nueva instancia de TiendaServiceMock */
    public TiendaServiceMock() {
    }
    /**
     * Método anotado con @PostConstruct para inicializar las variables de listados de clientes y listados de muebles.
     */
    @PostConstruct
    public void iniciar(){
        System.out.println("INICIO EL POSTCONSTRUCT DE TIENDASERVICE");
    }
    /**
     * Método para crear clientes a partir de un bucle. Verifica una sola instancia.
     * @return List de clientes
     */
    public void crearClientes(){
        contadorCliente++;
        // Se crean clientes solo si es una vez que se invoca.
        Usuario usua = null;
        if(contadorCliente==1){
            clientes = new ArrayList<Cliente>();
            contadorUsuario++;
            if(contadorUsuario==1){
                usuarios = new ArrayList<Usuario>();
            }
            for (int i = 1; i <= 5; i++) {
                Cliente temp = new Cliente("Cliente con Nombre "+i,TipoDocumento.CEDULA,80800900+i,"Cra. "+i+" # 10"+i+"-1"+i,TipoPais.values()[i-1],TipoDepartamento.values()[i-1],TipoCiudad.values()[i-1],"usuario"+i+"@uniandes.edu.co","Ingeniero Tipo-"+i,4556670+i,310233445+i, new ArrayList<Venta>());
                usua = new Usuario(80800900+i+"","usuario"+i, TipoUsuario.CLIENTE,temp);
                clientes.add(temp);
                usuarios.add(usua);
            }
            usua = new Usuario("admin","admin",TipoUsuario.ADMINISTRADOR,new Cliente());
            usuarios.add(usua);
        }
    }
    /**
     * Método para crear muebles a partir de un bucle.
     * @return List de muebles
     */
    public void crearMuebles() {
        contadorMueble++;
        // Se crean muebles solo si es una vez que se invoca.
        if(contadorMueble==1){
            muebles = new ArrayList<Mueble>();
            for (int i = 1; i <= 5; i++) {
                Mueble temp = new Mueble("001"+i, "Mueble-" + i, "Descripción del mueble " + i, TipoMueble.EXTERIOR,"Madera Fina Tipo-"+i,20+i,30+i,50+i,"Color-"+i,15+i,"mueble-"+i+".jpg", 20000*i,3*i);
                muebles.add(temp);
            }
        }
    }
    /**
     * Método para registrar un nuevo cliente en el listado.
     * @param cliente Variable tipo cliente.
     */
    public void registrarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    /**
    * Método para eliminar un cliente en el listado.
    * @param cliente Variable tipo cliente.
    */
    public void eliminarCliente(Cliente eliminado){
        clientes.remove(eliminado);
    }
    /**
     * Método para actualizar cliente.
     * @param cliente Variable de tipo Cliente.
     */
    public void actualizarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    /**
     * Método para retornar todos los clientes del listado de la tienda.
     * @return List con los clientes de la tienta.
     */
    public List<Cliente> retornarClientes(){
        return clientes;
    }
    /**
     * Método para registrar un nuevo usuario en el listado.
     * @param usuario Variable tipo Usuario.
     */
    public void registrarUsuario(Usuario usuario){
        contadorUsuario++;
        if(contadorUsuario==1){
            usuarios = new ArrayList<Usuario>();
        }
        usuarios.add(usuario);
    }
    /**
     * Método para retornar todos los usuarios del listado de la tienda.
     * @return List con los usuarios de la tienta.
     */
    public List<Usuario> retornarUsuarios(){
        return usuarios;
    }
        /**
     * MÃ©todo para actualizar los datos del usuario.
     * @param usuario Variable tipo Usuario.
     */
    public void actualizarUsuario(Usuario usuario){
        Usuario usua = securityService.login(usuario.getNombreUsuario(),usuario.getContrasenia());
        usua.setCliente(usuario.getCliente());
    }

    /**
     * Método para registrar mueble al catálogo.
     * @param mueble Variable tipo mueble.
     */
    public void registrarMueble(Mueble mueble){
        muebles.add(mueble);
    }
    /**
     * Método para eliminar un mueble del catálogo
     * @param mueble Variable tipo mueble
     */
    public void eliminarMueble(Mueble eliminado){
        muebles.remove(eliminado);
    }
    /**
     * Método para actualizar los valores de un mueble
     * @param mueble Variable mueble actualizado.
     */
    public void actualizarMueble(Mueble mueble){
        muebles.set(muebles.indexOf(mueble), mueble);
    }
     /**
     * Método que retorna el listado de todos los muebles del catálogo.
     * @return List con los muebles.
     */
    public List<Mueble> retornarMuebles(){
        return muebles;
    }
}
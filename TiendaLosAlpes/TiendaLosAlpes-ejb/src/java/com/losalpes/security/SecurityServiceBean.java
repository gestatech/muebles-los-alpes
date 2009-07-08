package com.losalpes.security;

import com.losalpes.enums.TipoUsuario;
import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.persistencia.ITiendaService;
import com.losalpes.ventas.ICarritoService;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 * Session Bean que implementa la interfaz con los métodos del Autenticacion. Abnotado con @Statelss para indicar que no guarda ningun estado.
 * @author Memo Toro
 * @author Hans Escallon
 */
@Stateless
public class SecurityServiceBean implements ISecurityService {
    /**
     * Interfaz Anotada con @EJB que inyecta la referencia a la interfaz IVentaService para las ventas.
     */
    @EJB
    private ICarritoService carritoService;
    /**
     * Interfaz anotada como @EJB para que haga referencia e inyección con el Bean Mock de de la TiendaService.
     */
    @EJB
    private ITiendaService tienda;
    /** Crea una nueva instancia de SecurityServiceBean */
    public SecurityServiceBean() {
    }
    /**
     * Método anotado con PostConstructor para que inicialice los usuarios de prueba.
     */
    @PostConstruct
    public void iniciar(){
        // Llama al contructor de tienda para el llenado de clientes iniciales.
        tienda.crearClientes();
    }
    /**
     * Método para autenticar usuarios y establecer su rol.
     * @param nombreUsuario Variable con el nombre de usuario nombreUsuario
     * @param contrasenia Variable con la contraseñia contrasenia
     * @return Usuario variable tipo Usuario
     */
    public Usuario login(String nombreUsuario, String contrasenia) {
        // Verifica si el nombre de usuario y la contraseÃ±ia son admin, si son, crea un usuario con perfil de administrador.
        List<Usuario> usuarios = tienda.retornarUsuarios();
        Iterator itUsuario = usuarios.iterator();
        Usuario usua = new Usuario();
        boolean encontro = false;
        while(itUsuario.hasNext()){
            usua = (Usuario)itUsuario.next();
            if(usua.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && usua.getContrasenia().equalsIgnoreCase(contrasenia)){
                encontro = true;
                break;
            }
        }
        if(encontro == true){
            if(usua.getTipoUsuario().equals(TipoUsuario.CLIENTE)){
            carritoService.clienteAutenticado(Integer.valueOf(nombreUsuario).intValue());
        }
        //Mete al cliente asociado al usuario en la sesion.
        if(usua.getTipoUsuario().equals(TipoUsuario.CLIENTE) ){
            setObjetoSesion(usua.getCliente());
            setObjetoSesion(usua);
        }
        return usua;
        }
        else{
            return null;
        }
    }
    /**
     * Método para registrar en la sesión el cliente y usuario que realizo el login
     * @param objeto Variable tipo Object.
     */
    public void setObjetoSesion(Object objeto) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext exc = ctx.getExternalContext();
        HttpSession session = (HttpSession)exc.getSession(true);
        if(objeto instanceof Cliente){
            session.setAttribute("cliente", (Cliente)objeto);
        }
        else{
            session.setAttribute("usuario", (Usuario)objeto);
        }
    }
     /**
     * Método para obtener el cliente o usuario de la sessión
     * @param clave Variable tipo String, que identifica el objeto a recuperar.
     */
    public Object getObjetoSesion(String clave) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext exc = ctx.getExternalContext();
        HttpSession session = (HttpSession)exc.getSession(false);
        if (!(session == null)){
            if(!(clave.equals("")) && !(clave == null)){
                return (Object)session.getAttribute(clave);
            }
        }
        return null;

    }
    /**
     * Método para obtener el cliente o usuario de la sessión
     * @param clave Variable tipo String, que identifica el objeto a recuperar.
     */
    public void editarUsuario(Cliente cliente) {
        Object objeto = getObjetoSesion("usuario");
        if (!(objeto == null)){
            Usuario usua = (Usuario)objeto;
            usua.setCliente(cliente);
            setObjetoSesion(usua);
        }
    }
}
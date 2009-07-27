package com.losalpes.catalog;

import com.losalpes.persistence.IPersistenceServices;
import com.losalpes.persistence.entity.Promocion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
/**
 * Session Bean para el manejo de las promociones.
 * Bean anotado con @DeclareRoles para asignar los rolesque pueden utilizar este bean.
 * @author Kerlyn Hans
 * @author Memo Toro
 */
@Stateless
@DeclareRoles({"Administrador"})
public class PromocionServices implements IPromocionServices {
    /**
     * Interfaz anotada con @EJB para inyectar dependencia de IPersistenceServices.
     */
    @EJB
    private IPersistenceServices persistencia;
    /**
     * Conexion anotada con @Resource para declara el topic factory de la conexion de mensajes.
     */
    @Resource(mappedName="jms/NuevaPromocionTopicFactory")
    private ConnectionFactory connectionFactory;
    /**
     * Topic asociado a la conexión y a la fábrica de mensajes.
     */
    @Resource(mappedName="jms/NuevaPromocionTopic")
    private Topic topico;
    /**
     * Contiene la información del Promocion actual.
     */
    private Promocion cPromocion;
    /** Crea una nueva instancia de PromocionServices */
    public PromocionServices() {
        cPromocion = new Promocion();
    }
    /**
    * Método para obtener la promocion.
    * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
    * @return Promocion.
    */
    @RolesAllowed({"Administrador"})
    public Promocion getPromocion() {
        return (cPromocion);
    }
    /**
     * Método para asignar la promocion actual.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param promo Promocion actual.
     */
    @RolesAllowed({"Administrador"})
    public void setPromocion(Promocion promo) {
        cPromocion = promo;
    }
    /**
     * Metoco para la construcción del mensaje a enviar al Topic.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @param session Sesion como parametro de contexto de unicidad de mensaje.
     * @return Message Mensaje enviado al topic.
     * @throws javax.jms.JMSException
     */
    @RolesAllowed({"Administrador"})
    private Message crearMensajePromocion(Session session) throws JMSException{
        String msg = cPromocion.getNombre() + "|";
        msg += cPromocion.getMuebleReferencia() + "|";
        msg += cPromocion.getFechaInicio() + "|";
        msg += cPromocion.getFechaFin();
        // Variable de tipo TextMessage para ser enviado
        TextMessage tm = session.createTextMessage();
        // Asignación del texto al cuerpo del mensaje.
        tm.setText(msg);
        // Retorna el mensaje creado.
        return(tm);
    }
    /**
     * Método para enviar el mensaje.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @throws javax.jms.JMSException.
     */
    @RolesAllowed({"Administrador"})
    private void notificarMensajePromocion() throws JMSException{
        // Creación de una conexión al factory de mensajes.
        Connection conexion = connectionFactory.createConnection();
        // Creación de la sesión para crear el mensaje unico.
        Session session = conexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Creación del productor del mensaje, encargado de crear y enviar el mensaje.
        MessageProducer messageProducer = session.createProducer((Destination) topico);
        // Bloque con logica de envio de mensaje al Topic.
        try {
            // Envio del mensaje al Topic.
            messageProducer.send(crearMensajePromocion(session));
        } catch (JMSException e) {
            // En caso de error notifica el error en el Log de la consolta del servidor
            Logger.getLogger(PromocionServices.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            // Lógica de cerrado de sesión y de envio de mensaje.
            if(session != null){
                try {
                    session.close();
                } catch (JMSException ex) {
                    // En caso de error notifica el error en el Log de la consolta del servidor
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error cerrando la session", ex);
                }
                if(conexion != null){
                    // Cerrar la conexion al factory de mensajes.
                    conexion.close();
                }
            }
        }
    }
    /**
     * Método para crear la promocion.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     */
    @RolesAllowed({"Administrador"})
    public void create() {
        // Registra la promocino en el servicio de persistencia.
        persistencia.create(cPromocion);
        try{
            // Llama al mensaje de creación y envio de mensaje.
            notificarMensajePromocion();
        }catch(JMSException ex){
            // En caso de error notifica el error en el Log de la consolta del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error enviando la notificación de la nueva promoción.", ex);
        }
    }
    /**
     * Método para retornar todas las promociones del servicio de persistencia.
     * Anotado con @RolesAllowed para que pueda solo acceder el Administrador a la funcionalidad
     * @return List con promociones.
     */
    @RolesAllowed({"Administrador"})
    public List <Promocion> findAll() {
        return persistencia.findAll(Promocion.class);
    }
}
package com.losalpes.catalog;

import com.losalpes.persistence.entity.Promocion;
import com.losalpes.persistencia.IPromociones;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
 * @author Kerlyn Hans
 * @author Memo Toro
 */
@Stateless
public class PromocionServices implements IPromocionServices {
    /**
     * Interfaz anotada con @EJB para inyectar IPromociones.
     */
    @EJB
    private IPromociones persistencePromos;
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
     * Contiene la información del Promocion actual
     */
    private Promocion cPromocion;
    /** Crea una nueva instancia de PromocionServices */
    public PromocionServices() {
    }
    /**
     * Método que inicializa la promocion y la retorna.
     * @return
     */
    public Promocion newPromocion() {
        cPromocion = new Promocion();
        return(cPromocion);
    }
    /**
     * Metoco para la construcción del mensaje a enviar al Topic.
     * @param session Sesion como parametro de contexto de unicidad de mensaje.
     * @return Message Mensaje enviado al topic.
     * @throws javax.jms.JMSException
     */
    private Message crearMensajePromocion(Session session) throws JMSException{
        String msg = cPromocion.getNombre() + "|";
        msg += cPromocion.getMueble() + "|";
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
     * Método para enviar el mensaje
     * @throws javax.jms.JMSException
     */
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
     */
    public void create() {
        // Registra la promocino en el servicio de persistencia Mock.
        persistencePromos.registrarPromocion(cPromocion);
        try{
            // Llama al mensaje de creación y envio de mensaje.
            notificarMensajePromocion();
        }catch(JMSException ex){
            // En caso de error notifica el error en el Log de la consolta del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error enviando la notificación de la nueva promoción.", ex);
        }
    }
    /**
     * Método para retornar todas las promociones del servicio Mock de persistencia
     * @return List con promociones
     */
    public List <Promocion> findAll() {
        return persistencePromos.retornarPromociones();
    }
    /**
     * Método para obtener la promocion
     * @return Promocion
     */
    public Promocion getPromocion() {
        return (cPromocion);
    }
    /**
     * Método para asignar la promocion actual
     * @param promo Promocion actual
     */
    public void setPromocion(Promocion promo) {
        cPromocion = promo;
    }
}

package com.losalpes.jms;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * Message-Driven Bean para simular la aplicaciond el CallCenter.
 * Se anotan todas la propiedades que necesita el MDB para su funcionamiento y procesamiento del mensaje.
 * @author Kerlyn Hans
 * @author Memo Toro
 */
@MessageDriven(mappedName = "jms/NuevaPromocionTopic", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "clientId", propertyValue = "CallCenterMessageBean"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "CallCenterMessageBean")
    })
public class CallCenterMessageBean implements MessageListener {
    /**
     * Atributo de tipo Contexto de Mensaje anotado como @Resource el cual inyecta la conexion con el Topic.
     */
    @Resource
    private MessageDrivenContext mdc;
    /** Crea una conexión de CallCenterMessageBean*/
    public CallCenterMessageBean() {}
    /**
     * Método para la recepción del mensaje de manera asincrona leyendo el topico.
     * @param message
     */
    public void onMessage(Message message) {
        // Mensaje
        TextMessage msg = null;
        try {
            if(message instanceof TextMessage){
                // Cast del Mensaje a mensaje de texto
                msg = (TextMessage)message;
                // Lógica de procesamiento del mensaje con Tokenizer
                StringTokenizer tokens = new StringTokenizer(msg.getText(), "|");
                String[] datos=new String[tokens.countTokens()];
                int i=0;
                while(tokens.hasMoreTokens()){
                    datos[i]=tokens.nextToken();
                    i++;
                }
                // Creación del String del mensaje procesado al log
                String sMsg = "CALLCENTER - PROMOCIÓN '" +datos[0]+ "'. Aplica desde '" + datos[3] +"' hasta '" + datos[4] + "', para el '" + datos[1] + "' de tipo '" + datos[2] +"'";
                // Mensaje en el Log
                Logger.getLogger(CallCenterMessageBean.class.getName()).log(Level.INFO, sMsg);
            }
        }catch (JMSException ej) {
            ej.printStackTrace();
            mdc.setRollbackOnly();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
package com.losalpes.persistencia;

import com.losalpes.persistence.entity.Promocion;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz anotada como @Local para dar acceso al session bean mock de persistencia.
 * @author Kerlyn Hans
 */
@Local
public interface IPromociones {
    /**
     * Método que registra promociones.
     * @param promo Promocion.
     */
    void registrarPromocion(Promocion promo);
    /**
     * Método que retorna el listado de promociones.
     * @return List de promociones.
     */
    List <Promocion> retornarPromociones();
}
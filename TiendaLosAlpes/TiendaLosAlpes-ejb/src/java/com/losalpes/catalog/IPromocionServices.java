package com.losalpes.catalog;

import com.losalpes.persistence.entity.Promocion;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz para las promociones anotado con @Local
 * @author Kerlyn Hans
 */
@Local
public interface IPromocionServices {
    /**
     * Método para retornar todas las promociones del servicio Mock de persistencia
     * @return List con promociones
     */
    public List<Promocion> findAll();
    /**
     * Método que inicializa la promocion y la retorna.
     * @return
     */
    public Promocion newPromocion();
    /**
     * Método para crear la promocion.
     */
    public void create();
    /**
     * Método para obtener la promocion
     * @return Promocion
     */
    public Promocion getPromocion();
    /**
     * Método para asignar la promocion actual
     * @param promo Promocion actual
     */
    public void setPromocion(Promocion promo);
}
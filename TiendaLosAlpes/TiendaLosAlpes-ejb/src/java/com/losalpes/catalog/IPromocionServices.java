package com.losalpes.catalog;

import com.losalpes.persistence.entity.Promocion;
import java.util.List;
import javax.ejb.Local;
/**
 * Interfaz para las promociones anotado con @Local para indicar el acceso al Session Bean como local.
 * @author Kerlyn Hans
 * @author Memo Toro
 */
@Local
public interface IPromocionServices {
    /**
     * Método para retornar todas las promociones del servicio de persistencia.
     * @return List con promociones.
     */
    public List<Promocion> findAll();
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
     * Método para asignar la promocion actual.
     * @param promo Promocion actual.
     */
    public void setPromocion(Promocion promo);
}
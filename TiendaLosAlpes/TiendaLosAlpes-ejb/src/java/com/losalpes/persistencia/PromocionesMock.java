package com.losalpes.persistencia;

import com.losalpes.persistence.entity.Promocion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
/**
 * Session Bean anotado como @Stateful para guardar los valores de las promociones en la persistencia.
 * @author Kerlyn Hans
 */
@Stateless
public class PromocionesMock implements IPromociones {
    /**
     * Listado para almacenar las promociones.
     */
    private static List<Promocion> promos = new ArrayList<Promocion>();
    /** Crea una nueva instancia de PromocionesMock */
    public PromocionesMock() {
    }
    /**
     * Método que registra promociones.
     * @param promo Promocion.
     */
    public void registrarPromocion(Promocion promo) {
        promos.add(promo);
    }
    /**
     * Método que retorna el listado de promociones.
     * @return List de promociones.
     */
    public List<Promocion> retornarPromociones() {
        return promos;
    }
}
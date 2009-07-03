package com.losalpes.persistencia;

import com.losalpes.persistence.entity.Vendedor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Camilo Alvarez
 */
@Stateless
public class PersistenceServicesMock implements IPersistenceServices {

    private static ArrayList sData;

    public PersistenceServicesMock() {
        if (sData == null) {
            sData = new ArrayList();
            for (int i = 0; i < 10; i++) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdentificacion(i + "");
                vendedor.setNombres("Nombre " + i);
                vendedor.setApellidos("Apellidos " + i);
                vendedor.setSalario(1500);
                sData.add(vendedor);
            }
        }
    }

    public void create(Object obj) {
        if (obj instanceof Vendedor) {
            ((Vendedor) obj).setIdentificacion(sData.size() + "");
        }
        sData.add(obj);
    }

    public void update(Object obj) {
        sData.set(sData.indexOf(obj), obj);
    }

    public void delete(Object obj) {
        sData.remove(obj);
    }

    public List findAll(Class c) {
        return sData;
    }

    public Object findById(Class c, Object id) {
        if (c.equals(Vendedor.class)) {
            for (Object v : findAll(c)) {
                Vendedor ven = (Vendedor) v;
                if (ven.getIdentificacion().equals(id)) {
                    return ven;
                }
            }
        }
        return new Vendedor();
    }
}

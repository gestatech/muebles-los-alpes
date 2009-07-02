package com.losalpes.persistencia;

import com.losalpes.persistence.entity.Vendedor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Session Bean Mock de persistena de vendedores. Esta anotado con @Stateless ya que no almancena ningun estado conversacional.
 * @author Memo Toro
 * @author Orlando
 */
@Stateless
public class PersistenciaServiceBean implements IPersistenciaService {
    
private static ArrayList sData;

    public PersistenciaServiceBean() {
        if (sData == null) {
            sData = new ArrayList();
            for (int i = 0; i < 10; i++) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdentificacion(i + "");
                vendedor.setNombres("Nombre " + i);
                vendedor.setApellidos("Apellidos " + i);
                vendedor.setSalario(1500*2*i);
                vendedor.setComisionVentas(vendedor.getSalario()*0.1);
                vendedor.setPerfil("Ingeniero Tipo-"+i);
                vendedor.setDireccionResidencia("Carrera "+(i+4)*2+" No. 1"+i+"-12"+(i*3)+". Bogotá.");
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
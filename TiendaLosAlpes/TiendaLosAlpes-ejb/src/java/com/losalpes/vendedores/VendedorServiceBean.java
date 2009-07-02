package com.losalpes.vendedores;

import com.losalpes.persistence.entity.Vendedor;
import com.losalpes.persistencia.IPersistenciaService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Session Bean para vendedores. Anotado con @Stateful para almacenar algunos datos durante la sesion.
 * @author Orlando
 */
@Stateful
public class VendedorServiceBean implements IVendedorService {
    @EJB
    private IPersistenciaService persistenceServices;
    /**
     * Contiene la información del vendedor actual
     */
    private Vendedor cVendedor;

    public VendedorServiceBean() {
    }

    public Vendedor newVendedor() {
        cVendedor = new Vendedor();
        return cVendedor;
    }

    @Remove
    public Vendedor createOrUpdate() {
        if (cVendedor.getIdentificacion() == null || cVendedor.getIdentificacion().isEmpty()) {
            persistenceServices.create(cVendedor);
        } else {
            persistenceServices.update(cVendedor);
        }
        return cVendedor;
    }

    public List<Vendedor> findAll() {
        return persistenceServices.findAll(Vendedor.class);
    }

    public List<Vendedor> findByIdentificacion(String id) {
        return persistenceServices.findAll(Vendedor.class);
    }

    @Remove
    public void delete() {
        persistenceServices.delete(cVendedor);
    }

    public Vendedor getVendedor() {
        return cVendedor;
    }

    public Vendedor setVendedor(String id) {
        cVendedor = (Vendedor) persistenceServices.findById(Vendedor.class, id);
        return cVendedor;
    }
}
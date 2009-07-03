package com.losalpes.vendors;

import com.losalpes.persistencia.IPersistenceServices;
import com.losalpes.persistence.entity.Vendedor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Camilo Alvarez Duran
 */
@Stateful
public class VendorServices implements IVendorServices {

    @EJB
    private IPersistenceServices persistenceServices;
    /**
     * Contiene la información del vendedor actual
     */
    private Vendedor cVendedor;

    public VendorServices() {
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

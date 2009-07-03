package com.losalpes.vendor;

import com.losalpes.persistence.entity.ExperienciaVendendor;
import com.losalpes.persistence.entity.Vendedor;
import com.losalpes.vendors.IVendorServices;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Camilo Alvarez Duran
 */
public class VendorsBean {

    @EJB
    private IVendorServices vendorServices;
    private ExperienciaVendendor experienciaVendendor = new ExperienciaVendendor();
    private String id;

    /* Crear cache de los objetos para alimentan la interfaz para evitar el llamado excesivo
    la especificación de JSF no especifica que cada metodo se llame una unica vez
     */
    private List<Vendedor> vendedores;
    private Vendedor vendedor;

    /** Creates a new instance of VendorsBean */
    public VendorsBean() {
    }

    @PostConstruct
    public void init() {
        vendedores = vendorServices.findAll();
        vendedor = vendorServices.getVendedor();
    }

    @PreDestroy
    public void destroy() {
    }

    public String newVendor() {
        vendedor = vendorServices.newVendedor();
        return null;
    }

    public String createOrUpdate() {
        vendedor = vendorServices.createOrUpdate();
        destroyBean();
        return null;
    }

    public String delete() {
        vendorServices.setVendedor(id);
        vendorServices.delete();
        destroyBean();
        return null;
    }

    public void destroyBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("VendorsBean");
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public String addExperience() {
        vendedor.getExperiencia().add(experienciaVendendor);
        return null;
    }

    public List<Vendedor> getAllVendors() {
        return vendedores;
    }

    public String findById() {
        vendedor = vendorServices.setVendedor(id);
        return null;
    }

    public ExperienciaVendendor getExperienciaVendendor() {
        return experienciaVendendor;
    }

    public void setExperienciaVendendor(ExperienciaVendendor experienciaVendendor) {
        this.experienciaVendendor = experienciaVendendor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
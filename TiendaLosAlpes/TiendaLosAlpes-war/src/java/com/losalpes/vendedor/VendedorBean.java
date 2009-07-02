package com.losalpes.vendedor;

import com.losalpes.persistence.entity.ExperienciaVendendor;
import com.losalpes.persistence.entity.Vendedor;
import com.losalpes.vendedores.IVendedorService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Camilo Alvarez Duran
 */
public class VendedorBean {

    @EJB
    private IVendedorService vendedorService;
    private ExperienciaVendendor experienciaVendendor = new ExperienciaVendendor();
    private String id;

    /* Crear cache de los objetos para alimentan la interfaz para evitar el llamado excesivo
    la especificación de JSF no especifica que cada metodo se llame una unica vez
     */
    private List<Vendedor> vendedores;
    private Vendedor vendedor;

    /** Creates a new instance of VendorsBean */
    public VendedorBean() {
    }

    @PostConstruct
    public void init() {
        vendedores = vendedorService.findAll();
        vendedor = vendedorService.getVendedor();
    }

    @PreDestroy
    public void destroy() {
    }

    public String newVendor() {
        vendedor = vendedorService.newVendedor();
        return null;
    }

    public String createOrUpdate() {
        vendedor = vendedorService.createOrUpdate();
        destroyBean();
        return null;
    }

    public String delete() {
        vendedorService.setVendedor(id);
        vendedorService.delete();
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
        vendedor = vendedorService.setVendedor(id);
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
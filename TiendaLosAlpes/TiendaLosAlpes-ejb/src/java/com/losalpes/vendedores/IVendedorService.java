package com.losalpes.vendedores;

import com.losalpes.persistence.entity.Vendedor;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz anotada con @Local para el session bean de vendedores
 * @author Orlando
 */
@Local
public interface IVendedorService {
    
    public List<Vendedor> findAll();

    public Vendedor newVendedor();

    public Vendedor createOrUpdate();

    public List<Vendedor> findByIdentificacion(String id);

    public void delete();

    public Vendedor getVendedor();

    public Vendedor setVendedor(String id);
}
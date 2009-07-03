/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.losalpes.vendors;

import com.losalpes.persistence.entity.Vendedor;
import javax.ejb.Local;

/**
 *
 * @author Orlando
 */
@Local
public interface IVendorServices {

    public java.util.List<com.losalpes.persistence.entity.Vendedor> findAll();

    public Vendedor newVendedor();

    public Vendedor createOrUpdate();

    public java.util.List<com.losalpes.persistence.entity.Vendedor> findByIdentificacion(java.lang.String id);

    public void delete();

    public com.losalpes.persistence.entity.Vendedor getVendedor();

    public Vendedor setVendedor(java.lang.String id);

}

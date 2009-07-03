/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.losalpes.persistencia;

import javax.ejb.Local;

/**
 *
 * @author Orlando
 */
@Local
public interface IPersistenceServices {

    public void create(java.lang.Object obj);

    public void update(java.lang.Object obj);

    public void delete(java.lang.Object obj);

    public java.util.List findAll(java.lang.Class c);

    public java.lang.Object findById(java.lang.Class c, java.lang.Object id);

}

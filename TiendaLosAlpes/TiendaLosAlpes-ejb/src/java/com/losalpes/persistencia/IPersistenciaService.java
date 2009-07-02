package com.losalpes.persistencia;

import javax.ejb.Local;

/**
 * Interfaz para el Session Bean Mocl de persistentica de vendedores
 * @author Orlando
 */
@Local
public interface IPersistenciaService {

    public void create(Object obj);

    public void update(Object obj);

    public void delete(Object obj);

    public java.util.List findAll(Class c);

    public java.lang.Object findById(Class c, Object id);

}
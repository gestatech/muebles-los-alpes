package com.losalpes.catalog;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Mueble;
import java.util.List;

/**
 * Interfaz con métodos de Tienda
 * @author Memo Toro
 */
public interface ITiendaService {
    /**
     * Método para crear clientes a partir de un bucle.
     * @return List de clientes
     */
    List<Cliente> crearClientes();
    /**
     * Método para crear muebles a partir de un bucle.
     * @return List de muebles
     */
    List<Mueble> crearMuebles();
}
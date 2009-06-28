package com.losalpes.catalog;

import com.losalpes.persistence.entity.Cliente;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.TipoDocumento;
import com.losalpes.persistence.entity.TipoMueble;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio Mock que implementa la interfaz con los métodos del Autenticacion.
 * @author Memo Toro
 */
public class TiendaServiceMock implements ITiendaService{
    /**
     * Listado de clientes de la tienda.
     */
    private List<Cliente> clientes;
    /**
     * Listado de Muebles de la Tienda.
     */
    private List<Mueble> muebles;

    /** Crea una nueva instancia de TiendaServiceMock */
    public TiendaServiceMock() {
        clientes = new ArrayList<Cliente>();
        muebles = new ArrayList<Mueble>();
    }
    /**
     * Método para crear clientes a partir de un bucle.
     * @return List de clientes
     */
    public List crearClientes(){
        for (int i = 1; i <= 5; i++) {
            Cliente temp = new Cliente("Cliente con Nombre "+i,TipoDocumento.CEDULA,80800900+i,"Colombia","Departamento-"+i,"Cra. "+i+" # 10"+i+"-1"+i,"Ciudad-"+i,"usuario"+i+"@uniandes.edu.co","Ingeniero Tipo-"+i,4556670+i,310233445+i);
            clientes.add(temp);
        }
        return clientes;
    }
    /**
     * Método para crear muebles a partir de un bucle.
     * @return List de muebles
     */
    public List crearMuebles() {
        for (int i = 1; i <= 5; i++) {
            Mueble temp = new Mueble("001"+i, "Mueble-" + i, "Descripción del mueble " + i, TipoMueble.EXTERIOR,"Madera Fina Tipo-"+i,20+i,30+i,50+i,"Color-"+i,15+i,"mueble-"+i+".jpg", 20000*i,3*i);
            muebles.add(temp);
        }
        return muebles;
    }
}
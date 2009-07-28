package com.losalpes.persistence;

import com.losalpes.persistence.entity.DetalleVenta;
import com.losalpes.persistence.entity.Mueble;
import com.losalpes.persistence.entity.Tarjeta;
import com.losalpes.persistence.entity.Usuario;
import com.losalpes.persistence.entity.Venta;
import com.losalpes.utils.SQLService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
/**
 * Sessión Bean con lógica de persistencia. Implementa un Entity Manager para realizar las
 * operaciones sobre la base de datos a nivel de object. Todos los session bean que quieren persistir
 * una entidad invocan este session bean.
 * Bean anotado con @Stateless por no ser necesario guardar los datos de persistencia en sesion.
 * Bean anotado con @TransactionManagement para indicar que la transaccion se trabaja al interior del bean y no por el contenedor.
 * @author Memo Toro
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenceServices implements IPersistenceServices {
    /**
     * Variable de Persistence Context para realizar las operaciones de persistencia de entidades @Entity
     */
    @PersistenceContext(unitName = "Tienda-Oracle")
    private EntityManager em;
    /**
     * Atributo que permite al contenedor inyectar una Transacción de Usuario
     */
    @Resource
    UserTransaction ut;
    /**
     * Atributo que permite al contenedor inyectar el datasource de
     * conexiones a la base de datos de Derby.
     */
    @Resource(mappedName = "jdbc/tiendalosalapesext")
    private DataSource dataSource;

    /**  Crea una nueva instacia de PersistenceServices */
    public PersistenceServices() {
    }
    /**
     * Método para Iniciar una transaccion
     */
    private void initTransaction() {
        try {
            ut.begin();
        } catch (NotSupportedException ex) {
            Logger.getLogger(PersistenceServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(PersistenceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método para confirmar una transaccion
     */
    private void commitTransaction() {
        try {
            if (ut.getStatus() != Status.STATUS_ROLLEDBACK) {
                ut.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                ut.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Método para marcar una transaccion para rollback
     */
    private void rollBackTransaction() {
        try {
            ut.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para crear una venta con transaccionalidad
     * @param obj parametro de tipo Venta
     */
    public void createVenta(Object obj) {
        try {
            Venta vent = (Venta) obj;
            initTransaction();
            em.persist(vent);
            updateRemoteDatabase(vent);
            commitTransaction();
        } catch (Exception ex) {
            Logger.getLogger(PersistenceServices.class.getName()).log(Level.SEVERE, null, ex);
            rollBackTransaction();
        }
    }
    /**
     * Método para crear el cliente
     * @param objeto parametro de tipo Object
     */
    public void createCliente(Object obj) {
        Usuario usua = (Usuario) obj;
        Tarjeta tarje = usua.getCliente().getTarjeta();
        initTransaction();
        em.persist(usua);
        insertRemoteDatabase(tarje);
        commitTransaction();
    }
    /**
     * Método para crear la tarjeta del cliente
     * @param objeto parametro de tipo Object
     */
    private void insertRemoteDatabase(Object objeto) {
        Tarjeta tarjeta = (Tarjeta) objeto;
        PreparedStatement ps;
        try {
            Connection con = dataSource.getConnection();
            con.setAutoCommit(false);
            /* Verificar si el usuario ya se encuentra registrado (existe un registro con la misma identificación)
             * en caso afirmativo hacer rollback de la transacción global
             */
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("insert into tarjeta(tarjnume,tarjclie,tarjmont,tarjcose,tarjfeex) values(?,?,?,?,?)");
            ps.setString(1, tarjeta.getNumeroTarjeta());
            ps.setInt(2, tarjeta.getClienudo());
            ps.setDouble(3, tarjeta.getMonto());
            ps.setInt(4, tarjeta.getCodigoSeguridad());
            ps.setString(5, tarjeta.getFechaExpiracionTarjeta());
            int registrosAfectado = ps.executeUpdate();
            if (registrosAfectado != 1) {
                throw new RuntimeException();
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL");
        }
    }
    /**
     * Método para actualizar le monto de la tarjeta en base de datos remota con las tarjetas de credito
     * @param objeto parametro de tipo Object
     */
    private void updateRemoteDatabase(Object objeto) throws VentaException,SQLException{
        Venta venta = (Venta) objeto;
        PreparedStatement ps;
        try {
            Connection con = dataSource.getConnection();
            con.setAutoCommit(false);
            // Verificar si el usuario ya se encuentra registrado (existe un registro con la misma identificación) en caso afirmativo hacer rollback de la transacción global             
            ps = con.prepareStatement("select tarjnume,tarjclie,tarjmont,tarjcose,tarjfeex from tarjeta where tarjnume = ?");
            ps.setString(1, venta.getNumeroTarjeta());
            ResultSet rs = ps.executeQuery();
            // Verificar si el usuario ya se encuentra registrado (existe un registro con la misma identificación) en caso afirmativo hacer rollback de la transacción global
            if (!rs.next()) {
                System.out.println("Error en la venta");
                throw new VentaException();
            } else {
                String numeroTarjeta = rs.getString("tarjnume");
                double monto = rs.getDouble("tarjmont");
                if (monto < venta.getValor()) {
                    System.out.println("Error en la venta, El saldo de su tarjeta es inferior al valor de la venta");
                    throw new VentaException();
                } else {
                    ps = con.prepareStatement("update tarjeta set tarjmont = ? where tarjnume = ?");
                    ps.setDouble(1, monto - venta.getValor());
                    ps.setString(2, numeroTarjeta);
                    int registrosAfectado = ps.executeUpdate();
                    if (registrosAfectado != 1) {
                        System.out.println("Error Al Realizar La Venta");
                    }

                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de SQL");
            throw new SQLException();
        }
    }
    /**
     * Método para realizar la busqueda de la tarjeta de credito del cliente
     * @param id variable de tipo int ue corresponde al numero de documento del cliente
     */
    public Object findRemoteDatabase(int id) {

        PreparedStatement ps;
        Tarjeta tar = new Tarjeta();
        try {
            // Verificar si el usuario ya se encuentra registrado (existe un registro con la misma identificación) en caso afirmativo hacer rollback de la transacción global
            Connection con = dataSource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("select tarjnume,tarjclie,tarjmont,tarjcose,tarjfeex from tarjeta where tarjclie = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            // Verificar si el usuario ya se encuentra registrado (existe un registro con la misma identificación) en caso afirmativo hacer rollback de la transacción global
            if (!rs.next()) {
                throw new VentaException();
            } else {
                tar.setNumeroTarjeta(rs.getString("tarjnume"));
                tar.setClienudo(rs.getInt("tarjclie"));
                tar.setCodigoSeguridad(rs.getInt("tarjcose"));
                tar.setMonto(rs.getDouble("tarjmont"));
                tar.setFechaExpiracionTarjeta(rs.getString("tarjfeex"));
            }
        } catch (Exception ex) {
            System.out.println("Error en SQL");
        }
        return tar;

    }
    /**
     * Método para almacenar entidades.
     * @param obj Object de una entidad anotada con @Entity
     */
    public void create(Object obj) {
        initTransaction();
        em.persist(obj);
        commitTransaction();
    }
    /**
     * Método para actualizar entidades
     * @param obj Object de una entidad anotada con @Entity
     */
    public void update(Object obj) {
        initTransaction();
        em.merge(obj);
        commitTransaction();
    }
    /**
     * Método para eliminar entidades
     * @param obj Object de una entidad anotada con @Entity
     */
    public void delete(Object obj) {
        initTransaction();
        em.remove(obj);
        commitTransaction();
    }
    /**
     * Método para retornar el listado de toda una entidad
     * @param c Class con el tipo de clase a consultar
     * @return List Listado con las instancias de la entidad
     */
    public List findAll(Class c) {
        return em.createQuery("SELECT o FROM " + c.getSimpleName() + " AS o").getResultList();
    }
    /**
     * Método para encontrar un objeto por su Id
     * @param c Class con el tipo de clase a consultar
     * @param id Object como Id para buscar el objeto
     * @return Object Objeto retornado y consultado
     */
    public Object findById(Class c, Object id) {
        return em.find(c, id);
    }
    /**
     * Método para cosultar por varios criterios y retornar listas de objetos
     * @param consulta String Criterio de consulta
     * @param valores List con la configuracion de columna, valor para la consulta
     * @return List Listado con los objetos que satisfacen la consulta.
     */
    public List findObjects(String consulta, List<String> valores) {
        // Crea la sentencia con el String que le retorna la clase SQLService
        Query qry = em.createQuery(SQLService.buscarObjetos(consulta, valores));
        // Ejecuta el query
        List temp = qry.getResultList();
        return temp;
    }
}
package com.losalpes.persistence.entity;

import com.losalpes.enums.TipoUsuario;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * POJO de Usuario
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Memo Toro
 * @author Hans Escallon
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
    /**
     * String para el nombre de usuario.
     */
    private String nombreUsuario;
    /**
     * String para la contraseña del usuario.
     */
    private String contrasenia;
    /**
     * TipoUsuario para el usuario y rol.
     */
    private TipoUsuario tipoUsuario;
    /**
     * Cliente para asociar al cliente.
     */
    private Cliente cliente;
    /** Crea una nueva instancia de Usuario vacia*/
    public Usuario() {}
    /**
     * Método que retorna el identificador del cliente.
     * Anotado con @Id para ser la llave primaria al persistir.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con el nombre de usuario.
     */
    @Id
    @Column(name = "usuanous")
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    /**
     * Mëtodo para asignar el nombre de usuario.
     * @param nombreUsuario Nombre de Usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    /**
     * Método para obtener el cliente.
     * Anotado con @OneToOne al ser el atributo de relación entre ciente y usuario.
     * Anotado con @JoinColumn para indicar la columna de enlace de la relación.
     * @return Cliente cliente asociado al usuario.
     */
    @OneToOne(cascade = CascadeType.ALL,optional=true)
    @JoinColumn(name = "clienudo")
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Mëtodo para asignar el cliente al usuario.
     * @param cliente Cliente asociado al usuario.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Método para obtener la contraseña del usuario.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String contraseña
     */
    @Column(name="usuacont")
    public String getContrasenia() {
        return contrasenia;
    }
    /**
     * Mëtodo para asignar el password
     * @param contrasenia Contraseña de Usuario
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    /**
     * Método para obtener el tipo de usuario.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoUsuario Tipo de usuario.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="usuatisu")
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    /**
     * Mëtodo para asignar el Tipo de Usuario
     * @param tipoUsuario Tipo de Usuario.
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
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
/**
 * POJO de Usuario
 * @author Memo Toro
 * @author Hans Escallon
 */
@Entity
public class Usuario implements Serializable{

    private String nombreUsuario;
    private String contrasenia;
    private TipoUsuario tipoUsuario;
    private Cliente cliente;
    /** Crea una nueva instancia de Usuario vacia*/
    public Usuario() {}

    @Id
    @Column(name = "usuanous")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    @OneToOne(cascade = CascadeType.ALL,optional=true)
    @JoinColumn(name = "clienudo")
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Column(name="usuacont")
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="usuatisu")
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
package com.losalpes.persistence.entity;

import com.losalpes.enums.TipoCiudad;
import com.losalpes.enums.TipoDepartamento;
import com.losalpes.enums.TipoPais;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * POJO de Cliente
 * Anotado con @Entity y @Table para indicar que este objeto persiste y será una tabla.
 * @author Memo Toro
 */
@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
    /**
     * String para el nombre del cliente.
     */
    private String nombres;
    /**
     * String para el tipo de documento del cliente.
     */
    private String tipoDocumento;
    /**
     * Int para el numero de documento.
     */
    private int numeroDocumento;

    private Tarjeta tarjeta;
    /**
     * String para la direccion.
     */
    private String direccion;
    /**
     * TipoPais para el pais del cliente.
     */
    private TipoPais pais;
    /**
     * TipoDepartamento para el departamento del cliente.
     */
    private TipoDepartamento departamento;
    /**
     * TipoCiudad para la ciudad del cliente.
     */
    private TipoCiudad ciudadResidencia;
    /**
     * String para el email del cliente.
     */
    private String email;
    /**
     * String para la profesion del cliente.
     */
    private String profesion;
    /**
     * Int para el telefono de residencia.
     */
    private int telefonoResidencia;
    /**
     * Long para el telefono celular.
     */
    private long telefonoCelular;
    /**
     * Tarjeta de credito del cliente.
     */
     //Tarjeta tarjeta;
    /**
     * Usuario para el usuario del cliente.
     */
    private Usuario usuario;
    /** Crea una nueva instancia de Cliente */
    public Cliente() {}
    /**
     * Método para obtener el numero de documento.
     * Anotado con @Id para ser la llave primaria al persistir.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return int Numoer de Documento.
     */
    @Id
    @Column(name = "clienudo")
    public int getNumeroDocumento() {
        return numeroDocumento;
    }
    /**
     * Método para asignar el numero de documento.
     * @param numeroDocumento Numero de documento del cliente.
     */
    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    /**
     * Método para obtener el usuario.
     * Anotado con @OneToOne al ser el atributo de relación entre cliente y usuario.
     * Anotado con @JoinColumn para indicar la columna de enlace de la relación.
     * @return Usuario TIpo de usuario del cliente.
     */
    @OneToOne(cascade={CascadeType.ALL},mappedBy="cliente")
    @JoinColumn(name="clieusu")
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * M;etdo para asignar el usuario.
     * @param usuario Usuario asignado al cliente.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Método para obtener la ciudad de residencia.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoCiudad Tipo de ciudad del cliente.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="cliecire")
    public TipoCiudad getCiudadResidencia() {
        return ciudadResidencia;
    }
    /**
     * Método para asignar la ciudad de residencia del cliente.
     * @param ciudadResidencia CIudad de residencia del cliente.
     */
    public void setCiudadResidencia(TipoCiudad ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }
    /**
     * Método para obtener el departamento de residencia.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoDepartamento Tipo de departamento del cliente.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="cliedept")
    public TipoDepartamento getDepartamento() {
        return departamento;
    }
    /**
     * Método para asignar el departamento del cliente.
     * @param departamento Departamento del cliente.
     */
    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }
    /**
     * Método para obtener el pais de residencia.
     * Anotado con @Enumerated para indicar que es enumeración.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return TipoPais Tipo de pais del cliente.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="cliepais")
    public TipoPais getPais() {
        return pais;
    }
    /**
     * Método para asignar el pais del cliente.
     * @param pais Pais del cliente.
     */
    public void setPais(TipoPais pais) {
        this.pais = pais;
    }
    /**
     * Método para obtener la dirección del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String direccion del cliente.
     */
    @Column(name="cliedire")
    public String getDireccion() {
        return direccion;
    }
    /**
     * Método para asignar la dirección
     * @param direccion direccion del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * Método para obtener el email del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String email del cliente.
     */
    @Column(name="clieemai")
    public String getEmail() {
        return email;
    }
    /**
     * Método para asignar el email del cliente.
     * @param email Email del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Método para obtener el nombre del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String nombre del cliente.
     */
    @Column(name="clienomb")
    public String getNombres() {
        return nombres;
    }
    /**
     * Método para asignar el nombre del cliente.
     * @param nombres Nombres del cliente.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    /**
     * Método para obtener la profesion del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String profesion del cliente.
     */
    @Column(name="clieprof")
    public String getProfesion() {
        return profesion;
    }
    /**
     * Método para asignar la profesion del cliente.
     * @param profesion Profesion del cliente.
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    /**
     * Método para obtener el telefono del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return Long Numero celular del cliente.
     */
    @Column(name="cliecelu")
    public long getTelefonoCelular() {
        return telefonoCelular;
    }
    /**
     * Método para asignar el telefono celular del cliente.
     * @param telefonoCelular Telefono celular del cliente.
     */
    public void setTelefonoCelular(long telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }
    /**
     * Método para obtener el telefono de residencia.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return Int Numero de telefono residencia.
     */
    @Column(name="clietere")
    public int getTelefonoResidencia() {
        return telefonoResidencia;
    }
    /**
     * Método para asignar el telefono de residencia del cliente.
     * @param telefonoResidencia Telefono del cliente.
     */
    public void setTelefonoResidencia(int telefonoResidencia) {
        this.telefonoResidencia = telefonoResidencia;
    }
    /**
     * Método para obtener el tipo de documento del cliente.
     * Anotado con @Column para el nombre del atributo en la pesistencia.
     * @return String con el tipo de documento del cliente.
     */
    @Column(name="clietido")
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    /**
     * Método para asignar el tipo de documento del cliente.
     * @param tipoDocumento Tipo de documento del cliente.
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    /**
     * Método para obtener la tarjeta de credito del cliente.
     */
     @Transient
    public Tarjeta getTarjeta() {
        return tarjeta;
    }
    /**
     * Método para asignar la tarjeta de credito del cliente.
     * @param tarjeta Tarjeta de credito del cliente.
     */
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}
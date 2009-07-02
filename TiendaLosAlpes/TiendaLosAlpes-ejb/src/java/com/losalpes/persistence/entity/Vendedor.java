package com.losalpes.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO de Vendedor
 * @author Memo Toro
 * @author Camilo Alvarez Duran
 */
public class Vendedor implements Serializable {

    private String identificacion;
    private String nombres;
    private String apellidos;
    private List<ExperienciaVendendor> experiencia;
    private double salario;
    private double comisionVentas;
    private String perfil;
    private byte[] foto;
    private String direccionResidencia;

    public Vendedor() {
        experiencia = new ArrayList<ExperienciaVendendor>();
        for (int i = 0; i < 10; i++) {
            ExperienciaVendendor experienciaVendendor = new ExperienciaVendendor();
            experienciaVendendor.setId(i);
            experienciaVendendor.setNombreEmpesa("Empresa " + i);
            experienciaVendendor.setDescripcion("Descripción " + i);
            experienciaVendendor.setAnio(200 + i);
            experienciaVendendor.setCargo("Cargo " + i);
            experiencia.add(experienciaVendendor);
        }
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getComisionVentas() {
        return comisionVentas;
    }

    public void setComisionVentas(double comisionVentas) {
        this.comisionVentas = comisionVentas;
    }

    public List<ExperienciaVendendor> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(List<ExperienciaVendendor> experiencia) {
        this.experiencia = experiencia;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object obj) {
        Vendedor ov = (Vendedor) obj;
        return ov.getIdentificacion().equals(identificacion);
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }
}
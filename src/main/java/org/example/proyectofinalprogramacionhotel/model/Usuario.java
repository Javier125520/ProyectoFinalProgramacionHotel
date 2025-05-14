package org.example.proyectofinalprogramacionhotel.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Usuario {
    protected String nombre;
    protected String gmail;
    protected String contrasena;

    public Usuario(String nombre, String gmail, String contrasena) {
        this.nombre = nombre;
        this.gmail = gmail;
        this.contrasena = contrasena;
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public abstract String getTipoUsuario();
}

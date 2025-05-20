package org.example.proyectofinalprogramacionhotel.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Usuario {
    // Atributos
    protected String nombre;
    protected String gmail;
    protected String contrasena;

    // Constructor
    public Usuario(String nombre, String gmail, String contrasena) {
        this.nombre = nombre;
        this.gmail = gmail;
        this.contrasena = contrasena;
    }

    // Constructor sin parámetros
    public Usuario() {

    }

    // Getters y Setters
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

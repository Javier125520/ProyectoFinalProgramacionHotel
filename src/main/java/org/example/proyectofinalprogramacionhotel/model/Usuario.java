package org.example.proyectofinalprogramacionhotel.model;

public abstract class Usuario {
    // Atributos
    protected String nombre;
    protected String email;
    protected String contrasena;

    // Constructor
    public Usuario(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public abstract String getTipoUsuario();
}

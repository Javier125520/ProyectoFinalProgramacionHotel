package org.example.proyectofinalprogramacionhotel.model;

public class Cliente extends Usuario {
    private int idCliente;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String gmail, String contrasena, String dni, String telefono) {
        super(nombre, gmail, contrasena);
        this.dni = dni;
        this.telefono = telefono;
    }

    public Cliente() {
        super();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}

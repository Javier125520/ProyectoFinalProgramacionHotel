package org.example.proyectofinalprogramacionhotel.model;

import java.util.List;

public class Cliente extends Usuario {
    // Atributos
    private int idCliente;
    private String dni;
    private String telefono;
    private List<Reserva> misReservas;

    // Constructor
    public Cliente(String nombre, String gmail, String contrasena, int idCliente, String dni, String telefono, List<Reserva> misReservas) {
        super(nombre, gmail, contrasena);
        this.idCliente = idCliente;
        this.dni = dni;
        this.telefono = telefono;
        this.misReservas = misReservas;
    }

    // Constructor sin parámetros
    public Cliente() {
        super();
    }

    // Getters y Setters
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

    public List<Reserva> getMisReservas() {
        return misReservas;
    }

    public void setMisReservas(List<Reserva> misReservas) {
        this.misReservas = misReservas;
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", gmail='" + gmail + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", misReservas=" + misReservas +
                '}';
    }
}

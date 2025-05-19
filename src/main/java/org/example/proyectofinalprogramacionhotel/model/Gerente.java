package org.example.proyectofinalprogramacionhotel.model;

import java.util.List;

public class Gerente extends Usuario {
    private int idGerente;
    private String codigo;
    private List<Habitacion> misHabitaciones;

    public Gerente(String nombre, String gmail, String contrasena, int idGerente, String codigo, List<Habitacion> misHabitaciones) {
        super(nombre, gmail, contrasena);
        this.idGerente = idGerente;
        this.codigo = codigo;
        this.misHabitaciones = misHabitaciones;
    }

    public Gerente() {

    }

    @Override
    public String getTipoUsuario() {
        return "Gerente";
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Habitacion> getMisHabitaciones() {
        return misHabitaciones;
    }

    public void setMisHabitaciones(List<Habitacion> misHabitaciones) {
        this.misHabitaciones = misHabitaciones;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "idGerente=" + idGerente +
                ", nombre='" + nombre + '\'' +
                ", gmail='" + gmail + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", código=" + codigo + '\'' +
                ", misHabitaciones=" + misHabitaciones +
                '}';
    }
}

package org.example.proyectofinalprogramacionhotel.model;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Usuario {
    private int inGerente;
    private String codigo;
    private List<Habitacion> misHabitaciones;

    public Gerente(String nombre, String gmail, String contrasena, int inGerente, String codigo, List<Habitacion> misHabitaciones) {
        super(nombre, gmail, contrasena);
        this.inGerente = inGerente;
        this.codigo = codigo;
        this.misHabitaciones = misHabitaciones;
    }

    public Gerente() {

    }

    @Override
    public String getTipoUsuario() {
        return "Gerente";
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

    public int getInGerente() {
        return inGerente;
    }

    public void setInGerente(int inGerente) {
        this.inGerente = inGerente;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "idGerente=" + inGerente +
                ", nombre='" + nombre + '\'' +
                ", gmail='" + gmail + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", código=" + codigo + '\'' +
                ", misHabitaciones=" + misHabitaciones +
                '}';
    }
}

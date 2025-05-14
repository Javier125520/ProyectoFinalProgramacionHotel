package org.example.proyectofinalprogramacionhotel.model;

public class Habitacion {
    private int idHabitacion;
    private int numeroHabitacion;
    private Enum tipoHabitacion;
    private double precioNoche;
    private Enum estadoHabitacion;
    private int idGerente;
    private int idReserva;

    public Habitacion(int idHabitacion, int numeroHabitacion, Enum tipoHabitacion, double precioNoche, Enum estadoHabitacion, int idGerente, int idReserva) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioNoche = precioNoche;
        this.estadoHabitacion = estadoHabitacion;
        this.idGerente = idGerente;
        this.idReserva = idReserva;
    }

    public Habitacion() {

    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Enum getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(Enum tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public Enum getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(Enum estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}

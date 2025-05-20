package org.example.proyectofinalprogramacionhotel.model;

public class Habitacion {
    // Atributos
    private int idHabitacion;
    private int numeroHabitacion;
    private tipoHabitacion tipoHabitacion;
    private double precioNoche;
    private estadoHabitacion estadoHabitacion;
    private int idGerente;
    private Integer idReserva;

    // Constructor
    public Habitacion(int idHabitacion, int numeroHabitacion, org.example.proyectofinalprogramacionhotel.model.tipoHabitacion tipoHabitacion, double precioNoche, org.example.proyectofinalprogramacionhotel.model.estadoHabitacion estadoHabitacion, int idGerente, Integer idReserva) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioNoche = precioNoche;
        this.estadoHabitacion = estadoHabitacion;
        this.idGerente = idGerente;
        this.idReserva = idReserva;
    }

    // Constructor sin parámetros
    public Habitacion() {

    }

    // Getters y Setters
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

    public org.example.proyectofinalprogramacionhotel.model.tipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(org.example.proyectofinalprogramacionhotel.model.tipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public org.example.proyectofinalprogramacionhotel.model.estadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(org.example.proyectofinalprogramacionhotel.model.estadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", numeroHabitacion=" + numeroHabitacion +
                ", tipoHabitacion=" + tipoHabitacion +
                ", precioNoche=" + precioNoche +
                ", estadoHabitacion=" + estadoHabitacion +
                ", idGerente=" + idGerente +
                '}';
    }
}

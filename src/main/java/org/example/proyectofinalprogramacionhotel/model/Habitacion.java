package org.example.proyectofinalprogramacionhotel.model;

public class Habitacion {
    // Atributos
    private int idHabitacion;
    private int numeroHabitacion;
    private tipoHabitacion tipoHabitacion;
    private double precioNoche;
    private estadoHabitacion estadoHabitacion;
    private Gerente gerente;
    private Reserva reserva;

    public Habitacion(int idHabitacion, int numeroHabitacion, tipoHabitacion tipoHabitacion, double precioNoche, estadoHabitacion estadoHabitacion, Gerente gerente, Reserva reserva) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioNoche = precioNoche;
        this.estadoHabitacion = estadoHabitacion;
        this.gerente = gerente;
        this.reserva = reserva;
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

    public estadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(estadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }


    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", numeroHabitacion=" + numeroHabitacion +
                ", tipoHabitacion=" + tipoHabitacion +
                ", precioNoche=" + precioNoche +
                ", estadoHabitacion=" + estadoHabitacion +
                ", gerente=" + gerente +
                ", reserva=" + reserva +
                '}';
    }
}

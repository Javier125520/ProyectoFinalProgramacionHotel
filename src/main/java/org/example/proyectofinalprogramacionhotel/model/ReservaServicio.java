package org.example.proyectofinalprogramacionhotel.model;

import java.time.LocalDate;

public class ReservaServicio {
    // Atributos
    private Reserva reserva;
    private Servicio servicio;
    private LocalDate fechaReserva;
    private int numeroPersonas;
    private int precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor
    public ReservaServicio(Reserva reserva, Servicio servicio, LocalDate fechaReserva, int numeroPersonas, int precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.reserva = reserva;
        this.servicio = servicio;
        this.fechaReserva = fechaReserva;
        this.numeroPersonas = numeroPersonas;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Constructor sin parámetros
    public ReservaServicio() {

    }

    // Getters y Setters

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    @Override
    public String toString() {
        return "ReservaServicio{" +
                "reserva=" + reserva +
                ", servicio=" + servicio +
                ", fechaReserva=" + fechaReserva +
                ", numeroPersonas=" + numeroPersonas +
                ", precio=" + precio +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}

package org.example.proyectofinalprogramacionhotel.model;

import java.time.LocalDate;

public class ReservaServicio {
    private int idReserva;
    private int idServicio;
    private LocalDate fechaReserva;
    private int numeroPersonas;
    private int precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ReservaServicio(int idReserva, int idServicio, LocalDate fechaReserva, int numeroPersonas, int precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idReserva = idReserva;
        this.idServicio = idServicio;
        this.fechaReserva = fechaReserva;
        this.numeroPersonas = numeroPersonas;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ReservaServicio() {

    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
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
                "idReserva=" + idReserva +
                ", idServicio='" + idServicio + '\'' +
                ", fechaReserva=" + fechaReserva +
                ", numeroPersonas=" + numeroPersonas +
                ", precio=" + precio +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}

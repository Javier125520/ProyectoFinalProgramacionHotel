package org.example.proyectofinalprogramacionhotel.model;

import java.time.LocalDate;

public class ReservaServicio {
    private int idReserva;
    private String idServicio;
    private LocalDate fechaReserva;
    private int precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ReservaServicio(int idReserva, String idServicio, LocalDate fechaReserva, int precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idReserva = idReserva;
        this.idServicio = idServicio;
        this.fechaReserva = fechaReserva;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
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
}

package org.example.proyectofinalprogramacionhotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private estadoReserva estadoReserva;
    private int numPersonas;
    private int idCliente;
    private List<ReservaServicio> serviciosIncluidos;
    private List<Habitacion> habitacionesContratadas;

    public Reserva(int idReserva, LocalDate fechaEntrada, LocalDate fechaSalida, org.example.proyectofinalprogramacionhotel.model.estadoReserva estadoReserva, int numPersonas, int idCliente, List<ReservaServicio> serviciosIncluidos, List<Habitacion> habitacionesContratadas) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estadoReserva = estadoReserva;
        this.numPersonas = numPersonas;
        this.idCliente = idCliente;
        this.serviciosIncluidos = serviciosIncluidos;
        this.habitacionesContratadas = habitacionesContratadas;
    }

    public Reserva() {

    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public org.example.proyectofinalprogramacionhotel.model.estadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(org.example.proyectofinalprogramacionhotel.model.estadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public List<ReservaServicio> getServiciosIncluidos() {
        return serviciosIncluidos;
    }

    public void setServiciosIncluidos(List<ReservaServicio> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }

    public List<Habitacion> getHabitacionesContratadas() {
        return habitacionesContratadas;
    }
    public void setHabitacionesContratadas(List<Habitacion> habitacionesContratadas) {
        this.habitacionesContratadas = habitacionesContratadas;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva='" + idReserva + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", estadoReserva=" + estadoReserva +
                ", numPersonas=" + numPersonas +
                '}';
    }
}

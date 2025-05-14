package org.example.proyectofinalprogramacionhotel.model;

public enum estadoReserva {
    EnProceso("Su reserva esta en proceso"), Completada("Su reserva esta completada"), Cancelada("Su reserva a sido cancelada");

    private final String mensaje;

    estadoReserva(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}

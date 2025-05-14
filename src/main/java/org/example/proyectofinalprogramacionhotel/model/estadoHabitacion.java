package org.example.proyectofinalprogramacionhotel.model;

public enum estadoHabitacion {
    LIBRE("Libre"),
    OCUPADA("Ocupada"),
    MANTENIMIENTO("En mantenimiento");

    private final String descripcion;

    estadoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

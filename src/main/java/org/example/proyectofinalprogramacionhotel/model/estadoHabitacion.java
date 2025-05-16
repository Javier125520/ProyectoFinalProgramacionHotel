package org.example.proyectofinalprogramacionhotel.model;

public enum estadoHabitacion {
    Libre("Libre"),
    Ocupada("Ocupada"),
    Mantenimiento("En mantenimiento");

    private final String descripcion;

    estadoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

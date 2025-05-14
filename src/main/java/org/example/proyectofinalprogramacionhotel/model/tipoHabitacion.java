package org.example.proyectofinalprogramacionhotel.model;

public enum tipoHabitacion {
    INDIVIDUAL("Habitacion individual"),
    DOBLE("Habitacion doble"),
    FAMILIAR("Habitacion familiar (3 o 4 personas)"),
    SUITE("Suite");

    private final String descripcion;

    tipoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

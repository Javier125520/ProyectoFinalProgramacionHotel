package org.example.proyectofinalprogramacionhotel.model;

public enum tipoHabitacion {
    Individual("Habitacion individual"),
    Doble("Habitacion doble"),
    Familiar("Habitacion familiar (3 o 4 personas)"),
    Suit("Suite");

    private final String descripcion;

    tipoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.HabitacionDAO;
import org.example.proyectofinalprogramacionhotel.model.Habitacion;
import org.example.proyectofinalprogramacionhotel.model.estadoHabitacion;
import org.example.proyectofinalprogramacionhotel.model.tipoHabitacion;

public class PruebaInsertarHabitacion {
    public static void main(String[] args) {
        // ID de la reserva a la que se asociará la habitación
        int idReserva = 1; // Asegúrate de que esta reserva exista en la base de datos

        // Crear una nueva habitación
        Habitacion nuevaHabitacion = new Habitacion();
        nuevaHabitacion.setNumeroHabitacion(101);
        nuevaHabitacion.setTipoHabitacion(tipoHabitacion.DOBLE); // Asegúrate de que tipoHabitacion sea un Enum válido
        nuevaHabitacion.setPrecioNoche(150.0);
        nuevaHabitacion.setEstadoHabitacion(estadoHabitacion.LIBRE); // Asegúrate de que estadoHabitacion sea un Enum válido
        nuevaHabitacion.setIdGerente(1); // ID del gerente que está creando la habitación
        nuevaHabitacion.setIdReserva(idReserva); // Asociar la habitación a la reserva

        // Insertar la habitación y asociarla a la reserva
        Habitacion resultado = HabitacionDAO.insertHabitacion(nuevaHabitacion);

        // Verificar si la inserción fue exitosa
        if (resultado != null) {
            System.out.println("Habitación insertada y asociada a la reserva con éxito.");
        } else {
            System.out.println("Error al insertar la habitación en la reserva.");
        }
    }
}
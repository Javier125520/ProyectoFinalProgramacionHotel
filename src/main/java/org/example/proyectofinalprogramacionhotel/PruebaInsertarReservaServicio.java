package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ReservaServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;

import java.time.LocalDate;

public class PruebaInsertarReservaServicio {
    public static void main(String[] args) {
        // Crear una nueva reserva de servicio
        ReservaServicio nuevaReservaServicio = new ReservaServicio();
        nuevaReservaServicio.setIdReserva(5); // ID de la reserva existente
        nuevaReservaServicio.setIdServicio(1); // ID del servicio existente
        nuevaReservaServicio.setFechaReserva(LocalDate.now());
        nuevaReservaServicio.setNumeroPersonas(2); // Número de personas
        nuevaReservaServicio.setPrecio(100); // Precio del servicio
        nuevaReservaServicio.setFechaInicio(LocalDate.of(2023, 12, 1));
        nuevaReservaServicio.setFechaFin(LocalDate.of(2023, 12, 2));

        // Insertar la reserva del servicio en la base de datos
        try {
            ReservaServicioDAO.insertReservaServicio(nuevaReservaServicio);
            System.out.println("Reserva de servicio añadida con éxito: " + nuevaReservaServicio);
        } catch (Exception e) {
            System.out.println("Error al añadir la reserva de servicio: " + e.getMessage());
        }
    }
}
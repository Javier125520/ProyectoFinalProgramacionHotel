package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ReservaServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;

import java.time.LocalDate;

public class PruebaInsertarReservaServicio {
    public static void main(String[] args) {
        // ID de la reserva y del servicio
        int idReserva = 1; // Asegúrate de que esta reserva exista en la base de datos
        String idServicio = "SPA"; // Asegúrate de que este servicio exista en la base de datos

        // Crear una nueva reserva de servicio
        ReservaServicio nuevaReservaServicio = new ReservaServicio();
        nuevaReservaServicio.setIdReserva(idReserva);
        nuevaReservaServicio.setIdServicio(idServicio);
        nuevaReservaServicio.setFechaReserva(LocalDate.now());
        nuevaReservaServicio.setPrecio(100); // Precio del servicio
        nuevaReservaServicio.setFechaInicio(LocalDate.of(2023, 12, 1));
        nuevaReservaServicio.setFechaFin(LocalDate.of(2023, 12, 2));

        // Insertar la reserva del servicio en la base de datos
        try {
            ReservaServicioDAO.insertReservaServicio(nuevaReservaServicio);
            System.out.println("Reserva de servicio añadida con éxito.");
        } catch (Exception e) {
            System.out.println("Error al añadir la reserva de servicio: " + e.getMessage());
        }
    }
}
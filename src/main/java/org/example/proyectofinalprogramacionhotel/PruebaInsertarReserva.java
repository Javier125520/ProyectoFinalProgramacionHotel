package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.model.Reserva;
import org.example.proyectofinalprogramacionhotel.model.estadoReserva;

import java.time.LocalDate;

public class PruebaInsertarReserva {
    public static void main(String[] args) {
        // Crear una nueva reserva
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setFechaEntrada(LocalDate.of(2023, 12, 1));
        nuevaReserva.setFechaSalida(LocalDate.of(2023, 12, 5));
        nuevaReserva.setEstadoReserva(estadoReserva.Completada);
        nuevaReserva.setNumPersonas(2);
        nuevaReserva.setIdCliente(1); // Asegúrate de que este cliente exista en la base de datos

        // Insertar la reserva en la base de datos
        Reserva reservaInsertada = ReservaDAO.insertReserva(nuevaReserva);

        // Verificar si la inserción fue exitosa
        if (reservaInsertada != null) {
            System.out.println("Reserva insertada con éxito: " + reservaInsertada);
        } else {
            System.out.println("Error al insertar la reserva.");
        }
    }
}

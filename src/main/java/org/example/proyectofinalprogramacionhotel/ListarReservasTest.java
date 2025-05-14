package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.model.Reserva;

import java.util.List;

public class ListarReservasTest {
    public static void main(String[] args) {
        List<Reserva> reservas = ReservaDAO.findAll();

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("Listado de reservas:");
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }
}

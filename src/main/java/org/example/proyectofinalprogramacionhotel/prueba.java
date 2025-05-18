package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.HabitacionDAO;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.Habitacion;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;

import java.util.List;

public class prueba {
    public static void main(String[] args) {
        List<ReservaServicio> servicios = ReservaServicioDAO.findByIdReserva(5); // Cambia el ID según tu base de datos
        servicios.forEach(System.out::println);

        List<Habitacion> habitaciones = HabitacionDAO.findByIdReserva(5); // Cambia el ID según tu base de datos
        habitaciones.forEach(System.out::println);
    }
}

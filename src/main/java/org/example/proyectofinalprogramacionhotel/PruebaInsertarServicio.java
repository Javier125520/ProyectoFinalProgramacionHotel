package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

public class PruebaInsertarServicio {
    public static void main(String[] args) {
        // Crear un nuevo servicio
        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setPrecioHora(50.0); // Precio por hora del servicio
        nuevoServicio.setTipoServicio("SPA"); // Tipo de servicio

        // Insertar el servicio en la base de datos
        try {
            ServicioDAO.insertServicio(nuevoServicio);
            System.out.println("Servicio añadido con éxito: " + nuevoServicio);
        } catch (Exception e) {
            System.out.println("Error al añadir el servicio: " + e.getMessage());
        }
    }
}

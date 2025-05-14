package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.GerenteDAO;
import org.example.proyectofinalprogramacionhotel.model.Gerente;

public class PruebaInsertarGerente {
    public static void main(String[] args) {
        // Crear un nuevo gerente
        Gerente nuevoGerente = new Gerente();
        nuevoGerente.setNombre("Pepe Garcia");
        nuevoGerente.setGmail("pepe.garcia@gmail.com");
        nuevoGerente.setContrasena("miContrasena123");
        nuevoGerente.setCodigo("B473R");

        // Insertar el gerente en la base de datos
        Gerente gerenteInsertado = GerenteDAO.insertGerente(nuevoGerente);

        // Verificar si la inserción fue exitosa
        if (gerenteInsertado != null) {
            System.out.println("Gerente insertado con éxito: " + gerenteInsertado);
        } else {
            System.out.println("Error al insertar el gerente.");
        }
    }
}

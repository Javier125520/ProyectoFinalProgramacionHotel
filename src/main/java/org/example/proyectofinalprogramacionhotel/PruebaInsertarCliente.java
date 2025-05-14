package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ClienteDAO;
import org.example.proyectofinalprogramacionhotel.model.Cliente;

public class PruebaInsertarCliente {
    public static void main(String[] args) {
        // Crear un nuevo cliente
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Antonio Castro");
        nuevoCliente.setGmail("antoni.castro@gmail.com");
        nuevoCliente.setContrasena("123131");
        nuevoCliente.setDni("1231231Q");
        nuevoCliente.setTelefono("638195187");

        // Insertar el cliente en la base de datos
        Cliente clienteInsertado = ClienteDAO.insertCliente(nuevoCliente);

        // Verificar si la inserción fue exitosa
        if (clienteInsertado != null) {
            System.out.println("Cliente insertado con éxito: " + clienteInsertado);
        } else {
            System.out.println("Error al insertar el cliente.");
        }
    }
}

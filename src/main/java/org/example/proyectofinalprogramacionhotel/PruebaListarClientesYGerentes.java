package org.example.proyectofinalprogramacionhotel;

import org.example.proyectofinalprogramacionhotel.DAO.ClienteDAO;
import org.example.proyectofinalprogramacionhotel.DAO.GerenteDAO;
import org.example.proyectofinalprogramacionhotel.model.Cliente;
import org.example.proyectofinalprogramacionhotel.model.Gerente;

import java.util.List;

public class PruebaListarClientesYGerentes {
    public static void main(String[] args) {
        // Listar todos los clientes
        System.out.println("Lista de Clientes:");
        List<Cliente> clientes = ClienteDAO.findAll();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        // Listar todos los gerentes
        System.out.println("\nLista de Gerentes:");
        List<Gerente> gerentes = GerenteDAO.findAll();
        for (Gerente gerente : gerentes) {
            System.out.println(gerente);
        }
    }
}

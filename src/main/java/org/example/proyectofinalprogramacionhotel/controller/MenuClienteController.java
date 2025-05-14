package org.example.proyectofinalprogramacionhotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.model.Reserva;

import java.util.List;

public class MenuClienteController {

    @FXML
    private ListView<String> listaReservas;

    @FXML
    private Label idReservaLabel;

    @FXML
    private Label fechaEntradaLabel;

    @FXML
    private Label fechaSalidaLabel;

    @FXML
    private Label estadoReservaLabel;

    @FXML
    private Label numPersonasLabel;

    @FXML
    private MenuButton menuButton;

    private int idCliente; // ID del cliente actual

    /*public void initialize() {
        listaReservas.setCellFactory( lv -> new ListCell<Reserva>() {
            protected void updateItem(Reserva reserva, boolean empty) {
                super.updateItem(reserva, empty);
                if (empty || reserva == null) {
                    setText(null);
                } else {
                    setText();
                }
            }
        });

        List<Reserva> reservas = ReservaDAO.findAll();
        listaReservas.getItems().setAll(String.valueOf(reservas));
    }*/

    private void cargarReservas() {
        List<Reserva> reservas = ReservaDAO.findByIdCliente(idCliente);
        for (Reserva reserva : reservas) {
            listaReservas.getItems().add("Reserva ID: " + reserva.getIdReserva());
        }
    }

    @FXML
    private void mostrarDetallesReserva() {
        String seleccion = listaReservas.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            int idReserva = Integer.parseInt(seleccion.split(": ")[1]);
            Reserva reserva = ReservaDAO.findById(idReserva);

            idReservaLabel.setText(String.valueOf(reserva.getIdReserva()));
            fechaEntradaLabel.setText(reserva.getFechaEntrada().toString());
            fechaSalidaLabel.setText(reserva.getFechaSalida().toString());
            estadoReservaLabel.setText(reserva.getEstadoReserva().toString());
            numPersonasLabel.setText(String.valueOf(reserva.getNumPersonas()));
        }
    }

    @FXML
    private void accionMenuItem1() {
        // Implementar acción para el primer elemento del menú
    }

    @FXML
    private void accionMenuItem2() {
        // Implementar acción para el segundo elemento del menú
    }
}

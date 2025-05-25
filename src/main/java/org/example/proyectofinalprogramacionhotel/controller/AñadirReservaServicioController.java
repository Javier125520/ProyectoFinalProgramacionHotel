package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.Reserva;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

import java.time.LocalDate;

public class AñadirReservaServicioController {

    @FXML
    private DatePicker fechaInicioPicker;

    @FXML
    private DatePicker fechaFinPicker;

    @FXML
    private TextField numPersonasTxt;

    @FXML
    private TextField precioTxt;

    private Reserva reservaSeleccionada;
    private Servicio servicioSeleccionado;

    public void setDatos(Reserva reserva, Servicio servicio) {
        this.reservaSeleccionada = reserva;
        this.servicioSeleccionado = servicio;
    }

    @FXML
    private void guardarReservaServicio(ActionEvent event) {
        try {
            LocalDate fechaInicio = fechaInicioPicker.getValue();
            LocalDate fechaFin = fechaFinPicker.getValue();
            int numeroPersonas = Integer.parseInt(numPersonasTxt.getText());
            int precio = Integer.parseInt(precioTxt.getText());

            if (fechaInicioPicker.getValue() == null || fechaFinPicker.getValue() == null || fechaInicio.isAfter(fechaFin)) {
                mostrarAlerta("Error", "Debe completar las fechas de inicio y fin.");
                return;
            }

            // Crear y guardar la reserva del servicio
            ReservaServicio reservaServicio = new ReservaServicio();
            reservaServicio.setReserva(reservaSeleccionada);
            reservaServicio.setServicio(servicioSeleccionado);
            reservaServicio.setFechaReserva(LocalDate.now());
            reservaServicio.setFechaInicio(fechaInicio);
            reservaServicio.setFechaFin(fechaFin);
            reservaServicio.setNumeroPersonas(Integer.parseInt(numPersonasTxt.getText()));
            reservaServicio.setPrecio(Integer.parseInt(precioTxt.getText()));

            ReservaServicioDAO.insertReservaServicio(reservaServicio);

            mostrarAlerta("Éxito", "Reserva del servicio guardada correctamente.");
            cerrarVentana(event);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Los datos ingresados son inválidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cerrarVentana(event);
    }

    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
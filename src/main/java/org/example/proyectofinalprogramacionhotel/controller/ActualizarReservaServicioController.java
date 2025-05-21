package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;

import java.time.LocalDate;

public class ActualizarReservaServicioController {

    @FXML
    private DatePicker fechaInicioPicker;

    @FXML
    private DatePicker fechaFinPicker;

    @FXML
    private TextField numPersonasTxt;

    @FXML
    private TextField precioTxt;

    private ReservaServicio reservaServicioSeleccionada;

    /**
     * Método para cargar los datos de la reserva seleccionada en los campos del formulario.
     * @param reservaServicioSeleccionada La reserva de servicio seleccionada.
     */
    public void setReservaServicio(ReservaServicio reservaServicioSeleccionada) {
        this.reservaServicioSeleccionada = reservaServicioSeleccionada;

        if (reservaServicioSeleccionada != null) {
            fechaInicioPicker.setValue(reservaServicioSeleccionada.getFechaInicio());
            fechaFinPicker.setValue(reservaServicioSeleccionada.getFechaFin());
            numPersonasTxt.setText(String.valueOf(reservaServicioSeleccionada.getNumeroPersonas()));
            precioTxt.setText(String.valueOf(reservaServicioSeleccionada.getPrecio()));
        }
    }

    /**
     * Método que guarda los cambios realizados en la reserva de servicio.
     * @param event Evento de acción.
     */
    @FXML
    public void guardarReservaServicioActualizada(ActionEvent event) {
        if (reservaServicioSeleccionada == null) {
            mostrarAlerta("Error", "No se ha seleccionado ninguna reserva.");
            return;
        }

        try {
            // Validar y actualizar los datos de la reserva
            LocalDate fechaInicio = fechaInicioPicker.getValue();
            LocalDate fechaFin = fechaFinPicker.getValue();
            int numeroPersonas = Integer.parseInt(numPersonasTxt.getText());
            int precio = Integer.parseInt(precioTxt.getText());

            if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
                mostrarAlerta("Error", "Debe completar las fechas correctamente.");
                return;
            }

            reservaServicioSeleccionada.setFechaInicio(fechaInicio);
            reservaServicioSeleccionada.setFechaFin(fechaFin);
            reservaServicioSeleccionada.setNumeroPersonas(numeroPersonas);
            reservaServicioSeleccionada.setPrecio(precio);

            // Guardar cambios en la base de datos
            ReservaServicioDAO.updateReservaServicio(reservaServicioSeleccionada);
            mostrarAlerta("Éxito", "Reserva actualizada correctamente.");
            cerrarVentana();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Los datos ingresados son inválidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar la reserva: " + e.getMessage());
        }
    }

    /**
     * Método que cierra la ventana sin realizar cambios.
     * @param event Evento de acción.
     */
    @FXML
    public void cancelarActualizacion(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Método que muestra una alerta con el mensaje y el título especificado.
     * @param titulo Título de la alerta.
     * @param mensaje Mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Método que cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) fechaInicioPicker.getScene().getWindow();
        stage.close();
    }
}

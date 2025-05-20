package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.model.Reserva;
import org.example.proyectofinalprogramacionhotel.model.estadoReserva;

import java.time.LocalDate;

public class ActualizarReservaController {

    @FXML
    private DatePicker fechaEntradaPicker;

    @FXML
    private DatePicker fechaSalidaPicker;

    @FXML
    private ComboBox<estadoReserva> estadoReservaCombo;

    @FXML
    private TextField numPersonasField;

    private Reserva reservaSeleccionada;

    /**
     * Metodo que se ejecuta al inicializar el controlador.
     * Carga los datos de la reserva seleccionada en los campos de texto.
     */
    public void initialize() {
        // Cargar los valores de la enumeración estadoReserva en el ComboBox
        estadoReservaCombo.getItems().setAll(estadoReserva.values());
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de guardar.
     * Valida los campos y actualiza la reserva en la base de datos.
     */
    @FXML
    public void guardarReservaActualizada(ActionEvent actionEvent) {
        if (reservaSeleccionada == null) {
            mostrarAlerta("Error", "No se ha seleccionado ninguna reserva.");
            return;
        }

        try {
            // Validar y actualizar los datos de la reserva
            LocalDate fechaEntrada = fechaEntradaPicker.getValue();
            LocalDate fechaSalida = fechaSalidaPicker.getValue();
            String estadoReservaStr = String.valueOf(estadoReservaCombo.getValue());
            String numPersonasStr = numPersonasField.getText();

            if (fechaEntrada != null && fechaSalida != null && !fechaSalida.isAfter(fechaEntrada)) {
                mostrarAlerta("Error", "La fecha de salida debe ser posterior a la fecha de entrada.");
                return;
            }

            reservaSeleccionada.setFechaEntrada(fechaEntrada);
            reservaSeleccionada.setFechaSalida(fechaSalida);
            reservaSeleccionada.setEstadoReserva(estadoReservaStr != null ? estadoReserva.valueOf(estadoReservaStr) : null);
            reservaSeleccionada.setNumPersonas(numPersonasStr != null && !numPersonasStr.isEmpty() ? Integer.parseInt(numPersonasStr) : 0);

            // Guardar cambios en la base de datos
            ReservaDAO.updateReserva(reservaSeleccionada);
            mostrarAlerta("Éxito", "Reserva actualizada correctamente.");
            cerrarVentana();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar la reserva: " + e.getMessage());
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de cancelar.
     * Cierra la ventana de actualización.
     */
    public void cancelarActualizacion(ActionEvent actionEvent) {
        cerrarVentana();
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de cancelar.
     * Cierra la ventana de actualización.
     */
    public void setReserva(Reserva reservaSeleccionada) {
        this.reservaSeleccionada = reservaSeleccionada;
        if (reservaSeleccionada != null) {
            fechaEntradaPicker.setValue(reservaSeleccionada.getFechaEntrada());
            fechaSalidaPicker.setValue(reservaSeleccionada.getFechaSalida());
            estadoReservaCombo.setValue(reservaSeleccionada.getEstadoReserva());
            numPersonasField.setText(String.valueOf(reservaSeleccionada.getNumPersonas()));
        }
    }

    /**
     * Metodo que muestra una alerta con el mensaje y el titulo especificado.
     * @param titulo Titulo de la alerta.
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
     * Metodo que cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) fechaEntradaPicker.getScene().getWindow();
        stage.close();
    }
}

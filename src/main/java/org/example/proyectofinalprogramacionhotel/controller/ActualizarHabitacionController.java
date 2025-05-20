package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.HabitacionDAO;
import org.example.proyectofinalprogramacionhotel.model.Habitacion;
import org.example.proyectofinalprogramacionhotel.model.estadoHabitacion;
import org.example.proyectofinalprogramacionhotel.model.tipoHabitacion;

public class ActualizarHabitacionController {

    @FXML
    private TextField numeroHabitacionField;

    @FXML
    private ComboBox<String> tipoHabitacionCombo;

    @FXML
    private TextField precioNocheField;

    @FXML
    private ComboBox<String> estadoHabitacionCombo;

    private Habitacion habitacionSeleccionada;

    public void initialize() {
        // Cargar los estados posibles en el ComboBox
        estadoHabitacionCombo.getItems().addAll("Disponible", "Ocupada", "Mantenimiento");
        // Cargar los tipos de habitación en el ComboBox
        tipoHabitacionCombo.getItems().addAll("Individual", "Doble", "Suite");

    }

    public void setHabitacion(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
        if (habitacionSeleccionada != null) {
            numeroHabitacionField.setText(String.valueOf((habitacionSeleccionada.getNumeroHabitacion())));
            tipoHabitacionCombo.setValue(String.valueOf(habitacionSeleccionada.getTipoHabitacion()));
            precioNocheField.setText(String.valueOf(habitacionSeleccionada.getPrecioNoche()));
            estadoHabitacionCombo.setValue(String.valueOf(habitacionSeleccionada.getEstadoHabitacion()));
        }
    }

    @FXML
    public void guardarHabitacionActualizada(ActionEvent event) {
        if (numeroHabitacionField.getText().isEmpty() || tipoHabitacionCombo.getValue() == null ||
                precioNocheField.getText().isEmpty() || estadoHabitacionCombo.getValue() == null) {
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
            return;
        }

        try {
            habitacionSeleccionada.setNumeroHabitacion(Integer.parseInt(numeroHabitacionField.getText()));
            habitacionSeleccionada.setTipoHabitacion(tipoHabitacion.valueOf(tipoHabitacionCombo.getValue()));
            habitacionSeleccionada.setPrecioNoche(Double.parseDouble(precioNocheField.getText()));
            habitacionSeleccionada.setEstadoHabitacion(estadoHabitacion.valueOf(estadoHabitacionCombo.getValue()));

            HabitacionDAO.updateHabitacion(habitacionSeleccionada);
            mostrarAlerta("Éxito", "Habitación actualizada correctamente.");
            cerrarVentana();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar la habitación: " + e.getMessage());
        }
    }

    @FXML
    public void cancelarActualizacionHabitacion(ActionEvent event) {
        cerrarVentana();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) numeroHabitacionField.getScene().getWindow();
        stage.close();
    }
}

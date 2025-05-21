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
    private ComboBox<tipoHabitacion> tipoHabitacionCombo;

    @FXML
    private TextField precioNocheField;

    @FXML
    private ComboBox<estadoHabitacion> estadoHabitacionCombo;

    private Habitacion habitacionSeleccionada;

    /**
     * Metodo que se ejecuta al inicializar el controlador.
     * Carga los datos de la habitación seleccionada en los campos de texto.
     */
    public void initialize() {
        // Cargar los valores de la enumeración tipoHabitacion en el ComboBox
        tipoHabitacionCombo.getItems().setAll(tipoHabitacion.values());

        // Cargar los valores de la enumeración estadoHabitacion en el ComboBox
        estadoHabitacionCombo.getItems().setAll(estadoHabitacion.values());
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de guardar.
     * Valida los campos y actualiza la habitación en la base de datos.
     */
    public void setHabitacion(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
        if (habitacionSeleccionada != null) {
            numeroHabitacionField.setText(String.valueOf((habitacionSeleccionada.getNumeroHabitacion())));
            tipoHabitacionCombo.setValue((habitacionSeleccionada.getTipoHabitacion()));
            precioNocheField.setText(String.valueOf(habitacionSeleccionada.getPrecioNoche()));
            estadoHabitacionCombo.setValue((habitacionSeleccionada.getEstadoHabitacion()));
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de guardar.
     * Valida los campos y actualiza la habitación en la base de datos.
     */
    @FXML
    public void guardarHabitacionActualizada(ActionEvent event) {
        if (numeroHabitacionField.getText().isEmpty() || tipoHabitacionCombo.getValue() == null ||
                precioNocheField.getText().isEmpty() || estadoHabitacionCombo.getValue() == null) {
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
            return;
        }

        try {
            habitacionSeleccionada.setNumeroHabitacion(Integer.parseInt(numeroHabitacionField.getText()));
            habitacionSeleccionada.setTipoHabitacion(tipoHabitacionCombo.getValue());
            habitacionSeleccionada.setPrecioNoche(Double.parseDouble(precioNocheField.getText()));
            habitacionSeleccionada.setEstadoHabitacion(estadoHabitacionCombo.getValue()); // Asegurar que no sea null

            HabitacionDAO.updateHabitacion(habitacionSeleccionada);
            mostrarAlerta("Éxito", "Habitación actualizada correctamente.");
            cerrarVentana();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar la habitación: " + e.getMessage());
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de cancelar.
     * Cierra la ventana de actualización.
     */
    @FXML
    public void cancelarActualizacion(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Metodo que muestra una alerta con el mensaje especificado.
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
        Stage stage = (Stage) numeroHabitacionField.getScene().getWindow();
        stage.close();
    }
}

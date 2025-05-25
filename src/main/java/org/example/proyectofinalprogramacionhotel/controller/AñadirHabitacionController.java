package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.HabitacionDAO;
import org.example.proyectofinalprogramacionhotel.model.Gerente;
import org.example.proyectofinalprogramacionhotel.model.Habitacion;
import org.example.proyectofinalprogramacionhotel.model.estadoHabitacion;
import org.example.proyectofinalprogramacionhotel.model.tipoHabitacion;
import org.example.proyectofinalprogramacionhotel.utils.Utiles;

public class AñadirHabitacionController {

    @FXML
    private TextField numeroHabitacionTxt;

    @FXML
    private ComboBox<tipoHabitacion> tipoHabitacionCombo;

    @FXML
    private TextField precioNocheTxt;

    @FXML
    private ComboBox<estadoHabitacion> estadoHabitacionCombo;

    @FXML
    private Gerente gerenteSeleccionado;


    @FXML
    public void initialize() {
        // Cargar valores en los ComboBox
        tipoHabitacionCombo.getItems().setAll(tipoHabitacion.values());
        estadoHabitacionCombo.getItems().setAll(estadoHabitacion.values());
    }

    public void setGerente(Gerente gerenteSeleccionado) {
        this.gerenteSeleccionado = gerenteSeleccionado;
    }

    @FXML
    public void guardarHabitacion(ActionEvent event) {
        String numeroHabitacionStr = numeroHabitacionTxt.getText();
        String precioNocheStr = precioNocheTxt.getText();
        tipoHabitacion tipo = tipoHabitacionCombo.getValue();
        estadoHabitacion estado = estadoHabitacionCombo.getValue();

        // Validar campos
        if (numeroHabitacionStr == null || numeroHabitacionStr.isEmpty() || !Utiles.validarNumeroHabitacion(numeroHabitacionStr)) {
            mostrarAlerta("Error", "El número de habitación es inválido.");
            return;
        }
        if (precioNocheStr == null || precioNocheStr.isEmpty() || !Utiles.validarPrecioNoche(precioNocheStr)) {
            mostrarAlerta("Error", "El precio por noche es inválido.");
            return;
        }
        if (tipo == null) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de habitación.");
            return;
        }
        if (estado == null) {
            mostrarAlerta("Error", "Debe seleccionar un estado para la habitación.");
            return;
        }

        // Crear objeto Habitacion
        Habitacion nuevaHabitacion = new Habitacion();
        nuevaHabitacion.setNumeroHabitacion(Integer.parseInt(numeroHabitacionStr));
        nuevaHabitacion.setPrecioNoche(Double.parseDouble(precioNocheStr));
        nuevaHabitacion.setTipoHabitacion(tipo);
        nuevaHabitacion.setEstadoHabitacion(estado);
        nuevaHabitacion.setGerente(gerenteSeleccionado);
        nuevaHabitacion.setReserva(null);

        // Guardar en la base de datos
        try {
            HabitacionDAO.insertHabitacion(nuevaHabitacion);
            mostrarAlerta("Éxito", "Habitación añadida correctamente.");
            cerrarVentana(event);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar la habitación: " + e.getMessage());
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
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

package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

public class ActualizarServicioController {
    @FXML
    private TextField idServicioField;

    @FXML
    private TextField tipoServicioField;

    @FXML
    private TextField precioHoraField;

    private Servicio servicioSeleccionado;

    public void setServicio(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
        if (servicioSeleccionado != null) {
            idServicioField.setText(String.valueOf(servicioSeleccionado.getIdServicio()));
            tipoServicioField.setText(servicioSeleccionado.getTipoServicio());
            precioHoraField.setText(String.valueOf(servicioSeleccionado.getPrecioHora()));
        }
    }

    @FXML
    public void guardarServicioActualizado(ActionEvent event) {
        if (idServicioField.getText().isEmpty() || tipoServicioField.getText().isEmpty() || precioHoraField.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
            return;
        }

        try {
            servicioSeleccionado.setIdServicio(Integer.parseInt(idServicioField.getText()));
            servicioSeleccionado.setTipoServicio(tipoServicioField.getText());
            servicioSeleccionado.setPrecioHora(Double.parseDouble(precioHoraField.getText()));

            ServicioDAO.updateServicio(servicioSeleccionado);
            mostrarAlerta("Éxito", "Servicio actualizado correctamente.");
            cerrarVentana();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar el servicio: " + e.getMessage());
        }
    }

    @FXML
    public void cancelarActualizacionServicio(ActionEvent event) {
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
        Stage stage = (Stage) idServicioField.getScene().getWindow();
        stage.close();
    }
}

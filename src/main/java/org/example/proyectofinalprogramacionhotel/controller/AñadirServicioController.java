package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ServicioDAO;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

public class AñadirServicioController {

    @FXML
    private TextField precioHoraTxt;

    @FXML
    private TextField tipoServicioTxt;

    public void guardarServicio(ActionEvent event) {
        String tipoServicio = tipoServicioTxt.getText();
        String precioHoraStr = precioHoraTxt.getText();

        if (tipoServicio.isEmpty() || precioHoraStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setTipoServicio(tipoServicio);
        nuevoServicio.setPrecioHora(Double.parseDouble(precioHoraStr));

        try {
            ServicioDAO.insertServicio(nuevoServicio);
            mostrarAlerta("Éxito", "Servicio añadido correctamente.");
            cerrarVentana();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio por hora debe ser un número válido.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo añadir el servicio: " + e.getMessage());
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tipoServicioTxt.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

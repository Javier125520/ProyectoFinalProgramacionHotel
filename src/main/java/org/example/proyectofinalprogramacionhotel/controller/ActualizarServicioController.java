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

    /**
     * Metodo que se ejecuta al inicializar el controlador.
     * Carga los datos del servicio seleccionado en los campos de texto.
     */
    public void setServicio(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
        if (servicioSeleccionado != null) {
            idServicioField.setText(String.valueOf(servicioSeleccionado.getIdServicio()));
            tipoServicioField.setText(servicioSeleccionado.getTipoServicio());
            precioHoraField.setText(String.valueOf(servicioSeleccionado.getPrecioHora()));
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de guardar.
     * Valida los campos y actualiza el servicio en la base de datos.
     */
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
     *
     * @param titulo   Titulo de la alerta
     * @param mensaje  Mensaje de la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de cancelar.
     * Cierra la ventana de actualización.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) idServicioField.getScene().getWindow();
        stage.close();
    }
}

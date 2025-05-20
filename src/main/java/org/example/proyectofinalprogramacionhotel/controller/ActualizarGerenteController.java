package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.GerenteDAO;
import org.example.proyectofinalprogramacionhotel.model.Gerente;

public class ActualizarGerenteController {
    @FXML
    private TextField nombreGerenteField;

    @FXML
    private TextField gmailGerenteField;

    @FXML
    private TextField contrasenaGerenteField;

    @FXML
    private TextField codigoGerenteField;

    private Gerente gerenteSeleccionado;

    /**
     * Metodo que se ejecuta al inicializar el controlador.
     * Carga los datos del gerente seleccionado en los campos de texto.
     */
    public void setGerente(Gerente gerenteSeleccionado) {
        this.gerenteSeleccionado = gerenteSeleccionado;
        if (gerenteSeleccionado != null) {
            nombreGerenteField.setText(gerenteSeleccionado.getNombre());
            gmailGerenteField.setText(gerenteSeleccionado.getGmail());
            contrasenaGerenteField.setText(gerenteSeleccionado.getContrasena());
            codigoGerenteField.setText(gerenteSeleccionado.getCodigo());
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de guardar.
     * Valida los campos y actualiza el gerente en la base de datos.
     */
    @FXML
    public void guardarGerenteActualizado(ActionEvent event) {
        if (nombreGerenteField.getText() == null || nombreGerenteField.getText().isEmpty() ||
                gmailGerenteField.getText() == null || gmailGerenteField.getText().isEmpty() ||
                contrasenaGerenteField.getText() == null || contrasenaGerenteField.getText().isEmpty() ||
                codigoGerenteField.getText() == null || codigoGerenteField.getText().isEmpty()) {

            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
            return;
        }

        if (gerenteSeleccionado != null) {
            gerenteSeleccionado.setNombre(nombreGerenteField.getText());
            gerenteSeleccionado.setGmail(gmailGerenteField.getText());
            gerenteSeleccionado.setContrasena(contrasenaGerenteField.getText());
            gerenteSeleccionado.setCodigo(codigoGerenteField.getText());

            try {
                GerenteDAO.updateGerente(gerenteSeleccionado);
                mostrarAlerta("Éxito", "Gerente actualizado correctamente.");
                cerrarVentana();
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo actualizar el gerente: " + e.getMessage());
            }
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de cancelar.
     * Cierra la ventana de actualizacion.
     */
    @FXML
    public void cancelarActualizacion(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Metodo que muestra una alerta con el mensaje especificado.
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
     * Metodo que cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) nombreGerenteField.getScene().getWindow();
        stage.close();
    }
}

package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ClienteDAO;
import org.example.proyectofinalprogramacionhotel.model.Cliente;

public class ActualizarClienteController {

    @FXML
    public Button cancelarActualizacionCliente;

    @FXML
    private TextField nombreClienteField;

    @FXML
    private TextField emailClienteField;

    @FXML
    private TextField contrasenaClienteField;

    @FXML
    private TextField dniClienteField;

    @FXML
    private TextField telefonoClienteField;

    private Cliente clienteSeleccionado;

    /**
     * Metodo que se ejecuta al inicializar el controlador.
     * Carga los datos del cliente seleccionado en los campos de texto.
     */
    public void setCliente(Cliente cliente) {
        this.clienteSeleccionado = cliente;
        if (cliente != null) {
            nombreClienteField.setText(cliente.getNombre());
            emailClienteField.setText(cliente.getEmail());
            contrasenaClienteField.setText(cliente.getContrasena());
            dniClienteField.setText(cliente.getDni());
            telefonoClienteField.setText(cliente.getTelefono());
        }
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de guardar.
     * Valida los campos y actualiza el cliente en la base de datos.
     */
    @FXML
    public void guardarClienteActualizado() {
        if (nombreClienteField.getText() == null || nombreClienteField.getText().isEmpty() ||
                emailClienteField.getText() == null || emailClienteField.getText().isEmpty() ||
                contrasenaClienteField.getText() == null || contrasenaClienteField.getText().isEmpty() ||
                dniClienteField.getText() == null || dniClienteField.getText().isEmpty() ||
                telefonoClienteField.getText() == null || telefonoClienteField.getText().isEmpty()) {

            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
            return;
        }

        if (clienteSeleccionado != null) {
            clienteSeleccionado.setNombre(nombreClienteField.getText());
            clienteSeleccionado.setEmail(emailClienteField.getText());
            clienteSeleccionado.setContrasena(contrasenaClienteField.getText());
            clienteSeleccionado.setDni(dniClienteField.getText());
            clienteSeleccionado.setTelefono(telefonoClienteField.getText());

            try {
                ClienteDAO.updateCliente(clienteSeleccionado);
                mostrarAlerta("Éxito", "Cliente actualizado correctamente.");
                cerrarVentana();
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo actualizar el cliente: " + e.getMessage());
            }
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
        Stage stage = (Stage) nombreClienteField.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo que se ejecuta al hacer clic en el boton de cancelar.
     * Cierra la ventana actual.
     * @param actionEvent Evento de accion.
     */
    public void cancelarActualizacion(ActionEvent actionEvent) {
        // Cerrar la ventana actual
        Stage stage = (Stage) cancelarActualizacionCliente.getScene().getWindow();
        stage.close();
    }
}

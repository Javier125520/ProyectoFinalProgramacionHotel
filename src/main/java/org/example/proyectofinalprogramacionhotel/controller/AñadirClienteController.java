package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ClienteDAO;
import org.example.proyectofinalprogramacionhotel.model.Cliente;
import org.example.proyectofinalprogramacionhotel.utils.Utiles;

import java.util.ArrayList;
import java.util.List;

public class AñadirClienteController {
    @FXML
    private Button guardarCliente;

    @FXML
    private Button cancelarCliente;

    @FXML
    private TextField nombreClienteField;

    @FXML
    private TextField gmailClienteField;

    @FXML
    private TextField contrasenaClienteField;

    @FXML
    private TextField dniClienteField;

    @FXML
    private TextField telefonoClienteField;

    @FXML
    public void guardarNuevoCliente(ActionEvent actionEvent) {
        List<String> errores = new ArrayList<>();

        String nombre = nombreClienteField.getText();
        String gmail = gmailClienteField.getText();
        String contrasena = contrasenaClienteField.getText();
        String dni = dniClienteField.getText();
        String telefono = telefonoClienteField.getText();

        // Validar campos vacíos
        if (nombre.isEmpty()) errores.add("El campo 'Nombre' es obligatorio.");
        if (gmail.isEmpty()) errores.add("El campo 'Gmail' es obligatorio.");
        if (contrasena.isEmpty()) errores.add("El campo 'Contraseña' es obligatorio.");
        if (dni.isEmpty()) errores.add("El campo 'DNI' es obligatorio.");
        if (telefono.isEmpty()) errores.add("El campo 'Teléfono' es obligatorio.");

        // Validar formato del nombre
        if (!nombre.isEmpty() && !Utiles.validarNombre(nombre)) {
            errores.add("El nombre solo puede contener letras y espacios.");
        }

        // Validar formato del Gmail
        if (!gmail.isEmpty() && !Utiles.validarGmail(gmail)) {
            errores.add("El Gmail no tiene un formato válido.");
        }

        // Validar formato de la contraseña
        if (!contrasena.isEmpty() && !Utiles.validarContrasena(contrasena)) {
            errores.add("La contraseña debe tener al menos 8 caracteres, incluyendo letras y números.");
        }

        // Validar formato del teléfono
        if (!telefono.isEmpty() && !Utiles.validarTelefono(telefono)) {
            errores.add("El teléfono no tiene un formato válido (9 dígitos).");
        }

        // Validar formato del DNI
        if (!dni.isEmpty() && !Utiles.validarDNI(dni)) {
            errores.add("El DNI no tiene un formato válido (8 dígitos seguidos de una letra mayúscula).");
        }

        // Validar duplicados
        if (ClienteDAO.existeGmailCliente(gmail)) {
            errores.add("El Gmail ya está registrado.");
        }
        if (ClienteDAO.existeDNICliente(dni)) {
            errores.add("El DNI ya está registrado.");
        }

        // Mostrar errores si existen
        if (!errores.isEmpty()) {
            mostrarErrores(errores);
            return;
        }

        // Crear objeto Cliente
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setGmail(gmail);
        nuevoCliente.setContrasena(contrasena);
        nuevoCliente.setDni(dni);
        nuevoCliente.setTelefono(telefono);

        // Insertar cliente en la base de datos
        try {
            ClienteDAO.insertCliente(nuevoCliente);

            // Limpiar campos
            nombreClienteField.clear();
            gmailClienteField.clear();
            contrasenaClienteField.clear();
            dniClienteField.clear();
            telefonoClienteField.clear();

            // Mostrar mensaje de éxito
            mostrarAlerta("Éxito", "Cliente añadido correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar el cliente: " + e.getMessage());
        }
    }

    private void mostrarErrores(List<String> errores) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Errores de Validación");
        alerta.setHeaderText("Se encontraron los siguientes errores:");
        alerta.setContentText(String.join("\n", errores));
        alerta.showAndWait();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void cancelarCliente(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) cancelarCliente.getScene().getWindow();
        stage.close();
    }


}

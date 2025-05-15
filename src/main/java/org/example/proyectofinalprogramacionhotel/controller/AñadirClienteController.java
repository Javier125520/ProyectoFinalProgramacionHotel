package org.example.proyectofinalprogramacionhotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.proyectofinalprogramacionhotel.utils.Utiles;

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

    // Método para manejar el evento de añadir un cliente
    public void añadirCliente() {
        // Validar los datos
        if (validarDatos()) {
            // Lógica para añadir el cliente a la base de datos
            System.out.println("Cliente añadido");
        } else {
            System.out.println("Datos inválidos");
        }
    }

    // Método para validar los datos ingresados
    public boolean validarDatos() {
        // Obtener los datos ingresados
        String nombre = nombreClienteField.getText();
        String gmail = gmailClienteField.getText();
        String contrasena = contrasenaClienteField.getText();
        String dni = dniClienteField.getText();
        String telefono = telefonoClienteField.getText();
        // Aquí puedes usar las funciones de validación que ya tienes en Utiles
        if (!Utiles.validarNombre(nombre)) {
            System.out.println("Nombre inválido");
            return false;
        }
        if (!Utiles.validarGmail(gmail)) {
            System.out.println("Gmail inválido");
            return false;
        }
        if (!Utiles.validarContrasena(contrasena)) {
            System.out.println("Contraseña inválida");
            return false;
        }
        if (!Utiles.validarDNI(dni)) {
            System.out.println("DNI inválido");
            return false;
        }
        if (!Utiles.validarTelefono(telefono)) {
            System.out.println("Teléfono inválido");
            return false;
        }

        return true; // Cambia esto según la validación real
    }
}

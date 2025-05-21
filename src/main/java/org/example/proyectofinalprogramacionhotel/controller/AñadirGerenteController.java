package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.GerenteDAO;
import org.example.proyectofinalprogramacionhotel.model.Gerente;
import org.example.proyectofinalprogramacionhotel.utils.Utiles;

import java.util.ArrayList;
import java.util.List;

public class AñadirGerenteController {

    @FXML
    private TextField nombreGerenteTxt;

    @FXML
    private TextField gmailGerenteTxt;

    @FXML
    private TextField contrasenaGerenteTxt;

    @FXML
    private TextField codigoGerenteTxt;

    @FXML
    public void guardarGerente(ActionEvent event) {
        List<String> errores = new ArrayList<>();

        String nombre = nombreGerenteTxt.getText();
        String gmail = gmailGerenteTxt.getText();
        String contrasena = contrasenaGerenteTxt.getText();
        String codigo = codigoGerenteTxt.getText();

        // Validar campos vacíos
        if (nombre.isEmpty()) errores.add("El campo 'Nombre' es obligatorio.");
        if (gmail.isEmpty()) errores.add("El campo 'Gmail' es obligatorio.");
        if (contrasena.isEmpty()) errores.add("El campo 'Contraseña' es obligatorio.");
        if (codigo.isEmpty()) errores.add("El campo 'Código' es obligatorio.");

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

        // Validar formato del código
        if (!codigo.isEmpty() && !Utiles.validarCodigoGerente(codigo)) {
            errores.add("El código solo puede contener letras y números.");
        }

        // Validar que el Gmail no esté ya registrado
        if (GerenteDAO.existeGmailGerente(gmail)) {

            errores.add("El Gmail ya está registrado.");
        }

        // Validar que el código no esté ya registrado
        if (GerenteDAO.existeCodigoGerente(codigo)) {
            errores.add("El código ya está registrado.");
        }

        // Mostrar errores si existen
        if (!errores.isEmpty()) {
            mostrarErrores(errores);
            return;
        }

        // Crear objeto Gerente
        Gerente nuevoGerente = new Gerente();
        nuevoGerente.setNombre(nombre);
        nuevoGerente.setGmail(gmail);
        nuevoGerente.setContrasena(contrasena);
        nuevoGerente.setCodigo(codigo);

        // Insertar gerente en la base de datos
        try {
            GerenteDAO.insertGerente(nuevoGerente);

            // Limpiar campos
            nombreGerenteTxt.clear();
            gmailGerenteTxt.clear();
            contrasenaGerenteTxt.clear();
            codigoGerenteTxt.clear();

            // Mostrar mensaje de éxito
            mostrarAlerta("Éxito", "Gerente añadido correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar el gerente: " + e.getMessage());
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

    @FXML
    public void cancelar(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) nombreGerenteTxt.getScene().getWindow();
        stage.close();
    }
}

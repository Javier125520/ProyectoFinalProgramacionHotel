package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.model.Cliente;
import org.example.proyectofinalprogramacionhotel.model.Reserva;
import org.example.proyectofinalprogramacionhotel.model.estadoReserva;
import org.example.proyectofinalprogramacionhotel.utils.Utiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AñadirReservaController {
    public DatePicker fechaEntradaPicker;
    public DatePicker fechaSalidaPicker;
    public ComboBox estadoReservaCombo;
    public TextField numPersonasField;
    private Cliente clienteSeleccionado;

    public void initialize() {
        // Cargar los valores de la enumeración estadoReserva en el ComboBox
        estadoReservaCombo.getItems().setAll(
                estadoReserva.EnProceso.name(),
                estadoReserva.Completada.name(),
                estadoReserva.Cancelada.name()
        );
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public void guardarNuevaReserva(ActionEvent actionEvent) {
        List<String> errores = new ArrayList<>();

        // Obtener datos de los campos
        LocalDate fechaEntrada = fechaEntradaPicker.getValue();
        LocalDate fechaSalida = fechaSalidaPicker.getValue();
        String estadoReserva = (String) estadoReservaCombo.getValue();
        String numPersonasStr = numPersonasField.getText();

        // Validar campos vacíos
        if (fechaEntrada == null) errores.add("El campo 'Fecha Entrada' es obligatorio.");
        if (fechaSalida == null) errores.add("El campo 'Fecha Salida' es obligatorio.");
        if (estadoReserva == null || estadoReserva.isEmpty()) errores.add("El campo 'Estado Reserva' es obligatorio.");
        if (numPersonasStr == null || numPersonasStr.isEmpty()) errores.add("El campo 'Número de Personas' es obligatorio.");

        // Validar formato del número de personas
        if (!numPersonasStr.isEmpty() && !Utiles.validarNumeroPersonas(numPersonasStr)) {
            errores.add("El número de personas debe ser un número entero positivo.");
        }

        // Validar que la fecha de salida sea posterior a la fecha de entrada
        if (fechaEntrada != null && fechaSalida != null && !fechaSalida.isAfter(fechaEntrada)) {
            errores.add("La fecha de salida debe ser posterior a la fecha de entrada.");
        }

        // Mostrar errores si existen
        if (!errores.isEmpty()) {
            mostrarErrores(errores);
            return;
        }

        // Crear objeto Reserva
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setFechaEntrada(fechaEntrada);
        nuevaReserva.setFechaSalida(fechaSalida);
        nuevaReserva.setEstadoReserva(org.example.proyectofinalprogramacionhotel.model.estadoReserva.valueOf(estadoReserva));
        nuevaReserva.setNumPersonas(Integer.parseInt(numPersonasStr));

        // Obtener cliente seleccionado
        if (clienteSeleccionado == null) {
            mostrarAlerta("Error", "No se ha seleccionado un cliente.");
            return;
        }
        nuevaReserva.setIdCliente(clienteSeleccionado.getIdCliente());

        // Guardar reserva en la base de datos
        try {
            ReservaDAO.insertReserva(nuevaReserva);

            // Actualizar la lista de reservas del cliente
            clienteSeleccionado.getMisReservas().add(nuevaReserva);

            // Mostrar mensaje de éxito
            mostrarAlerta("Éxito", "Reserva añadida correctamente.");

            // Cerrar la ventana
            cancelarReserva(actionEvent);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar la reserva: " + e.getMessage());
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

    public void cancelarReserva(ActionEvent actionEvent) {
        // Cerrar la ventana actual
        fechaEntradaPicker.getScene().getWindow().hide();
    }
}

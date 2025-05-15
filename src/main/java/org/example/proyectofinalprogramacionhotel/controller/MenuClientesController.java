package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ClienteDAO;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.MainApplication;
import org.example.proyectofinalprogramacionhotel.model.Cliente;
import org.example.proyectofinalprogramacionhotel.model.Reserva;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;

import java.io.IOException;
import java.util.List;

public class MenuClientesController {

    @FXML
    private ListView<Cliente> clientesLst;

    @FXML
    private Label idCliente;

    @FXML
    private Label nombreCliente;

    @FXML
    private Label gmailCliente;

    @FXML
    private Label contrasenaCliente;

    @FXML
    private Label dniCliente;

    @FXML
    private Label telefonoCliente;

    @FXML
    private Button reservasCliente;

    @FXML
    private MenuButton menuOpciones;

    @FXML
    private MenuItem menuItemAñadir;

    @FXML
    private MenuItem menuItemActualizar;

    @FXML
    private MenuItem menuItemEliminar;

    @FXML
    public void initialize() {
        clientesLst.setCellFactory(lv -> new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);
                if (empty || cliente == null) {
                    setText(null);
                } else {
                    setText(cliente.getNombre());
                }
            }
        });

        // Cargar los clientes desde la base de datos
        List<Cliente> clientes = ClienteDAO.findAll();
        clientesLst.getItems().setAll(clientes); // Añadir los clientes al ListView
    }

    public void mostrarClienteSeleccionado(MouseEvent mouseEvent) {
        Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            mostrarInformacionCliente(clienteSeleccionado);
        }
    }

    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente != null) {
            idCliente.setText(String.valueOf(cliente.getIdCliente()));
            nombreCliente.setText(cliente.getNombre());
            gmailCliente.setText(cliente.getGmail());
            contrasenaCliente.setText(cliente.getContrasena());
            dniCliente.setText(cliente.getDni());
            telefonoCliente.setText(cliente.getTelefono());
        } else  {
            idCliente.setText("");
            nombreCliente.setText("");
            gmailCliente.setText("");
            contrasenaCliente.setText("");
            dniCliente.setText("");
            telefonoCliente.setText("");
        }
    }

    @FXML
    public void añadirCliente(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AñadirCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir Cliente");
        stage.show();
    }

    @FXML
    public void actualizarCliente(ActionEvent event) {
        // Lógica para actualizar un cliente
        System.out.println("Actualizar cliente seleccionado");
    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
        // Lógica para eliminar un cliente
        System.out.println("Eliminar cliente seleccionado");
    }
}

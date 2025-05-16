package org.example.proyectofinalprogramacionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.ClienteDAO;
import org.example.proyectofinalprogramacionhotel.DAO.ReservaDAO;
import org.example.proyectofinalprogramacionhotel.MainApplication;
import org.example.proyectofinalprogramacionhotel.model.Cliente;
import org.example.proyectofinalprogramacionhotel.model.Reserva;

import java.io.IOException;
import java.util.List;

public class MenuClientesController {

    @FXML
    public TableView reservasClienteTbl;

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
    private TableColumn<Reserva, Integer> colIdReserva;

    @FXML
    private TableColumn<Reserva, String> colFechaEntrada;

    @FXML
    private TableColumn<Reserva, String> colFechaSalida;

    @FXML
    private TableColumn<Reserva, String> colEstado;

    @FXML
    public void initialize() {
        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoReserva"));

        clientesLst.setCellFactory(_ -> new ListCell<>() {
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

        List<Cliente> clientes = ClienteDAO.findAll();
        clientesLst.getItems().setAll(clientes);
    }

    public void mostrarClienteSeleccionado(MouseEvent mouseEvent) {
        Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            mostrarInformacionCliente(clienteSeleccionado);
            mostrarReservasCliente(clienteSeleccionado.getIdCliente());
        }
    }

    private void mostrarReservasCliente(int idCliente) {
        List<Reserva> reservas = ReservaDAO.findByIdCliente(idCliente);
        reservasClienteTbl.getItems().setAll(reservas);
    }

    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente != null) {
            idCliente.setText(String.valueOf(cliente.getIdCliente()));
            nombreCliente.setText(cliente.getNombre());
            gmailCliente.setText(cliente.getGmail());
            contrasenaCliente.setText(cliente.getContrasena());
            dniCliente.setText(cliente.getDni());
            telefonoCliente.setText(cliente.getTelefono());
        } else {
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
        stage.setOnHidden(e -> {
            List<Cliente> clientes = ClienteDAO.findAll();
            clientesLst.getItems().setAll(clientes);
        });
        stage.show();
    }

    @FXML
    public void actualizarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarCliente.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ActualizarClienteController controller = fxmlLoader.getController();
                controller.setCliente(clienteSeleccionado);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Actualizar Cliente");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un cliente para actualizar.");
            alert.showAndWait();
        }
    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de que deseas eliminar este cliente?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ClienteDAO.deleteCliente(clienteSeleccionado.getIdCliente());
                    clientesLst.getItems().remove(clienteSeleccionado);
                    mostrarInformacionCliente(null);
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un cliente para eliminar.");
            alert.showAndWait();
        }
    }

    public void volverAlMenuPrincipal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Inicio");
        stage.show();
    }

    public void añadirReserva(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AñadirReserva.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AñadirReservaController controller = fxmlLoader.getController();
        controller.setClienteSeleccionado(clientesLst.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir Reserva");
        stage.setOnHidden(e -> {
            Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
            if (clienteSeleccionado != null) {
                mostrarReservasCliente(clienteSeleccionado.getIdCliente());
            }
        });
        stage.show();
    }

    public void actualizarReserva(ActionEvent actionEvent) {
        // Implementar lógica para actualizar reserva
    }

    public void eliminarReserva(ActionEvent actionEvent) {
        // Implementar lógica para eliminar reserva
    }
}

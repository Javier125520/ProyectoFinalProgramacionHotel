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
import org.example.proyectofinalprogramacionhotel.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MenuClientesController {

    @FXML
    public TableView reservasClienteTbl;

    @FXML
    private ListView<Cliente> clientesLst;

    @FXML
    private TableView<Habitacion> habitacionesTbl;

    @FXML
    private TableView<Servicio> serviciosTbl;

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
    private TableColumn<Reserva, Integer> colNumPersonas;

    @FXML
    private TableColumn<Habitacion, Integer> colIdHabitacion;

    @FXML
    private TableColumn<Habitacion, Integer> colNumeroHabitacion;

    @FXML
    private TableColumn<Habitacion, String> colTipoHabitacion;

    @FXML
    private TableColumn<Habitacion, Double> colPrecioNoche;

    @FXML
    private TableColumn<Habitacion, String> colEstadoHabitacion;

    @FXML
    private TableColumn<Habitacion, Integer> colIdGerente;

    @FXML
    private TableColumn<Servicio, Integer> colIdServicio;

    @FXML
    private TableColumn<Servicio, String> colTipoServicio;

    @FXML
    private TableColumn<Servicio, Double> colPrecioHoraServicio;

    @FXML
    private TableView<ReservaServicio> reservasServiciosTbl;

    @FXML
    private TableColumn<ReservaServicio, Integer> colIdReservaServicio;
    @FXML
    private TableColumn<ReservaServicio, LocalDate> colFechaReserva;
    @FXML
    private TableColumn<ReservaServicio, Integer> colNumeroPersonas;
    @FXML
    private TableColumn<ReservaServicio, Integer> colPrecio;
    @FXML
    private TableColumn<ReservaServicio, LocalDate> colFechaInicio;
    @FXML
    private TableColumn<ReservaServicio, LocalDate> colFechaFin;

    @FXML
    public void initialize() {
        // Configurar columnas de reservas
        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoReserva"));
        colNumPersonas.setCellValueFactory(new PropertyValueFactory<>("numPersonas"));

        // Configurar columnas de habitaciones
        colIdHabitacion.setCellValueFactory(new PropertyValueFactory<>("idHabitacion"));
        colNumeroHabitacion.setCellValueFactory(new PropertyValueFactory<>("numeroHabitacion"));
        colTipoHabitacion.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
        colPrecioNoche.setCellValueFactory(new PropertyValueFactory<>("precioNoche"));
        colEstadoHabitacion.setCellValueFactory(new PropertyValueFactory<>("estadoHabitacion"));
        colIdGerente.setCellValueFactory(new PropertyValueFactory<>("idGerente"));

        // Configurar columnas de reservas y servicios
        colIdReservaServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
        colFechaReserva.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));
        colNumeroPersonas.setCellValueFactory(new PropertyValueFactory<>("numeroPersonas"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));

        // Configurar columnas de servicios
        colIdServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
        colTipoServicio.setCellValueFactory(new PropertyValueFactory<>("tipoServicio"));
        colPrecioHoraServicio.setCellValueFactory(new PropertyValueFactory<>("precioHora"));

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
                stage.setOnHidden(e -> {
                    List<Cliente> clientes = ClienteDAO.findAll();
                    clientesLst.getItems().setAll(clientes);
                });
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
        Reserva reservaSeleccionada = (Reserva) reservasClienteTbl.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarReserva.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ActualizarReservaController controller = fxmlLoader.getController();
                controller.setReserva(reservaSeleccionada);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Actualizar Reserva");
                stage.setOnHidden(e -> {
                    Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
                    if (clienteSeleccionado != null) {
                        mostrarReservasCliente(clienteSeleccionado.getIdCliente());
                    }
                });
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una reserva para actualizar.");
            alert.showAndWait();
        }
    }

    public void eliminarReserva(ActionEvent actionEvent) {
        Reserva reservaSeleccionada = (Reserva) reservasClienteTbl.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de que deseas eliminar esta reserva?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ReservaDAO.deleteReserva(reservaSeleccionada.getIdReserva());
                    Cliente clienteSeleccionado = clientesLst.getSelectionModel().getSelectedItem();
                    if (clienteSeleccionado != null) {
                        mostrarReservasCliente(clienteSeleccionado.getIdCliente());
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una reserva para eliminar.");
            alert.showAndWait();
        }
    }

    private void mostrarServiciosReserva() {
        // Implementar lógica para mostrar servicios de la reserva
        Reserva reservaSeleccionada = (Reserva) reservasClienteTbl.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            List<Servicio> servicios = ReservaDAO.findServiciosByIdReserva(reservaSeleccionada.getIdReserva());
            serviciosTbl.getItems().setAll(servicios);
        } else {
            serviciosTbl.getItems().clear();
        }
    }

    private void mostrarHabitacionesReserva() {
        // Implementar lógica para mostrar habitaciones de la reserva
        Reserva reservaSeleccionada = (Reserva) reservasClienteTbl.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            List<Habitacion> habitaciones = ReservaDAO.findHabitacionesByIdReserva(reservaSeleccionada.getIdReserva());
            habitacionesTbl.getItems().setAll(habitaciones);
        } else {
            habitacionesTbl.getItems().clear();
        }
    }

    public void añadirHabitacion(ActionEvent actionEvent) {
    }

    public void añadirServicio(ActionEvent actionEvent) {
    }

    @FXML
    public void actualizarHabitacion() {
        Habitacion habitacionSeleccionada = habitacionesTbl.getSelectionModel().getSelectedItem();
        if (habitacionSeleccionada != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarHabitacion.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ActualizarHabitacionController controller = fxmlLoader.getController();
                controller.setHabitacion(habitacionSeleccionada);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Actualizar Habitación");
                stage.setOnHidden(e -> mostrarHabitacionesReserva());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una habitación para actualizar.");
            alert.showAndWait();
        }
    }

    @FXML
    public void actualizarServicio() {
        Servicio servicioSeleccionado = serviciosTbl.getSelectionModel().getSelectedItem();
        if (servicioSeleccionado != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarServicio.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ActualizarServicioController controller = fxmlLoader.getController();
                controller.setServicio(servicioSeleccionado);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Actualizar Servicio");
                stage.setOnHidden(e -> mostrarServiciosReserva());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un servicio para actualizar.");
            alert.showAndWait();
        }
    }

    public void eliminarHabitacion(ActionEvent actionEvent) {
    }

    public void eliminarServicio(ActionEvent actionEvent) {
    }

    public void añadirReservaServicio(ActionEvent actionEvent) {
        // Implementar lógica para añadir servicio a la reserva
    }

    public void eliminarReservaServicio(ActionEvent actionEvent) {
        // Implementar lógica para eliminar servicio de la reserva
    }

    public void actualizarReservaServicio(ActionEvent actionEvent) {
        // Implementar lógica para actualizar servicio de la reserva
    }

    public void volverAlMenuPrincipal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Inicio");
        stage.show();
    }
}

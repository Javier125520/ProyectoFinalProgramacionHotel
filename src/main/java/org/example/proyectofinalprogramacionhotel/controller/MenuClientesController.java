package org.example.proyectofinalprogramacionhotel.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.proyectofinalprogramacionhotel.DAO.*;
import org.example.proyectofinalprogramacionhotel.MainApplication;
import org.example.proyectofinalprogramacionhotel.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MenuClientesController {

    @FXML
    private TableView<Reserva> reservasClienteTbl;

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
    private Label emailCliente;

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
    private TableColumn<Servicio, Double> colPrecioHora;

    @FXML
    private TableView<Habitacion> habitacionesReservaTbl;

    @FXML
    private TableColumn<Habitacion, Integer> colNumeroHabitacionReserva;

    @FXML
    private TableColumn<Habitacion, String> colTipoHabitacionReserva;

    @FXML
    private TableColumn<Habitacion, Double> colPrecioNocheReserva;

    @FXML
    private TableView<ReservaServicio> serviciosReservaTbl;

    @FXML
    private TableColumn<ReservaServicio, String> colTipoServicioReserva;

    @FXML
    private TableColumn<ReservaServicio, Integer> colNumPersonasReserva;

    @FXML
    private TableColumn<ReservaServicio, LocalDate> colFechaReserva;

    @FXML
    private TableColumn<ReservaServicio, LocalDate> colFechaInicioServicio;

    @FXML
    private TableColumn<ReservaServicio, LocalDate> colFechaFinServicio;

    @FXML
    private TableColumn<ReservaServicio, Integer> colPrecioServicio;

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

        // Configurar columnas de la tabla de servicios
        colIdServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
        colTipoServicio.setCellValueFactory(new PropertyValueFactory<>("tipoServicio"));
        colPrecioHora.setCellValueFactory(new PropertyValueFactory<>("precioHora"));

        // Configurar columnas de habitaciones reservadas
        colNumeroHabitacionReserva.setCellValueFactory(new PropertyValueFactory<>("numeroHabitacion"));
        colTipoHabitacionReserva.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
        colPrecioNocheReserva.setCellValueFactory(new PropertyValueFactory<>("precioNoche"));

        // Configurar columnas de servicios
        colTipoServicioReserva.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getServicio().getTipoServicio()));
        colNumPersonasReserva.setCellValueFactory(new PropertyValueFactory<>("numeroPersonas"));
        colFechaReserva.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));
        colFechaInicioServicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFinServicio.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colPrecioServicio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        /**
         * Cuando se selecciona un cliente, se muestran sus reservas
         */
        reservasClienteTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mostrarHabitacionesReserva();
            mostrarServiciosDisponibles();
        });

        /**
         * Cuando se selecciona una reserva, se muestran las habitaciones y servicios
         */
        reservasClienteTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mostrarHabitacionesReserva(newValue);
                mostrarServiciosReserva(newValue);
            }
        });

        /**
         *
         */
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

    private void mostrarHabitacionesReserva(Reserva reserva) {
        List<Habitacion> habitaciones = HabitacionDAO.findByIdReserva(reserva.getIdReserva());
        habitacionesReservaTbl.getItems().setAll(habitaciones);
    }

    private void mostrarServiciosReserva(Reserva reserva) {
        List<ReservaServicio> servicios = ReservaServicioDAO.findByIdReserva(reserva.getIdReserva());
        serviciosReservaTbl.getItems().setAll(servicios);
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
            emailCliente.setText(cliente.getEmail());
            contrasenaCliente.setText(cliente.getContrasena());
            dniCliente.setText(cliente.getDni());
            telefonoCliente.setText(cliente.getTelefono());
        } else {
            idCliente.setText("");
            nombreCliente.setText("");
            emailCliente.setText("");
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
        Reserva reservaSeleccionada = reservasClienteTbl.getSelectionModel().getSelectedItem();
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
        Reserva reservaSeleccionada = reservasClienteTbl.getSelectionModel().getSelectedItem();
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

    private void mostrarServiciosDisponibles() {
        List<Servicio> servicios = ServicioDAO.findAll();
        serviciosTbl.getItems().setAll(servicios);
    }

    private void mostrarHabitacionesReserva() {
        Reserva reservaSeleccionada = reservasClienteTbl.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            // Mostrar habitaciones con estado "Libre"
            List<Habitacion> habitacionesLibres = HabitacionDAO.findHabitacionesDisponibles();
            habitacionesTbl.getItems().setAll(habitacionesLibres);
        } else {
            habitacionesTbl.getItems().clear();
        }
    }


    @FXML
    public void asignarHabitacion(ActionEvent actionEvent) {
        Reserva reservaSeleccionada = reservasClienteTbl.getSelectionModel().getSelectedItem();
        Habitacion habitacionSeleccionada = habitacionesTbl.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada == null) {
            mostrarAlerta("Error", "Por favor, selecciona una reserva.");
            return;
        }

        if (habitacionSeleccionada == null) {
            mostrarAlerta("Error", "Por favor, selecciona una habitación.");
            return;
        }

        try {
            // Verificar que el idReserva existe en la tabla reserva
            if (ReservaDAO.findById(reservaSeleccionada.getIdReserva()) == null) {
                mostrarAlerta("Error", "La reserva seleccionada no existe.");
                return;
            }

            // Asignar la habitación a la reserva
            habitacionSeleccionada.setIdReserva(reservaSeleccionada.getIdReserva());
            habitacionSeleccionada.setEstadoHabitacion(estadoHabitacion.Ocupada);
            HabitacionDAO.updateHabitacion(habitacionSeleccionada);

            // Actualizar tablas
            mostrarAlerta("Éxito", "Habitación asignada correctamente.");
            mostrarHabitacionesReserva();
            mostrarHabitacionesReserva(reservaSeleccionada);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo asignar la habitación: " + e.getMessage());
        }
    }

    @FXML
    public void cancelarReservaHabitacion(ActionEvent actionEvent) {
        Habitacion habitacionSeleccionada = habitacionesReservaTbl.getSelectionModel().getSelectedItem();

        if (habitacionSeleccionada == null) {
            mostrarAlerta("Error", "Por favor, selecciona una habitación para desvincular.");
            return;
        }

        try {
            habitacionSeleccionada.setIdReserva(null); // Desvincular la reserva
            habitacionSeleccionada.setEstadoHabitacion(estadoHabitacion.Libre); // Cambiar estado a Libre
            HabitacionDAO.updateHabitacion(habitacionesReservaTbl.getSelectionModel().getSelectedItem());

            mostrarAlerta("Éxito", "Habitación desvinculada correctamente.");

            Reserva reservaSeleccionada = reservasClienteTbl.getSelectionModel().getSelectedItem();
            if (reservaSeleccionada != null) {
                mostrarHabitacionesReserva(reservaSeleccionada);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo desvincular la habitación: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String error, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(error);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private void reservarServicio() {
        try {
            Reserva reservaSeleccionada = reservasClienteTbl.getSelectionModel().getSelectedItem();
            Servicio servicioSeleccionado = serviciosTbl.getSelectionModel().getSelectedItem();

            if (reservaSeleccionada == null) {
                mostrarAlerta("Error", "Debe seleccionar una reserva.");
                return;
            }

            if (servicioSeleccionado == null) {
                mostrarAlerta("Error", "Debe seleccionar un servicio.");
                return;
            }

            // Cargar el archivo FXML del formulario para reservar un servicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AñadirReservaServicio.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtener el controlador del formulario
            AñadirReservaServicioController controller = loader.getController();
            controller.setDatos(reservaSeleccionada, servicioSeleccionado);

            // Mostrar la ventana
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Reservar Servicio");
            stage.showAndWait();

            // Actualizar la tabla de reservas de servicios después de cerrar la ventana
            mostrarServiciosReserva(reservaSeleccionada);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el formulario: " + e.getMessage());
        }
    }

    @FXML
    public void cancelarReservaServicio(ActionEvent actionEvent) {
        ReservaServicio reservaServicioSeleccionada = serviciosReservaTbl.getSelectionModel().getSelectedItem();

        if (reservaServicioSeleccionada == null) {
            mostrarAlerta("Error", "Por favor, selecciona una reserva de servicio para eliminar.");
            return;
        }

        try {
            ReservaServicioDAO.deleteReservaServicio(reservaServicioSeleccionada.getIdReserva());

            mostrarAlerta("Éxito", "Reserva de servicio eliminada correctamente.");
            mostrarServiciosReserva(reservasClienteTbl.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar la reserva de servicio: " + e.getMessage());
        }
    }



    public void volverAlMenuPrincipal(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el menú principal.");
        }
    }

    public void actualizarReservaServicio(ActionEvent event) {
        ReservaServicio reservaServicioSeleccionada = serviciosReservaTbl.getSelectionModel().getSelectedItem();
        if (reservaServicioSeleccionada != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarReservaServicio.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ActualizarReservaServicioController controller = fxmlLoader.getController();
                controller.setReservaServicio(reservaServicioSeleccionada);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Actualizar Reserva de Servicio");
                stage.setOnHidden(e -> {
                    Reserva reservaSeleccionada = (Reserva) reservasClienteTbl.getSelectionModel().getSelectedItem();
                    if (reservaSeleccionada != null) {
                        mostrarServiciosReserva(reservaSeleccionada);
                    }
                });
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una reserva de servicio para actualizar.");
            alert.showAndWait();
        }
    }
}

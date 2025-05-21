package org.example.proyectofinalprogramacionhotel.controller;

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
import org.example.proyectofinalprogramacionhotel.model.Gerente;
import org.example.proyectofinalprogramacionhotel.model.Habitacion;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

import java.io.IOException;
import java.util.List;

public class MenuGerentesController {

    @FXML
    private ListView<Gerente> gerentesLst;

    @FXML
    private Label idGerente;

    @FXML
    private Label nombreGerente;

    @FXML
    private Label emailGerente;

    @FXML
    private Label contrasenaGerente;

    @FXML
    private Label codigoGerente;

    @FXML
    private TableView<Habitacion> habitacionesGerenteTbl;

    @FXML
    private TableColumn<Habitacion, Integer> colNumeroHabitacion;

    @FXML
    private TableColumn<Habitacion, String> colTipoHabitacion;

    @FXML
    private TableColumn<Habitacion, Double> colPrecioNoche;

    @FXML
    private TableColumn<Habitacion, String> colEstadoHabitacion;

    @FXML
    private TableView<Servicio> serviciosTbl;

    @FXML
    private TableColumn<Servicio, Integer> colIdServicio;

    @FXML
    private TableColumn<Servicio, String> colTipoServicio;

    @FXML
    private TableColumn<Habitacion, Double> colPrecioHoraServicio;

    public void initialize() {
        // Configurar columnas de habitaciones
        colNumeroHabitacion.setCellValueFactory(new PropertyValueFactory<>("numeroHabitacion"));
        colTipoHabitacion.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
        colPrecioNoche.setCellValueFactory(new PropertyValueFactory<>("precioNoche"));
        colEstadoHabitacion.setCellValueFactory(new PropertyValueFactory<>("estadoHabitacion"));

        // Configurar columnas de servicios
        colIdServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
        colTipoServicio.setCellValueFactory(new PropertyValueFactory<>("tipoServicio"));
        colPrecioHoraServicio.setCellValueFactory(new PropertyValueFactory<>("precioHora"));

        /**
         * Configurar la lista de gerentes para mostrar el nombre del gerente
         */
        gerentesLst.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(Gerente gerente, boolean empty) {
                super.updateItem(gerente, empty);
                if (empty || gerente == null) {
                    setText(null);
                } else {
                    setText(gerente.getNombre());
                }
            }
        });

        List<Gerente> gerentes = GerenteDAO.findAll();
        gerentesLst.getItems().setAll(gerentes);
        mostrarServicios();
    }

    @FXML
    private void mostrarGerenteSeleccionado(MouseEvent mouseEvent) {
        Gerente gerenteSeleccionado = gerentesLst.getSelectionModel().getSelectedItem();
        if (gerenteSeleccionado != null) {
            mostrarInformacionGerente(gerenteSeleccionado);
            mostrarHabitacionesGerente(gerenteSeleccionado.getIdGerente());
        }
    }

    private void mostrarHabitacionesGerente(int idGerente) {
        List<Habitacion> habitaciones = HabitacionDAO.findByIdGerente(idGerente);
        habitacionesGerenteTbl.getItems().setAll(habitaciones);
    }

    private void mostrarServicios() {
        List<Servicio> servicios = ServicioDAO.findAll();
        serviciosTbl.getItems().setAll(servicios);
    }

    @FXML
    public void mostrarInformacionGerente(Gerente gerente) {
        if (gerente != null) {
            idGerente.setText(String.valueOf(gerente.getIdGerente()));
            nombreGerente.setText(gerente.getNombre());
            emailGerente.setText(gerente.getEmail());
            contrasenaGerente.setText(gerente.getContrasena());
            codigoGerente.setText(gerente.getCodigo());
            mostrarHabitacionesGerente(gerente.getIdGerente());
        } else {
            idGerente.setText("");
            nombreGerente.setText("");
            emailGerente.setText("");
            contrasenaGerente.setText("");
            codigoGerente.setText("");
        }
    }

    public void añadirGerente(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AñadirGerente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir Gerente");
        stage.setOnHidden(e -> {
            List<Gerente> gerentes = GerenteDAO.findAll();
            gerentesLst.getItems().setAll(gerentes);
        });
        stage.show();
    }

    public void actualizarGerente(ActionEvent actionEvent) throws IOException {
        Gerente gerenteSeleccionado = gerentesLst.getSelectionModel().getSelectedItem();
        if (gerenteSeleccionado != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarGerente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ActualizarGerenteController controller = fxmlLoader.getController();
            controller.setGerente(gerenteSeleccionado);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Actualizar Gerente");
            stage.setOnHidden(e -> {
                List<Gerente> gerentes = GerenteDAO.findAll();
                gerentesLst.getItems().setAll(gerentes);
                mostrarInformacionGerente(gerenteSeleccionado);
            });
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un gerente para actualizar.");
            alert.showAndWait();
        }
    }

    public void eliminarGerente(ActionEvent actionEvent) {
        Gerente gerenteSeleccionado = gerentesLst.getSelectionModel().getSelectedItem();
        if (gerenteSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de que deseas eliminar este gerente?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    GerenteDAO.deleteGerente(gerenteSeleccionado.getIdGerente());
                    gerentesLst.getItems().remove(gerenteSeleccionado);
                    mostrarInformacionGerente(null);
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un cliente para eliminar.");
            alert.showAndWait();
        }
    }

    private void mostrarAlerta(String error, String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void añadirHabitacion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AñadirHabitacion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AñadirHabitacionController controller = fxmlLoader.getController();
        controller.setGerente(gerentesLst.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir Habitacion");
        stage.setOnHidden(event -> {
            Gerente gerenteSeleccionado = gerentesLst.getSelectionModel().getSelectedItem();
            if (gerenteSeleccionado != null) {
                mostrarHabitacionesGerente(gerenteSeleccionado.getIdGerente());
            }
        });
        stage.show();
    }

    public void eliminarHabitacion(ActionEvent actionEvent) {
        Habitacion habitacionSeleccionada = habitacionesGerenteTbl.getSelectionModel().getSelectedItem();
        if (habitacionSeleccionada != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de que deseas eliminar esta habitación?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    HabitacionDAO.deleteHabitacion(habitacionSeleccionada.getIdHabitacion()); // Corregido
                    Gerente gerenteSeleccionado = gerentesLst.getSelectionModel().getSelectedItem();
                    if (gerenteSeleccionado != null) {
                        mostrarHabitacionesGerente(gerenteSeleccionado.getIdGerente());
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una habitación para eliminar.");
            alert.showAndWait();
        }
    }

    public void actualizarHabitacion(ActionEvent actionEvent) {
        Habitacion habitacionSeleccionada = habitacionesGerenteTbl.getSelectionModel().getSelectedItem();
        if (habitacionSeleccionada != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ActualizarHabitacion.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ActualizarHabitacionController controller = fxmlLoader.getController();
                controller.setHabitacion(habitacionSeleccionada);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Actualizar Habitacion");
                stage.setOnHidden(event -> {
                    Gerente gerenteSeleccionado = gerentesLst.getSelectionModel().getSelectedItem();
                    if (gerenteSeleccionado != null) {
                        mostrarHabitacionesGerente(gerenteSeleccionado.getIdGerente());
                    }
                });
                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar la ventana de actualización de la habitación.");
            }
        } else {
            mostrarAlerta("Error", "Por favor, selecciona una habitación para actualizar.");
        }
    }

    public void añadirServicio(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AñadirServicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir Servicio");
        stage.setOnHidden(event -> mostrarServicios());
        stage.show();
    }

    public void eliminarServicio(ActionEvent actionEvent) {
        Servicio servicioSeleccionado = serviciosTbl.getSelectionModel().getSelectedItem();
        if (servicioSeleccionado != null) {
            ServicioDAO.deleteServicio(servicioSeleccionado.getIdServicio());
            mostrarServicios();
        } else {
            mostrarAlerta("Error", "Por favor, selecciona un servicio para eliminar.");
        }
    }

    public void actualizarServicio(ActionEvent actionEvent) {
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
                stage.setOnHidden(event -> mostrarServicios());
                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar la ventana de actualización del servicio.");
            }
        } else {
            mostrarAlerta("Error", "Por favor, selecciona un servicio para actualizar.");
        }
    }

    public void volverAlMenuPrincipal(ActionEvent event) {
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
}

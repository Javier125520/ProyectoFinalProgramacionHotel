module org.example.proyectofinalprogramacionhotel {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectofinalprogramacionhotel to javafx.fxml;
    exports org.example.proyectofinalprogramacionhotel;
}
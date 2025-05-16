module org.example.proyectofinalprogramacionhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens org.example.proyectofinalprogramacionhotel to javafx.fxml;
    exports org.example.proyectofinalprogramacionhotel;
    opens org.example.proyectofinalprogramacionhotel.controller to javafx.fxml;
    exports org.example.proyectofinalprogramacionhotel.controller to javafx.fxml;
    opens org.example.proyectofinalprogramacionhotel.baseDatos to java.xml.bind;
    opens org.example.proyectofinalprogramacionhotel.model to javafx.base;
}
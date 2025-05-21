package org.example.proyectofinalprogramacionhotel.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {
    private static final String File = "connection.xml";
    private static Connection connection;
    private static ConnectionBD _instance;

    private ConnectionBD() {
        // Constructor privado para evitar instanciación externa
        ConnectionProperties proterties = XMLManager.readXML(new ConnectionProperties(), File);
        try {
            connection = DriverManager.getConnection(proterties.getUrl(), proterties.getUser(), proterties.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            connection = null;
        }
    }

    public static Connection getConnection() {
        if (_instance == null) {
            _instance = new ConnectionBD();
        }
        return connection;
    }
}

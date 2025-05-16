package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Cliente;
import org.example.proyectofinalprogramacionhotel.model.Reserva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final static String SQL_INSERT = "INSERT INTO cliente (nombre, gmail, contrasena, dni, telefono) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_DELETE= "DELETE FROM cliente WHERE idCliente = ?";
    private final static String SQL_UPDATE = "UPDATE cliente SET nombre = ?, gmail = ?, contrasena = ?, dni = ?, telefono = ? WHERE idCliente = ?";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM cliente WHERE idCliente = ?";
    private final static String SQL_ALL = "SELECT * FROM cliente";

    public static Cliente insertCliente(Cliente cliente) {
        if (cliente != null && findById(cliente.getIdCliente()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setString(1, cliente.getNombre());
                pst.setString(2, cliente.getGmail());
                pst.setString(3, cliente.getContrasena());
                pst.setString(4, cliente.getDni());
                pst.setString(5, cliente.getTelefono());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            cliente = null;
        }
        return cliente;
    }

    private static Cliente findById(int idUsuario) {
        Cliente cliente = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public static List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setGmail(rs.getString("gmail"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setMisReservas(new ArrayList<>());
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static List<Cliente> findAllEager() {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                int idCliente = rs.getInt("idCliente");
                cliente.setIdCliente(idCliente);
                cliente.setNombre(rs.getString("nombre"));
                cliente.setGmail(rs.getString("gmail"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setMisReservas(ReservaDAO.findByIdCliente(idCliente));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static void updateCliente(Cliente cliente) {
        try (PreparedStatement stmt = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {;
            // Configurar los parámetros
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getGmail());
            stmt.setString(3, cliente.getContrasena());
            stmt.setString(4, cliente.getDni());
            stmt.setString(5, cliente.getTelefono());
            stmt.setInt(6, cliente.getIdCliente());
            // Ejecutar la actualización
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCliente(int idUsuario) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idUsuario);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

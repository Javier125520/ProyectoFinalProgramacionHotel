package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final static String SQL_INSERT = "INSERT INTO cliente (nombre, email, contrasena, dni, telefono) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_DELETE= "DELETE FROM cliente WHERE idCliente = ?";
    private final static String SQL_UPDATE = "UPDATE cliente SET nombre = ?, email = ?, contrasena = ?, dni = ?, telefono = ? WHERE idCliente = ?";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM cliente WHERE idCliente = ?";
    private final static String SQL_ALL = "SELECT * FROM cliente";
    private final static String SQL_FIND_BY_GMAIL_EQUALS = "SELECT COUNT(*) FROM cliente WHERE gmail = ?";
    private final static String SQL_FIND_BY_DNI_EQUALS = "SELECT COUNT(*) FROM cliente WHERE dni = ?";


    /**
     * Metodo que inserta un cliente en la base de datos.
     * @param cliente El cliente que vas a insertar.
     * @return El cliente insertado.
     */
    public static Cliente insertCliente(Cliente cliente) {
        if (cliente != null && findById(cliente.getIdCliente()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setString(1, cliente.getNombre());
                pst.setString(2, cliente.getEmail());
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

    /**
     * Metodo que busca un cliente por su id.
     * @param idCliente El id del cliente que quieres buscar.
     * @return El cliente encontrado.
     */
    private static Cliente findById(int idCliente) {
        Cliente cliente = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idCliente);
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

    /**
     * Version Lazy de obtener todos los clientes, esto quiere decir que me traigo de la base de datos los clientes
     * pero sin cargar las reservas que a hecho cada cliente.
     * @return Una lista con todos los clientes sin las reservas realizadas por cada uno.
     */
    public static List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
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


    /**
     * Version Eager de obtener todos los clientes, esto quiere decir que me traigo de la base de datos los clientes
     * con las reservas que a hecho cada cliente.
     * @return Una lista con todos los clientes con las reservas realizadas por cada uno.
     */
    public static List<Cliente> findAllEager() {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                int idCliente = rs.getInt("idCliente");
                cliente.setIdCliente(idCliente);
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
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

    /**
     * Metodo que actualiza un cliente en la base de datos.
     * @param cliente El cliente que quieres actualizar.
     */
    public static void updateCliente(Cliente cliente) {
        try (PreparedStatement stmt = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {;
            // Configurar los parámetros
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
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

    /**
     * Metodo que elimina un cliente de la base de datos.
     * @param idUsuario El id del cliente que quieres eliminar.
     */
    public static void deleteCliente(int idUsuario) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idUsuario);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que busca un cliente por su gmail.
     * @param gmail El gmail del cliente que quieres buscar.
     * @return El cliente encontrado.
     */
    public static boolean existeGmailCliente(String gmail) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_GMAIL_EQUALS)) {
            pst.setString(1, gmail);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar el gmail: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Metodo que busca un cliente por su dni.
     * @param dni El dni del cliente que quieres buscar.
     * @return El cliente encontrado.
     */
    public static boolean existeDNICliente(String dni) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DNI_EQUALS)) {
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar el DNI: " + e.getMessage(), e);
        }
        return false;
    }

}

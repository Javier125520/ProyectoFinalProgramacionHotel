package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Gerente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenteDAO {
    private final static String SQL_INSERT = "INSERT INTO gerente (nombre, gmail, contrasena, codigo) VALUES (?, ?, ?, ?)";
    private final static String SQL_ALL = "SELECT * FROM gerente";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM gerente WHERE idGerente = ?";
    private final static String SQL_UPDATE = "UPDATE gerente SET nombre = ?, gmail = ?, contrasena = ?, codigo = ? WHERE idGerente = ?";
    private final static String SQL_DELETE = "DELETE FROM gerente WHERE idGerente = ?";
    private final static String SQL_FIND_BY_GMAIL_EQUALS = "SELECT COUNT(*) FROM gerente WHERE gmail = ?";
    private final static String SQL_FIND_BY_DNI_EQUALS = "SELECT COUNT(*) FROM gerente WHERE dni = ?";
    private final static String SQL_FIND_BY_CODIGO_EQUALS = "SELECT COUNT(*) FROM gerente WHERE codigo = ?";

    public static Gerente insertGerente(Gerente gerente) {
        if (gerente != null && findById(gerente.getIdGerente()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setString(1, gerente.getNombre());
                pst.setString(2, gerente.getGmail());
                pst.setString(3, gerente.getContrasena());
                pst.setString(4, gerente.getCodigo());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            gerente = null;
        }
        return gerente;
    }

    public static List<Gerente> findAll() {
        List<Gerente> gerentes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Gerente gerente = new Gerente();
                gerente.setIdGerente(rs.getInt("idGerente"));
                gerente.setNombre(rs.getString("nombre"));
                gerente.setGmail(rs.getString("gmail"));
                gerente.setContrasena(rs.getString("contrasena"));
                gerente.setCodigo(rs.getString("codigo"));
                gerente.setMisHabitaciones(new ArrayList<>());
                gerentes.add(gerente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gerentes;
    }

    public static List<Gerente> findAllEager() {
        List<Gerente> gerentes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Gerente gerente = new Gerente();
                int idGerente = rs.getInt("idGerente");
                gerente.setIdGerente(idGerente);
                gerente.setNombre(rs.getString("nombre"));
                gerente.setGmail(rs.getString("gmail"));
                gerente.setContrasena(rs.getString("contrasena"));
                gerente.setCodigo(rs.getString("codigo"));
                gerente.setMisHabitaciones(HabitacionDAO.findByIdGerente(idGerente));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gerentes;
    }

    public static Gerente findById(int idGerente) {
        Gerente gerente = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idGerente);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                gerente = new Gerente();
                gerente.setIdGerente(rs.getInt("idGerente"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gerente;
    }

    public static void updateGerente(Gerente gerente) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            pst.setString(1, gerente.getNombre());
            pst.setString(2, gerente.getGmail());
            pst.setString(3, gerente.getContrasena());
            pst.setString(4, gerente.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteGerente(int idGerente) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idGerente);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existeGmailGerente(String gmail) {
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

    public static boolean existeDNIGerente(String dni) {
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

    public static boolean existeCodigoGerente(String codigo) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CODIGO_EQUALS)) {
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar el código: " + e.getMessage(), e);
        }
        return false;
    }
}

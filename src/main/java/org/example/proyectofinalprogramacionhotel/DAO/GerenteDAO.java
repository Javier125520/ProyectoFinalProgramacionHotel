package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Gerente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenteDAO {
    private final static String SQL_INSERT = "INSERT INTO gerente (nombre, email, contrasena, codigo) VALUES (?, ?, ?, ?)";
    private final static String SQL_ALL = "SELECT * FROM gerente";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM gerente WHERE idGerente = ?";
    private final static String SQL_UPDATE = "UPDATE gerente SET nombre = ?, gmail = ?, contrasena = ?, codigo = ? WHERE idGerente = ?";
    private final static String SQL_DELETE = "DELETE FROM gerente WHERE idGerente = ?";
    private final static String SQL_FIND_BY_GMAIL_EQUALS = "SELECT COUNT(*) FROM gerente WHERE gmail = ?";
    private final static String SQL_FIND_BY_CODIGO_EQUALS = "SELECT COUNT(*) FROM gerente WHERE codigo = ?";


    /**
     * Metodo que inserta un gerente en la base de datos.
     *
     * @param gerente El gerente que vas a insertar.
     * @return El gerente insertado.
     */
    public static Gerente insertGerente(Gerente gerente) {
        if (gerente != null && findById(gerente.getIdGerente()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setString(1, gerente.getNombre());
                pst.setString(2, gerente.getEmail());
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

    /**
     * Version Lazy de obtener todos los gerentes, esto quiere decir que me traigo de la base de datos los gerentes
     * pero sin cargar las habitaciones de las que se encarga cada gerente.
     * @return Una lista con todos los gerentes sin las habitaciones que gestiona.
     */
    public static List<Gerente> findAll() {
        List<Gerente> gerentes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Gerente gerente = new Gerente();
                gerente.setIdGerente(rs.getInt("idGerente"));
                gerente.setNombre(rs.getString("nombre"));
                gerente.setEmail(rs.getString("email"));
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

    /**
     * Version Eager de obtener todos los gerentes, esto quiere decir que me traigo de la base de datos los gerentes
     * con las habitaciones de las que se encarga cada gerente.
     * @return Una lista con todos los gerentes con las habitaciones que gestiona.
     */
    public static List<Gerente> findAllEager() {
        List<Gerente> gerentes = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Gerente gerente = new Gerente();
                int idGerente = rs.getInt("idGerente");
                gerente.setIdGerente(idGerente);
                gerente.setNombre(rs.getString("nombre"));
                gerente.setEmail(rs.getString("email"));
                gerente.setContrasena(rs.getString("contrasena"));
                gerente.setCodigo(rs.getString("codigo"));
                gerente.setMisHabitaciones(HabitacionDAO.findByIdGerente(idGerente));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gerentes;
    }


    /**
     * Metodo que busca un gerente por su id.
     * @param idGerente El id del gerente que quieres buscar.
     * @return El gerente encontrado.
     */
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

    /**
     * Metodo que actualiza un gerente en la base de datos.
     * @param gerente El gerente que vas a actualizar.
     */
    public static void updateGerente(Gerente gerente) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            pst.setString(1, gerente.getNombre());
            pst.setString(2, gerente.getEmail());
            pst.setString(3, gerente.getContrasena());
            pst.setString(4, gerente.getCodigo());
            pst.setInt(5, gerente.getIdGerente());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que elimina un gerente por su id.
     * @param idGerente El id del gerente que quieres eliminar.
     */
    public static void deleteGerente(int idGerente) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idGerente);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que busca un gerente por su gmail.
     * @param gmail El gmail del gerente que quieres buscar.
     * @return El gerente encontrado.
     */
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

    /**
     * Metodo que busca un gerente por su codigo.
     * @param codigo El codigo del gerente que quieres buscar.
     * @return El gerente encontrado.
     */
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

package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private final static String SQL_INSERT = "INSERT INTO servicio (precioHora, tipoServicio) VALUES (?, ?)";
    private final static String SQL_FIND_ALL = "SELECT * FROM servicio";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM servicio WHERE idServicio = ?";
    private final static String SQL_UPDATE = "UPDATE servicio SET precioHora = ?, tipoServicio = ? WHERE idServicio = ?";
    private final static String SQL_DELETE = "DELETE FROM servicio WHERE idServicio = ?";
    private final static String SQL_FIND_BY_TIPO_SERVICIO = "SELECT COUNT(*) FROM servicio WHERE tipoServicio = ?";
    /**
     * Metodo que inserta un servicio en la base de datos.
     * @param servicio El servicio que vas a insertar.
     * @return El servicio insertado.
     */
    public static Servicio insertServicio(Servicio servicio) {
        if (servicio != null || findById(servicio.getIdServicio()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setDouble(1, servicio.getPrecioHora());
                pst.setString(2, servicio.getTipoServicio());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return servicio;
    }

    /**
     * Metodo que busca un servicio por su id.
     * @param idServicio El id del servicio que quieres buscar.
     * @return El servicio encontrado.
     */
    public static Servicio findById(int idServicio) {
        Servicio servicio = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idServicio);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("idServicio"));
                servicio.setPrecioHora(rs.getDouble("precioHora"));
                servicio.setTipoServicio(rs.getString("tipoServicio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return servicio;
    }

    /**
     * Metodo que busca todos los servicios de la base de datos.
     * @return Una lista con todos los servicios.
     */
    public static List<Servicio> findAll() {
        List<Servicio> servicios = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("idServicio"));
                servicio.setPrecioHora(rs.getDouble("precioHora"));
                servicio.setTipoServicio(rs.getString("tipoServicio"));
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return servicios;
    }


    /**
     * Metodo que actualiza un servicio en la base de datos.
     * @param servicio El servicio que quieres actualizar.
     */
    public static void updateServicio(Servicio servicio) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            pst.setDouble(1, servicio.getPrecioHora());
            pst.setString(2, servicio.getTipoServicio());
            pst.setInt(3, servicio.getIdServicio());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que elimina un servicio de la base de datos.
     * @param idServicio El id del servicio que quieres eliminar.
     */
    public static void deleteServicio(int idServicio) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idServicio);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que verifica si ya existe un servicio con el mismo tipo de servicio.
     * @param tipoServicio El tipo de servicio que quieres verificar.
     * @return true si existe un servicio con el mismo tipo, false en caso contrario.
     */
    public static boolean existeTipoServicio(String tipoServicio) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO_SERVICIO)) {
            pst.setString(1, tipoServicio);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar el tipo de servicio: " + e.getMessage(), e);
        }
        return false;
    }
}

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

    public static void deleteServicio(int idServicio) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idServicio);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservaServicioDAO {
    private final static String SQL_INSERT = "INSERT INTO reserva_servicio (fechaReserva, numeroPersonas, precio, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM reserva_servicio WHERE idReserva = ?";
    private final static String SQL_DELETE = "DELETE FROM reserva_servicio WHERE idReserva = ?";

    public static void insertReservaServicio(ReservaServicio reserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            pst.setDate(1, Date.valueOf(reserva.getFechaReserva()));
            pst.setInt(2, reserva.getNumeroPersonas());
            pst.setInt(3, reserva.getPrecio());
            pst.setDate(4, Date.valueOf(reserva.getFechaInicio()));
            pst.setDate(5, Date.valueOf(reserva.getFechaFin()));
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Servicio> findById(int idReserva) {
        List<Servicio> servicios = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idReserva);
            ResultSet rs = pst.executeQuery();
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

    public static void deleteReservaServicio(int idReserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idReserva);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.ReservaServicio;
import org.example.proyectofinalprogramacionhotel.model.Servicio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaServicioDAO {
    private final static String SQL_INSERT = "INSERT INTO reserva_servicio (idReserva, idServicio, fechaReserva, numeroPersonas, precio, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_FIND_SERVICIOS_BY_ID_RESERVA = "SELECT * FROM reserva_servicio WHERE idReserva = ?";
    private final static String SQL_DELETE = "DELETE FROM reserva_servicio WHERE idReserva = ?";

    public static void insertReservaServicio(ReservaServicio reserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            pst.setInt(1, reserva.getIdReserva());
            pst.setInt(2, reserva.getServicio().getIdServicio());
            pst.setDate(3, Date.valueOf(reserva.getFechaReserva()));
            pst.setInt(4, reserva.getNumeroPersonas());
            pst.setInt(5, reserva.getPrecio());
            pst.setDate(6, Date.valueOf(reserva.getFechaInicio()));
            pst.setDate(7, Date.valueOf(reserva.getFechaFin()));
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ReservaServicio> findByIdReserva(int idReserva) {
        List<ReservaServicio> reservasServicios = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_SERVICIOS_BY_ID_RESERVA)) {
            pst.setInt(1, idReserva);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ReservaServicio reservaServicio = new ReservaServicio();
                reservaServicio.setIdReserva(rs.getInt("idReserva"));
                reservaServicio.setFechaReserva(rs.getDate("fechaReserva").toLocalDate());
                reservaServicio.setNumeroPersonas(rs.getInt("numeroPersonas"));
                reservaServicio.setPrecio(rs.getInt("precio"));
                reservaServicio.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                reservaServicio.setFechaFin(rs.getDate("fechaFin").toLocalDate());

                // Buscar el servicio por idServicio
                int idServicio = rs.getInt("idServicio");
                Servicio servicio = ServicioDAO.findById(idServicio);
                reservaServicio.setServicio(servicio);

                reservasServicios.add(reservaServicio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservasServicios;
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

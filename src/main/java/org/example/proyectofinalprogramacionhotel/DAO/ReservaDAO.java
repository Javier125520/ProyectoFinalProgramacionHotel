package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Reserva;
import org.example.proyectofinalprogramacionhotel.model.estadoReserva;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private final static String SQL_INSERT = "INSERT INTO reserva (fechaEntrada, fechaSalida, estado, numeroPersonas, IdCliente) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_DELETE= "DELETE FROM reserva WHERE idReserva = ?";
    private final static String SQL_UPDATE = "UPDATE reserva SET fechaEntrada = ?, fechaSalida = ?, estado = ?, numeroPersonas = ?";
    private final static String SQL_ALL = "SELECT * FROM reserva";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM reserva WHERE idReserva = ?";
    private final static String SQL_FIND_BY_ID_CLIENTE = "SELECT * FROM reserva WHERE idCliente = ?";


    public static Reserva insertReserva(Reserva reserva) {
        if (reserva != null && findById(reserva.getIdReserva()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pst.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pst.setString(3, reserva.getEstadoReserva().name());
                pst.setInt(4, reserva.getNumPersonas());
                pst.setInt(5, reserva.getIdCliente());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            reserva = null;
        }
        return reserva;
    }

    public static Reserva findById(int idReserva) {
        Reserva reserva = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idReserva);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reserva;
    }

    public static List<Reserva> findByIdCliente(int idCliente) {
        List<Reserva> reservas = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID_CLIENTE)) {
            pst.setInt(1, idCliente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estado")));
                reserva.setNumPersonas(rs.getInt("numeroPersonas"));
                reserva.setIdCliente(idCliente);
                reserva.setServiciosIncluidos(ReservaServicioDAO.findById(reserva.getIdReserva()));
                reserva.setHabitacionesContratadas(HabitacionDAO.findByIdReserva(reserva.getIdReserva()));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public static List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estado")));
                reserva.setNumPersonas(rs.getInt("numeroPersonas"));
                reserva.setServiciosIncluidos(new ArrayList<>());
                reserva.setHabitacionesContratadas(new ArrayList<>());
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public static List<Reserva> findAllEager() {
        List<Reserva> reservas = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva();
                int idReserva = rs.getInt("idReserva");
                reserva.setIdReserva(idReserva);
                reserva.setFechaEntrada(rs.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estado")));
                reserva.setNumPersonas(rs.getInt("numeroPersonas"));
                reserva.setServiciosIncluidos(ReservaServicioDAO.findById(idReserva));
                reserva.setHabitacionesContratadas(HabitacionDAO.findByIdReserva(idReserva));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public static void updateReserva(Reserva reserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            pst.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
            pst.setDate(2, Date.valueOf(reserva.getFechaSalida()));
            pst.setString(3, reserva.getEstadoReserva().name());
            pst.setInt(4, reserva.getNumPersonas());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteReserva(int idReserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idReserva);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

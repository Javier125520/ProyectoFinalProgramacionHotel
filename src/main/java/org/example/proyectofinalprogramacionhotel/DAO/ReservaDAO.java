package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private final static String SQL_INSERT = "INSERT INTO reserva (fechaEntrada, fechaSalida, estadoReserva, numeroPersonas, IdCliente) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_DELETE= "DELETE FROM reserva WHERE idReserva = ?";
    private final static String SQL_UPDATE = "UPDATE reserva SET fechaEntrada = ?, fechaSalida = ?, estadoReserva = ?, numeroPersonas = ? WHERE idReserva = ?";;
    private final static String SQL_ALL = "SELECT * FROM reserva";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM reserva WHERE idReserva = ?";
    private final static String SQL_FIND_BY_ID_CLIENTE = "SELECT * FROM reserva WHERE idCliente = ?";

    /**
     * Metodo que inserta una reserva en la base de datos.
     * @param reserva La reserva que vas a insertar.
     * @return La reserva insertada.
     */
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

    /**
     * Metodo que busca una reserva por su id.
     * @param idReserva El id de la reserva que quieres buscar.
     * @return La reserva encontrada.
     */
    public static Reserva findById(int idReserva) {
        Reserva reserva = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idReserva);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estadoReserva")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reserva;
    }

    /**
     * Metodo que busca una reserva por su idCliente.
     * @param idCliente El id del cliente que quieres buscar.
     * @return La reserva encontrada.
     */
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
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estadoReserva")));
                reserva.setNumPersonas(rs.getInt("numeroPersonas"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    /**
     * Version Lazy de obtener todos las reservas, esto quiere decir que me traigo de la base de datos las reservas
     * pero sin cargar las reservas de los servicios y habitaciones.
     * @return Una lista con todas las reservas.
     */
    public static List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estadoReserva")));
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

    /**
     * Version Eager de obtener todos las reservas, esto quiere descir que me traigo de la base de datos las reservas
     * con sus habitaciones y sus servicios reservados.
     * @return Una lista con todas las reservas.
     */
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
                reserva.setEstadoReserva(estadoReserva.valueOf(rs.getString("estadoReserva")));
                reserva.setNumPersonas(rs.getInt("numeroPersonas"));
                reserva.setServiciosIncluidos(ReservaServicioDAO.findByIdReserva(idReserva));
                reserva.setHabitacionesContratadas(HabitacionDAO.findByIdReserva(reserva.getIdReserva()));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    /**
     * Metodo que busca todas las reservas de la base de datos.
     * @return Una lista con todas las reservas.
     */
    public static void updateReserva(Reserva reserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            pst.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
            pst.setDate(2, Date.valueOf(reserva.getFechaSalida()));
            pst.setString(3, reserva.getEstadoReserva().name());
            pst.setInt(4, reserva.getNumPersonas());
            pst.setInt(5, reserva.getIdReserva());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que elimina una reserva por su id.
     * @param idReserva El id de la reserva que quieres eliminar.
     */
    public static void deleteReserva(int idReserva) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idReserva);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

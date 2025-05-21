package org.example.proyectofinalprogramacionhotel.DAO;

import org.example.proyectofinalprogramacionhotel.baseDatos.ConnectionBD;
import org.example.proyectofinalprogramacionhotel.model.Habitacion;
import org.example.proyectofinalprogramacionhotel.model.estadoHabitacion;
import org.example.proyectofinalprogramacionhotel.model.tipoHabitacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private final static String SQL_INSERT = "INSERT INTO habitacion (numeroHabitacion, tipoHabitacion, precioNoche, estadoHabitacion, idGerente, idReserva) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_FIND_ALL = "SELECT * FROM habitacion";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM habitacion WHERE idHabitacion = ?";
    private final static String SQL_UPDATE = "UPDATE habitacion SET numeroHabitacion = ?, tipoHabitacion = ?, precioNoche = ?, estadoHabitacion = ?, idReserva = ? WHERE idHabitacion = ?";
    private final static String SQL_DELETE = "DELETE FROM habitacion WHERE idHabitacion = ?";
    private final static String SQL_FIND_BY_ID_GERENTE = "SELECT * FROM habitacion WHERE idGerente = ?";
    private final static String SQL_FIND_BY_ID_RESERVA = "SELECT * FROM habitacion WHERE idReserva = ?";
    private final static String SQL_FIND_HABITACIONES_DISPONIBLES = "SELECT * FROM habitacion WHERE estadoHabitacion = ?";

    /**
     * Metodo que inserta una habitacion en la base de datos.
     * @param habitacion La habitacion que vas a insertar.
     * @return La habitacion insertada.
     */
    public static Habitacion insertHabitacion(Habitacion habitacion) {
        if (habitacion != null && findById(habitacion.getIdHabitacion()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                pst.setInt(1, habitacion.getNumeroHabitacion());
                pst.setString(2, habitacion.getTipoHabitacion().name());
                pst.setDouble(3, habitacion.getPrecioNoche());
                pst.setString(4, habitacion.getEstadoHabitacion().name());
                pst.setInt(5, habitacion.getIdGerente());
                pst.setObject(6, habitacion.getIdReserva());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            habitacion = null;
        }
        return habitacion;
    }

    /**
     * Metodo que busca todas las habitaciones de la base de datos.
     * @return Una lista con todas las habitaciones.
     */
    public static List<Habitacion> findAll() {
        List<Habitacion> habitaciones = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                habitacion.setTipoHabitacion(tipoHabitacion.valueOf(rs.getString("tipoHabitacion")));
                habitacion.setPrecioNoche(rs.getDouble("precioNoche"));
                habitacion.setEstadoHabitacion(estadoHabitacion.valueOf(rs.getString("estadoHabitacion")));
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitaciones;
    }

    /**
     * Metodo que busca una habitacion por su id.
     * @param idHabitacion El id de la habitacion que quieres buscar.
     * @return La habitacion encontrada.
     */
    public static Habitacion findById(int idHabitacion) {
        Habitacion habitacion = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            pst.setInt(1, idHabitacion);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                habitacion.setTipoHabitacion(tipoHabitacion.valueOf(rs.getString("tipoHabitacion")));
                habitacion.setPrecioNoche(rs.getDouble("precioNoche"));
                habitacion.setEstadoHabitacion(estadoHabitacion.valueOf(rs.getString("estadoHabitacion")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacion;
    }

    /**
     * Metodo que busca una habitacion por su idReserva.
     * @param idReserva El id de la reserva que quieres buscar.
     * @return La habitacion encontrada.
     */
    public static List<Habitacion> findByIdReserva(int idReserva) {
        List<Habitacion> habitaciones = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID_RESERVA)) {
            pst.setInt(1, idReserva);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                habitacion.setTipoHabitacion(tipoHabitacion.valueOf(rs.getString("tipoHabitacion")));
                habitacion.setPrecioNoche(rs.getDouble("precioNoche"));
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitaciones;
    }

    /**
     * Metodo que actualiza una habitacion en la base de datos.
     * @param habitacion La habitacion que vas a actualizar.
     */
    public static void updateHabitacion(Habitacion habitacion) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            pst.setInt(1, habitacion.getNumeroHabitacion());
            pst.setString(2, habitacion.getTipoHabitacion().name());
            pst.setDouble(3, habitacion.getPrecioNoche());
            pst.setString(4, habitacion.getEstadoHabitacion().name());
            if (habitacion.getIdReserva() == null) {
                pst.setNull(5, java.sql.Types.INTEGER); // Manejar valores nulos
            } else {
                pst.setInt(5, habitacion.getIdReserva());
            }
            pst.setInt(6, habitacion.getIdHabitacion());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la habitación: " + e.getMessage(), e);
        }
    }

    /**
     * Metodo que elimina una habitacion por su id.
     * @param idHabitacion El id de la habitacion que quieres eliminar.
     */
    public static void deleteHabitacion(int idHabitacion) {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            pst.setInt(1, idHabitacion);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que busca todas las habitaciones de un gerente por su id.
     * @param idGerente El id del gerente que quieres buscar.
     * @return Una lista con todas las habitaciones del gerente.
     */
    public static List<Habitacion> findByIdGerente(int idGerente) {
        List<Habitacion> habitaciones = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID_GERENTE)) {
            pst.setInt(1, idGerente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                habitacion.setTipoHabitacion(tipoHabitacion.valueOf(rs.getString("tipoHabitacion")));
                habitacion.setPrecioNoche(rs.getDouble("precioNoche"));
                habitacion.setEstadoHabitacion(estadoHabitacion.valueOf(rs.getString("estadoHabitacion")));
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitaciones;
    }

    /**
     * Metodo que busca todas las habitaciones disponibles.
     * @return Una lista con todas las habitaciones disponibles.
     */
    public static List<Habitacion> findHabitacionesDisponibles() {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(SQL_FIND_HABITACIONES_DISPONIBLES)) {
            pst.setString(1, estadoHabitacion.Libre.name());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                habitacion.setTipoHabitacion(tipoHabitacion.valueOf(rs.getString("tipoHabitacion")));
                habitacion.setPrecioNoche(rs.getDouble("precioNoche"));
                habitacion.setEstadoHabitacion(estadoHabitacion.valueOf(rs.getString("estadoHabitacion")));
                habitacionesDisponibles.add(habitacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionesDisponibles;
    }

}

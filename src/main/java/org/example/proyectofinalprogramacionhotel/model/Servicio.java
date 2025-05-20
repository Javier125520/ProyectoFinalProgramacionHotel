package org.example.proyectofinalprogramacionhotel.model;

public class Servicio {
    // Atributos
    private int idServicio;
    private double precioHora;
    private String tipoServicio;

    // Constructor
    public Servicio(int idServicio, double precioHora, String tipoServicio) {
        this.idServicio = idServicio;
        this.precioHora = precioHora;
        this.tipoServicio = tipoServicio;
    }

    // Constructor sin parámetros
    public Servicio() {
    }

    // Getters y Setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", precioHora=" + precioHora +
                ", tipoServicio='" + tipoServicio + '\'' +
                '}';
    }
}
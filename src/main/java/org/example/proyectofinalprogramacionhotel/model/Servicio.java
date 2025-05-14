package org.example.proyectofinalprogramacionhotel.model;

public class Servicio {
    private int idServicio;
    private double precioHora;
    private int numPersonas;
    private String tipoServicio;

    public Servicio(int idServicio, double precioHora, int numPersonas, String tipoServicio) {
        this.idServicio = idServicio;
        this.precioHora = precioHora;
        this.numPersonas = numPersonas;
        this.tipoServicio = tipoServicio;
    }

    public Servicio() {
    }

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

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
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
                ", precio=" + precioHora +
                ", numPersonas=" + numPersonas +
                ", tipoServicio='" + tipoServicio + '\'' +
                '}';
    }
}
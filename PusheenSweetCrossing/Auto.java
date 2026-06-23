package edu.upb.lp.game.PusheenSweetCrossing;

public class Auto {
    private String imagen;
    private String direccion; // "D" para Derecha, "I" para Izquierda

    public Auto(String imagen, String direccion) {
        this.imagen = imagen;
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDireccion() {
        return direccion;
    }
}
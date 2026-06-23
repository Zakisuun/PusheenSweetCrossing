package edu.upb.lp.game.PusheenSweetCrossing;

public class Auto {
    private String imagen;
    private int direccion; 

    public Auto(String imagen, int direccion) {
        this.imagen = imagen;
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public int getDireccion() {
        return direccion;
    }
}
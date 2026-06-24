package edu.upb.lp.game.PusheenSweetCrossing;

public class Auto {
    private String imagen;
    private int direccion; 
    private int filas;
    private int columnas;

    public Auto(String imagen, int direccion, int filas, int columnas) {
        this.imagen = imagen;
        this.direccion = direccion;
        this.filas = filas;
        this.columnas = columnas;
    }

    public void mover() {
        if (this.direccion == 0) {
            this.columnas = this.columnas + 1;
        } else {
            this.columnas = this.columnas - 1;
        }
    }

    public void bajarFila() {
        this.filas = this.filas + 1;
    }

    public String getImagen() {
        return this.imagen;
    }

    public int getDireccion() {
        return this.direccion;
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }
}
package edu.upb.lp.game.PusheenSweetCrossing;

public class Auto {

    private int fila;
    private int columna;
    private String direccion;

    public Auto(int fila, int columna, String direccion) {
        this.fila = fila;
        this.columna = columna;
        this.direccion = direccion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
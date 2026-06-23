package edu.upb.lp.game.PusheenSweetCrossing;

public class Auto {
    private PusheenGame game;
    private String imagen;
    private int direccion; // 0 para Derecha, 1 para Izquierda
    private int fila;
    private int columna;

    public Auto(PusheenGame game, String imagen, int direccion, int fila, int columna) {
        this.game = game;
        this.imagen = imagen;
        this.direccion = direccion;
        this.fila = fila;
        this.columna = columna;
    }

    public void mover() {
        int columnaVieja = this.columna;
        
        if (direccion == 0) {
            this.columna++; 
        } else {
            this.columna--; 
        }

        if (this.columna < 0 || this.columna >= 15) {
            game.eliminarObjetoMatriz(this.fila, columnaVieja);
        } 
        else {
            game.actualizarPosicionAuto(this.fila, columnaVieja, this.columna, this);
        }
    }

    public String getImagen() {
        return imagen;
    }

    public int getDireccion() {
        return direccion;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int nuevaFila) {
        this.fila = nuevaFila;
    }
}
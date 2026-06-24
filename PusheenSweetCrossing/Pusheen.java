package edu.upb.lp.game.PusheenSweetCrossing;

public class Pusheen {
    private PusheenGame game;
    private int direccion = 0;
    private int filas = 3;
    private int columnas = 7;

    public Pusheen(PusheenGame game) {
        this.game = game;
    }

    public void moverArriba() {
    }

    public void moverAbajo() {
    }

    public void moverDerecha() {
        this.game.borrarPusheen(filas, columnas);
        if (columnas + 1 < 15) {
            columnas++;
            direccion = 1;
        }
        this.game.dibujarPusheen(filas, columnas, direccion);
    }

    public void moverIzquierda() {
        this.game.borrarPusheen(filas, columnas);
        if (columnas - 1 >= 0) {
            columnas--;
            direccion = 3;
        }
        this.game.dibujarPusheen(filas, columnas, direccion);
    }

    public void reiniciarPosicion() {
        this.game.borrarPusheen(filas, columnas);
        filas = 3;
        columnas = 7;
        direccion = 0;
        this.game.dibujarPusheen(filas, columnas, direccion);
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public int getDireccion() {
        return this.direccion;
    }
}
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
        this.game.borrarPusheen(this.filas, this.columnas);
        if (this.columnas + 1 < 15) {
            this.columnas++;
            this.direccion = 1;
        }
        this.game.dibujarPusheen(this.filas, this.columnas, this.direccion);
    }

    public void moverIzquierda() {
        this.game.borrarPusheen(this.filas, this.columnas);
        if (this.columnas - 1 >= 0) {
            this.columnas--;
            this.direccion = 3;
        }
        this.game.dibujarPusheen(this.filas, this.columnas, this.direccion);
    }

    public void reiniciarPosicion() {
        this.filas = 3;
        this.columnas = 7;
        this.direccion = 0;
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
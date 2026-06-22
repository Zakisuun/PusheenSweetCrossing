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
//	    game.borrarPusheen(filas, columnas);
//	    if (filas - 1 >= 0) {
//	        filas--;
//	        direccion = 0; 
//	    }
//	    game.dibujarPusheen(filas, columnas, direccion);
	}

	public void moverAbajo() {
//	    game.borrarPusheen(filas, columnas);
//	    if (filas + 1 < 7) {
//	        filas++;
//	        direccion = 2; 
//	    }
//	    game.dibujarPusheen(filas, columnas, direccion);
	}

	public void moverDerecha() {
	    game.borrarPusheen(filas, columnas);
	    if (columnas + 1 < 15) {
	        columnas++;
	        direccion = 1; 
	    }
	    game.dibujarPusheen(filas, columnas, direccion);
	}

	public void moverIzquierda() {
	    game.borrarPusheen(filas, columnas);
	    if (columnas - 1 >= 0) {
	        columnas--;
	        direccion = 3;
	    }
	    game.dibujarPusheen(filas, columnas, direccion);
	}
	public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getDireccion() {
        return direccion;
    }
}
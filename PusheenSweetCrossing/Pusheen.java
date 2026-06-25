package edu.upb.lp.game.PusheenSweetCrossing;

public class Pusheen {
	private PusheenGame game;
	private int direccion = 0; // 0= arriba,1= derecha, 2= abajo, 3= izquierda
	private int filas = 3;
	private int columnas = 7;
	private int vidas = 3;

	public Pusheen(PusheenGame game) {
		this.game = game;
	}

	public void moverArriba() {
	}

	public void moverAbajo() {

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

	public void reiniciarPosicion() {
		game.borrarPusheen(filas, columnas);
		filas = 3;
		columnas = 7;
		direccion = 0;
		game.dibujarPusheen(filas, columnas, direccion);

	}
	
	public void perderVida() {
		vidas--;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void setVidas() {
		this.vidas=3;
	}
}



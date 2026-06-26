package edu.upb.lp.game.PusheenSweetCrossing;

import edu.upb.lp.game.core.GameController;
import edu.upb.lp.game.core.GameUI;

public class PusheenSweetCrossingController implements GameController {
	private GameUI libreria;
	private boolean gameEnded = false;

	private PusheenGame game = new PusheenGame(this);

	public PusheenSweetCrossingController(GameUI libreria) {
		this.libreria = libreria;
	}

	public void onCellPressed(int row, int col) {

	}

	public void initialiseInterface() {
		libreria.configureGrid(7, 15, 1200, 600, false);
		libreria.setCellObjectImage(3, 7, "PusheenAdelante");
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 15; c++) {
				String nombreImagen = "PastoB_r" + (r + 1) + "_c" + (c + 1);
				libreria.setCellBackgroundImage(r, c, nombreImagen);
			}
		}
		libreria.setLabel("pusheensweetcrossing", "Pusheen Sweet Crossing");
		libreria.bindKey("W", "MoverArriba");
		libreria.bindKey("S", "MoverAbajo");
		libreria.bindKey("A", "MoverIzquierda");
		libreria.bindKey("D", "MoverDerecha");
		
		libreria.addButton("Reiniciar");
		

		actualizarTodoElMapa();
		dibujarVidas();

		game.start();
	}

	public void onButtonPressed(String name) {
		if (name.equals("Reiniciar")) {
			gameEnded = false;
			game.reiniciarJuego();
			initialiseInterface();
			actualizarTodoElMapa();
		}
	}

	public void onCommand(String command) {
		if (!gameEnded) {
			if (command.equals("MoverArriba")) {
				game.moverArriba();
			} else if (command.equals("MoverAbajo")) {
				game.moverAbajo();
			} else if (command.equals("MoverIzquierda")) {
				game.moverIzquierda();
			} else if (command.equals("MoverDerecha")) {
				game.moverDerecha();
			}
		}
	}

	public void borrarPusheen(int filas, int columnas) {
		String objeto = game.getImagenObjeto(filas, columnas);
		if (objeto != null) {
			libreria.setCellObjectImage(filas, columnas, objeto);
		} else {
			this.libreria.clearCellObjectImage(filas, columnas);
		}
	}

	public void dibujarPusheen(int filas, int columnas, int direccion) {
		String Pusheenimg = "PusheenAdelante";
		if (direccion == 0) {
			Pusheenimg = "PusheenDetras";
		} else if (direccion == 1) {
			Pusheenimg = "PusheenDerecha";
		} else if (direccion == 2) {
			Pusheenimg = "PusheenAdelante";
		} else if (direccion == 3) {
			Pusheenimg = "PusheenIzquierda";
		}
		libreria.setCellObjectImage(filas, columnas, Pusheenimg);
	}

	public void actualizarTodoElMapa() {
		dibujarVidas();
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 15; c++) {
				libreria.setCellBackgroundImage(r, c, game.getImagenFondo(r, c));
				libreria.clearCellObjectImage(r, c);

				String objeto = game.getImagenObjeto(r, c);
				if (objeto != null) {
					libreria.setCellObjectImage(r, c, objeto);
				}
			}
		}
		Pusheen gato = game.getPusheen();
		dibujarPusheen(gato.getFilas(), gato.getColumnas(), gato.getDireccion());
	}

	public void perdiste() {
		gameEnded = true;
		libreria.configureGrid(7, 15, 1200, 600, false);
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 15; c++) {
				String nombreImagen = "GameOver_r" + (r + 1) + "_c" + (c + 1);
				libreria.setCellBackgroundImage(r, c, nombreImagen);
			}
		}
		game.stop();
	}

	public void dibujarVidas() {
		libreria.setLabel("vidas", "Vidas: " + game.getVidas());
	}

	public String executeRepeatedly(Runnable r, int ms) {
		return libreria.executeRepeatedly(r, ms);
	}

	public void stopLoop(String id) {
		libreria.stopLoop(id);
	}

}

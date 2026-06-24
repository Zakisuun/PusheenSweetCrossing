package edu.upb.lp.game.PusheenSweetCrossing;

import edu.upb.lp.game.core.GameController;
import edu.upb.lp.game.core.GameUI;

public class PusheenSweetCrossingController implements GameController {
    private GameUI libreria;
    private PusheenGame game;

    public PusheenSweetCrossingController(GameUI libreria) {
        this.libreria = libreria;
        this.game = new PusheenGame(this);
    }

    @Override
    public void onButtonPressed(String name) {
    }

    @Override
    public void onCellPressed(int row, int col) {
    }

    public void initialiseInterface() {
        this.libreria.configureGrid(7, 15, 1200, 600, false);
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 15; c++) {
                String nombreImagen = "PastoB_r" + (r + 1) + "_c" + (c + 1);
                this.libreria.setCellBackgroundImage(r, c, nombreImagen);
            }
        }
        this.libreria.bindKey("W", "MoverArriba");
        this.libreria.bindKey("S", "MoverAbajo");
        this.libreria.bindKey("A", "MoverIzquierda");
        this.libreria.bindKey("D", "MoverDerecha");
        this.libreria.bindKey("SPACE", "MoverCarros");
        actualizarTodoElMapa();
    }

    @Override
    public void onCommand(String command) {
        if (command.equals("MoverArriba")) {
            this.game.moverArriba();
        } else if (command.equals("MoverAbajo")) {
            this.game.moverAbajo();
        } else if (command.equals("MoverIzquierda")) {
            this.game.moverIzquierda();
        } else if (command.equals("MoverDerecha")) {
            this.game.moverDerecha();
        } else if (command.equals("MoverCarros")) {
            this.game.moverCarrosManualmente();
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

    public void actualizarTodoElMapa() {
        // Fondos (pasto o carretera) y objetos fondo
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
        // Pusheen fuera del bucle
        Pusheen gato = game.getPusheen();
        dibujarPusheen(gato.getFilas(), gato.getColumnas(), gato.getDireccion());
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
        this.libreria.setCellObjectImage(filas, columnas, Pusheenimg);
    }
    
    public void perdiste() {
    	libreria.configureGrid(1, 1, 1200, 600, false);
    	libreria.setCellBackgroundImage(0, 0, "bugs_sad_bug");
    }
}
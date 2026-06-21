package edu.upb.lp.game.PusheenSweetCrossing;

import edu.upb.lp.game.core.GameController;
import edu.upb.lp.game.core.GameUI;

public class PusheenSweetCrossingController implements GameController {
    private GameUI libreria;
    private PusheenGame game = new PusheenGame(this);
 
    public PusheenSweetCrossingController(GameUI libreria) {
        this.libreria = libreria;
    }

    @Override
    public void onButtonPressed(String name) {}

    @Override
    public void onCellPressed(int row, int col) {}

    @Override
    public void initialiseInterface() {
        libreria.configureGrid(7, 15, 1200, 600, false);
        
        libreria.bindKey("W", "MoverArriba");
        libreria.bindKey("S", "MoverAbajo");
        libreria.bindKey("A", "MoverIzquierda");
        libreria.bindKey("D", "MoverDerecha");

        // Dibujamos el pasto de fondo
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 15; c++) {
                int numeroFila = (r % 4) + 1; 
                int numeroColumna = c + 1;
                String nombreImagen = "PastoB_r" + numeroFila + "_c" + numeroColumna;
                libreria.setCellBackgroundImage(r, c, nombreImagen);
            }
        }
        
        // Pusheen aparece al inicio en la fila 3, columna 7
        libreria.setCellObjectImage(3, 7, "PusheenAdelante");
    }

    @Override
    public void onCommand(String command) {
        if (command.equals("MoverArriba")) {
            game.moverArriba();
        } else if (command.equals("MoverAbajo")) {
            game.moverAbajo();
        } else if (command.equals("MoverIzquierda")) {
            game.moverIzquierda();
        } else if (command.equals("MoverDerecha")){
            game.moverDerecha();
        }
    }

    public void borrarPusheen(int filas, int columnas) {
        libreria.clearCellObjectImage(filas, columnas);
    }
     
    public void dibujarPusheen(int filas, int columnas, int direccion) {
        libreria.setCellObjectImage(filas, columnas, "PusheenAdelante");
    }
}
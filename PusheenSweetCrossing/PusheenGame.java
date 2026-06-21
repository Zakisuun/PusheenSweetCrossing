package edu.upb.lp.game.PusheenSweetCrossing;

public class PusheenGame {
  private PusheenSweetCrossingController controller;
  private Pusheen pusheen = new Pusheen(this);

  public PusheenGame(PusheenSweetCrossingController controller) {
   this.controller = controller;
  }

  public void moverArriba() {
   pusheen.moverArriba();

  }
  
  public void moverAbajo() {
   pusheen.moverAbajo();
  }
  
  public void moverDerecha() {
   pusheen.moverDerecha();
  }
  
  public void moverIzquierda() {
   pusheen.moverIzquierda();
  }

  public void borrarPusheen(int filas, int columnas) {
   controller.borrarPusheen(filas, columnas);
  }

  public void dibujarPusheen(int filas, int columnas, int direccion) {
   controller.dibujarPusheen(filas, columnas, direccion);
  }
  
  

 }

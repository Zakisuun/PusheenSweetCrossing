package edu.upb.lp.game.PusheenSweetCrossing;
import java.util.Random;

public class PusheenGame {
  private PusheenSweetCrossingController controller;
  private Pusheen pusheen = new Pusheen(this);
  private Random random = new Random();
  private String[][] mapaFondos = new String[7][15];

  public PusheenGame(PusheenSweetCrossingController controller) {
   this.controller = controller;
   this.pusheen = new Pusheen(this);
 
   inicializarMapaInicial();
  }

  public void moverArriba() {
//   pusheen.moverArriba();

  }
  
  public void moverAbajo() {
//   pusheen.moverAbajo();
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
  
  private void inicializarMapaInicial() {
      for (int r = 0; r < 7; r++) {
          for (int c = 0; c < 15; c++) {
              int numeroFila = (r % 4) + 1;
              int numeroColumna = c + 1;
              mapaFondos[r][c] = "PastoB_r" + numeroFila + "_c" + numeroColumna;
          }
      }
  }
  
  public String getImagenFondo(int f, int c) {
      return mapaFondos[f][c];
  }

  public Pusheen getPusheen() {
      return pusheen;
  }
  
  public void avanzarMundo (){
	      for (int r = 6; r > 0; r--) {
	          for (int c = 0; c < 15; c++) {
	              mapaFondos[r][c] = mapaFondos[r-1][c];
	          }
	      }

	     
	      boolean esPasto = random.nextBoolean(); // Elige true o false
	      
	      for (int c = 0; c < 15; c++) {
	          if (esPasto) {
	              // Si toca pasto, generamos un número del 1 al 4 para variedad
	              int numeroFilaPasto = random.nextInt(4) + 1;
	              mapaFondos[0][c] = "PastoB_r" + numeroFilaPasto + "_c" + (c + 1);
	          } else {
	              //Si toca carretera
	              mapaFondos[0][c] = "Carretera_r4_c" + (c + 1); 
	          }
	      }
	      
	      //Le avisamos al controlador que redibuje toda la pantalla
	      controller.actualizarTodoElMapa();
	  }
  }


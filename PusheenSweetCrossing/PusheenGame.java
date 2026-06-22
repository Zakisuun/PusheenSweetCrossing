package edu.upb.lp.game.PusheenSweetCrossing;
import java.util.Random;

public class PusheenGame {
  private PusheenSweetCrossingController controller;
  private Pusheen pusheen = new Pusheen(this);
  private Random random = new Random();
  private String[][] mapaFondos = new String[7][15];
  
  private String[][] mapaObjetos = new String[7][15];

  public PusheenGame(PusheenSweetCrossingController controller) {
   this.controller = controller;
   this.pusheen = new Pusheen(this);
 
   inicializarMapaInicial();
  }

  public void moverArriba() {
	  avanzarMundo();

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
  
  
  public void inicializarMapaInicial() {
      for (int r = 0; r < 7; r++) {
          for (int c = 0; c < 15; c++) {
              int numeroFila = (r % 4) + 1;
              int numeroColumna = c + 1;
              mapaFondos[r][c] = "PastoB_r" + numeroFila + "_c" + numeroColumna;
              mapaObjetos[r][c] = null; // Al principio no hay autos en el mapa
          }
      }
  }
  
  public void avanzarMundo (){
	      for (int r = 6; r > 0; r--) {
	          for (int c = 0; c < 15; c++) {
	              mapaFondos[r][c] = mapaFondos[r-1][c];
	              mapaObjetos[r][c] = mapaObjetos[r-1][c];
	          }
	      }
	      int tipoFila = random.nextInt(3); // 0 = Pasto, 1 = Carretera Derecha, 2 = Carretera Izquierda
	      
	      if (tipoFila == 0) {
	          // Es Pasto
	          int numeroFilaPasto = random.nextInt(4) + 1;
	          for (int c = 0; c < 15; c++) {
	              mapaFondos[0][c] = "PastoB_r" + numeroFilaPasto + "_c" + (c + 1);
	              mapaObjetos[0][c] = null;
	          }
	      } else if (tipoFila == 1) {
	          // Carretera hacia la DERECHA (Normales)
	          for (int c = 0; c < 15; c++) {
	              mapaFondos[0][c] = "Carretera_r4_c" + (c + 1);
	              mapaObjetos[0][c] = null;
	          }
	          // Spawneamos un carro al principio de la carretera (columna 0) con dirección D
	          mapaObjetos[0][0] = elegirCarroAleatorio("D");
	      } else {
	          // Carretera hacia la IZQUIERDA (Terminan en _i)
	          for (int c = 0; c < 15; c++) {
	              mapaFondos[0][c] = "Carretera_r4_c" + (c + 1) + "_i";
	              mapaObjetos[0][c] = null;
	          }
	          // Spawneamos un carro al final de la carretera (columna 14) con dirección I
	          mapaObjetos[0][14] = elegirCarroAleatorio("I");
	      }
	      //Le avisamos al controlador que redibuje toda la pantalla
	      controller.actualizarTodoElMapa();
	  }
  
  private String elegirCarroAleatorio(String direccion) {
      int modelo = random.nextInt(4); // 0, 1, 2 o 3
      if (modelo == 0) return "CAMIOND-" + direccion;   // Camión Donas
      if (modelo == 1) return "CAMIONHB-" + direccion;  // Camión Hamburguesa
      if (modelo == 2) return "CAMIONH-" + direccion;   // Camión Helados
      return "PANQUEQUECAR";                            // Panqueque funciona para ambos
  }
  public void moverCarrosManualmente() {
      // Creamos una matriz temporal para calcular los movimientos sin pisar los datos actuales
      String[][] nuevaMatrizObjetos = new String[7][15];
      
      for (int r = 0; r < 7; r++) {
          for (int c = 0; c < 15; c++) {
              String carro = mapaObjetos[r][c];
              if (carro != null) {
                  // Si el carro va a la DERECHA o es el panqueque en carretera derecha
                  if (carro.endsWith("-D") || (carro.equals("PANQUEQUECAR") && !mapaFondos[r][c].endsWith("_i"))) {
                      if (c + 1 < 15) {
                          nuevaMatrizObjetos[r][c + 1] = carro; // Avanza a la derecha
                      }
                  } 
                  // Si el carro va a la IZQUIERDA o es el panqueque en carretera izquierda
                  else {
                      if (c - 1 >= 0) {
                          nuevaMatrizObjetos[r][c - 1] = carro; // Avanza a la izquierda
                      }
                  }
              }
          }
      }
      mapaObjetos = nuevaMatrizObjetos;
      controller.actualizarTodoElMapa();
  }
  
  public String getImagenFondo(int f, int c) {
      return mapaFondos[f][c];
  }

  public String getImagenObjeto(int f, int c) { 
      return mapaObjetos[f][c]; 
  }

  public Pusheen getPusheen() { 
      return pusheen; 
  }

  public void borrarPusheen(int filas, int columnas) { 
      controller.borrarPusheen(filas, columnas); 
  }

  public void dibujarPusheen(int filas, int columnas, int direccion) { 
      controller.dibujarPusheen(filas, columnas, direccion); 
  }
}
package edu.upb.lp.game.PusheenSweetCrossing;
import java.util.Random;

public class PusheenGame {
  private PusheenSweetCrossingController controller;
  private Pusheen pusheen = new Pusheen(this);
  private Random random = new Random();
  private String[][] mapaFondos = new String[7][15];
  
  private Auto[][] mapaObjetos = new Auto[7][15];

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
	              
	              // Si movimos un auto hacia abajo, actualizamos su atributo interno de fila
	              if (mapaObjetos[r][c] != null) {
	                  mapaObjetos[r][c].setFila(r);
	              }
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
	          mapaObjetos[0][0] = crearAutoAleatorio(0, 0, 0);
	      } else {
	          // Carretera hacia la IZQUIERDA (Terminan en _i)
	          for (int c = 0; c < 15; c++) {
	              mapaFondos[0][c] = "Carretera_r4_c" + (c + 1) + "_i";
	              mapaObjetos[0][c] = null;
	          }
	          // Spawneamos un carro al final de la carretera (columna 14) con dirección I
	          mapaObjetos[0][14] = crearAutoAleatorio(1, 0, 14);
	      }
	      //Le avisamos al controlador que redibuje toda la pantalla
	      controller.actualizarTodoElMapa();
	  }
  
  private Auto crearAutoAleatorio(int direccion, int fila, int columna) {
      int modelo = random.nextInt(4); // 0, 1, 2 o 3
      String suffix = (direccion == 0) ? "D" : "I";
      String imagen = "PANQUEQUECAR";
      
      if (modelo == 0) imagen = "CAMIOND-" + suffix;   // Camión Donas
      else if (modelo == 1) imagen = "CAMIONHB-" + suffix;  // Camión Hamburguesa
      else if (modelo == 2) imagen = "CAMIONH-" + suffix;   // Camión Helados
      
      // Le pasamos al auto toda su información de nacimiento, incluyendo dónde aparece
      return new Auto(this, imagen, direccion, fila, columna);
  }
  
  // El espacio presiona el botón y el juego solo le ordena a cada auto que se mueva a sí mismo
  public void moverCarrosManualmente() {
      // Lista temporal para saber qué autos ya se movieron en este turno y no moverlos dos veces
      boolean[][] yaMovido = new boolean[7][15];
      
      for (int r = 0; r < 7; r++) {
          for (int c = 0; c < 15; c++) {
              Auto carro = mapaObjetos[r][c];
              if (carro != null && !yaMovido[r][c]) {
                  // Guardamos la columna destino para marcarla como movida
                  int colDestino = (carro.getDireccion() == 0) ? c + 1 : c - 1;
                  if (colDestino >= 0 && colDestino < 15) {
                      yaMovido[r][colDestino] = true;
                  }
                  
                  // ¡EL AUTO SE MUEVE A SÍ MISMO AQUÍ!
                  carro.mover(); 
              }
          }
      }
      controller.actualizarTodoElMapa();
  }

  // Métodos que el Auto manda a llamar para que el juego modifique la matriz
  public void actualizarPosicionAuto(int fila, int colVieja, int colNueva, Auto auto) {
      mapaObjetos[fila][colVieja] = null;
      mapaObjetos[fila][colNueva] = auto;
  }

  public void eliminarObjetoMatriz(int fila, int columna) {
      mapaObjetos[fila][columna] = null;
  }
  
  public String getImagenFondo(int f, int c) {
      return mapaFondos[f][c];
  }

  public String getImagenObjeto(int f, int c) { 
      if (mapaObjetos[f][c] == null) {
          return null;
      }
      return mapaObjetos[f][c].getImagen(); 
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
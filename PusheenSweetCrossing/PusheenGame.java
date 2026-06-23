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
              mapaObjetos[r][c] = null;
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
       int tipoFila = random.nextInt(3); 
       
       if (tipoFila == 0) {
           int numeroFilaPasto = random.nextInt(4) + 1;
           for (int c = 0; c < 15; c++) {
               mapaFondos[0][c] = "PastoB_r" + numeroFilaPasto + "_c" + (c + 1);
               mapaObjetos[0][c] = null;
           }
       } else if (tipoFila == 1) {
           for (int c = 0; c < 15; c++) {
               mapaFondos[0][c] = "Carretera_r4_c" + (c + 1);
               mapaObjetos[0][c] = null;
           }
           mapaObjetos[0][0] = crearAutoAleatorio(0);
       } else {
           for (int c = 0; c < 15; c++) {
               mapaFondos[0][c] = "Carretera_r4_c" + (c + 1) + "_i";
               mapaObjetos[0][c] = null;
           }
           mapaObjetos[0][14] = crearAutoAleatorio(1);
       }
       controller.actualizarTodoElMapa();
   }
  
  private Auto crearAutoAleatorio(int direccion) {
      int modelo = random.nextInt(4); 
      String sufijo = "D";
      
      if (direccion == 0) {
          sufijo = "D";
      } else {
          sufijo = "I";
      }
      
      String imagen = "PANQUEQUECAR";
      if (modelo == 0) imagen = "CAMIOND-" + sufijo;   
      else if (modelo == 1) imagen = "CAMIONHB-" + sufijo;  
      else if (modelo == 2) imagen = "CAMIONH-" + sufijo;   
      
      return new Auto(imagen, direccion);
  }
  
  public void moverCarrosManualmente() {
      Auto[][] nuevaMatrizObjetos = new Auto[7][15];
      boolean[][] yaMovido = new boolean[7][15];
      
      for (int r = 0; r < 7; r++) {
          for (int c = 0; c < 15; c++) {
              Auto carro = mapaObjetos[r][c];
              
              if (carro != null && !yaMovido[r][c]) {
                  int colDestino = c;
                  
                  if (carro.getDireccion() == 0) {
                      colDestino = c + 1;
                  } else {
                      colDestino = c - 1;
                  }
                  
                  if (colDestino >= 0 && colDestino < 15) {
                      nuevaMatrizObjetos[r][colDestino] = carro;
                      yaMovido[r][colDestino] = true;
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
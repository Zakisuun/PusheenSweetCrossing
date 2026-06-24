package edu.upb.lp.game.PusheenSweetCrossing;
import java.util.Random;

public class PusheenGame {
  private PusheenSweetCrossingController controller;
  private Pusheen pusheen = new Pusheen(this);
  private Random random = new Random();
  private String[][] mapaFondos = new String[7][15];
  private Auto[] listaAutos = new Auto[100];

  public PusheenGame(PusheenSweetCrossingController controller) {
   this.controller = controller;
   this.pusheen = new Pusheen(this);
   inicializarMapaInicial();
  }

  public void moverArriba() {
   avanzarMundo();
   verificarColision();
  }
  
  public void moverAbajo() {
  }
  
  public void moverDerecha() {
   pusheen.moverDerecha();
   verificarColision();
  }
  
  public void moverIzquierda() {
   pusheen.moverIzquierda();
   verificarColision();
  }
  
  public void inicializarMapaInicial() {
      for (int r = 0; r < 7; r++) {
          for (int c = 0; c < 15; c++) {
              int numeroFila = (r % 4) + 1;
              int numeroColumna = c + 1;
              mapaFondos[r][c] = "PastoB_r" + numeroFila + "_c" + numeroColumna;
          }
      }
      for (int i = 0; i < 100; i++) {
          listaAutos[i] = null;
      }
  }
  
  public void avanzarMundo() {
       for (int r = 6; r > 0; r--) {
           for (int c = 0; c < 15; c++) {
               mapaFondos[r][c] = mapaFondos[r-1][c];
           }
       }
       
       for (int i = 0; i < 100; i++) {
           if (listaAutos[i] != null) {
               listaAutos[i].bajarFila();
               if (listaAutos[i].getFilas() >= 7) {
                   listaAutos[i] = null;
               }
           }
       }
       
       int tipoFila = random.nextInt(3); 
       
       if (tipoFila == 0) {
           int numeroFilaPasto = random.nextInt(4) + 1;
           for (int c = 0; c < 15; c++) {
               mapaFondos[0][c] = "PastoB_r" + numeroFilaPasto + "_c" + (c + 1);
           }
       } else if (tipoFila == 1) {
           for (int c = 0; c < 15; c++) {
               mapaFondos[0][c] = "Carretera_r4_c" + (c + 1);
           }
           Auto nuevoAuto = crearAutoAleatorio(0, 0, 0);
           agregarAutoALista(nuevoAuto);
       } else {
           for (int c = 0; c < 15; c++) {
               mapaFondos[0][c] = "Carretera_r4_c" + (c + 1) + "_i";
           }
           Auto nuevoAuto = crearAutoAleatorio(1, 0, 14);
           agregarAutoALista(nuevoAuto);
       }
       controller.actualizarTodoElMapa();
   }
  
  private Auto crearAutoAleatorio(int direccion, int fila, int columna) {
      int modelo = random.nextInt(4); 
      String sufijo = "D";
      
      if (direccion == 0) {
          sufijo = "D";
      } else {
          sufijo = "I";
      }
      
      String imagen = "PANQUEQUECAR";
      if (modelo == 0) {
          imagen = "CAMIOND-" + sufijo;   
      } else if (modelo == 1) {
          imagen = "CAMIONHB-" + sufijo;  
      } else if (modelo == 2) {
          imagen = "CAMIONH-" + sufijo;   
      }
      
      return new Auto(imagen, direccion, fila, columna);
  }
  
  private void agregarAutoALista(Auto nuevoAuto) {
      for (int i = 0; i < 100; i++) {
          if (listaAutos[i] == null) {
              listaAutos[i] = nuevoAuto;
              break;
          }
      }
  }
  
  public void moverCarrosManualmente() {
      for (int i = 0; i < 100; i++) {
          if (listaAutos[i] != null) {
              listaAutos[i].mover();
              if (listaAutos[i].getColumnas() < 0 || listaAutos[i].getColumnas() >= 15) {
                  listaAutos[i] = null;
              }
          }
      }
      verificarColision();
      controller.actualizarTodoElMapa();
  }
  
  public void verificarColision() {
      int pusheenFila = pusheen.getFilas();
      int pusheenColumna = pusheen.getColumnas();
      
      for (int i = 0; i < 100; i++) {
          if (listaAutos[i] != null) {
              if (listaAutos[i].getFilas() == pusheenFila) {
                  if (listaAutos[i].getColumnas() == pusheenColumna) {
                      pusheen.reiniciarPosicion();
                  }
              }
          }
      }
  }
  
  public String getImagenFondo(int f, int c) {
      return mapaFondos[f][c];
  }

  public String getImagenObjeto(int f, int c) { 
      for (int i = 0; i < 100; i++) {
          if (listaAutos[i] != null) {
              if (listaAutos[i].getFilas() == f) {
                  if (listaAutos[i].getColumnas() == c) {
                      return listaAutos[i].getImagen();
                  }
              }
          }
      }
      return null; 
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
package edu.upb.lp.game.PusheenSweetCrossing;

public class Pusheen {
 private PusheenGame game;
 private int direccion = 0;
 private int filas = 3;
 private int columnas = 7;

 public Pusheen(PusheenGame game) {
  this.game = game;
 }

 public void moverArriba() {
  game.borrarPusheen(filas, columnas);
  if (filas > 0) {
   filas--;
  } else {
   filas=0;
  }
  game.dibujarPusheen(filas, columnas, direccion);
 }

 public void moverAbajo() {
  game.borrarPusheen(filas, columnas);
  if (filas < 6) {
   filas++;
  } else {
   filas=6;
  }
  game.dibujarPusheen(filas, columnas, direccion);
 }

 public void moverDerecha() {
  game.borrarPusheen(filas, columnas);
  if (columnas < 14) {
   columnas++;
  } else {
   columnas=14;
  }
  game.dibujarPusheen(filas, columnas, direccion);
 }

 public void moverIzquierda() {
  game.borrarPusheen(filas, columnas);
  if (columnas > 0) {
   columnas--;
  } else {
   columnas=0;
  }
  game.dibujarPusheen(filas, columnas, direccion);
 }

}
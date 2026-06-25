package edu.upb.lp.game.PusheenSweetCrossing;

public class Auto {
    private String imagen;
    private int direccion; 
    private int filas;
    private int columnas;

    public Auto(String imagen, int direccion, int filas, int columnas) {
        this.imagen = imagen;
        this.direccion = direccion;
        this.filas = filas;
        this.columnas = columnas;
    }

    public void mover() {
        if (direccion == 0) { //va a ir derecha
            columnas = columnas + 1;
        } else {
            columnas = columnas - 1;
        }
    }

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public String getImagen() {
		return imagen;
	}

	public void bajarFila() {
		filas = filas + 1; // 
	}
	
	//mayores dificultades y logros, problemas, estructura=> clases quienes hablan con quienes, como están conectados
	// codigo interesante 
	// diapositivas recomendadas
	// demostración de juego 
	// preguntas de como es que el objeto hace algo, conocer los métodos
}
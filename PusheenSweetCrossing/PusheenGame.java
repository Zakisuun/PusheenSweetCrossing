package edu.upb.lp.game.PusheenSweetCrossing;

import java.util.Random;

public class PusheenGame {
	private PusheenSweetCrossingController controller;
	private Pusheen pusheen = new Pusheen(this);
	private Random random = new Random();
	private String[][] mapaFondos = new String[7][15]; //string para poner el nombre de la imagen en cada casilla de la matriz
	private Auto[] listaAutos = new Auto[5]; //almacena 5 tipo de autos helado hb etc
	private MovedorDeCarros movedor = new MovedorDeCarros(this);
	private String idDelMovedor;

	public PusheenGame(PusheenSweetCrossingController controller) {
		this.controller = controller;
		this.pusheen = new Pusheen(this); //TODO
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

	public void inicializarMapaInicial() { //recorre toda la matriz
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 15; c++) {
				int numeroFila = (r % 7) + 1;
				int numeroColumna = c + 1;
				mapaFondos[r][c] = "PastoB_r" + numeroFila + "_c" + numeroColumna; //dibuja el fondo de cada casilla el PASTOOO
			}
		}
		for (int i = 0; i < 5; i++) { //4 tipos de autos, por eso <5
			listaAutos[i] = null; // no puede aparecer el pusheen en medio de la calle 
		}
	}

	public Auto crearAutoAleatorio(int direccion, int fila, int columna) {
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
		} else {
			imagen = "PANQUEQUECAR";
		}

		return new Auto(imagen, direccion, fila, columna);
	}

	public void agregarAutoALista(Auto nuevoAuto) {
		for (int i = 0; i < 5; i++) {
			if (listaAutos[i] == null) {
				listaAutos[i] = nuevoAuto;
				break;
			}
		}
	}

	public void moverCarros() {
		for (int i = 0; i < 5; i++) {
			if (listaAutos[i] != null) {
				listaAutos[i].mover();
				if (listaAutos[i].getColumnas() < 0 || listaAutos[i].getColumnas() >= 15) {
					listaAutos[i] = null;
				}
			}
		}
		controller.actualizarTodoElMapa();
		verificarColision();
	}
	
	public void aumentarCarro() {
		
	}

	public void avanzarMundo() {
		for (int r = 6; r > 0; r--) {
			for (int c = 0; c < 15; c++) {
				mapaFondos[r][c] = mapaFondos[r - 1][c];
			}
		}
		for (int i = 0; i < 5; i++) {
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

	public void verificarColision() {
		int pusheenFila = pusheen.getFilas();
		int pusheenColumna = pusheen.getColumnas(); //ubica en que columna y fila está
		

		for (int i = 0; i < 5; i++) {
			if (listaAutos[i] != null) { //si existe cualquier auto TODO
				if (listaAutos[i].getFilas() == pusheenFila && listaAutos[i].getColumnas() == pusheenColumna) { //compara si el pusheen y el auto están en la misma fila
					pusheen.perderVida();
					pusheen.reiniciarPosicion();
					controller.actualizarTodoElMapa(); //TODO
					if (pusheen.getVidas()==0) {
						controller.perdiste();
					}
				}
			}
		}
	}

	public String getImagenFondo(int f, int c) {
		return mapaFondos[f][c];
	}

	public String getImagenObjeto(int f, int c) {
		for (int i = 0; i < 5; i++) {
			if (listaAutos[i] != null) {
				if (listaAutos[i].getFilas() == f && listaAutos[i].getColumnas() == c) {
						return listaAutos[i].getImagen();
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

	public int getVidas() {
		return pusheen.getVidas();
	}
	
	public void reiniciarJuego() {
		inicializarMapaInicial();
		pusheen.reiniciarPosicion();
		pusheen.setVidas();
	}

	public void start() {
		idDelMovedor = controller.executeRepeatedly(movedor, 1000);
//		idDelAumentador = 
	}
	
	public void stop() {
		controller.stopLoop(idDelMovedor);
		
	}
}
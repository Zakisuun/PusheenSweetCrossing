package edu.upb.lp.game.PusheenSweetCrossing;

public class MovedorDeCarros implements Runnable {
	private PusheenGame game;
	
	
	public MovedorDeCarros(PusheenGame game) {
		this.game = game;
	}


	@Override
	public void run() {
		game.moverCarros();
	}

}

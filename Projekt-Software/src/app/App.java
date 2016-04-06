package app;

import game.controllers.GameController;

public class App {
	public static void main(String[] args){
		
		// instantiate GameControlelr and starts game.
		GameController game = new GameController();
		game.run();
		
		
	}
}

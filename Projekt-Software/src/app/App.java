package app;


import game.controllers.GameController;



public class App {
	public static void main(String[] args) {
		
		// instantiate GameController and starts game.
		GameController controller = new GameController();
		controller.run();
		
		
	}
}

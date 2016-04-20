package app;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import game.controllers.GameController;



public class App {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		
		// instantiate GameControlelr and starts game.
		GameController controller = new GameController();
		controller.run();
		
		
	}
}

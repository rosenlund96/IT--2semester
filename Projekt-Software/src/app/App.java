package app;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import game.boundaries.GUIBoundary;
import game.boundaries.Outputable;
import game.controllers.GameController;
import game.entities.CardManager;
import game.entities.FieldManager;
import game.entities.fields.LuckyCard;

public class App {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		
		// instantiate GameControlelr and starts game.
		GameController controller = new GameController();
		controller.run();
		
		
	}
}

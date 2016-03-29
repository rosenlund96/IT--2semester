package app;

import java.util.Stack;

import game.entities.fields.LuckyCard;
import game.util.CSVReader;
import game.util.FieldDataHandler;

public class App {
	

public static void main(String[] args){
		
		// instantiate GameControlelr and starts game.
	FieldDataHandler handler = new FieldDataHandler();
	handler.readData();
	System.out.println(handler.readData());
	
	
		
	
		
	}
}

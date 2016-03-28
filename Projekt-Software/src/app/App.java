package app;

import game.util.CSVReader;

public class App {
public static void main(String[] args){
		
		// instantiate GameControlelr and starts game.
		CSVReader reader = new CSVReader("resources/Matador - feltdata - kortdata.csv");
		reader.ReadFile();
		reader.printArrayList();
		
	
		
	}
}

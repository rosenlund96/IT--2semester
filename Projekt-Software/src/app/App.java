package app;

import java.util.Stack;

import game.entities.fields.LuckyCard;
import game.util.CSVReader;

public class App {
	

public static void main(String[] args){
		
		// instantiate GameControlelr and starts game.
		LuckyCard cards = new LuckyCard(null, null, null);
		cards.hentKort();
		cards.opretStak();
		for (int i = 0; i < 100; i++) {
			
		System.out.println(cards.returnerKort());
		}
		
	
		
	}
}

package game.entities.fields;

import game.entities.*;
import java.util.*;
import game.boundaries.*;
import game.util.*;


public class LuckyCard extends AbstractField{

	public String filePath = "resources/prov lykken kort.csv";
	ArrayList <String>Cards = new ArrayList<String>();
	CSVReader reader = new CSVReader(filePath);
	private Random randomGenerator;
	
	public LuckyCard(FieldManager fieldManager, FieldType fieldType, Outputable output) {
		super(fieldManager, fieldType, output);
		
	}

	
	
	
	public ArrayList<String> hentKort(){
		reader.ReadFile();
		Cards = reader.getArrayList();
		return Cards;
	}
	public ArrayList<String> blandKort(){
	Collections.shuffle(Cards);
	return Cards;
	
	}
	public String returnerKort(){
		int index = randomGenerator.nextInt(Cards.size());
		return Cards.get(index);
	}



	
	
	
	@Override
	public void landOnField(Player player) {
		hentKort();
		blandKort();
		returnerKort();
        
		
	}
	
	

}

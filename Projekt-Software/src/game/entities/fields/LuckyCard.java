package game.entities.fields;

import game.entities.*;
import java.util.*;
import game.boundaries.*;
import game.util.*;


public class LuckyCard extends AbstractField{

	public String filePath = "resources/Matador - cards.csv";
	public ArrayList <String>Cards = new ArrayList<String>();
	CSVReader reader = new CSVReader(filePath);
	public Stack<String> Stak = new Stack<String>();
	public Stack <String> temp = new Stack<String>();
	public int cardNo = 0;
	
	public LuckyCard(FieldManager fieldManager, FieldType fieldType, Outputable output) {
		super(fieldManager, fieldType, output);
		
	}

	
	
	
	public ArrayList<String> hentKort(){
		reader.ReadFile();
		Cards = reader.getArrayList();
		return Cards;
	}
	public String returnerKort(){
		if(cardNo < 33 ){
		temp.push(Stak.peek());
		cardNo++;
		return Stak.pop();
		}
		else {
			Stak.push(temp.peek());
			cardNo++;
			while(cardNo==66) cardNo=0;
			return temp.pop();
			
		}
		
	}
	public Stack<String> opretStak(){
		
		Stak.addAll(Cards);
		Collections.shuffle(Stak);
		return Stak;
	}
	

	
	
	
	@Override
	public void landOnField(Player player) {
		returnerKort();
        
		
	}
	
	

}

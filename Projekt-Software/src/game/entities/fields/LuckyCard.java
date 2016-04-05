package game.entities.fields;

import game.entities.*;
import java.util.*;
import game.boundaries.*;
import game.util.*;


public class LuckyCard extends AbstractField{

	public String filePath = "resources/Matador - cards.csv";
	public ArrayList <String>Cards = new ArrayList<String>();//Henter kortene ned fra CSV
	CSVReader reader = new CSVReader(filePath);
	public Stack<String> Stak = new Stack<String>(); //Kortene gemmes i denne stak
	public Stack <String> temp = new Stack<String>();//Og lægges herover efter de er brugt. 
	public int cardNo = 0;
	
	public LuckyCard(FieldManager fieldManager, FieldType LUCKYCARD, Outputable output) {
		super(fieldManager, LUCKYCARD, output);
		
	}

	
	
	
	public Stack<String> hentKort(){
		reader.ReadFile();
		Cards = reader.getArrayList();
		Stak.addAll(Cards);
		Collections.shuffle(Stak);
		return Stak;
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
	public void gemKort(){
		if (cardNo < 33 ){
			temp.pop();
		}
		else {
			Stak.pop();
		}
	}
	
	

	
	
	
	@Override
	public void landOnField(Player player) {
		returnerKort();
        
		
	}
	
	

}

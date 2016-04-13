package game.entities.fields;

import game.entities.*;
import java.util.*;
import game.boundaries.*;


public class LuckyCard extends AbstractField{

	public Stack<String> Stak = new Stack<String>(); //Kortene gemmes i denne stak
	public Stack <String> temp = new Stack<String>();//Og lægges herover efter de er brugt. 
	public int cardNo = 0;
	
	public LuckyCard(FieldManager fieldManager, Outputable output) {
		super(fieldManager, FieldType.LUCKYCARD, output);
		
	}

	//kortene skal hentes fra cardmanager og derfra skal de så gemmes i stak, via hentkort. Herfra skal returnerkort, klare resten. 
	//Skal vi mon have en tredje stak til de kort der er ude og svømme? Eller bare slette dem og oprette dem på ny? 
	
	
	public Stack<String> hentKort(){
		
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
	public void gemKort(Player player, game.entities.cards.AbstractOwnable card){
		if (cardNo < 33 ){
			temp.pop();
			player.setoutOfJailCard(1);
			card.setOwner(player);
			
		}
		else {
			Stak.pop();
			player.setoutOfJailCard(1);
			card.setOwner(player);
		}
	}
	public int antalKort(){
		int stak = Stak.size();
		int Temp = temp.size();
		return stak+Temp;
	}
	

	
	
	
	@Override
	public void landOnField(Player player) {
		returnerKort();
        
		
	}
	
	

}

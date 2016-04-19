package game.entities.fields;

import game.entities.*;
import game.entities.cards.AbstractCard;
import game.resources.CardEffect;

import java.util.*;
import game.boundaries.*;


public class LuckyCard extends AbstractField{

	public ArrayList<AbstractCard> Stak = new ArrayList<AbstractCard>(); //Kortene gemmes i denne stak
	public Stack<AbstractCard> temp = new Stack<AbstractCard>();//Og lægges herover efter de er brugt. 
	public int cardNo = 0;
	CardManager cardManager;
	
	public LuckyCard(FieldManager fieldManager, Outputable output) {
		super(fieldManager, FieldType.LUCKYCARD, output);
		
	}

	//kortene skal hentes fra cardmanager og derfra skal de så gemmes i stak, via hentkort. Herfra skal returnerkort, klare resten. 
	//Skal vi mon have en tredje stak til de kort der er ude og svømme? Eller bare slette dem og oprette dem på ny? 
	
	
	public ArrayList<AbstractCard> hentKort(){
	for (int i = 0; i < CardEffect.CardNo_DATA.length; i++) {
		Stak.add(cardManager.cards[i]);
	}
		return Stak;
	}
	public AbstractCard returnerKort(){
		
		return null;
	}
	public void gemKort(Player player, game.entities.cards.AbstractOwnable card){
		
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

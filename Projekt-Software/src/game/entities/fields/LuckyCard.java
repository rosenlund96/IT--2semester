package game.entities.fields;

import game.entities.*;
import game.entities.cards.AbstractCard;
import game.entities.cards.MovaActivePlayer;
import game.resources.CardEffect;

import java.util.*;
import game.boundaries.*;


public class LuckyCard extends AbstractField{

	public int NUMBER_OF_CARDS = 33;
	AbstractCard[] cards;
	CardManager cardManager;
	
	public LuckyCard(FieldManager fieldManager, Outputable output) {
		super(fieldManager, FieldType.LUCKYCARD, output);
	}

	//kortene skal hentes fra cardmanager og derfra skal de så gemmes i stak, via hentkort. Herfra skal returnerkort, klare resten. 
	//Skal vi mon have en tredje stak til de kort der er ude og svømme? Eller bare slette dem og oprette dem på ny? 
	
	
	private void initializeCards(Outputable gui){
		cards = new AbstractCard[NUMBER_OF_CARDS];

		for (int i = 0; i < cards.length; i++) {
			switch(CardEffect.CardType_DATA[i]){
			case MOVE: 
				cards[i] = new MovaActivePlayer(gui, CardEffect.CardNo_DATA[i]) ;
				break;
			case PRISON: 
				cards[i] = new game.entities.cards.Prison(gui, CardEffect.CardNo_DATA[i]);
				break;
			case REFUGE: 
				cards[i] = new game.entities.cards.Refuge(gui, CardEffect.CardEffect_DATA[i],CardEffect.CardNo_DATA[i]);
				break;
			case TAX: 
				cards[i] = new game.entities.cards.Tax(gui, CardEffect.CardEffect_DATA[i],CardEffect.CardNo_DATA[i]) ;
				break;
			
			}	
			
		}	
	}

	public AbstractCard returnerKort(Player player){
		AbstractCard randomObj = cardManager.cards[(int)Math.random() * cards.length];
		return randomObj;
	}
	@Override
	public void landOnField(Player player) {
		
        
		
	}
	
	

}

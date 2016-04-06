package game.entities;

import java.util.Collections;

import game.boundaries.Outputable;
import game.entities.cards.AbstractCard;
import game.entities.cards.MovaActivePlayer;
import game.resources.CardEffect;

public class CardManager {

	
	public final int NUMBER_OF_CARDS = 33;
	private AbstractCard[] cards;
	
	/**************************************************
	 * Constructor, takes a GUI to pass to the fields *
	 **************************************************/
	public CardManager(Outputable gui){
		initializeCards(gui);

	}
	
	/****************************************
	 * Returns the field number of a field *
	 ****************************************/
	public int getCardNumber(AbstractCard card){
		int number  = 0;
		for(int i = 0; i < NUMBER_OF_CARDS; i++){
			if(card == cards[i])
				number = i;
		}
		return number;
	
	}
	
	/************************************************************
	 * Creates the array of fields use from the FieldData class *
	 ************************************************************/
	private void initializeCards(Outputable gui){
		cards = new AbstractCard[NUMBER_OF_CARDS];

		for (int i = 0; i < cards.length; i++) {
			switch(CardEffect.CardType_DATA[i]){
			case MOVE: 
				cards[i] = new MovaActivePlayer(this, gui, CardEffect.CardPositionEffect_Data[i]) ;
				break;
			case PRISON: 
				cards[i] = new game.entities.cards.Prison(this, gui);
				break;
			case REFUGE: 
				cards[i] = new game.entities.cards.Refuge(this, gui, CardEffect.CardEffect_DATA[i]);
				break;
			case TAX: 
				cards[i] = new game.entities.cards.Tax(this, gui, CardEffect.CardEffect_DATA[i]) ;
				break;
			
			}	
		}	
	}
	
}

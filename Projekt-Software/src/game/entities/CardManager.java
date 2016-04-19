package game.entities;


import java.util.Collections;
import java.util.List;

import game.boundaries.Outputable;
import game.entities.cards.AbstractCard;
import game.entities.cards.MovaActivePlayer;
import game.resources.CardEffect;

public class CardManager {
//SKal vise en tekstbesked. Denne klasse lave en stak af kort, som kan udskrives til gui. Temp stak og aktiv stak. 
	
	public final int NUMBER_OF_CARDS = 33;
	public AbstractCard[] cards;
	
	/**************************************************
	 * Constructor, takes a GUI to pass to the fields *
	 **************************************************/
	public CardManager(Outputable gui){
		initializeCards(gui);

	}
	
	/****************************************
	 * Returns the card number of a card *
	 ****************************************/
	public int getCardNumber(AbstractCard card){
		int number  = 0;
		for(int i = 0; i < NUMBER_OF_CARDS; i++){
			if(card == cards[i])
				number = i;
		}
		return number;
	
	}
	public void drawCardByNumber(Player player, int cardNumber){
		cards[cardNumber].drawCard(player);
	}
	
	/************************************************************
	 * Creates the array of cards from CardEffect class and shuffles 
	 * Kortene skal loades ind i luckycard klassen og derfra gives*
	 ************************************************************/
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
	
}

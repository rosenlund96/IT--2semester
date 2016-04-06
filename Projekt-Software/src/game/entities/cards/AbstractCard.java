package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public abstract class AbstractCard {
	
	public enum CardType{MOVE, PRISON, REFUGE, TAX}

	
	protected CardManager cardManager;
	protected Outputable output;
    protected CardType cardType;
	
	public AbstractCard(CardManager cardManager, Outputable output, CardType cardType){
		this.cardManager = cardManager;
		this.cardType = cardType;
		this.output = output;
	}
	
	public CardType getFieldType(){
		return cardType;
	}
	
	// Can be called for a player drawing the card
		public abstract void drawCard(Player player);
		
		public String toString(){
			switch(cardType){
			case MOVE:
				return "cardType=Move";
			case PRISON:
				return "cardType=prison";
			case REFUGE:
				return "cardType=Refuge";
			case TAX:
				return "cardType=Tax";
			default:
				return "";
			}
		}
}

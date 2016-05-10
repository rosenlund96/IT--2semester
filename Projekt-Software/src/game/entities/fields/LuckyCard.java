package game.entities.fields;

import game.entities.*;
import game.entities.cards.AbstractCard;
import game.resources.CardEffect;
import game.util.XMLReader;
import game.entities.cards.MoveActivePlayer;
import game.boundaries.*;


public class LuckyCard extends AbstractField{

	public AbstractCard drawnCard;
	private Outputable output;
	private int fieldNo;
	
	String userHome = System.getProperty("user.home");
	XMLReader reader = new XMLReader(userHome+"/git/IT--2semester/Projekt-Software/resources/language2.xml");


	public LuckyCard(FieldManager fieldManager, Outputable output, int fieldNo) {
		super(fieldManager, FieldType.LUCKYCARD, output, fieldNo);
		this.output = output;
		//initializeCards(output);
		this.fieldNo = fieldNo;
	}
	@Override
	public int getFieldNo(){
		return fieldNo;
	}

	//Draw a card like in a queue
	public AbstractCard drawCard(Player player){
		
		this.drawnCard=FieldManager.cards[0];
		this.drawnCard.drawCard(player);
		for(int i = 0; i < (FieldManager.cards.length-1); i++){
			FieldManager.cards[i]=FieldManager.cards[i+1];
		}
		FieldManager.cards[FieldManager.cards.length-1]=drawnCard;	
		output.showCard(drawnCard.getText());
		return drawnCard;
	}
	public void landOnField(Player player){
		drawCard(player);
	}
}

package game.entities.fields;

import game.entities.*;
import game.entities.cards.AbstractCard;
import game.entities.cards.LuckyCards;
import game.entities.cards.MovaActivePlayer;
import game.resources.CardEffect;

import java.util.*;
import game.boundaries.*;


public class LuckyCard extends AbstractField{

	LuckyCards card;
	AbstractCard card1 = new game.entities.cards.Refuge(output, 3000, 1);
	Queue<AbstractCard> ko = new LinkedList<AbstractCard>();
	
	public LuckyCard(FieldManager fieldManager, Outputable output) {
		super(fieldManager, FieldType.LUCKYCARD, output);
	}

	//kortene skal hentes fra cardmanager og derfra skal de så gemmes i stak, via hentkort. Herfra skal returnerkort, klare resten. 
	//Skal vi mon have en tredje stak til de kort der er ude og svømme? Eller bare slette dem og oprette dem på ny? 
	

	public AbstractCard returnerKort(){
		card.initialize();
		return card.ko.get(0);
	}
	@Override
	public void landOnField(Player player) {
        
		
	}
	
	

}

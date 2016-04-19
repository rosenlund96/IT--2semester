package game.entities.cards;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import game.boundaries.Outputable;

public class LuckyCards {

	Outputable output;
	public ArrayList<AbstractCard> ko = new ArrayList<AbstractCard>();
	
	public void Luckycard(Outputable output){
		this.output = output;
	}
	
	
	public ArrayList<AbstractCard> initialize(){
		AbstractCard card1 = new game.entities.cards.Refuge(output, 3000, 1);
		AbstractCard card2 = new game.entities.cards.MovaActivePlayer(output, 2);
		AbstractCard card3 = new game.entities.cards.Prison(output, 3);
		AbstractCard card4 = new game.entities.cards.Prison(output, 4);
		AbstractCard card5 = new game.entities.cards.Tax(output, 1000, 5);
		AbstractCard card6 = new game.entities.cards.Tax(output, 1000, 6);
		AbstractCard card7 = new game.entities.cards.Refuge(output, 3000, 7);
		AbstractCard card8 = new game.entities.cards.Refuge(output, 3000, 8);
		AbstractCard card9 = new game.entities.cards.Refuge(output, 3000, 9);
		AbstractCard card10 = new game.entities.cards.Refuge(output, 3000, 10);
		AbstractCard card11 = new game.entities.cards.Refuge(output, 3000, 11);
		AbstractCard card12 = new game.entities.cards.Tax(output, 0, 12);
		AbstractCard card13 = new game.entities.cards.Tax(output, 0, 13);
		AbstractCard card14 = new game.entities.cards.MovaActivePlayer(output, 14);
		AbstractCard card15 = new game.entities.cards.MovaActivePlayer(output, 15);
		AbstractCard card16 = new game.entities.cards.MovaActivePlayer(output, 16);
		AbstractCard card17 = new game.entities.cards.MovaActivePlayer(output, 17);
		AbstractCard card18 = new game.entities.cards.Prison(output, 18);
		AbstractCard card19 = new game.entities.cards.Prison(output, 19);
		AbstractCard card20 = new game.entities.cards.Refuge(output, 0, 20);
		AbstractCard card21 = new game.entities.cards.MovaActivePlayer(output, 21);
		AbstractCard card22 = new game.entities.cards.Refuge(output, 0, 22);
		AbstractCard card23 = new game.entities.cards.MovaActivePlayer(output, 23);
		AbstractCard card24 = new game.entities.cards.MovaActivePlayer(output, 24);
		AbstractCard card25 = new game.entities.cards.Tax(output, 0, 25);
		AbstractCard card26 = new game.entities.cards.Tax(output, 0, 26);
		AbstractCard card27 = new game.entities.cards.Tax(output, 0, 27);
		AbstractCard card28 = new game.entities.cards.Refuge(output, 0, 28);
		AbstractCard card29 = new game.entities.cards.Refuge(output, 0, 29);
		AbstractCard card30 = new game.entities.cards.Refuge(output, 0, 30);
		AbstractCard card31 = new game.entities.cards.Refuge(output, 0, 31);
		AbstractCard card32 = new game.entities.cards.Tax(output, 0, 32);
		AbstractCard card33 = new game.entities.cards.Tax(output, 0, 33);
		
		ko.add(card1);ko.add(card2);ko.add(card3);ko.add(card4);ko.add(card5);ko.add(card6);ko.add(card7);
		ko.add(card8);ko.add(card9);ko.add(card10);ko.add(card11);ko.add(card12);ko.add(card13);ko.add(card14);
		ko.add(card15);ko.add(card16);ko.add(card17);ko.add(card18);ko.add(card19);ko.add(card20);ko.add(card21);
		ko.add(card22);ko.add(card23);ko.add(card24);ko.add(card25);ko.add(card26);ko.add(card27);ko.add(card28);
		ko.add(card29);ko.add(card30);ko.add(card31);ko.add(card32);ko.add(card33);
		
		return ko;
	}
	
}

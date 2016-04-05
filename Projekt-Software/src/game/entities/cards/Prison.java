package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Prison extends AbstractOwnable {

	
	
	public Prison(FieldManager fieldManager, Outputable output, CardType cardType, Player owner) {
		super(fieldManager, output, cardType, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawCard(Player player) {
		if (player.getImprisoned()==true) {
			owner = player;
			player.setImprisoned(false);
			this.clearOwner();
		}
		else player = owner;
		
	}

}

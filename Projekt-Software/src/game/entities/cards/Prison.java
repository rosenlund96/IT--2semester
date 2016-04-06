package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public class Prison extends AbstractOwnable {

	
	
	public Prison(CardManager cardManager, Outputable output) {
		super(cardManager, output, CardType.PRISON);
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

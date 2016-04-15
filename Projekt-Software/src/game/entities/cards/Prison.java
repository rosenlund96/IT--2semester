package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public class Prison extends AbstractOwnable {

	
	
	public Prison(CardManager cardManager, Outputable output, int cardNo) {
		super(cardManager, output, CardType.PRISON, cardNo);
	}

	@Override
	public void drawCard(Player player) {
		output.showCardMessage(player, cardNo);
		this.setOwner(player);
		player.setoutOfJailCard(player.getoutOfJailCard()+1);
		output.showGetPrisonCardMessage(player);
	}
	

}

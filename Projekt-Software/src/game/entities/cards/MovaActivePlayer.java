package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public class MovaActivePlayer extends AbstractCard {

	private int effect;
	
	public MovaActivePlayer(CardManager cardManager, Outputable output, int effect) {
		super(cardManager, output, CardType.MOVE);
		this.effect = effect;
	}

	@Override
	public void drawCard(Player player) {
		switch (effect) {
		//Ryk tre felter tilbage
		case -3:
			int currentPosition = player.getPosition();
			player.setPosition(currentPosition-3);
			break;
			
			//Metoderne tag til* samt ryk til nærmeste rederi mangler

		
		}
		
	}
	
	public int getEffect(){
		return effect;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", effect=" + effect;
	}
	
	

}

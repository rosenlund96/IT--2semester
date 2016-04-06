package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;
import game.resources.CardEffect;

public class MovaActivePlayer extends AbstractCard {

	private int effect;
	private int cardNo;
	
	public MovaActivePlayer(CardManager cardManager, Outputable output, int effect) {
		super(cardManager, output, CardType.MOVE);
		this.effect = effect;
		this.cardNo = cardManager.getCardNumber(this);
	}

	@Override
	public void drawCard(Player player) {
		switch (cardNo) {
		//Ryk tre felter tilbage
		case (24):
			int currentPosition = player.getPosition();
			player.setPosition(currentPosition-3);
			break;
			//Gå til fængsel
		case (3):
			//Gå til fængsel
		case (4):
			//Gå til start
		case (2):
			//Gå til Grønningen
		case(14):
			//Nærmeste redderi
		case(15):
			//Nærmeste redderi
		case(16):
			//LB færgerne
		case(17):
			//Frederiksberg Alle
		case(21):
			//Rådhuspladsen
		case(23):
			
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

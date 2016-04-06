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
			//G� til f�ngsel
		case (3):
			//G� til f�ngsel
		case (4):
			//G� til start
		case (2):
			//G� til Gr�nningen
		case(14):
			//N�rmeste redderi
		case(15):
			//N�rmeste redderi
		case(16):
			//LB f�rgerne
		case(17):
			//Frederiksberg Alle
		case(21):
			//R�dhuspladsen
		case(23):
			
			//Metoderne tag til* samt ryk til n�rmeste rederi mangler

		
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

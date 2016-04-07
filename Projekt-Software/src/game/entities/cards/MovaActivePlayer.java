package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public class MovaActivePlayer extends AbstractCard {

	private int effect;
	private int cardNo;
	private int fieldNo;
	
	
	public MovaActivePlayer(CardManager cardManager, Outputable output, int effect) {
		super(cardManager, output, CardType.MOVE);
		this.effect = effect;
		this.cardNo = cardManager.getCardNumber(this);
	}

	@Override
	public void drawCard(Player player) {
		int currentPosition = player.getPosition();
		int difference;
		switch (cardNo) {
		
		case (24)://Ryk tre felter tilbage
			player.setPosition(Math.abs((currentPosition-3)) % 41);
			break;	
		case (3)://Gå til fængsel
			fieldNo = 11;
			player.setPosition(fieldNo);
			break;
			
		case (4)://Gå til fængsel
			fieldNo = 11;
			player.setPosition(fieldNo);
			break;
			
		case (2)://Gå til start
			fieldNo=1;
			difference = getCount(currentPosition, fieldNo);
			player.setPosition(difference % 41);
			break;
			
		case(14)://Gå til Grønningen
			fieldNo = 25;
			difference = getCount(currentPosition, fieldNo);
			player.setPosition(difference % 41);
			break;
			
		case(15)://Nærmeste redderi
			fieldNo = closestRefuge(currentPosition);
			difference = getCount(currentPosition, fieldNo);
			player.setPosition(difference % 41);
			
		case(16)://Nærmeste redderi
			fieldNo = closestRefuge(currentPosition);
			difference = getCount(currentPosition, fieldNo);
			player.setPosition(difference % 41);
			
			
		case(17)://LB færgerne
			fieldNo=6;
			difference = getCount(currentPosition, fieldNo);
			player.setPosition(difference % 41);
			break;
			
		case(21)://Frederiksberg Alle
			fieldNo=12;
			difference = getCount(currentPosition, fieldNo);
			player.setPosition(difference % 41);
			break;
		case(23)://Rådhuspladsen
			player.setPosition(40);
			break;
			
			//Denne klasse er meget ucharmerende, men den virker!

		
		}
		
	}
	
	public int getEffect(){
		return effect;
	}
	public int getCount(int currentPosition, int fieldNo){
		int count = 0;
		while(currentPosition != fieldNo){
			if(currentPosition<=40)
			currentPosition++;
			count++;
			
			if(currentPosition==41){
			currentPosition=0;
			count++;
			}
		}
		return count;
	}
	public int closestRefuge(int currentPosition){
		int refuge1= 6;
		int refuge2 = 13;
		int refuge3 = 29;
		
		int difference1 = getCount(currentPosition, refuge1);
		int difference2 = getCount(currentPosition, refuge2);
		int difference3 = getCount(currentPosition, refuge3);
		
		if(difference1<difference2 && difference1<difference3){
		    return refuge1;
		}else if(difference2<difference3 && difference2<difference1){
		    return refuge2;
		}else{
		    return refuge3;
		}
	}
	
	@Override
	public String toString(){
		return super.toString() + ", effect=" + effect;
	}
	
	

}

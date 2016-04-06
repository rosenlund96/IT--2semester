package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public class Refuge extends AbstractCard {
	
	private int bonus;

	public Refuge(CardManager cardManager, Outputable output, int bonus) {
		super(cardManager, output, CardType.REFUGE);
		this.bonus=bonus;
	}

	
	@Override
	public void drawCard(Player player) {
		//Metoder for de 40.000 samt fødselsdag mangler
		switch (bonus) {
		case 1000:
			player.deposit(1000);
			break;
		case 200:
			player.deposit(200);
			break;
		case 500:
			player.deposit(500);
			break;
		case 3000:
			player.deposit(3000);
			break;
		}
		
	}
	
	
	public int getBonus(){
		return bonus;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", bonus=" + bonus;
	}

	
}

package game.entities.cards;

import desktop_resources.GUI;
import game.boundaries.Outputable;
import game.controllers.GameController;
import game.entities.FieldManager;
import game.entities.Player;
import game.util.XMLReader;

public class Refuge extends AbstractCard {
	
	private int bonus;
	private int cardNo;
	private FieldManager fieldManager;
	private static String text;
	
	
	public Refuge(Outputable output, int bonus, int cardNo) {
		super(output, CardType.REFUGE, text);
		this.bonus=bonus;
		this.cardNo = cardNo;
	
	}
	
	public int getCardNo(){
		return cardNo;
	}
	
	@Override
	public void drawCard(Player player) {
		
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
		default:
			//Træk 200 fra hvermodspiller
		
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

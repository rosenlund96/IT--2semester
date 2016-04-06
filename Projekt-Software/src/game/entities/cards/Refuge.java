package game.entities.cards;

import game.boundaries.Outputable;
import game.controllers.GameController;
import game.entities.CardManager;
import game.entities.FieldManager;
import game.entities.Player;

public class Refuge extends AbstractCard {
	
	private int bonus;
	private FieldManager fieldManager;
	private GameController gameController;

	public Refuge(CardManager cardManager, Outputable output, int bonus) {
		super(cardManager, output, CardType.REFUGE);
		this.bonus=bonus;
		fieldManager = new FieldManager(output);
		gameController = new GameController();
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
		case 40000:
			if (fieldManager.getFieldsValue(player)<= 15000) {
				player.deposit(bonus);
			}
			break;
		default:
			bonus = 200;
			int playerCount = gameController.names.size();
			player.deposit(bonus*playerCount);
		
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

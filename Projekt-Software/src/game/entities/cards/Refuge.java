package game.entities.cards;

import game.boundaries.Outputable;
import game.controllers.GameController;
import game.entities.FieldManager;
import game.entities.Player;

public class Refuge extends AbstractCard {
	
	private int bonus;
	private int cardNo;
	private FieldManager fieldManager;
	private GameController gameController;

	public Refuge(Outputable output, int bonus, int cardNo) {
		super(output, CardType.REFUGE);
		this.bonus=bonus;
		this.cardNo = cardNo;
	
	}
	
	@Override
	public void drawCard(Player player) {
		output.showCardMessage(player, cardNo);
		switch (bonus) {
		case 1000:
			player.deposit(1000);
			output.showDepositMessage(player.getName(), bonus);
			break;
		case 200:
			player.deposit(200);
			output.showDepositMessage(player.getName(), bonus);
			break;
		case 500:
			player.deposit(500);
			output.showDepositMessage(player.getName(), bonus);
			break;
		case 3000:
			player.deposit(3000);
			output.showDepositMessage(player.getName(), bonus);
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

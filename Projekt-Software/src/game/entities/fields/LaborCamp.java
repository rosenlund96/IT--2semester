package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;
import game.util.DieCup;

public class LaborCamp extends AbstractOwnable {


	private DieCup dices;		// for rent calculation 

	public LaborCamp(FieldManager fieldManager, int price, int baseRent, Outputable output ) {
		super(fieldManager, FieldType.LABOR_CAMP, price, baseRent, output);
		dices = new DieCup();
	}
	
	@Override
	public void landOnField(Player player){
		
		// checks if the field is owned
		if (this.owner == null) {
			super.landOnField(player);
		}
		
		// checks if the player is the owner if not. pay rent
		else if (this.owner != player) {
			// check how many LABOR CAMPS owner has
			int fieldsOwned = fieldManager.getFieldsOwned(owner, FieldType.LABOR_CAMP);
			output.showRollingDiceForRent(player.getName());
			
			int amountToPay = dices.roll() * rent * fieldsOwned; 
			output.setDice(dices.getDice());
			transferRent(amountToPay, player);
		}
		// checks if the player is the owner
		else if (this.owner == player) {
			output.showPlayerIsOwner(player.getName());

		}
	}
	
	
	// method to transfer money from the player to the owner of the field
	private void transferRent(int amountToPay, Player player){
		// withdraws the due rent or the rest of the players balance if he/she can't afford it 
		int withdrawAmount = player.withdraw(amountToPay);
		// deposits the withdrawed money
		owner.deposit(withdrawAmount);
		output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount);
	}
	
	@Override
	public String toString(){
		return super.toString() + ", baserent=" + rent;
	}
}

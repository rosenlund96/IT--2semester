package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Territory extends AbstractOwnable {

	public Territory(FieldManager fieldManager, int price, int rent, Outputable output) {
		super(fieldManager, FieldType.TERRITORY, price, rent, output);
	}	

	@Override
	public void landOnField(Player player){

		// If owner is null, call Ownable.landonfield for option to buy the field
		if (this.owner == null) {
			super.landOnField(player);
		}

		else if (this.owner != player) 
		{	// if player is not owner. player pay rent logic
			transferRent(player);
		}
		else // Player is owner and should not pay rent
			output.showPlayerIsOwner(player.getName());
	}


	/**
	 * Transfer rent from player to owner
	 * @param player the player which should transfer the money to the owner
	 */
	private void transferRent(Player player){
		int withdrawAmount = player.withdraw(rent);
		owner.deposit(withdrawAmount);
		output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount);
	}

	@Override
	public String toString(){
		return super.toString()+ ", rent="+rent;
	}
}

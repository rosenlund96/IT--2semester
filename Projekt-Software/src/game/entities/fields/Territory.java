package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;
import game.resources.FieldData;

public class Territory extends AbstractOwnable {
	
	int houseCount;
	int fieldNo;

	public Territory(FieldManager fieldManager, int price, int rent, int fieldNo, Outputable output) {
		super(fieldManager, FieldType.TERRITORY, price, rent, output);
		houseCount = 0;
		this.fieldNo = fieldNo;
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
		switch (houseCount) {
		case 1:
			int withdrawAmount1 = FieldData.FIELDRENT2_DATA[fieldNo];
			player.withdraw(withdrawAmount1);
			owner.deposit(withdrawAmount1);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount1);
			break;
		case 2:
			int withdrawAmount2 = FieldData.FIELDRENT3_DATA[fieldNo];
			player.withdraw(withdrawAmount2);
			owner.deposit(withdrawAmount2);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount2);
			break;
			
		case 3:
			int withdrawAmount3 = FieldData.FIELDRENT4_DATA[fieldNo];
			player.withdraw(withdrawAmount3);
			owner.deposit(withdrawAmount3);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount3);
			break;
			
		case 4:
			int withdrawAmount4 = FieldData.FIELDRENT5_DATA[fieldNo];
			player.withdraw(withdrawAmount4);
			owner.deposit(withdrawAmount4);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount4);
			break;
		case 5:
			int withdrawAmount5 = FieldData.FIELDRENT6_DATA[fieldNo];
			player.withdraw(withdrawAmount5);
			owner.deposit(withdrawAmount5);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount5);

		default:
			int withdrawAmount = player.withdraw(rent);
			owner.deposit(withdrawAmount);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount);
			break;
		}
	}

	@Override
	public String toString(){
		if (houseCount==1){
			return super.toString() + ", rent =" + FieldData.FIELDRENT2_DATA[fieldNo];
		}
		else if(houseCount==2){
			return super.toString() + ", rent =" + FieldData.FIELDRENT3_DATA[fieldNo];
		}
		else if(houseCount==3){
			return super.toString() + ", rent =" + FieldData.FIELDRENT4_DATA[fieldNo];
		}
		else if(houseCount==4){
			return super.toString() + ", rent =" + FieldData.FIELDRENT5_DATA[fieldNo];
}
		else if(houseCount==5){
			return super.toString() + ", rent =" + FieldData.FIELDRENT6_DATA[fieldNo];
}
		return super.toString()+ ", rent=" + rent;
	}
}

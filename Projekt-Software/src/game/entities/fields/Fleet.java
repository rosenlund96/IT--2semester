package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Fleet extends AbstractOwnable {

	private final int RENT_1 = 500;
	private final int RENT_2 = 1000;
	private final int RENT_3 = 2000;
	private final int RENT_4 = 4000;

	public Fleet(FieldManager fieldManager, int price, Outputable output) {
		super(fieldManager, FieldType.FLEET, price, 500, output);

	}

	@Override
	public void landOnField (Player player) {
		if (owner == null)
			super.landOnField(player);
		else if (player != owner){
			int fieldsOwned = fieldManager.getFieldsOwned(owner, FieldType.FLEET);
			if (fieldsOwned == 1){
				transferRent(RENT_1, player);
			}
			else if(fieldsOwned == 2){
				transferRent(RENT_2, player);
			}
			else if(fieldsOwned == 3){
				transferRent(RENT_3, player);
			}
			else if(fieldsOwned == 4){
				transferRent(RENT_4, player);
			}
		}
		else if (player == owner) {
			output.showPlayerIsOwner(player.getName());
		}
	}

	private void transferRent(int rentAmount, Player player){
		int withdrawAmount = player.withdraw(rentAmount);
		owner.deposit(withdrawAmount);
		output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount);
	}

	@Override
	public String toString(){
		int fieldsOwned = 0;
		if(owner != null)
			fieldsOwned = fieldManager.getFieldsOwned(owner, FieldType.FLEET);

		int currentRent = RENT_1;

		switch(fieldsOwned){
		case 1:
			currentRent = RENT_1;
			break;
		case 2: 
			currentRent = RENT_2;
			break;
		case 3:
			currentRent = RENT_3;
			break;
		case 4: 
			currentRent = RENT_4;
			break;
		}
		
		return super.toString() + ", rent=" + currentRent;

	}
}

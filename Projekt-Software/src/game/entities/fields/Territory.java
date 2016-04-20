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
		else if (this.owner==player) {// Player is owner and should not pay rent
			output.showPlayerIsOwner(player.getName());
			int choice = output.promptAction(player.getName());
			if(choice==1){
				buyPropertys(player);
			}
			else if(choice==2){
					sellPropertys(player);
			}
			else if(choice==3){
			}
		}
	}

	private void sellPropertys(Player player) {
		if(this.owner==player){
			if(houseCount==0){
			output.showNoPropertys(player.getName());
			this.landOnField(player);
			}
			
			
			if(houseCount<=4){
				boolean choice = output.promptSellProperty(player.getName(), FieldData.FIELDPROPERTYBUY_DATA[fieldNo-1]/2);
			if(choice==true){
				houseCount--;	
				output.setHouse(houseCount,fieldNo);
				this.landOnField(player);
			}
		}
			else if(houseCount==5){
				boolean choice = output.promptSellProperty(player.getName(), FieldData.FIELDPROPERTYBUY_DATA[fieldNo-1]/2);
				if(choice==true){
					houseCount--;	
					output.setHotel(false,fieldNo);
					this.landOnField(player);
				}
				
			}
	}	
	}

	//Gives the player the ability to buy properties
	private void buyPropertys(Player player){
		int price = FieldData.FIELDPROPERTYBUY_DATA[fieldNo-1];
		if(this.owner==player){
			if(houseCount<=3){
				boolean choice = output.promptBuyProperty(player.getName(), price);
			if(choice==true){
				player.withdraw(price);
				houseCount++;
				output.setHouse(houseCount,fieldNo);
				this.landOnField(player);
			}
		}
			else if(houseCount==4){
				boolean choice = output.promptBuyProperty(player.getName(), FieldData.FIELDPROPERTYBUY_DATA[fieldNo-1]);
				if(choice==true){
					
					houseCount++;	
					output.setHotel(true, fieldNo);
				}
				
			}
	}
		else{
			output.showEnoughPropertys(player.getName());
		}
	}
	
	public void sellField(Player player){
		int price = FieldData.FIELDPROPERTYBUY_DATA[fieldNo-1];
		if(this.owner==player){
			if(houseCount!=0){
				output.showHousesOnFieldMessage(player);
			}
			else if(houseCount==0){
				boolean choice = output.promptSellFields(player);
				if(choice==true){
					this.owner=null;
					player.deposit(price/2);
				}
				else if(choice==false){
					output.showDontSell(player);
				}
			}
		}
		}
		
	

	/**
	 * Transfer rent from player to owner
	 * @param player the player which should transfer the money to the owner
	 */
	private void transferRent(Player player){
		switch (houseCount) {
		case 1:
			int withdrawAmount1 = FieldData.FIELDRENT2_DATA[fieldNo-1];
			player.withdraw(withdrawAmount1);
			owner.deposit(withdrawAmount1);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount1);
			break;
		case 2:
			int withdrawAmount2 = FieldData.FIELDRENT3_DATA[fieldNo-1];
			player.withdraw(withdrawAmount2);
			owner.deposit(withdrawAmount2);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount2);
			break;
			
		case 3:
			int withdrawAmount3 = FieldData.FIELDRENT4_DATA[fieldNo-1];
			player.withdraw(withdrawAmount3);
			owner.deposit(withdrawAmount3);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount3);
			break;
			
		case 4:
			int withdrawAmount4 = FieldData.FIELDRENT5_DATA[fieldNo-1];
			player.withdraw(withdrawAmount4);
			owner.deposit(withdrawAmount4);
			output.showTransferMessage(player.getName(), owner.getName(), withdrawAmount4);
			break;
		case 5:
			int withdrawAmount5 = FieldData.FIELDRENT6_DATA[fieldNo-1];
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
			return super.toString() + ", rent =" + FieldData.FIELDRENT2_DATA[fieldNo-1];
		}
		else if(houseCount==2){
			return super.toString() + ", rent =" + FieldData.FIELDRENT3_DATA[fieldNo-1];
		}
		else if(houseCount==3){
			return super.toString() + ", rent =" + FieldData.FIELDRENT4_DATA[fieldNo-1];
		}
		else if(houseCount==4){
			return super.toString() + ", rent =" + FieldData.FIELDRENT5_DATA[fieldNo-1];
}
		else if(houseCount==5){
			return super.toString() + ", rent =" + FieldData.FIELDRENT6_DATA[fieldNo-1];
}
		return super.toString()+ ", rent=" + rent;
	}
}

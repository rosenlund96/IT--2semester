package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public abstract class AbstractOwnable extends AbstractField {

	protected int price;
	protected int rent; 
	protected Player owner;

	public AbstractOwnable(FieldManager fieldManager, FieldType fieldType, int price, int rent ,Outputable output) {
		super(fieldManager, fieldType, output);
		this.price = price;
		this.rent = rent; 
		this.owner = null;
	}

	public int getPrice(){
		return price;
	}

	public Player getOwner(){
		return owner; 
	}

	public int getRent(){
		return rent;
	}

	/****************************************************************************
	 * Function can only be called by an inheriting class						*
	 * If Field has no owner. Player can buy the field if he has enough money	*
	 ***************************************************************************/
	@Override
	public void landOnField(Player player){
		// Is the field owned by anyone?
		if(this.owner == null){
			// Do the player have enough money to buy the field
			if (player.getBalance()>price){

				// Prompt player whether he wants to buy or not
				if(output.promptBuy(player.getName(), price)){
					player.withdraw(price);	
					owner = player;
					output.showFieldBoughtMessage(player.getName(), fieldManager.getFieldNumber(this));

				}else
					// Display info telling player declined buy
					output.showNotBoughtMessage(player.getName());
			}else
				// displays info telling player haven't got money enough
				output.showNotEnoughBalanceMessage(player.getName());
		}

	}

	public void clearOwner(){
		output.removeOwner(fieldManager.getFieldNumber(this));
		owner = null;
	}
	
	@Override
	public String toString(){
		String str = super.toString() + ", " + "price=" + price;
		if(owner != null)
			str += ", owner=" + owner.getName();
		
		return str;
	}
	

}

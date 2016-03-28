package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Refuge extends AbstractField {

	private int bonus; 

	public Refuge(FieldManager fieldManager, int bonus, Outputable output) {
		super(fieldManager, FieldType.REFUGE, output);
		this.bonus = bonus;
	}

	@Override
	public void landOnField(Player player) {
		// deposits amount specified in bonus the the player landed on this field
		player.deposit(bonus);		
		
		// outputs result of deposit
		output.showDepositMessage(player.getName(), bonus);
	}
	
	public int getBonus(){
		return bonus;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", bonus=" + bonus;
	}
}

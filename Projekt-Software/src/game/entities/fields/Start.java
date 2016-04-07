package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Start extends AbstractField {
	private int bonus = 4000;

	public Start(FieldManager fieldManager, int bonus, Outputable output) {
		super(fieldManager, FieldType.START, output);
		this.bonus = bonus;
		
	}

	public void landOnField(Player player) {
		player.deposit(bonus);
		
	}

}


package game.entities.fields;

import javax.management.modelmbean.DescriptorSupport;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Start extends AbstractField {
	private int bonus = 4000;
	private int cardNo;

	public Start(FieldManager fieldManager, int bonus, int cardNo, Outputable output) {
		super(fieldManager, FieldType.START, output);
		this.bonus = bonus;
		this.cardNo = cardNo;
		
	}

	public void landOnField(Player player) {
		if(cardNo==1){
		player.deposit(bonus);
		}
		if(cardNo==21){
			output.showParkingMessage(player);
		}
		
	}

}


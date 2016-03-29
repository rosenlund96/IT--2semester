package game.entities.fields;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;

public class Prison extends AbstractField{
	
	private int fine;
	private Player player;

	public Prison(FieldManager fieldManager, int fine, Outputable output) {
		super(fieldManager,FieldType.PRISON, output);
		this.fine = fine;
		// TODO Auto-generated constructor stub
	}

	public void setParking(boolean isParking){
		player.setParking(isParking);
	}
	public boolean getParking(){
		return player.getParking();
	}
	public void setImprisoned(boolean isImprisoned){
		player.setImprisoned(isImprisoned);
	}
	public boolean getImprisoned(){
		return player.getImprisoned();
	}
	public void payFine(){
		player.withdraw(fine);
		output.showWithdrawMessage(player.getName(), fine);
		setImprisoned(false);
	}
	
	
	
	@Override
	public void landOnField(Player player) {
		// TODO Auto-generated method stub
		//Spilleren skal her have valget imellem at kaster terninger, vente eller betale bøden
		
	}

	
}

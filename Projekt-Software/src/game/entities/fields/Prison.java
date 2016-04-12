package game.entities.fields;

import game.boundaries.Outputable;
import game.controllers.GameController;
import game.entities.FieldManager;
import game.entities.Player;
import game.util.DieCup;

public class Prison extends AbstractField{
	
	private int fine;
	private DieCup dices;
	private int choice;
	GameController controller;

	public Prison(FieldManager fieldManager, int fine, Outputable output) {
		super(fieldManager,FieldType.PRISON, output);
		this.fine = fine;
		dices = new DieCup();
		// TODO Auto-generated constructor stub
	}

	public void setParking(boolean isParking, Player player){
		player.setParking(isParking);
	}
	public void setImprisoned(boolean isImprisoned, Player player){
		player.setImprisoned(isImprisoned);
	}
	public void payFine(Player player){
		player.withdraw(fine);
		output.showWithdrawMessage(player.getName(), fine);
		setImprisoned(false, player);

	}
	public void settimeInPrison(int timeInPrison, Player player) {
		player.settimeInPrison(timeInPrison);
	}
	public void setoutOfJailCard(int outOfJailCard, Player player){
		player.setoutOfJailCard(outOfJailCard);
	}



	public void initializeChoice(int choice, Player player){
	switch(choice){
		case 1:
			payFine(player);
			settimeInPrison(0, player);
		break;
		case 2:
			dices.roll();
			if (dices.getDie1()==dices.getDie2()) {
				setImprisoned(false, player);
				settimeInPrison(0, player);
				
			}
			else settimeInPrison(player.gettimeInPrison()+1, player);
		break; 
		case 3:
			if (player.getoutOfJailCard()>0)	{
				setImprisoned(false, player);
				settimeInPrison(0, player);
				setoutOfJailCard(player.getoutOfJailCard()-1, player);
			}
			else {
				output.showNoCardMessage(player.getName());
				this.landOnField(player);
			}
		break;
	}
	}
	//fuck this shit
	
	
	@Override
	public void landOnField(Player player) {
		if (player.gettimeInPrison()>=3)	{
		payFine(player);
		setImprisoned(false, player);
		settimeInPrison(0, player);
	}
	else {
		output.PromptPrison(player.getName(),fine,choice);
		initializeChoice(choice, player);
		//Spilleren skal her have valget imellem at kaster terninger, vente, benytte slip ud af fængsel kort eller betale bøden
		
	}
	}
	
}

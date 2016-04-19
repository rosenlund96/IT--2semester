package game.entities.fields;

import game.boundaries.Outputable;
import game.controllers.GameController;
import game.entities.FieldManager;
import game.entities.GameBoard;
import game.entities.Player;
import game.util.DieCup;

public class Prison extends AbstractField{
	
	private int fine;
	private DieCup dices;
	private int fieldNo;
	GameBoard board;

	public Prison(FieldManager fieldManager, Outputable output, int fieldNo) {
		super(fieldManager,FieldType.PRISON, output);
		dices = new DieCup();
		fine = 1000; 
		this.fieldNo = fieldNo;
		
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



	public void initializeChoice(Player player){
		int choice = output.PromptPrison(player.getName());
		switch(choice){
		case 1:
			payFine(player);
			settimeInPrison(0, player);
			setImprisoned(false, player);
		break;
		case 2:
			output.showRollingDiceForRent(player.getName());
			output.setDice(dices.getDice());
			
			if (dices.getDie1()==dices.getDie2()) {
				setImprisoned(false, player);
				settimeInPrison(0, player);
				
				
			}
			else settimeInPrison(player.gettimeInPrison()+1, player);
			    
			    
		break; 
		case 3:
			if (player.getoutOfJailCard()==0)	{
				output.showNoCardMessage(player.getName());
				initializeChoice(player);
			}
			else if(player.getoutOfJailCard()>0) {
				setImprisoned(false, player);
				settimeInPrison(0, player);
				setoutOfJailCard(player.getoutOfJailCard()-1, player);
			}
		break;
		case 4:
			settimeInPrison(player.gettimeInPrison()+1, player);
			output.showDoTimeMessage(player);
			break;
	}
	}
	
	
	@Override
	public void landOnField(Player player) {
		if(fieldNo==11){
		player.settimeInPrison(player.gettimeInPrison()+1);
		setImprisoned(true, player);
		initializeChoice(player);
		}
	else {
		setImprisoned(false, player);
		settimeInPrison(0, player);
		
	}
	}	
	}

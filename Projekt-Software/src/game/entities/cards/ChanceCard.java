package game.entities.cards;

public class ChanceCard {

	private String cardText;
	private int movePlayer;
	private int moveToPosition;
	private int moneyAmount;
	boolean jailCard;
	boolean jail;
	
	public ChanceCard(String cardText, int movePlayer, int moveToPosition, int moneyAmount, boolean jailCard, boolean jail){
		this.cardText = cardText;
		this.movePlayer = movePlayer;
		this.moveToPosition = moveToPosition;
		this.moneyAmount = moneyAmount;
		this.jailCard = jailCard;
		this.jail = jail;
	}
	
	public String getCardText(){
		return cardText;
	}
	
	public int getMoneyAmount(){
		return moneyAmount;
	}
	
	public int getMovePlayer(){
		return movePlayer;
	}
	
	public int getPosition(){
		return moveToPosition;
	}
	
	
	
	public boolean getJailCard(){
		return jailCard;
	}
	
	public boolean getJail(){
		return jail;
	}
	
	
	
}
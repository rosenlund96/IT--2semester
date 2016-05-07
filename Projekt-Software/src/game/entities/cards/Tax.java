package game.entities.cards;


import game.boundaries.Outputable;
import game.entities.Player;


public class Tax extends AbstractCard {

	private int taxAmount;
	private int cardNo;
	private static String text;
	
	
	public Tax(Outputable output, int taxAmount, int cardNo) {
		super(output, CardType.TAX, text);
		this.taxAmount = taxAmount;
		this.cardNo=cardNo;
		
	}
	
	public int getCardNo(){
		return cardNo;
	}

	@Override
	public void drawCard(Player player) {
		
		switch (cardNo) {
		case 5:
			collectTax(200, player);
			break;
			
		case 12:
			collectTax(1000, player);
			break;
			
		case 6:
			collectTax(2000, player);
			break;
			
		case 26:
			collectTax(3000, player);
			break;
		case 32:
			collectTax(1000, player);
			break;
		case 33:
			collectTax(200, player);
			break;
			
			//Her mangler metoder for kort omkring olieskatter og ejendomsskatter
		}
		
		
		
		
		
		
	}

	
	/************************************************************
	 * Withdraws an amount of money from the players balance	*
	 ***********************************************************/
	private void collectTax(int amount, Player player){
		player.withdraw(amount);
	}
	
	public int getTaxAmount(){
		return this.taxAmount;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", tax=" + taxAmount;
	}
}

	
	


package game.entities;

public class Player {


	// Attributes

	private int position;
	private String name;
	private Balance balance;
	private boolean isBroke, isParking, isImprisoned; 

	// Constructors

	public Player(String name, int startingBalance, int position, boolean isBroke, boolean isParking, boolean isImprisoned){
		this.name = name;
		this.balance = new Balance(startingBalance);
		this.isBroke = false; 
		this.position = position;
		this.isParking = false;
		this.isImprisoned = false;
	}

	// Mutators

	public String getName(){
		return name;
	}

	public int getPosition(){
		return position;
	}

	public void setPosition(int position){
		this.position = position;		
	}

	public boolean getBroke(){
		return isBroke; 
	}

	private void setBroke(boolean isBroke){
		this.isBroke = isBroke;
	}
	public boolean getParking(){
		return isParking;
	}
	public void setParking(boolean isParking){
		this.isParking = isParking;
	}
	public boolean getImprisoned(){
		return isImprisoned;
	}
	public void setImprisoned(boolean isImprisoned){
		this.isImprisoned = isImprisoned;
	}
	
	public int getBalance(){
		return balance.getBalance();
	}

	public void deposit(int amount){
		balance.deposit(amount);
	}

	/*
	 * Removes the amount from balance set as a parameter.
	 * If this is not possible it returns the amount which was
	 * able to be withdrawn
	 */
	public int withdraw(int amount){
		int withdrawen = balance.withdraw(amount);
		
		// if withdrawen amount is less than asked for. player must be broke
		if(withdrawen < amount)
			this.setBroke(true);
		
		return withdrawen;
	}

	@Override
	public String toString() {
		return "Player [position=" + position + ", name=" + name + ", " + balance + ", isBroke=" + isBroke
				+ "]";
	}

}
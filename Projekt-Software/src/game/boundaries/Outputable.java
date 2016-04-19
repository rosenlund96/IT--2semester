package game.boundaries;

import game.entities.Player;

/************************************************************************
 * 																		*
 * Interface defining methods for input output to screen from game		*
 * 																		*
 ************************************************************************/
public interface Outputable {
	
	public void update(int[] dice, int pos, int balance, String playerName);

	public void addPlayer(String playerName, int balance, int playerNo);

	public void setDice(int[] dice);

	public void removeAllOwners();

	public void removeOwner(int fieldNumber);

	public void showUpdateMessage(String playerName, int pos);
	
	public void showWelcome();
	
	public void showStartingPlayer(String playerName);
	
	public void showWinner(String playerName);
	
	public void showWithdrawMessage(String playerName, int amount);
	
	public void showDepositMessage(String playerName, int bonus);
	
	public void showTransferMessage(String playerName, String ownerName, int amount);
	
	public void showNotEnoughBalanceMessage(String playerName);
	
	public void showBrokeMessage(String playerName);
	
	public void showNotBoughtMessage(String playerName);
	
	public void showFieldBoughtMessage(String playerName, int fieldNumber);
	
	public void showRollingDiceForRent(String playeName);
	
	public void showPlayerIsOwner(String playerName);
	
	public String promptPlayerName(int playerNumber, boolean error);
	
	public void promptRollDice(String playerName);
	
	public boolean promptTax(String playerName, int taxAmount, int percentAmount);
	
	public boolean promptBuy(String name, int price);
	
	public void initializeBoard();


	public int PromptPrison(String playerName);
	
	public void setHouse(int houseCount, int fieldNo);
	
	public void sellProperty();

	public void showNoCardMessage(String name);

	public void showGetPrisonCardMessage(Player player);

	public void showDoTimeMessage(Player player);

	void showCardMessage(Player player, int cardNo);

	public boolean promptBuyProperty(String name, int i);

	public void showEnoughPropertys(String name);

	public void showNotOwner(String name);

	public void setHotel(boolean hasHotel, int fieldNo);

	public int promptAction(String name);

	public boolean promptSellProperty(String name, int i);

	public void showNoPropertys(String name);

	public void showRollingDiceForPrison1(Player player, int die1, int die2);

	public void showRollingDiceForPrison2(Player player, int die1, int die2);

	public int promptGameState();
	
	






}
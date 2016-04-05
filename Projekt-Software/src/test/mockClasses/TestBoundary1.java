package test.mockClasses;

import game.boundaries.Outputable;

public class TestBoundary1 implements Outputable {

	@Override
	public boolean promptBuy(String name, int price) {
		// returning true for automatic buy in testing
		return true;
	}
	@Override
	public void update(int[] dice, int pos, int balance, String playerName) {
	
	}

	@Override
	public void showUpdateMessage(String playerName, int pos) {
		
	}

	@Override
	public void showWelcome() {
		
	}

	@Override
	public void showStartingPlayer(String playerName) {
		
	}

	@Override
	public void showWinner(String playerName) {
		
	}

	@Override
	public void showWithdrawMessage(String playerName, int amount) {
		
	}

	@Override
	public void showDepositMessage(String playerName, int bonus) {
		
	}

	@Override
	public void showTransferMessage(String playerName, String ownerName, int amount) {
	}

	@Override
	public void showNotEnoughBalanceMessage(String playerName) {
		
	}

	@Override
	public void showBrokeMessage(String playerName) {
		
	}

	@Override
	public void showNotBoughtMessage(String playerName) {
		
	}

	@Override
	public void showFieldBoughtMessage(String playerName, int fieldNumber) {
		
	}

	@Override
	public void showRollingDiceForRent(String playeName) {
		
	}

	@Override
	public void showPlayerIsOwner(String playerName) {
		
	}

	@Override
	public String promptPlayerName(int playerNumber, boolean error) {
		return null;
	}

	@Override
	public void promptRollDice(String playerName) {
		
	}

	@Override
	public boolean promptTax(String playerName, int taxAmount, int percentAmount) {
		return false;
	}



	@Override
	public void setDice(int[] dice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer(String playerName, int balance, int playerNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllOwners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOwner(int fieldNumber) {
		// TODO Auto-generated method stub
		
	}

}

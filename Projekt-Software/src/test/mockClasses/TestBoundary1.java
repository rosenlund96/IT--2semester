package test.mockClasses;

import game.boundaries.Outputable;
import game.entities.Player;

public class TestBoundary1 implements Outputable {

	@Override
	public boolean promptBuy(String name, int price) {
		// returning true for automatic buy in testing
		return true;
	}

	@Override
	public void update(int[] dice, int pos, int balance, String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer(String playerName, int balance, int playerNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDice(int[] dice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllOwners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOwner(int fieldNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showUpdateMessage(String playerName, int pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWelcome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showStartingPlayer(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWinner(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWithdrawMessage(String playerName, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDepositMessage(String playerName, int bonus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showTransferMessage(String playerName, String ownerName, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showNotEnoughBalanceMessage(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showBrokeMessage(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showNotBoughtMessage(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFieldBoughtMessage(String playerName, int fieldNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showRollingDiceForRent(String playeName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPlayerIsOwner(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String promptPlayerName(int playerNumber, boolean error) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void promptRollDice(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean promptTax(String playerName, int taxAmount, int percentAmount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initializeBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int PromptPrison(String playerName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHouse(int houseCount, int fieldNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showNoCardMessage(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showGetPrisonCardMessage(String player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDoTimeMessage(String player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showCardMessage(String player, int cardNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean promptBuyProperty(String name, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showEnoughPropertys(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHotel(boolean hasHotel, int fieldNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int promptAction(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean promptSellProperty(String name, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showNoPropertys(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int promptGameState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showImprisonedMessage(String name2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showCard(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean promptSellFields(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showHousesOnFieldMessage(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDontSell(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showParkingMessage(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String promptLoadAction(String[] games) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showPassStartMessage(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showNextPlayerTurn(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFieldOwners(String playerName, int fieldNumber) {
		// TODO Auto-generated method stub
		
	}
}

	
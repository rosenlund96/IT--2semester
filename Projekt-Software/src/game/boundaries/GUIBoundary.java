package game.boundaries;

import java.awt.Color;
import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Shipping;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_fields.Tax;
import desktop_resources.GUI;
import game.entities.Player;
import game.entities.fields.AbstractField.FieldType;
import game.resources.FieldData;
import game.util.XMLReader;

/**
 * Handles indirection between the supplied GUI and the game
 */
public class GUIBoundary implements Outputable{
	
	// Fields and cards
	XMLReader reader;
	Field[] fields;
	String[] cards;
	

	
	
	// Constructors
	public GUIBoundary(String langFilePath) {
		this.reader = new XMLReader(langFilePath);
		this.fields = new Field[FieldData.FIELDNAME_DATA.length];
		this.initializeBoard();
	}
	
	
	// Methods
	
	@Override
	public void setDice(int[] dice){
		GUI.setDice(dice[0], dice[1]);
	}
	
	@Override
	public void setHotel(boolean hasHotel, int fieldNo) {
		if(hasHotel==false){
			GUI.setHouses(fieldNo, 4);
			GUI.setHotel(fieldNo, false);
		}
		else if(hasHotel==true){
		GUI.setHouses(fieldNo, 0);
		GUI.setHotel(fieldNo, true);
		}
	}
	
	@Override
	public void setHouse(int houseCount, int fieldNo) {
		GUI.setHotel(fieldNo, false);
		GUI.setHouses(fieldNo, houseCount);
	}



	/****************************************************************
	 * Updating active player balance and position and displays 	*
	 *  the dice rolled by the player								*
	 ***************************************************************/
	@Override
	public void update(int[] dice, int pos, int balance, String playerName) {
		// Updating dice values
		GUI.setDice(dice[0], dice[1]);
		// Updating active player balance
		GUI.setBalance(playerName, balance);
		// Removing players current car
		GUI.removeAllCars(playerName);
		// Adding player car at updated position
		GUI.setCar(pos+1, playerName);	
	}
	
	/************************************************************************
	 * Adds a new player to the board. using balance, name, and player #	*
	 * 																		*
	 * @param playerName Name of player which should be added to the board	*
	 * @param balance Starting balance of player							*
	 * @param playerNumber 0-5, otherwise error will occur					*
	 ***********************************************************************/
	@Override
	public void addPlayer(String playerName, int balance, int playerNumber) {
		Color[] colors = {Color.BLUE, Color.WHITE, Color.MAGENTA, Color.YELLOW, Color.BLACK, Color.GREEN};
		Car car = new Car.Builder()
				.primaryColor(colors[playerNumber])
				.secondaryColor(colors[5-playerNumber]).build();
		GUI.addPlayer(playerName, balance, car);
		GUI.setCar(1, playerName);
	}

	@Override
	public void removeAllOwners(){
		for(int i = 0; i < fields.length;i++){
			fields[i].setTitle(String.valueOf(i+1));
			GUI.setHouses(i+1, 0);
			GUI.setHotel(i+1, false);
		}
	}

	@Override
	public void removeOwner(int fieldNumber){
		fields[fieldNumber].setTitle(String.valueOf(fieldNumber+1));
	}

	@Override
	public void showUpdateMessage(String playerName, int pos){
		String s1  = reader.getElement("positionUpdate", 0);
		String msg = playerName + ": " + s1 + " " + (pos+1);
		GUI.showMessage(msg);
	}

	@Override
	public void showWelcome() {
		GUI.showMessage(reader.getElement("welcome", 0));
	}

	@Override
	public void showStartingPlayer(String playerName){
		String s1 = reader.getElement("starting", 0);
		String msg = playerName + " " + s1;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showWinner(String playerName) {
		GUI.showMessage(playerName + " " + reader.getElement("winner", 0));

	}

	// Message used when money is taken from player
	@Override
	public void showWithdrawMessage(String playerName, int amount) {
		String s1 = reader.getElement("withdraw", 0);
		String s2 = reader.getElement("withdraw", 1);
		String msg = playerName + ": " + s1 + " " + amount + " " + s2;		
		GUI.showMessage(msg);
	}
	
	// Message used when money is given to player
	@Override
	public void showDepositMessage(String playerName, int bonus) {
		String s1 = reader.getElement("deposit", 0);
		String s2 = reader.getElement("deposit", 1);
		String msg = playerName + ": " + s1 + " " + bonus + " " + s2;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showNotEnoughBalanceMessage(String playerName) {
		String s1 = reader.getElement("lowBalance", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showTransferMessage(String playerName, String ownerName, int amount) {
		String s1 = reader.getElement("transfer", 0);
		String s2 = reader.getElement("transfer", 1);
		String msg = playerName + ": " + s1 + " " + amount + " " + s2 + " " + ownerName;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showNotBoughtMessage(String playerName) {
		String s1 = reader.getElement("declinedBuy", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);	
	}
	
	@Override
	public void showFieldBoughtMessage(String playerName, int fieldNumber) {
		fields[fieldNumber].setTitle(String.valueOf(fieldNumber+1) + " (" + playerName + ")");
		String s1 = reader.getElement("acceptedBuy", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}

	@Override
	public void showRollingDiceForRent(String playerName) {
		String s1 = reader.getElement("rollForRent", 0);
		String btnRoll = reader.getElement("roll", 0);
		String msg = playerName + ": " + s1;
		GUI.getUserButtonPressed(msg, btnRoll);
	}


	@Override
	public void showBrokeMessage(String playerName) {
		GUI.removeAllCars(playerName);
		String s1 = reader.getElement("broke", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showNoCardMessage(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void showGetPrisonCardMessage(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDoTimeMessage(Player player) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void showCardMessage(Player playerName, int cardNo) {
		String s1 = reader.getElement("cards", cardNo);
		String msg = playerName + ": " + s1;
		GUI.displayChanceCard(msg);
	}
	
	@Override
	public void showEnoughPropertys(String name) {
		String msg = name + ": " + reader.getElement("noMore", 0);
		GUI.showMessage(msg);
	}
	
	@Override
	public void showNoPropertys(String name) {
		String msg = name + ": " + reader.getElement("noMore", 1);
		GUI.getUserButtonPressed(msg, "Ok");
	}
	
	@Override
	public void showImprisonedMessage(String name2) {
		String s1 = reader.getElement("imprisoned", 0);
		String msg = name2 + ": " + s1;
		GUI.showMessage(msg);
	}

	@Override
	public void showCard(String text) {
		GUI.displayChanceCard(text);
		GUI.getUserButtonPressed("", "OK");
	}
	
	@Override
	public void showPlayerIsOwner(String playerName) {
		String s1 = reader.getElement("isOwner", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showHousesOnFieldMessage(Player player) {
		String s1 = reader.getElement("protertiesHere", 0);
		String msg = player + ": " + s1;
		GUI.showMessage(msg);
	}
	
	@Override
	public void showDontSell(Player player) {
		String s1 = reader.getElement("protertiesHere", 1);
		String msg = player + ": " + s1;
		GUI.showMessage(msg);
	}

	@Override
	public void showParkingMessage(Player player) {
		String s1 = reader.getElement("parking", 0);
		String msg = player + ": " + s1;
		GUI.showMessage(msg);
	}

	//Player prompt methods
	@Override
	public void promptRollDice(String playerName) {
		String s1 = reader.getElement("rollDice", 0);
		String btnRoll = reader.getElement("roll", 0);
		String msg = playerName + ": " + s1;
		GUI.getUserButtonPressed(msg, btnRoll);
	}

	@Override
	public String promptPlayerName(int playerNumber, boolean error) {
		String s1, s2, msg;
		if(error){
			msg = reader.getElement("promptName", 2);			
		}
		else{
			s1 = reader.getElement("promptName", 0);
			s2 = reader.getElement("promptName", 1);
			msg = s1 + " " + playerNumber + " " + s2;
		}
		return GUI.getUserString(msg);
	}

	@Override
	public boolean promptTax(String playerName, int taxAmount, int percentAmount) {
		String s1 = reader.getElement("taxChoice", 0);
		String s2 = reader.getElement("taxChoice", 1);
		String msg = playerName + ": " + s1 + "(" + percentAmount + ") " + s2 + " " + taxAmount;
		String btnPercent = reader.getElement("taxButton", 0) + "(" + percentAmount + ")";
		String btnTaxAmount = String.valueOf(taxAmount);
		String result = GUI.getUserButtonPressed(msg, btnPercent, btnTaxAmount);
		
		if(result.equals(btnPercent)){
			return true;	
			}
		return false;
	}
	
	@Override
	public boolean promptBuy(String playerName, int price) {
		String s1 = reader.getElement("buy", 0);
		String yes = reader.getElement("yes", 0);
		String no = reader.getElement("no", 0);
		String msg = playerName + ": " + s1 + " " + price;
		return GUI.getUserLeftButtonPressed(msg, yes, no);
	}
	
	@Override
	public int PromptPrison(String playerName) {
		//choice for prisoncard
		String msg = reader.getElement("prison", 0);
		String c1 = reader.getElement("prison", 1);
		String c2 = reader.getElement("prison", 2);
		String c3 = reader.getElement("prison", 3);
		String c4 = reader.getElement("prison", 4);
		String result = GUI.getUserButtonPressed(msg, c1,c2,c3,c4);
		
		if(result == c1){
			return 1;
		}
		else if(result == c1){
			return 2;
		}
		else if(result == c3){
			return 3;
		}
		else{
			return 4;
		}
	}
	
	@Override
	public boolean promptBuyProperty(String name, int i) {
		String s1 = reader.getElement("buy", 2);
		int price = i;
		String yes = reader.getElement("yes", 0);
		String no = reader.getElement("no", 0);
		String msg = name + ": " + s1 + " " + price;
		String result = GUI.getUserButtonPressed(msg, yes,no);
		if(result ==yes){
			return true;
		}
		else {
		return false;
		}
	}
	
	@Override
	public boolean promptSellProperty(String name, int i) {
		String s1 = reader.getElement("sell", 1);
		String yes = reader.getElement("yes", 0);
		String no = reader.getElement("no", 0);
		int price = i;
		String msg = name + ": " + s1 + " " + price;
		String result = GUI.getUserButtonPressed(msg, yes,no);
		
		if(result == yes){
			return true;
		}
		else {
			return false;
			}
	}
	
	@Override
	public int promptAction(String name) {
		String s1 = reader.getElement("action", 0);
		String msg = name + ": " + s1;
		String c1 = reader.getElement("buy", 1);
		String c2 = reader.getElement("sell", 0);
		String c3 = reader.getElement("nothing", 0);
		String result = GUI.getUserButtonPressed(msg, c1,c2, c3);
		
		if(result==c1){
			return 1;
		}
		else if(result==c2){
			return 2;
		}
		else {
			return 3;
		}
	}

	@Override
	public int promptGameState() {
		String s1 = reader.getElement("load", 0);
		String c1 = reader.getElement("load", 1);
		String c2 = reader.getElement("load", 2);
		String result = GUI.getUserButtonPressed(s1, c1,c2);
		
		if (result == c1) {
			return 1;
		}
		else{
			return 2;
		}
	}

	@Override
	public boolean promptSellFields(Player player) {
		String s1 = reader.getElement("sell", 2);
		String c1 = reader.getElement("yes", 0);
		String c2 = reader.getElement("no", 0);
		String msg = player + ": " + s1;
		String result = GUI.getUserButtonPressed(msg, c1,c2);
		
		if(result==c1){
			return true;
		}
		return false;
	}
	//Initialize text on GUI
	@Override
	public void initializeBoard() {
		
		for(int i = 0; i < FieldData.FIELDNAME_DATA.length; i++){
			switch(FieldData.FIELDTYPE_DATA[i]){
			case REFUGE:
				fields[i] = new Refuge.Builder().build();
				fields[i].setDescription(reader.getElement("get", 0) + " " + FieldData.FIELDRENT1_DATA[i]);
				break;
			case TAX:
				fields[i] = new Tax.Builder().build();
				fields[i].setDescription(reader.getElement("pay", 0) + " " + FieldData.FIELDRENT1_DATA[i]);
				break;
			case TERRITORY:
				fields[i] = new Street.Builder()
				.setBgColor(FieldData.fieldColor[i])
				.build();
				fields[i].setDescription(reader.getElement("ownable", 0) + " " + FieldData.FIELDBUYPRICE_DATA[i] +
						", " + reader.getElement("territory", 0) + " " +FieldData.FIELDRENT1_DATA[i]);
				break;
			case LABOR_CAMP:
				fields[i] = new Brewery.Builder().build();
				fields[i].setDescription(reader.getElement("ownable", 0) + " " + FieldData.FIELDBUYPRICE_DATA[i] +
						", " + reader.getElement("laborCamp", 0));
				break;
			case FLEET:
				fields[i] = new Shipping.Builder().build();
				fields[i].setDescription(reader.getElement("ownable", 0) + " " + FieldData.FIELDBUYPRICE_DATA[i] +
						", " + reader.getElement("fleet", 0));
				break;
			case PRISON:
				fields[i] = new Jail.Builder().build();
				fields[i].setDescription(reader.getElement("fine", 0)+ " " + "Kr. 1000");
				break;
			case START:
				fields[i] = new Start.Builder().build();
				fields[i].setDescription(reader.getElement("bonus", 0) + " " + "Kr. 4000");
				break;
			
			case LUCKYCARD:
				fields[i] = new Chance.Builder().build();
				fields[i].setDescription(reader.getElement("lucky", 0));
				break;
			}
			fields[i].setTitle(String.valueOf(i+1));
			fields[i].setSubText(reader.getElement(FieldData.FIELDNAME_DATA[i], 0));
		}
		GUI.create(fields);
	}
}

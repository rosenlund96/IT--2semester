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

	/***********************************************
	 * Displays the dices in the GUI for the player*
	 ***********************************************/

	@Override
	public void setDice(int[] dice){
		GUI.setDice(dice[0], dice[1]);
	}
	/************************************************************************
	 * Sets a hotel on the GUI, if the player has enough houses on the field*
	 * 																		*
	 * @param hasHotel boolean to show if a hotel i present                 *
	 * @param fieldNo fieldNo for the field to set a hotel                  *
	 ************************************************************************/
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
	/**********************************************************************
	 * Displays a specific amount of houses on a specific field on the GUI*
	 * 																	  *	
	 * @param houseCount the amount of houses to display                  *
	 * @param fieldNo the specific field to show the houses on            *
	 **********************************************************************/
	@Override
	public void setHouse(int houseCount, int fieldNo) {
		GUI.setHotel(fieldNo, false);
		GUI.setHouses(fieldNo, houseCount);
	}

	/****************************************************************
	 * Updating active player balance and position and displays 	*
	 * the dice rolled by the player								*
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

	/************************************************************************
	 * Clears all owners from the interactive gameboard.					*
	 ***********************************************************************/
	@Override
	public void removeAllOwners(){
		for(int i = 0; i < fields.length;i++){
			fields[i].setTitle(String.valueOf(i+1));
			GUI.setHouses(i+1, 0);
			GUI.setHotel(i+1, false);
		}
	}

	/************************************************************************
	 * Removes the owner of a specific field so that the field is empty		*
	 * and can be bought by another player.									*
	 * 																		*
	 * @param fieldNumber The number of the field 1-40						*
	 ***********************************************************************/
	@Override
	public void removeOwner(int fieldNumber){
		fields[fieldNumber].setTitle(String.valueOf(fieldNumber+1));
	}

	/************************************************************************
	 * Shows a message in the interface which is the playername and the		*
	 * position of that player												*
	 * 																		*
	 * @param playerName Name of active player who has moved				*
	 * @param pos The position where the player is on the board				*		
	 ***********************************************************************/
	@Override
	public void showUpdateMessage(String playerName, int pos){
		String s1  = reader.getElement("positionUpdate", 0);
		String msg = playerName + ": " + s1 + " " + (pos+1);
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a welcome message when the application have opened.			*
	 ***********************************************************************/
	@Override
	public void showWelcome() {
		GUI.showMessage(reader.getElement("welcome", 0));
	}

	/************************************************************************
	 * Shows a message stating who is the starting player.					*
	 * 																		*
	 * @param playerName Name of player who is starting						*
	 ***********************************************************************/
	@Override
	public void showStartingPlayer(String playerName){
		String s1 = reader.getElement("starting", 0);
		String msg = playerName + " " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a message with the last player standing in the game and who	*
	 * therefore is the winner of the game.									*
	 * 																		*
	 * @param playerName Name of player who won the game					*
	 ***********************************************************************/
	@Override
	public void showWinner(String playerName) {
		GUI.showMessage(playerName + " " + reader.getElement("winner", 0));

	}
	
	/************************************************************************
	 * Shows a message with the player whose turn it is next. This 			*
	 * method doesn't have any real function other than that.				*
	 * 																		*
	 * @param name Name of the player who is next in throwing the dice.		*
	 ***********************************************************************/
	public void showNextPlayerTurn(String name){
		String s1 = reader.getElement("doNothing", 0);
		String msg = name + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a message in the interface with the amount of money that 		*
	 * has been withdrawn from a players account for one of several			*
	 * reasons.																*
	 * 																		*
	 * @param playerName Name of player whose account is withdrawn from		*
	 * @param amount Amount of money that has been withdrawn				*
	 ***********************************************************************/
	@Override
	public void showWithdrawMessage(String playerName, int amount) {
		String s1 = reader.getElement("withdraw", 0);
		String s2 = reader.getElement("withdraw", 1);
		String msg = playerName + ": " + s1 + " " + amount + " " + s2;		
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a massage in the interface with the amount of money that has	*
	 * been deposit into a players account for one of several reasons. 		*
	 * 																		*
	 * @param playerName Name of player whose account is deposit into		*
	 * @param amount Amount of money that has been deposit					*
	 ***********************************************************************/
	@Override
	public void showDepositMessage(String playerName, int bonus) {
		String s1 = reader.getElement("deposit", 0);
		String s2 = reader.getElement("deposit", 1);
		String msg = playerName + ": " + s1 + " " + bonus + " " + s2;
		GUI.showMessage(msg);
	}
	
	/************************************************************************
	 * Shows a message saying that a player hit a double if the two 
	 * dice shows identical number of eyes.
	 * 
	 * @param name Name of player rolling the double.
	 ***********************************************************************/
	@Override
	public void showIsDoublesMessage(String name){
		String s1 = reader.getElement("doubles", 0);
		String msg = name + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a message which says that there are not enough money in the	*
	 * account to spend on a certain item.									*
	 * 																		*
	 * @param playerName Name of player that doesn't have enough money		*
	 ***********************************************************************/
	@Override
	public void showNotEnoughBalanceMessage(String playerName) {
		String s1 = reader.getElement("lowBalance", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a massage that money has been transferred from one players 	*
	 * account to another players account									*
	 * 																		*
	 * @param playerName Name of player who's having money taken from him	*
	 * @param ownerName Name of player who owns the field and is having		*
	 * 			money transferred to his account.							*
	 * @param amount Amount of money being transferred between the players	*
	 ***********************************************************************/
	@Override
	public void showTransferMessage(String playerName, String ownerName, int amount) {
		String s1 = reader.getElement("transfer", 0);
		String s2 = reader.getElement("transfer", 1);
		String msg = playerName + ": " + s1 + " " + amount + " " + s2 + " " + ownerName;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a message saying that the player who is trying to buy a field	*
	 * didn't succeed in buying it.											*
	 * 																		*
	 * @param playerName Name of player not succeeding in buying a field	*
	 ***********************************************************************/
	@Override
	public void showNotBoughtMessage(String playerName) {
		String s1 = reader.getElement("declinedBuy", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);	
	}

	/************************************************************************
	 * Shows a message that a field has been bought.						*
	 * 																		*
	 * @param playerName Name of player that has just bought the field		*
	 * @param fieldNumber The number of the field that has been bought		*
	 ***********************************************************************/
	@Override
	public void showFieldBoughtMessage(String playerName, int fieldNumber) {
		fields[fieldNumber].setTitle(String.valueOf(fieldNumber+1) + " (" + playerName + ")");
		String s1 = reader.getElement("acceptedBuy", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}
	
	/************************************************************************
	 * This method will change the status of a field if it changes owner	*
	 * or is bought by a player.											*
	 * 																		*
	 * @param playerName Name of player getting ownership of the field.		*
	 * @param fieldNumber Identification number of field changing ownership	* 
	 ***********************************************************************/
	public void setFieldOwners(String playerName, int fieldNumber){
			fields[fieldNumber].setTitle(String.valueOf(fieldNumber+1) + " (" + playerName + ")");	
	}

	/************************************************************************
	 * Shows a message that the player have to roll the dice to find out 	*
	 * how much money he has to pay when he lands on another players 		*
	 * LaborCamp field.														*
	 * 																		*
	 * @param playerName Name of player who has to roll the dice.			*
	 ***********************************************************************/
	@Override
	public void showRollingDiceForRent(String playerName) {
		String s1 = reader.getElement("rollForRent", 0);
		String btnRoll = reader.getElement("roll", 0);
		String msg = playerName + ": " + s1;
		GUI.getUserButtonPressed(msg, btnRoll);
	}
	
	/************************************************************************
	 * Shows a message saying that the active player has passed 'start' and	*
	 * therefore will receive an amount of money.							*
	 * 																		*
	 * @param name Name of player passing the 'start'-field					*
	 ***********************************************************************/
	@Override 
	public void showPassStartMessage(String name){
		String s1 = reader.getElement("pass", 0);
		String msg = name + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a message that the active player has got no more money in 		*
	 * his account and therefore is broke.									*
	 * 																		*
	 * @param playerName Name of player who is broke						*
	 ***********************************************************************/
	@Override
	public void showBrokeMessage(String playerName) {
		GUI.removeAllCars(playerName);
		String s1 = reader.getElement("broke", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows a message saying that the player is still imprisoned as a 		*
	 * result of not having a get-out-of-jail card.							*
	 * 																		*
	 * @param name Name of player who is still imprisoned.					*
	 ***********************************************************************/
	@Override
	public void showNoCardMessage(String name) {
		String s1 = reader.getElement("imprisoned", 2);
		String msg = name + ": " + s1;
		GUI.showMessage(msg);
	}

	/*********************************************************************
	 * Show a message to the player telling that he rolled to equal dices*
	 *********************************************************************/
	@Override
	public void showFreeMessage(String name){
		String s1 = reader.getElement("free", 0);
		String msg = name = ": " + s1;
		GUI.showMessage(msg);
	}
	
	/****************************************************************************
	 * Show a message to the player telling that he  did not roll to equal dices*
	 ****************************************************************************/
	@Override
	public void showNotFreeMessage(String name){
		String s1 = reader.getElement("free", 0);
		String msg = name = ": " + s1;
		GUI.showMessage(msg);
	}
	/************************************************************************
	 * Shows a message that a player that has landed on a 'Prøv lykken'-	*
	 * field and got a get-out-of-jail card.								*
	 * 																		*
	 * @param player The player who has received a get-out-of-jail card		*
	 ***********************************************************************/
	@Override
	public void showGetPrisonCardMessage(String player) {
		String s1 = reader.getElement("imprisoned", 3);
		String msg = player + ": " + s1;
		GUI.showMessage(msg);

	}

	/************************************************************************
	 * Shows a message that the player has to go to prison.					*
	 * 																		*
	 * @param player The player that has been imprisoned.					*
	 ***********************************************************************/
	@Override
	public void showDoTimeMessage(String player) {
		String s1 = reader.getElement("imprisoned", 1);
		String msg = player + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Show a message with the 'Prøv Lykken' card and what it reads.		*
	 * Also what effect it will have on the player.							*
	 * 																		*
	 * @param playerName Name of player landing on the 'Prøv Lykken' field.	*
	 * @param cardNo What number the card has which is used to identify the	*
	 * 			card and its effect.										*
	 ***********************************************************************/
	@Override
	public void showCardMessage(String playerName, int cardNo) {
		String s1 = reader.getElement("cards", cardNo);
		String msg = playerName + ": " + s1;
		GUI.displayChanceCard(msg);
	}
	/*******************************************************************************
	 * The player already has a hotel, and cannot buy more properties for the field*
	 *******************************************************************************/
	@Override
	public void showEnoughPropertys(String name) {
		String msg = name + ": " + reader.getElement("noMore", 0);
		GUI.showMessage(msg);
	}
	/***********************************************************
	 * The player has no properties, and can derfefore not sell*
	 ***********************************************************/
	@Override
	public void showNoPropertys(String name) {
		String msg = name + ": " + reader.getElement("noMore", 1);
		GUI.getUserButtonPressed(msg, "Ok");
	}

	/************************************************************************
	 * Shows a message that a player has been put in prison.				*
	 * 																		*
	 * @param																*
	 ***********************************************************************/
	@Override
	public void showImprisonedMessage(String name2) {
		String s1 = reader.getElement("imprisoned", 0);
		String msg = name2 + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows the 'Prøv Lykken' card in the interface and will 				*
	 * display the effect and text of the card.								*
	 * 																		*
	 * @param text What is actually written on the card						*
	 ***********************************************************************/
	@Override
	public void showCard(String text) {
		GUI.displayChanceCard(text);
		GUI.getUserButtonPressed("", "OK");
	}

	/************************************************************************
	 * When a player lands on a field that is owned by himself this method	*
	 * will display a message saying that he owns it already.				*
	 * 																		*
	 * @param playerName Name of player who lands on a field he owns.		*
	 ***********************************************************************/
	@Override
	public void showPlayerIsOwner(String playerName) {
		String s1 = reader.getElement("isOwner", 0);
		String msg = playerName + ": " + s1;
		GUI.showMessage(msg);
	}

	/************************************************************************
	 * Shows how many houses are on a field in the interface				*
	 * 																		*
	 * @param player The player who owns the houses and therefore the field	* 
	 ***********************************************************************/
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

	/************************************************************************
	 * Shows a message that the active player has landed on the parking		*
	 * field.																*
	 * 																		*
	 * @param player The player that has landed on the parking field		*
	 ***********************************************************************/
	@Override
	public void showParkingMessage(Player player) {
		String s1 = reader.getElement("parking", 0);
		String msg = player + ": " + s1;
		GUI.showMessage(msg);
	}

	//Player prompt methods
	/************************************************************************
	 * The player has to roll the dice to move on the gameboard and this	*
	 * method will prompt that action on the interface.						*
	 *																		*
	 *	@param playerName Name of active player rolling the dice.			*
	 ***********************************************************************/
	@Override
	public void promptRollDice(String playerName) {
		String s1 = reader.getElement("rollDice", 0);
		String btnRoll = reader.getElement("roll", 0);
		String msg = playerName + ": " + s1;
		GUI.getUserButtonPressed(msg, btnRoll);
	}

	/************************************************************************
	 * This method will prompt the option to insert a playerName. It is		*
	 * created with an if-else statement where you have to insert a name.	*
	 * 																		*
	 * @param playerNumber The number that defines each player.				*
	 * @param error If there is an error with the insertion of name into	*
	 * 			the name box in the interface. 								*
	 ***********************************************************************/
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

	/************************************************************************
	 * This method will prompt a button on the interface when you land on 	*
	 * a field of the type tax. You will have the option to pay a certain 	*
	 * amount or a percentage of your account balance.						*
	 * 																		*
	 * @param playerName Name of player having to pay the tax.				*
	 * @param taxAmount The certain amount that the player can choose to pay*
	 * @param percentAmount The percentage amount of the balance the player	*
	 * 			can choose to pay.											*
	 ***********************************************************************/
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

	/************************************************************************
	 * This method will prompt a button so that the active player can buy 	*
	 * the field he has landed on, if it is not owned by another player.	*
	 * 																		*
	 * @param playerName Name of active player that wants to buy an 		*
	 * 			available field.											*
	 * @param price Amount of money that the field will cost to buy.		*
	 ***********************************************************************/
	@Override
	public boolean promptBuy(String playerName, int price) {
		String s1 = reader.getElement("buy", 0);
		String yes = reader.getElement("yes", 0);
		String no = reader.getElement("no", 0);
		String msg = playerName + ": " + s1 + " " + price;
		return GUI.getUserLeftButtonPressed(msg, yes, no);
	}

	/********************************************************
	 * Prompts the player to take a choice, when imprisoned.*
	 * Either pay the fine                                  *
	 * Roll the dice                                        *
	 * Use a jailcard                                       *
	 * or wait 3 rounds                                     *
	 * @param playerName the player on the field            *
	 ********************************************************/
	
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
		else if(result == c2){
			return 2;
		}
		else if(result == c3){
			return 3;
		}
		else{
			return 4;
		}
	}

	/************************************************************************
	 * This method will prompt a button in the interface where the active 	*
	 * player will have the opportunity to buy houses or a hotel for a		*
	 * specific field he owns.												*													*
	 * 																		*
	 * @param name															*
	 * @param i A price that varies from one field to another. Different 	*
	 * 			prices for all different fields.							*
	 ***********************************************************************/
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

	/************************************************************************
	 * This method will prompt a button which is used to sell property to 	*
	 * gain money to put in your account.									*
	 * 																		*
	 * @param name															*
	 * @param i The amount of money you will receive for selling the		*
	 * 			property on your field.										*			*
	 ***********************************************************************/
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

	/************************************************************************************************
	 * Prompts the player to take a choice whether he wants to sell, buy or do nothing on his field.*
	 ************************************************************************************************/
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

	/************************************************************************	
	 * This method will initiate the GameState after the names has been 	*
	 * entered in the NameState.											*
	 ***********************************************************************/
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

	/************************************************************************
	 * This method will load a saved game if there is one. It is loaded		*
	 * from an array of strings from the database.							*
	 * 																		*
	 * @param games An array of strings from the database games				*
	 ***********************************************************************/
	@Override
	public String promptLoadAction(String[] games){
		String s1 = reader.getElement("load", 3);
		String result = GUI.getUserSelection(s1, games);
		return result;
	}

	/************************************************************************
	 * This method will prompt an option to sell an active players owned	*
	 * field and will deposit the amount into the active players account.	*
	 * 																		*
	 * @param player The active player who is selling one of his fields		*
	 ***********************************************************************/
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
	/************************************************************************
	 * This method will initiate the gameboard in the interface. It will	*
	 * create the fields so that they are visible for the user. This method	*
	 * is essential for the entire visual experience of the game.			*
	 ***********************************************************************/
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
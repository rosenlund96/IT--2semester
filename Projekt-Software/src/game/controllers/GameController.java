package game.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import game.boundaries.*;
import game.entities.FieldManager;
import game.entities.GameBoard;
import game.util.DBHandler;
import game.util.DieCup;
import game.util.Rollable;

public class GameController {

	private final int STARTING_BALANCE = 30000;
	private final int STARTING_POSITION = 1;
	private final int STARTING_PRISONCARDS = 0;
	private final int STARTING_HOUSES = 0;
	private final int STARTING_HOTELS = 0;
	
	public enum GameState {LOAD_STATE, NAME_STATE , PLAY_STATE, WIN_STATE};

	public GameBoard board;
	public String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
	public DBHandler handler = new DBHandler();
	public String gameName;
	private FieldManager fieldManager;
	private int turnNumber = 0;
	private Outputable output;			// Gui controller to change the gui
	private Rollable dieCup;		// dieCup through the Rollable interface for easy change of dice
	public ArrayList<String> names;

	private GameState state = GameState.LOAD_STATE;

	public GameController(){
		String userHome = System.getProperty("user.home");
		output = new GUIBoundary(userHome+"/git/IT--2semester/Projekt-Software/resources/language2.xml");
		this.gameName = null;
		dieCup = new DieCup();	
		names = new ArrayList<String>();
		fieldManager = new FieldManager(output);
	}

	/*********************************************
	 * Loop running as long as you play the game *
	 *********************************************/
	public void run(){
		while (true){
			switch (state){
			case LOAD_STATE: loadState();
			break;
			case NAME_STATE: nameState();
			break;
			case PLAY_STATE: playState();
			break;
			case WIN_STATE: winState();
			break;

			}
		}
	}

	private void loadState(){
		int choice = output.promptGameState();
		//Make new game, and create DB.
		if (choice==1){
			try {
				this.gameName = handler.createGameDB(timeStamp);
				handler.createTables(gameName);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			state = GameState.NAME_STATE;
		}
		//load old game.
		else if(choice==2){
			int choice2 = output.promtLoadAction();
		}
	}
	
	
	
	/*********************************************
	 * Method used to get the players names 	 *
	 ********************************************/
	private void nameState(){
		// Shows a welcome message in the GUI
		//output.showWelcome();
		
		// Adds names to string array
		for (int i = 0; i < 6; i++){
			boolean error = false;
			// Checks if names are long enough and saves them in array
			while (true){
			
				String name = output.promptPlayerName(i+1, error);
				if (name.length() == 0){
					if(i>=3){
						// break loop (no more players)
						i = 6;
						break;
					}
					else
						error = true;
				}
				else{
					names.add(name);
					// Add player to gui
					output.addPlayer(name, STARTING_BALANCE, i);
					
					break;
				}				
	}	
		}

		// Creates the gameboard
		initBoard();

		// Set state back to play state
		state = GameState.PLAY_STATE;
	}


	/*******************************************************************
	 * Method used as the main playstate running until winner is found *
	 *******************************************************************/
	private void playState(){
		while (true){

			// If it is the first turn find starting player and show starting player
			if (turnNumber == 0){
				board.findStartingPlayer();
				output.showStartingPlayer(board.getActivePlayerName());
			}
			String name = board.isActivePlayerImprisoned();
			if(name != null){
			output.showImprisonedMessage(name);
			board.nextTurn();
			}
			
			// Prompts the GUI to show active player and get him to roll
			output.promptRollDice(board.getActivePlayerName());

			// Moves the active player on the board
			board.moveActivePlayer(dieCup.roll());
			
			
			// Updates the GUI
			output.update(dieCup.getDice(), board.getActivePlayerPosition(), board.getActivePlayerBalance(), board.getActivePlayerName());
			output.showUpdateMessage(board.getActivePlayerName(),  board.getActivePlayerPosition());
			board.activePlayerFieldAction();
			
			// Update GUI again.
			output.update(dieCup.getDice(), board.getActivePlayerPosition(), board.getActivePlayerBalance(), board.getActivePlayerName());
			
			// Check if active player is broke
			String name1 =board.isActivePlayerBroke();
			if(name1 != null){
				output.showBrokeMessage(name1);				
			}
			
			
			turnNumber++;
			
			// Changes turn
			handler.updatePlayerTable(timeStamp, board.getActivePlayerName(), board.getActivePlayerBalance(), board.getActivePlayerHouses(), board.getActivePlayerHotels(), board.getActivePlayerPrisonCards(),board.getActivePlayerPosition());
			handler.updateFieldTable(timeStamp, fieldManager.getFieldOwner(board.getActivePlayerPosition(),board.getActivePlayer()),board.getActivePlayerPosition(), fieldManager.getHouseCount(board.getActivePlayerPosition(),board.getActivePlayer()), fieldManager.getHotelCount(board.getActivePlayerPosition(), board.getActivePlayer()));
			
			board.nextTurn();
			
			// Check to see if we have a winner
			if (board.getWinner()){
				state = GameState.WIN_STATE;
				return;
			}		
		}
	
}
	/************************************
	 * Method used when winner is found *
	 ************************************/
	private void winState(){
		// Shows the winner
		output.showWinner(board.getActivePlayerName());
		//Drops the current game table, as the game is no longer active.
		handler.dropCurrentGameTable(gameName);
		
		// Updates GUI to show the new scores and set position to the first
		for(String name:names){
			output.update(dieCup.getDice(), 0, STARTING_BALANCE, name);
		}
		initBoard();
		turnNumber = 0;
		state = GameState.PLAY_STATE;
	}
	
	private void initBoard(){
		board = new GameBoard(names,STARTING_BALANCE, output);
		for (int i = 0; i < names.size(); i++) {
			handler.addToPlayerTable(timeStamp, names.get(i), STARTING_BALANCE, STARTING_HOUSES, STARTING_HOTELS, STARTING_PRISONCARDS,STARTING_POSITION);
		}
		for (int i = 0; i < fieldManager.NUMBER_OF_FIELDS; i++) {
			handler.addToFieldTable(timeStamp, null, fieldManager.fields[i].getFieldNo()-1, 0, 0);
		}
		
		output.removeAllOwners();
		
		
	}
}
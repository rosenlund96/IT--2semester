package game.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import game.boundaries.*;
import game.entities.GameBoard;
import game.entities.fields.Prison;
import game.util.DieCup;
import game.util.Rollable;

public class GameController {

	private final int STARTING_BALANCE = 30000;

	public enum GameState {LOAD_STATE, NAME_STATE , PLAY_STATE, WIN_STATE};

	public GameBoard board;

	private int turnNumber = 0;
	private Outputable output;			// Gui controller to change the gui
	private Rollable dieCup;		// dieCup through the Rollable interface for easy change of dice
	public ArrayList<String> names;

	private GameState state = GameState.LOAD_STATE;

	public GameController(){
		String userHome = System.getProperty("user.home");
		output = new GUIBoundary(userHome+"/git/IT--2semester/Projekt-Software/resources/language2.xml");
		
		
		dieCup = new DieCup();	
		names = new ArrayList<String>();
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
			state = GameState.NAME_STATE;
		}
		//load old game.
		else if(choice==2){
			
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
		output.removeAllOwners();
		
		
	}
}
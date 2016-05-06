package game.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import game.controllers.GameController;

public class DBHandler implements DBFunctions {
	GameController controller;
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
	String createDB = "CREATE DATABASE " + timeStamp;
	DBConnector con = new DBConnector();


	//Opretter en ny database og returner navnet på denne
	public String createGameDB(String timeStamp){
		try {
			con.doUpdate(createDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeStamp;
	}
	//Opretter de fornødne tabeller i databasen
	public void createTables(String gameName){
		createGame(gameName);
		createField(gameName);
		createPlayersList(gameName);
		createCards(gameName);





	}
	public void createGame(String gameName){
		//Gemmer alt fra GameController.
		String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".game " + 
				"(game_id varchar (255) NOT NULL, " + 
				"game_State varchar(45) NOT NULL, " +
				"turnNumber int (11) NOT NULL, " +
				"player_turn varchar (45) DEFAULT NULL, " +
				"PRIMARY KEY (game_id));");
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPlayersList(String gameName){
		String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".player_list " +
				"(playerName varchar(45) NOT NULL, " + 
				"playerIndex int (11) NOT NULL, " +
				"playerBalance int (11) DEFAULT NULL, " +
				"housesOwned int(2) DEFAULT NULL, " +
				"hotelsOwned tinyint (1) DEFAULT NULL, " + 
				"prisonCards int (1) DEFAULT NULL, " +
				"playerPosition int (2) DEFAULT NULL, " +
				"PRIMARY KEY (playerIndex));");

		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createField(String gameName){
		//Gemmer alt om felter.
		String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".field " +
				"(fieldNo int (11) NOT NULL," +
				"fieldOwner varchar (45) DEFAULT NULL, " +
				"houseOnField int(2) DEFAULT NULL, " + 
				"hotelOnField tinyint(1) DEFAULT NULL, " +
				"PRIMARY KEY (fieldNo));");
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void createCards(String gameName){
		String query =("CREATE TABLE IF NOT EXISTS " + gameName+".cards " +
				"(CardNo int (11) NOT NULL," +
				"CardIndex int (11) DEFAULT NULL, " +
				"CardOwner varchar (45) DEFAULT NULL, " + 
				"CardText varchar (4096) NOT NULL, " +
				"PRIMARY KEY (CardIndex));");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void addToFieldTable(String gameName, String playerName, int fieldNo, int housesOnField, int hotelsOnField) {
		String query = ("INSERT INTO " + gameName+".field(fieldNo, fieldOwner, houseOnField, hotelOnField)" +
						"VALUES('"+fieldNo+"','"+playerName+"','"+housesOnField+"','"+hotelsOnField+"');");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//adds player to table when gameBoard is initialized
	public void addToPlayerTable(String gameName, int playerIndex,String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition){
		String query = ("INSERT INTO " + gameName+".player_list(playerName, playerIndex,playerBalance, housesOwned, hotelsOwned, prisonCards, playerPosition)" +
						"VALUES('"+playerName+"','"+playerIndex+"','"+balance+"','"+housesOwned+"','"+hotelsOwned+"','"+prisonCards+"','"+playerPosition+"');");
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void updatePlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition){
		String query = ("UPDATE "+ gameName+".player_list " +
						"SET playerBalance='"+balance+"', housesOwned='"+housesOwned+"', hotelsOwned='"+hotelsOwned+"', prisonCards='"+prisonCards+"', playerPosition='"+playerPosition+"' " + 
						"WHERE playerName='"+playerName+"';");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Drops current Database , when a winner is found
	public void dropCurrentGameTable(String gameName){
		String query = ("DROP DATABASE "+ gameName);
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateFieldTable(String gameName, String playerName, int fieldNo, int housesOnField, int hotelsOnField) {
		String query = ("UPDATE "+ gameName+".field " + 
						"SET fieldOwner='"+playerName+"', houseOnField='"+housesOnField+"', hotelOnField='"+hotelsOnField+"'" + 
						"WHERE fieldNO='"+fieldNo+"';");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void addToGameTable(String gameName, String gameState, int turnNumber, String playerTurn) {
		String query = ("INSERT INTO " + gameName+".game(game_id, game_State, turnNumber, player_turn)"+
						"VALUES('"+gameName+"', '"+gameState+"', '"+turnNumber+"', '"+playerTurn+"');");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateGameTable(String gameName, String gameState, int turnNumber, String playerTurn) {
		String query = ("UPDATE " + gameName+".game " +
						"SET game_State='" + gameState + "', turnNumber='" + turnNumber + "', player_turn='" + playerTurn + "'" +
						"WHERE game_id='"+gameName+"';");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void addToCardsTable(String gameName, int cardIndex,int cardNo, String cardOwner, String cardText){
		String query = ("INSERT INTO " + gameName+".cards(CardNo,CardIndex, CardOwner, CardText)" +
						"VALUES('"+cardNo+"', '"+cardIndex+"', '"+cardOwner+"', '"+cardText+"');");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateCardsTable(String gameName, int cardIndex,int cardNo, String cardOwner, String cardText){
		String query = ("UPDATE "+ gameName+".cards " +
						"SET CardNo='"+cardNo+"', CardOwner='"+cardOwner+"', CardText='"+cardText+"'" +
						"WHERE CardIndex='"+cardIndex+"';");
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String[] loadGames(){
		return null;
	}


}
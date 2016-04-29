package game.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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





	}
	public void createGame(String gameName){
		//Gemmer alt fra GameController.
		String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".game " + 
				"(game_id int (255) NOT NULL, " + 
				"game_State varchar(45) NOT NULL, " +
				"turnNumber int (11) NOT NULL, " +
				"player_id int (11) NOT NULL, " +
				"fieldNo int (11) NOT NULL, " +
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
				"playerBalance int (11) DEFAULT NULL, " +
				"housesOwned int(2) DEFAULT NULL, " +
				"hotelsOwned tinyint (1) DEFAULT NULL, " + 
				"prisonCards int (1) DEFAULT NULL, " +
				"playerPosition int (2) DEFAULT NULL, " +
				"PRIMARY KEY (playerName));");

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
	public void addToPlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition){
		String query = ("INSERT INTO " + gameName+".player_list(playerName, playerBalance, housesOwned, hotelsOwned, prisonCards, playerPosition)" +
						"VALUES('"+playerName+"','"+balance+"','"+housesOwned+"','"+hotelsOwned+"','"+prisonCards+"','"+playerPosition+"');");
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addToGameTable(String gameName, String gameState, int turnNumber, int playerID, int fieldNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateGameTable(String gameName, String gameState, int turnNumber, int playerID, int fieldNo) {
		// TODO Auto-generated method stub
		
	}


}

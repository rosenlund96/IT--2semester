package game.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import game.controllers.GameController;

public class Creator {
	GameController controller;
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	String createDB = "CREATE DATABASE " + timeStamp;
	DBConnector con = new DBConnector();


	//Opretter en ny database og returner navnet p� denne
	public String createGameDB(String timeStamp){
		try {
			con.doUpdate(createDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeStamp;
	}
	//Opretter de forn�dne tabeller i databasen
	public void createTables(String gameName){
		createGame(gameName);
		createField(gameName);
		createPlayersList(gameName);





	}
	public void createGame(String gameName){
		//Gemmer alt fra GameController.
		String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".game " + 
				"(game_id int (11) NOT NULL, " + 
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
				"(game_id int(11) NOT NULL," +
				"player_id int (11) NOT NULL," + 
				"playerName varchar(45) NOT NULL, " + 
				"playerBalance int (11) DEFAULT NULL, " +
				"housesOwned int(2) DEFAULT NULL, " +
				"hotelsOwned tinyint (1) DEFAULT NULL, " + 
				"prisonCards int (1) DEFAULT NULL, " +
				"PRIMARY KEY (player_id));");

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
				"(game_id int(11) NOT NULL, " +
				"fieldNo int (11) NOT NULL," +
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
	
	public void dropCurrentGameTable(String gameName){
		String query = ("DROP TABLE "+ gameName);
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

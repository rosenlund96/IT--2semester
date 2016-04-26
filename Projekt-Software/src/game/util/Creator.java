package game.util;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Creator {
	LocalDateTime time = LocalDateTime.now();
	String createDB = "CREATE DATABASE" + time;
	DBConnector con = new DBConnector();
	
	
	//Opretter en ny database og returner navnet p� denne
	public LocalDateTime createGameDB(LocalDateTime time){
		try {
			con.doUpdate(createDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return time;
	}
	//Opretter de forn�dne tabeller i databasen
	public void createTables(){
		createGame();
		createField();
		createPlayersList();
		
		
		

	}
	public void createGame(){
		//Gemmer alt fra GameController.
		String query = ("CREATE TABLE IF NOT EXISTS game " + 
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
	public void createPlayersList(){
		String query = ("CREATE TABLE IF NOT EXISTS player_list " +
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
	public void createField(){
		//Gemmer alt om felter.
		String query = ("CREATE TABLE IF NOT EXISTS field " +
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
	
}

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
		String query = ("CREATE TABLE Game" + 
						"(game_id int (11) NOT NULL, " + 
						"game_State varchar(45) NOT NULL, " +
						"turnNumber int (11) NOT NULL, " +
						"primary key (game_id));");
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPlayersList(){
		String query = ("CREATE TABLE player_List" +
						"(player_id int (11) NOT NULL, " +
						"playerName varchar(45) NOT NULL, " + 
						"playerBalance int (11) DEFAULT NULL, " +
						"housesOwned int (2) DEFAULT NULL, " +
						"hotelsOwned tinyint (1) DEFAULT NULL, " + 
						"prisonCards int (1) DEFAULT NULL, " +
						"primary key (player_id), " +
						"foreign key (housesOwned) references Field (houseOnField));");
		
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createField(){
		//Gemmer alt om felter.
		String query = ("CREATE TABLE Field" +
						"(fieldNo int (11) NOT NULL, " +
						"fieldOwner varchar (45) DEFAULT NULL, " +
						"houseOnField int(2) DEFAULT NULL, " + 
						"hotelOnField tinyint(1) DEFAULT NULL, " +
						"primary key (fieldNo));");
		try {
			con.doUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

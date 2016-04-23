package game.util;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Creator {
	LocalDateTime time = LocalDateTime.now();
	String createDB = "create database" + time;
	DBConnector con = new DBConnector();
	
	
	//Opretter en ny database og returner navnet p� denne
	public LocalDateTime createGameDB(LocalDateTime time){
		try {
			con.doQuery(createDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return time;
	}
	//Opretter de forn�dne tabeller i databasen
	public void createTables(){
		createGame();
		createPlayersList();
		createField();

	}
	public void createGame(){
		//Gemmer alt fra GameController.
		String query = ("create table Game" + 
						"(game_id int (11) NOT NULL, " + 
						"game_State varchar(45) NOT NULL, " +
						"turnNumber int (11) NOT NULL, " +
						"primary key (game_id), " + 
						"foreign key (player_id) references player_LIST (player_id));");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPlayersList(){
		String query = ("create table player_List" +
						"(player_id int (11) NOT NULL, " +
						"playerName varchar(45) NOT NULL, " + 
						"playerBalance int (11) DEFAULT NULL, " +
						"housesOwned int (2) DEFAULT NULL, " +
						"hotelsOwned int (2) DEFAULT NULL, " + 
						"prisonCards int (1) DEFAULT NULL, " +
						"primary key (player_id), " + 
						"foreign key (housesOwned) references Field (houseOnField), " +
						"foreign key (hotelsOwned) references Field (hotelOnField));");
		
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createField(){
		//Gemmer alt om felter.
		String query = ("create table Field" +
						"(fieldNo int (11) NOT NULL, " +
						"fieldOwner varchar (45) DEFAULT NULL, " +
						"houseOnField int(1) DEFAULT NULL, " + 
						"hotelOnField tinyint(1) DEFAULT NULL, " +
						"primary key (fieldNo)" + 
						"foreign key (fieldOwner) references player_List (player_id));");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

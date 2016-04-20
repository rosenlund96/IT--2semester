package game.util;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Creator {
	LocalDateTime time = LocalDateTime.now();
	String createDB = "create database" + time;
	DBConnector con = new DBConnector();
	
	
	//Opretter en ny database og returner navnet på denne
	public LocalDateTime createGameDB(LocalDateTime time){
		try {
			con.doQuery(createDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return time;
	}
	//Opretter de fornødne tabeller i databasen
	public void createTables(){
		createGame();
		createOwnable();
		createBoard();
	}
	public void createGame(){
		//Gemmer alt fra GameController.
		String query = ("create table Game" + 
						"game_id time, " + 
						"game_State varchar(10)" +
						"turnNumber int" +
						"primary key (time), " + 
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
						"player_id int NOT NULL, " +
						"playerName varchar(40) NOT NULL," + 
						"playerBalance int NOT NULL," +
						"playerPosition int NOT NULL," +
						"housesOwned int, NOT NULL" +
						"hotelsOwned int, NOT NULL" + 
						"prisonCards_owned int, NOT NULL" +
						"primary key (playerName,playerBalance,playerPosition)");
		
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createOwnable(){
		//Gemmer alt om felter.
		String query = ("create table Ownable" +
						"(ownable boolean, " +
						"");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createBoard() {
		//Gemmer alt på boardet, herunder positioner og bygninger
		String query = ("create table Board" +
						"field_NoOwnedBy int,"+
						"housesOnFields int," +
						"");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

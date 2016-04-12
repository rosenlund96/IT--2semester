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
		createPlayers();
		createOwnable();
		createCards();
		createBank();
	}
	public void createGame(){
		String query = ("game_id time" + 
						"player_id varchar(4)" + 
						"primary key (time)" + 
						"foreign key (player_id) refereneces players (player_id)");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPlayers(){
		String query = ("player_id varchar(4)" + 
						"playerOnBoard int" + 
						"playerBalance int" +
						"housesOwned int" +
						"hotelsOwned int" + 
						"cardsOwned varchar" +
						"primary key (player_id)");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createOwnable(){
		String query = "";
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createCards(){
		String query = "";
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createBank() {
		String query = "";
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

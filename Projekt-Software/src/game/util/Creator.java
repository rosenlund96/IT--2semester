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
		createBoard();
	}
	public void createGame(){
		String query = ("create table Game" + 
						"(game_id time, " + 
						"player_id varchar(4), " + 
						"primary key (time), " + 
						"foreign key (player_id) references Players (player_id));");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPlayers(){
		String query = ("create table Players" + 
						"(player_id varchar(4), " + 
						"playerOnBoard int, " + 
						"playerBalance int, " +
						"housesOwned int, " +
						"hotelsOwned int, " + 
						"card_id varchar, " +
						"primary key (player_id), " +
						"foreign key (card_id) references Cards (card_id));");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createOwnable(){
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
	public void createCards(){
		String query = ("create table Cards" + 
						"(card_id varchar, " +
						"cardTemp varchar, " +
						"cardActive varchar, " + 
						"primary key (card_id)");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createBoard() {
		String query = ("create table Board" +
						"(board_id time, " +
						"");
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

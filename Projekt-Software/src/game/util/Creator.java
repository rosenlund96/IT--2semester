package game.util;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Creator {
	LocalDateTime time = LocalDateTime.now();
	String createDB = "Create database" + time;
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
		createPlayers();
		createOwnable();
	}
	public void createGame(){
		String query = "";
		try {
			con.doQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPlayers(){
		String query = "";
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
}

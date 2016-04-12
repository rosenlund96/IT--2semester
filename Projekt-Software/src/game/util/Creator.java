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
		
	}
}

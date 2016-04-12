
package game.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	/** @author Ronnie Dalsgaard */
	public class Connector {
		private final String HOST     = "Localhost";
		private final int    PORT     = 3306;
		private final String DATABASE = "ct";
		private final String USERNAME = "root"; 
		private final String PASSWORD = "";
		private Connection connection;
	}
	
	public DBConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver")
		}
	}
}

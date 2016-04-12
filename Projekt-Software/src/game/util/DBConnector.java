
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
		private final String DATABASE = "Projekt";
		private final String USERNAME = "root"; 
		private final String PASSWORD = "";
		private Connection connection;


		public Connector() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
				connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		public Connection getConnection() {
			return connection;
		}

		public ResultSet doQuery(String query) throws SQLException{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		
		public void doUpdate(String query) throws SQLException{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
		}
	}
}


package game.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {
	/** @author Ronnie Dalsgaard */
		private final String HOST     = "Localhost";
		private final int    PORT     = 3307;
		private final String DATABASE = "test";
		private final String USERNAME = "root"; 
		private final String PASSWORD = "usbw";
		public Connection connection;


		public DBConnector() {
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
		public String[] doQueryToString(String query) throws SQLException {
			ArrayList<String> list= new ArrayList<String>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
			    list.add(rs.getString(1));   
			    
			} 

			String[] result = new String[list.size()];
			result = list.toArray(result);

			return result;
			}
		}



package game.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import game.entities.Player;

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
		public ArrayList<Player> loadPlayersToArray(String query) throws SQLException{
			ArrayList<Player> list= new ArrayList<Player>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 while (rs.next())
		      {
		        String PlayerName = rs.getString("playerName");
		        int PlayerIndex = rs.getInt("playerIndex");
		        int PlayerBalance = rs.getInt("playerBalance");
		        int HousesOwned = rs.getInt("housesOwned");
		        int HotelsOwned = rs.getInt("hotelsOwned");
		        int PrisonCards = rs.getInt("prisonCards");
		        int PlayerPosition = rs.getInt("playerPosition");
		       Player player = new Player(PlayerName, PlayerBalance, PlayerPosition, false, false, 0, PrisonCards);
		       list.add(player);
		         
		        // print the results
		        
		        
		      }
			
				return list;
		  
		}
		
		}


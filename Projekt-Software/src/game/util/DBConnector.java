
package game.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import game.entities.Player;
import game.entities.fields.AbstractField;
import game.entities.fields.Start;
import game.entities.fields.Territory;
import game.resources.CardEffect;
import game.resources.FieldData;

public class DBConnector {
	/** @author Ronnie Dalsgaard */
		private final String HOST     = "Localhost";
		private final int    PORT     = 3307;
		private final String DATABASE = "test";
		private final String USERNAME = "root"; 
		private final String PASSWORD = "usbw";
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
		String createDB = "CREATE DATABASE " + timeStamp;
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
		        int PlayerBalance = rs.getInt("playerBalance");
		        int HousesOwned = rs.getInt("housesOwned");
		        int HotelsOwned = rs.getInt("hotelsOwned");
		        int PrisonCards = rs.getInt("prisonCards");
		        int PlayerPosition = rs.getInt("playerPosition");
		       Player player = new Player(PlayerName, PlayerBalance, PlayerPosition, false, false, 0, PrisonCards);
		       list.add(player);
		      }
				return list;
		}
		public ArrayList<String> loadGameToArray(String query) throws SQLException{
			ArrayList<String> list= new ArrayList<String>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 while (rs.next())
		      {
		        String PlayerName = rs.getString("player_turn");
		        int TurnNumber = rs.getInt("turnNumber");
		       list.add(PlayerName);
		       list.add(String.valueOf(TurnNumber));
		      }
				return list;
		}
		public ArrayList<String> loadFieldsToArray(String query) throws SQLException{
			ArrayList<String> list= new ArrayList<String>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 while (rs.next())
		      {
		        int fieldNo = rs.getInt("fieldNo");
		        String fieldOwner = rs.getString("fieldOwner");
		        int housesOnField = rs.getInt("houseOnField");
		        int hotelOnField = rs.getInt("hotelOnField");
		        String fieldType= rs.getString("fieldType");
		       list.add(fieldNo+","+fieldOwner+","+housesOnField+","+hotelOnField+","+fieldType);
		      
		      }
			    return list;
		}
		public void createGame(String gameName){
			//Gemmer alt fra GameController.
			String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".game " + 
					"(game_id varchar (255) NOT NULL, " + 
					"game_State varchar(45) NOT NULL, " +
					"turnNumber int (11) NOT NULL, " +
					"player_turn varchar (45) DEFAULT NULL, " +
					"PRIMARY KEY (game_id));");
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void createPlayersList(String gameName){
			String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".player_list " +
					"(playerName varchar(45) NOT NULL, " + 
					"playerIndex int (11) NOT NULL, " +
					"playerBalance int (11) DEFAULT NULL, " +
					"housesOwned int(2) DEFAULT NULL, " +
					"hotelsOwned tinyint (1) DEFAULT NULL, " + 
					"prisonCards int (1) DEFAULT NULL, " +
					"playerPosition int (2) DEFAULT NULL, " +
					"PRIMARY KEY (playerIndex));");

			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void createField(String gameName){
			//Gemmer alt om felter.
			String query = ("CREATE TABLE IF NOT EXISTS " + gameName+".field " +
					"(fieldNo int (11) NOT NULL," +
					"fieldType varchar (45) NOT NULL," + 
					"fieldOwner varchar (45) DEFAULT NULL, " +
					"houseOnField int(2) DEFAULT NULL, " + 
					"hotelOnField tinyint(1) DEFAULT NULL, " +
					"PRIMARY KEY (fieldNo));");
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void createCards(String gameName){
			String query =("CREATE TABLE IF NOT EXISTS " + gameName+".cards " +
					"(CardNo int (11) NOT NULL," +
					"CardIndex int (11) DEFAULT NULL, " +
					"CardOwner varchar (45) DEFAULT NULL, " + 
					"CardText varchar (4096) NOT NULL, " +
					"PRIMARY KEY (CardIndex));");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public void addToFieldTable(String gameName, String playerName, int fieldNo, int housesOnField, int hotelsOnField, String fieldType) {
			String query = ("INSERT INTO " + gameName+".field(fieldNo, fieldType, fieldOwner, houseOnField, hotelOnField)" +
							"VALUES('"+fieldNo+"','"+fieldType+"', '"+playerName+"','"+housesOnField+"','"+hotelsOnField+"');");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//adds player to table when gameBoard is initialized
		public void addToPlayerTable(String gameName, int playerIndex,String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition){
			String query = ("INSERT INTO " + gameName+".player_list(playerName, playerIndex,playerBalance, housesOwned, hotelsOwned, prisonCards, playerPosition)" +
							"VALUES('"+playerName+"','"+playerIndex+"','"+balance+"','"+housesOwned+"','"+hotelsOwned+"','"+prisonCards+"','"+playerPosition+"');");
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
		public void updatePlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition){
			String query = ("UPDATE "+ gameName+".player_list " +
							"SET playerBalance='"+balance+"', housesOwned='"+housesOwned+"', hotelsOwned='"+hotelsOwned+"', prisonCards='"+prisonCards+"', playerPosition='"+playerPosition+"' " + 
							"WHERE playerName='"+playerName+"';");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Drops current Database , when a winner is found
		public void dropCurrentGameTable(String gameName){
			String query = ("DROP DATABASE "+ gameName);
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void updateFieldTable(String gameName, String playerName, int fieldNo, int housesOnField, int hotelsOnField) {
			String query = ("UPDATE "+ gameName+".field " + 
							"SET fieldOwner='"+playerName+"', houseOnField='"+housesOnField+"', hotelOnField='"+hotelsOnField+"'" + 
							"WHERE fieldNO='"+fieldNo+"';");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void addToGameTable(String gameName, String gameState, int turnNumber, String playerTurn) {
			String query = ("INSERT INTO " + gameName+".game(game_id, game_State, turnNumber, player_turn)"+
							"VALUES('"+gameName+"', '"+gameState+"', '"+turnNumber+"', '"+playerTurn+"');");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		public void updateGameTable(String gameName, String gameState, int turnNumber, String playerTurn) {
			String query = ("UPDATE " + gameName+".game " +
							"SET game_State='" + gameState + "', turnNumber='" + turnNumber + "', player_turn='" + playerTurn + "'" +
							"WHERE game_id='"+gameName+"';");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void addToCardsTable(String gameName, int cardIndex,int cardNo, String cardOwner, String cardText){
			String query = ("INSERT INTO " + gameName+".cards(CardNo,CardIndex, CardOwner, CardText)" +
							"VALUES('"+cardNo+"', '"+cardIndex+"', '"+cardOwner+"', '"+cardText+"');");
			
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void updateCardsTable(String gameName, int cardIndex,int cardNo, String cardOwner, String cardText){
			String query = ("UPDATE "+ gameName+".cards " +
							"SET CardNo='"+cardNo+"', CardOwner='"+cardOwner+"', CardText='"+cardText+"'" +
							"WHERE CardIndex='"+cardIndex+"';");
			try {
				doUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		public void createTables(String gameName){
			createGame(gameName);
			createField(gameName);
			createPlayersList(gameName);
			createCards(gameName);
		}
		public String createGameDB(String timeStamp){
			try {
				doUpdate(createDB);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return timeStamp;
		}

	}
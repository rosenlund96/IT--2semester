package game.util;

public interface DBFunctions {

	/***************************************************
	 * Interface defining methods for SQL statements   *
	 ***************************************************/
	
	public String createGameDB(String timeStamp);
	public void createTables(String gameName);
	public void createGame(String gameName);
	public void createPlayersList(String gameName);
	public void createField(String gameName);
	public void addToPlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition);
	public void updatePlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition);
	public void dropCurrentGameTable(String gameName);

	}

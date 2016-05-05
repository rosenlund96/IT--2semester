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
	public void createCards(String gameName);
	public void addToPlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition);
	public void updatePlayerTable(String gameName, String playerName, int balance, int housesOwned, int hotelsOwned, int prisonCards, int playerPosition);
	public void addToFieldTable(String gameName, String playerName, int fieldNo, int housesOnField, int hotelsOnField);
	public void updateFieldTable(String gameName, String playerName, int fieldNo, int housesOnField, int hotelsOnField);
	public void addToGameTable(String gameName, String gameState, int turnNumber, String playerTurn);
	public void updateGameTable(String gameName, String gameState, int turnNumber, String playerTurn);
	public void addToCardsTable(String gameName, int cardNo, String cardOwner, String cardText);
	public void updateCardsTable();
	public void dropCurrentGameTable(String gameName);
	public String[] loadGames();
	}

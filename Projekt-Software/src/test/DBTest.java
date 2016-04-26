package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import game.util.DBConnector;

public class DBTest {

	@Before
	public void setUp() throws Exception {
		

	}
	
	@Test
	public void ConncectionTest(){
		//Setup and act
		DBConnector connector = new DBConnector();
		
		//Assert
		assertEquals("Connection should now be established to the Database", true,connector.getConnection()!=null);
	}
	@Test
	public void createTableTest(){
		DBConnector connector = new DBConnector();
		String query = ("CREATE TABLE TestTable" + 
				"(test_id int (11) NOT NULL)");
		String hent = ("SELECT table_name FROM information_schema.tables");
		try {
			connector.doUpdate(query);;
		} catch (SQLException e) {
			e.printStackTrace();
			assertEquals("Table should now be created", true,e==null);
		}
		
		
		try {
			connector.doQuery(hent);
			
		} catch (SQLException e) {
			e.printStackTrace();
			assertEquals("DataBase should not be empty", true,e==null);
		}
		
		assertEquals("Connection should now be establisted to the Database", true, connector.getConnection()!=null);
		
	}
}

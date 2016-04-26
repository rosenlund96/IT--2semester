package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import game.util.DBConnector;

public class DBTest {


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
		assertEquals("Connection should now be establisted to the Database", true, connector.getConnection()!=null);
		String create = ("CREATE TABLE testtable1" + 
				"(test_id int (11) NOT NULL)");
		String load = ("SELECT * FROM test.testtable1");
		try {
			connector.doUpdate(create);;
		} catch (SQLException e) {
			e.printStackTrace();
			assertEquals("Table should now be created", true,e==null);
		}
		
		
		try {
			connector.doQuery(load);
			
		} catch (SQLException e) {
			e.printStackTrace();
			assertEquals("DataBase should not be empty", true,e==null);
		}
	}
	@Test
	public void insertToTable(){
		DBConnector connector = new DBConnector();
		assertEquals("Connection should now be establisted to the Database", true, connector.getConnection()!=null);
		String create = ("CREATE TABLE TestTable2" + 
				"(test_id int (11) NOT NULL)");
		String insert = ("INSERT INTO testtable2(test_id) VALUES(21)");
		String load = ("SELECT test_id FROM test.testtable2");
	try {
		connector.doUpdate(create);
	} catch (SQLException e) {
		e.printStackTrace();
		assertEquals("Table should now be created", true,e==null);
	}
	
	try {
		connector.doUpdate(insert);
	} catch (SQLException e) {
		e.printStackTrace();
		assertEquals("id 21. should now be in the testtable2", true,e==null);
	}
	try {
		connector.doQuery(load);
	} catch (SQLException e) {
		e.printStackTrace();
		assertEquals("The id is in the testtable2", true,e==null);
	}
	
	}
}

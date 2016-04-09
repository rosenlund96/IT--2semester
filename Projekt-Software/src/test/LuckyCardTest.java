package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.FieldManager;
import game.entities.Player;
import game.entities.cards.AbstractCard.CardType;
import game.entities.cards.MovaActivePlayer;
import game.entities.cards.Refuge;
import game.entities.cards.Tax;
import game.entities.fields.AbstractField.FieldType;
import game.entities.fields.LuckyCard;
import test.mockClasses.TestBoundary1;


public class LuckyCardTest {
	private Outputable output;
	private CardManager cm;
	private Player player;

	public void setUp() throws Exception {
		output = new TestBoundary1();
		cm = new CardManager(output);

	}
	
	@Test
	public void MovaActivePlayerTest() {
		
		//Setup
		int cardNo = 15;
		MovaActivePlayer card = new MovaActivePlayer(cm, output, cardNo);
		player = new Player("name", 5000, 5, false, false, false);
		assertEquals("Player created at position 5",5, player.getPosition());
		//Act
		int fieldNo = 6;
		int closest = card.closestRefuge(player.getPosition());
		int count = card.getCount(player.getPosition(), fieldNo);
		
		
		//Asserts
		assertEquals("Closest should be field 6",6,closest);
		assertEquals("Distance should be 1 field", 1,count);
	}
	@Test
	public void RefugeCardTest(){
		//Setup
		int bonus = 1000;
		Refuge refuge = new Refuge(cm, output, bonus);
		player = new Player("name", 1000, 5, false, false, false);
		assertEquals("Player created with balance 1000", 1000, player.getBalance());
		
		//Act
		refuge.drawCard(player);
		
		//Asserts 
		assertEquals("Player balance should now be 2000", 2000, player.getBalance());
		//test fejler da fieldmanager ikke kan oprette felter. XML fil ikkke opdateret. 
	}
	@Test
	public void TaxCardTest(){
		//Setup
		int taxAmount = 1000;
		Tax tax = new Tax(cm, output, taxAmount);
		player = new Player("name", 2000, 5, false, false, false);
		assertEquals("Player created with balance 2000", 2000, player.getBalance());
		
		//Act
		tax.drawCard(player);
		
		//Asserts 
		assertEquals("Player balance should now be 1000", 1000, player.getBalance());
		//test fejler da fieldmanager ikke kan oprette felter. XML fil ikkke opdateret. 
	}
}

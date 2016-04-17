package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.FieldManager;
import game.entities.Player;
import game.entities.cards.AbstractCard.CardType;
import game.entities.cards.MovaActivePlayer;
import game.entities.cards.Prison;
import game.entities.cards.Refuge;
import game.entities.cards.Tax;
import game.entities.fields.AbstractField.FieldType;
import game.entities.fields.LuckyCard;
import test.mockClasses.TestBoundary1;


public class LuckyCardTest {
	private Outputable output;
	private CardManager cm;
	private FieldManager fm;
	private Player player;

	@Before
	public void setUp() throws Exception {
		output = new TestBoundary1();
		fm = new FieldManager(output);
		cm = new CardManager(output);

	}
	
	@Test
	public void MovaActivePlayerTest() {
		
		//Setup
		int cardNo = 15;
		MovaActivePlayer card = new MovaActivePlayer(cm, output, cardNo);
		player = new Player("name", 5000, 5, false, false, 0, 0);
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
		Refuge refuge = new Refuge(cm, output, bonus, 0);
		player = new Player("name", 1000, 0, false, false, 0, 0);
		assertEquals("Player created with balance 1000", 1000, player.getBalance());
		
		//Act
		refuge.drawCard(player);
		
		//Asserts 
		assertEquals("Player balance should now be 2000", 2000, player.getBalance());
	}
	@Test
	public void PrisonCardTest(){
		//Setup
		Prison prisonCard = new Prison(cm, output, 0);
		game.entities.fields.Prison prisonField = new game.entities.fields.Prison(fm, output);
		Player p1 = new Player("name", 5000, 0, false, false, 1, 0);
		assertEquals("Player instantiated, and not imprisoned", false, p1.getImprisoned());
		assertEquals("Player has no prison card", 0, p1.getoutOfJailCard());
		
		//Act and asserts
		prisonField.landOnField(p1);
		assertEquals("Player should now be imprisoned", true, p1.getImprisoned());
		prisonCard.drawCard(p1);
		assertEquals("Player should now have a prison card", 1, p1.getoutOfJailCard());
	}
	
	@Test
	public void LuckyCardClassTest(){
		//setup
		LuckyCard luckyCard = new LuckyCard(fm, output);
		Player player = new Player("name",5000 , 0, false, false, 0, 0);
		
		//Act
		luckyCard.landOnField(player);
		
		//Asserts
		assertEquals("LyckyCard class' STAK, should not be empty", 33,cm.cards.length);
	}
	
}

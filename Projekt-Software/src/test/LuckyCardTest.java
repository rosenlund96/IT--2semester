package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;
import game.entities.cards.AbstractCard;
import game.entities.cards.MoveActivePlayer;
import game.entities.cards.Prison;
import game.entities.cards.Refuge;
import game.entities.fields.LuckyCard;
import test.mockClasses.TestBoundary1;


public class LuckyCardTest {
	private Outputable output;
	private FieldManager fm;
	private Player player;

	@Before
	public void setUp() throws Exception {
		output = new TestBoundary1();
		fm = new FieldManager(output);

	}
	
	@Test
	public void MovaActivePlayerTest() {
		
		//Setup
		int cardNo = 15;
		MoveActivePlayer card = new MoveActivePlayer(output, cardNo);
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
		Refuge refuge = new Refuge(output, bonus, 0);
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
		Prison prisonCard = new Prison(output, 0);
		game.entities.fields.Prison prisonField = new game.entities.fields.Prison(fm, output, 11);
		Player p1 = new Player("name", 5000, 0, false, false, 1, 0);
		assertEquals("Player instantiated, and not imprisoned", false, p1.getImprisoned());
		assertEquals("Player has no prison card", 0, p1.getOutOfJailCard());
		
		//Act and asserts
		prisonField.landOnField(p1);
		assertEquals("Player should now be imprisoned", true, p1.getImprisoned());
		prisonCard.drawCard(p1);
		assertEquals("Player should now have a prison card", 1, p1.getOutOfJailCard());
	}
	@Test
	public void newPrisonCardTest(){
		//setup
		LuckyCard card = new LuckyCard(fm,output,0);
		Player p1 = new Player("name", 5000, 0, false, false, 1, 0);
		assertEquals("Player has no prison card", 0, p1.getOutOfJailCard());
		assertEquals("Cards instantiated, and array not empty", true,LuckyCard.cards.length>0);
		//Act
		AbstractCard oldCard = card.drawCard(p1);
		AbstractCard newCard = card.drawCard(p1);

		//Asserts
		assertEquals("The two cards drawn, should not be the same, as cards are stored as a queue", false,oldCard==newCard);
	}
	
}

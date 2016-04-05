package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import game.boundaries.Outputable;
import game.entities.FieldManager;
import game.entities.Player;
import game.entities.fields.AbstractField.FieldType;
import game.entities.fields.LuckyCard;
import test.mockClasses.TestBoundary1;


public class LuckyCardTest {
	private Outputable output;
	private FieldManager fm;
	private FieldType LuckyCard;
	private Player player;

	public void setUp() throws Exception {
		output = new TestBoundary1();
		fm = new FieldManager(output);
		player = new Player("name", 5000, 0, false, false, false);

	}
	
	@Test
	public void CardLoad() {
		
		//Setup
		LuckyCard card = new LuckyCard(fm, LuckyCard, output);
		assertEquals("Cards not loaded yet",true, card.Stak.isEmpty());
		//Act
		card.hentKort();
		
		//Asserts
		assertEquals("Cards not loaded, array ´STAK´ empty",false, card.Stak.isEmpty());
	}
	@Test
	public void CardReturn() {
		
		//Setup
		LuckyCard card = new LuckyCard(fm, LuckyCard, output);
		assertEquals("Cards not loaded yet",true, card.Stak.isEmpty());
		
		//Act
		card.hentKort();
		for (int i = 0; i < 66; i++) {
			card.returnerKort();
		}
		
		//Asserts
		
		
		
	}
	

}

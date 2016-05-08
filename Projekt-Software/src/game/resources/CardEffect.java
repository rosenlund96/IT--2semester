package game.resources;

import game.entities.cards.AbstractCard;


public class CardEffect {

	private static int houses;
	private static int hotels;

	//Hvordan skal jeg vide ovenst�ende? 



	public static final int[] CardNo_DATA = {
			1,2,3,4,5,6,7,8,9,10,11,12,
			13,14,15,16,17,18,19,20,21,
			22,23,24,25,26,27,28,29,30,
			31,32,33
	};
	
	public static final int[] CardEffect_DATA = {
			1000,0,0,0,-200,-2000,1000,1000,
			1000,1337,200,-1000,-(800*houses)-(2300*hotels),
			0,0,0,0,0,0,1000,0,500,0,0,-(500*houses)-(2000*hotels),
			-3000,-3000,40000,3000,1000,1000,-1000,-200

	};
	
	public static final AbstractCard.CardType[] CardType_DATA = {
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.PRISON,
			AbstractCard.CardType.PRISON,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.PRISON,
			AbstractCard.CardType.PRISON,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.MOVE,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.REFUGE,
			AbstractCard.CardType.TAX,
			AbstractCard.CardType.TAX
	};
}

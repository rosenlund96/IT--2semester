package game.resources;

import game.entities.cards.AbstractCard;


public class CardEffect {

	private static int players;
	private static int houses;
	private static int hotels;
	
//Hvordan skal jeg vide ovenstående? 

	
	
	public static final int[] CardNo_DATA = {
			1,2,3,4,5,6,7,8,9,10,11,12,
			13,14,15,16,17,18,19,20,21,
			22,23,24,25,26,27,28,29,30,
			31,32,33
	};
	public static final String[] CardText_DATA = {
			"De modtager deres aktieudbytte. Modtag kr. 1000 af banken",
			"Ryk frem til start.",
			"Gå i fængsel. Ryk direkte til fængslet. Selvom de passere start, indkassere De ikke kr. 4000.",
			"Gå i fængsel. Ryk direkte til fængslet. Selvom de passere start, indkassere De ikke kr. 4000.",
			"De har været en tur i udlandet og har haft for mange cigaretter med hjem. Betal kr. 200 i told",
			"De har modtaget deres tandlæge regning, betal kr. 2000.",
			"De havde en række med 11 rigtige i tipning. Modtag kr. 1000",
			"Deres præmieobligation er kommet ud. Modtag kr. 1000 af banken.",
			"Deres præmieobligation er kommet ud. Modtag kr. 1000 af banken.",
			"Det er deres fødselsdag. Modtag af hver spiller, kr. 200",
			"Værdien af egen avl fra nyttehaven udgør 200kr. Modtag kr. 200 af banken.",
			"Betal deres bilforsikring. Kr. 1000",
			"Ejendomsskatterne er steget, ekstraudgifterne er: 800kr pr hus, 2300kr pr hotel.",
			"Ryk frem til Grønningen, hvis De passere start. Indkasser da kr. 4000",
			"Ryk brikken frem til det nærmeste redderi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.",
			"Ryk brikken frem til det nærmeste redderi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.",
			"Tag med LB-færgerne --- Flyt brikken frem, og hvis de passere start, indkasser da kr. 4000",
			"I andledning af kongens fødselsdag benådes de herved for fængsel. Dette kort kan opbevares, indtil de får brug for det, eller de kan sælge det.",
			"I andledning af kongens fødselsdag benådes de herved for fængsel. Dette kort kan opbevares, indtil de får brug for det, eller de kan sælge det.",
			"Grundet dyrtiden har de fået gageforhøjelse. Modtag kr. 1000",
			"Ryk frem til Frederiksberg Allé,. Hvis de passere start, indkasser da kr. 4000.",
			"De har vundet i klasselotteriet, modtag kr. 500.",
			"Tag ind på Rådhuspladsen.",
			"Ryk tre felter tilbage.",
			"Oliepriserne er steget, og De skal betale; kr. 500 pr hus, kr. 2000 pr hotel.",
			"Betal kr. 3000 for reparation af deres vogn.",
			"Betal kr. 3000 for reparation af deres vogn.",
			"De modtager det store Matadorlegat for værdigt trængende, kr 40.000. Ved værdigt trængende forstås, at deres formue, dvs. deres kontakte penge, plus skøder, plus penge, ike overstiger kr. 15.000",
			"Kommunen har eftergivet en kvartalsskat, hæv i banken kr. 3000.",
			"Modtag udbytte af deres aktier, kr. 1000",
			"Modtag udbytte af deres aktier, kr. 1000",
			"De er kørt frem for fuld stop, betal kr. 1000 i bøde. ",
			"De har modtaget en parkeringsbåde, betal kr 200 i bøde.",
			
	};
	
	public static final int[] CardEffect_DATA = {
			1000,0,0,0,-200,-2000,1000,1000,
			1000,200,200,-1000,-(800*houses)-(2300*hotels),
			0,0,0,0,0,0,1000,0,500,0,0,-(500*houses)-(2000*hotels),
			-3000,-3000,40000,3000,1000,1000,-1000,-200
			
	};
	public static final int[] CardPositionEffect_Data = {
			
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

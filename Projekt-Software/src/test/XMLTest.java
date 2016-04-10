package test;

import org.junit.Test;

import game.resources.FieldData;
import game.util.XMLReader;

public class XMLTest {

	String filePath = "/Projekt-Software/language2.xml";
	XMLReader reader;

	public void setUp()throws Exception{
		XMLReader reader = new XMLReader(filePath);
	}


	@Test
	public void XMLTEST(){
		for (int i = 0; i < FieldData.FIELDNAME_DATA.length; i++) {
			String string = reader.getElement(FieldData.FIELDNAME_DATA[i], 0);
			System.out.println(string);
			
		}
	
	}
}

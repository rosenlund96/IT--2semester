package game.util;

import java.util.ArrayList;

public class FieldDataHandler {
	@SuppressWarnings("unchecked")
	ArrayList<String>[][] Fields = new ArrayList[40][10];
	ArrayList<String> temp = new ArrayList<String>();
	
	public ArrayList<String> readData(){
		CSVReader reader = new CSVReader("resources/Matador - feltdata - kortdata.csv");
		reader.ReadFile();
		temp = reader.getArrayList();
		return temp;  
		}
	public String[] toArray(){
		String[] stockArr = new String[temp.size()];
		stockArr = temp.toArray(stockArr);
		return stockArr;

		
		
	}
	
	public String[][] monoToBidi( final String[] array, final int rows, final int cols ) {
	    if (array.length != (rows*cols))
	        throw new IllegalArgumentException("Invalid array length");

	    String[][] bidi = new String[rows][cols];
	    for ( int i = 0; i < rows; i++ )
	        System.arraycopy(array, (i*cols), bidi[i], 0, cols);

	    return bidi;
}
}
 
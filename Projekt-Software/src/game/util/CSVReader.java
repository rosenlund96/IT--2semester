package game.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileInputStream;

public class CSVReader {		 
	String fileName;

	ArrayList <String>CSVElements = new ArrayList<String>();
	public CSVReader(String FileName)
	{
		this.fileName=FileName;
	}

	public void ReadFile()
	//Reads the CSV file and stores it as a ArrayList
	{
		try {
			BufferedReader bReader = new BufferedReader( new FileReader(fileName));
			int lineNumber = 0;
			//BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
			
			while( (fileName = bReader.readLine()) != null)
			{
				lineNumber++;
				CSVElements.add(fileName);
				//Add the element readden to the ArrayList
			}
			//Close reader just for the sake of it. 
			bReader.close();
		//If file not readable for some reason, catch an exception
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		//If file has any IO errors catch an exception
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//mutators
	public void setFileName(String newFileName){
		this.fileName=newFileName;
	}
	public String getFileName(){
		return fileName;
	}
	//Prints our the ArrayList to the console
	public void printArrayList(){
		for(int i=0;i<this.CSVElements.size();i++){
			System.out.println(CSVElements.get(i));
		}
		
	}
	public ArrayList<String> getArrayList(){
		return CSVElements;
		
	}

}

package game.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {		 
	String fileName;

	ArrayList <String>CSVElements = new ArrayList<String>();
	public CSVReader(String FileName)
	{
		this.fileName=FileName;
	}

	public void ReadFile()
	{
		try {
			BufferedReader bReader = new BufferedReader( new FileReader(fileName));
			int lineNumber = 0;
			while( (fileName = bReader.readLine()) != null)
			{
				lineNumber++;
				CSVElements.add(fileName);
				//break comma separated line using ","
				
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public void getArrayList(){
		for(int i=0;i<this.CSVElements.size();i++){
			System.out.println(CSVElements.get(i));
		}

	}

}

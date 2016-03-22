package game.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CSVReader {		 
	String fileName;

	ArrayList <String>storeValues = new ArrayList<String>();
	public CSVReader(String FileName)
	{
		this.fileName=FileName;
	}

	public void ReadFile()
	{
		try {
			BufferedReader bReader = new BufferedReader( new FileReader(fileName));
			StringTokenizer sTokenizer = null;
			int lineNumber = 0;
			while( (fileName = bReader.readLine()) != null)
			{
				lineNumber++;
				storeValues.add(fileName);
				//break comma separated line using ","
				sTokenizer = new StringTokenizer(fileName, "\t");
				while(sTokenizer.hasMoreTokens())
				{	
					System.out.println(sTokenizer.nextToken());
				}

				//reset token number
				
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


	//mutators and accesors 
	public void setFileName(String newFileName){
		this.fileName=newFileName;
	}
	public String getFileName(){
		return fileName;
	}
	public ArrayList getFileValues(){
		return this.storeValues;
	}
	public void getArrayList(){
		for(int i=0;i<this.storeValues.size();i++){
			System.out.println(storeValues.get(i));
		}

	}

}

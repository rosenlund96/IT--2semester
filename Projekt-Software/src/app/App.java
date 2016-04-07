package app;

import game.controllers.GameController;

public class App {
	public static void main(String[] args){
		
		// instantiate GameControlelr and starts game.
		int currentPosition = 4;
		int fieldNo = 1;
		printCount(currentPosition, fieldNo);
		
		
	
		
	}
	public static int getCount(int currentPosition, int fieldNo){
		int count = 0;
		while(currentPosition != fieldNo){
			if(currentPosition<=40)
			currentPosition++;
			count++;
			
			if(currentPosition==41){
			currentPosition=0;
			count++;
			}
		}
		return count;
		}
	public static void printCount(int currentPosition, int fieldNo){
		System.out.println(getCount(currentPosition, fieldNo));
		
	}
		
	}

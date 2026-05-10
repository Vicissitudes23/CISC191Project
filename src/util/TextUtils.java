package util;

import game.GameWindow;

/**
 * Lead Author(s):
 * @Matthew Chang
 * @author 
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * <<add more references here>>
 * Oracle. (n.d.). Thread (Java Platform SE 8 ). Oracle.
 * Retrieved April 22, 2026, from https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
 * got the idea from observing unit test from file IO module
 * 
 *  
 * Version/date: 
 * 
 * Responsibilities of class:
 * 
 */
/**
 */
public class TextUtils
{
	private static GameWindow gameWindow;//has-a
	public static void print(String text)//wanted to slow down the printed text to give the player time to read what is happening because println will instantly print everything all at once
	{	
		//ended up removing the thread part because it was too hard to work around for the GUI
		
		gameWindow.print(text);//sends print to gameWindow which prints to GUI

		gameWindow.newLine();
	}
	

	
	public static void setWindow(GameWindow window)
	{
		gameWindow = window;
	}

}
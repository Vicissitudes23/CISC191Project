package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



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
 *  
 * Version/date: 
 * 
 * Responsibilities of class:
 * 
 */
/**
 */
public class MainGame
{

	private Scanner sc = new Scanner(System.in);
	private Player player;// player variable
	private List<Occurrence> events = new ArrayList<>(); 
	private int OccurrenceCount = 0; //number of events or things have happened
	private int currency;

	
	public MainGame()
	{
		setupEvents(); //initializes events
		
	}
	public static void main(String[] args)
	{
		MainGame game = new MainGame();
		game.start();
	}
	
	public void start()// called in main.java to start the game
	{
		System.out.println("Hello! Welcome!");


		player = new Player("You!", 100, 20);

		mainMenu();// opens main menu

	}

	private void mainMenu()
	{
		while (true)
		{
			System.out.println("\n=== Main Menu ===");
			System.out.println("1. Start New Journey");
			System.out.println("2. View Stats");
			System.out.println("3. Load Save");
			System.out.println("4. Quit");
			System.out.println("Select 1-4");

			String choice = sc.nextLine();

			switch (choice)
			{
				case "1":
					currency = 200;
					System.out.print("Enter your player name: ");
					String name = sc.nextLine();
					player.setName(name);
					System.out.println("\nYour journey begins...");
					startJourney();
					break;

				case "2":
					player.showStats();
					break;
				
				case "3":
					loadGame();
					startJourney();

					break;

				case "4":
				{
					System.out.println("Goodbye!");

					return;
				}
				default:
					System.out.println("Invalid Choice");
			}

		}
	}
	
	
	private void startJourney()
	{
		boolean traveling = true;

		while (traveling)
		{
			System.out.println("\n=== Journey Event ===");
			System.out.println("1. Continue traveling 2. Save and return to main menu");
			
			String choice = sc.nextLine();
			
			switch (choice)
			{
				case "1":
					triggerRandomOccurrence();
					break;
				case "2":
					traveling = false;
					saveGame();
					break;
					
				default:
					System.out.println("Invalid Choice");
			}
			
			
		}
	}
	
	
	private void setupEvents()
	{
		// TODO Auto-generated method stub
		
	}
	
	private void triggerRandomOccurrence()
	{
		// TODO Auto-generated method stub
		
	}
	private void saveGame()
	{
		// TODO Auto-generated method stub
	}
	
	private void loadGame()
	{
		// TODO Auto-generated method stub	
	}
	
	
}

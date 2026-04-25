package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import util.TextUtils;



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
	private List<Occurrence> possibleOccurrences = new ArrayList<>(); //has-many Occurrences stores all possible occurrences that can be randomly selected
	private int occurrenceCount = 0; //number of events or things have happened
	private Map<String, Item> itemDatabase = new HashMap<>();

	
	public MainGame()
	{
		setupOccurrences(); //initializes occurrences
		
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
					player.setCurrency(200);
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
	
	
	private void setupOccurrences()
	{
		possibleOccurrences.add(new Occurrence(
				"Warrior On the Road",
				"You cross paths with a warrior on the road.",
				() -> warriorEvent()
				));
		possibleOccurrences.add(new Occurrence(
				"Goblin Encounter",
				"A goblin jumpscares you on the road.",
				() -> goblinEvent()
				));
		possibleOccurrences.add(new Occurrence(
				"Goblin robbery",
				"A goblin wants your money",
				() -> robberyEvent()
				));
		possibleOccurrences.add(new Occurrence(
				"Suspicious drawer",
				"You found a drawer in an abandoned building",
				() -> investigateDrawer()
				));
		possibleOccurrences.add(new Occurrence(
				"Wolf attack",
				 "You hear a howl and see a wolf running at you!",
				 () -> wolfAttack()
				 ));
		possibleOccurrences.add(new Occurrence(
				"Crazy encounter",
				"A crazy man runs at you saying profanities",
				() -> crazedMan()
				));
		possibleOccurrences.add(new Occurrence(
				"Strange Cheese",
				"You find a cheese in the middle of the road.",
				() -> ratQuest()
				));
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
	

	

	
	
	private void triggerRandomOccurrence()
	{
		if(player.getCurrency() >= 30)
		{
			if (occurrenceCount != 0)
			{
				TextUtils.slowPrint("It has been "+ occurrenceCount + " days since you began this journey", 20);
				TextUtils.slowPrint("You spent 25 gold to stay at an inn overnight", 30);
				TextUtils.slowPrint("You feel well rested and a little bit stronger", 20);
				player.increaseAttack(2);
				player.heal(10);
				
				player.changeCurrency(-25);
				
				TextUtils.slowPrint("You have " + player.getCurrency() + " gold left", 20);
				
				TextUtils.slowPrint("\n The Journey Continues...", 30);
			}
							

			
			
			int index = (int)(Math.random() * possibleOccurrences.size());
			
			Occurrence event = possibleOccurrences.get(index);
			TextUtils.slowPrint("\nEvent: " + event.getName(), 15);
			TextUtils.slowPrint(event.getDescription(), 20);
			
			event.trigger();
			occurrenceCount += 1;
		}
		else
		{
			
			TextUtils.slowPrint("You have run out of funds... you cannot continue...", 25);
			TextUtils.slowPrint("The journey has come to an abrupt end...", 35);
			mainMenu();
		}
		
	}
	private void saveGame()
	{
		// TODO Auto-generated method stub
	}
	
	private void loadGame()
	{
		// TODO Auto-generated method stub	
	}
	
	
	private void warriorEvent()
	{
		TextUtils.slowPrint("Choose 1. Fight them, 2. Talk to them, 3. Ignore them", 20);
		

		String choice = sc.nextLine();
		
		switch (choice)
		{
			case "1":
				TextUtils.slowPrint("You draw your weapon and take a fighting stance", 15);
				Enemy warrior = new Warrior();
				startBattle(warrior);
				break;
			
			case "2":
				TextUtils.slowPrint("You decide to say a few words.", 15);
				TextUtils.slowPrint("Greetings fellow traveler. Take this potion and stay safe.", 20);
				player.addItem("Potion");
				TextUtils.slowPrint("You obtained a potion!", 15);
				break;
				
			case "3":
				TextUtils.slowPrint("You walk past the warrior, and glance back over your shoulder... He is glaring at you.", 15);
				TextUtils.slowPrint("You start running away and trip...", 20);
				TextUtils.slowPrint("You lost 30 gold...", 15);
				player.changeCurrency(-30);
				
				
				
				break;
			default:
				System.out.println("Invalid Choice");
		}

		
	}
	
	
	private void wolfAttack()
	{
		TextUtils.slowPrint("You ready yourself for a fight", 15);
		Enemy wolf = new Wolf();
		startBattle(wolf);
	}
	
	
	private void ratQuest()
	{
		TextUtils.slowPrint("Choose 1. Eat the cheese, 2. hit the cheese", 20);
		

		String choice = sc.nextLine();
		
		switch (choice)
		{
			case "1":
				TextUtils.slowPrint("You eat the cheese.", 15);
				player.heal(10);
				player.increaseAttack(2);
				TextUtils.slowPrint("You heal 10 health.", 25);
				TextUtils.slowPrint("You gain 2 attack.", 25);
				break;
			
			case "2":
				
				TextUtils.slowPrint("An offended rat comes up to you and attacks!", 15);
				TextUtils.slowPrint("You ready yourself for a fight", 20);
				Enemy rat = new Rat();
				startBattle(rat);
				break;
				
				
			default:
				System.out.println("Invalid Choice");	
		}
	}
	

	}
	
	
	private void investigateDrawer()
	{
		TextUtils.slowPrint("Choose 1. Check the bottom drawer, 2. Check the top drawer", 20);
		

		String choice = sc.nextLine();
		
		switch (choice)
		{
			case "1":
				TextUtils.slowPrint("A Rat jumps out and attacks you!", 15);
				Enemy rat = new Rat();
				startBattle(rat);
				break;
			
			case "2":
				TextUtils.slowPrint("You search the drawer", 15);
				TextUtils.slowPrint("The drawer has a nice looking bomb inside.", 20);
				player.addItem("Bomb");
				TextUtils.slowPrint("You obtained a bomb!", 15);
				break;
				
				
			default:
				System.out.println("Invalid Choice");
		}

		
	}
	
	private void goblinEvent()
	{
		TextUtils.slowPrint("1. Attack it, 2. Talk to it, 3. Run away from it", 20);
		
		String choice = sc.nextLine();
		switch (choice)
		{
			case "1":
				TextUtils.slowPrint("You strike at the goblin with your weapon!", 15);
				TextUtils.slowPrint("The goblin is knocked out...", 20);
				TextUtils.slowPrint("Turns out the goblin was just trying to ask for directions...", 25);
				TextUtils.slowPrint("You decide to take its money", 15);
				player.changeCurrency(5);
				//gain gold
				break;
			case "2":
				TextUtils.slowPrint("You ask what the goblin wants", 15);
				TextUtils.slowPrint("Goblin: WHERE IS THE NEAREST TOWN!!!!???", 25);
				TextUtils.slowPrint("After pointing the goblin towards the nearest town, it thanks you for your kindness.", 15);
				player.addItem("Bomb");
				TextUtils.slowPrint("You have obtained a bomb!", 20);
				break;
			case "3":
				TextUtils.slowPrint("You run like the goblin is trying to kill you.", 15);
				TextUtils.slowPrint("You hear the goblin yelling at you...",15);
				TextUtils.slowPrint("Goblin: WAIT!!!!!!! I JUST NEED HELP!!!!!", 30);
				TextUtils.slowPrint("You feel a little bad for the goblin but you keep running anyways", 20);
				break;
			default:
				System.out.println("Invalid Choice");
		}
		
	}
	
	private void robberyEvent()
	{
		TextUtils.slowPrint("You have been robbed by a goblin...", 25);
		TextUtils.slowPrint("You lost 20 gold but managed to take its dagger as it ran away", 25);
		player.changeCurrency(-20);
		player.addItem("Knife");
	}
	
	private void ogreBlocking()
	{
		TextUtils.slowPrint("An ogre attacks you", 20);
		TextUtils.slowPrint("You draw your weapon and take a fighting stance", 15);
		Enemy ogre = new Ogre();
		startBattle(ogre);
		
	
		
	}
	
}

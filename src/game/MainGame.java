package game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import util.TextUtils;



/**
 * Lead Author(s):
 * @Matthew Chang
 * @author 
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * Mofeng Atlass (858)-266-8514
 * Elijah Jones (619)-888-8301
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * <<add more references here>>
 * Use of runnable was something I learned from previous discussions with my friend Elijah Jones
 * Retrieved April 25, 2026, from https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
 * I came across information on runnable while doing research on thread functionality on the same website
 * Use of Lambdas was encouraged by my friend Mofeng Atlass as I was discussing how I could run the events from different functions
 * I then did research on how I could use it properly
 * Retrieved April 20, 2026 from https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#use-case
 * 
 * was curious to see if I could use other methods to read from files and saw buffered reader on this website 
 * then I had the idea to try and use buffered reader instead of scanner to see how it would work as opposed to scanner
 * Retrieved May 16, 2026 from https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
 * I used this website to see the differences between bufferedReader and scanner and how they are implemented differently
 * Retrieved May 16, 2026 from https://www.baeldung.com/java-buffered-reader
 * 
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

	private Player player;//has-a
	private List<Occurrence> possibleOccurrences = new ArrayList<>(); //has-many Occurrences stores all possible occurrences that can be randomly selected
	private int occurrenceCount = 0;//has-a counts how many occurrences have happened during the journey
	private GameWindow gameWindow = new GameWindow();//has-a 
	private Map<String, Item> itemDatabase = new HashMap<>();//has-many possible items
	
	private CombatManager combatManager;//has-a

	
	public MainGame()
	{
		setupOccurrences(); //initializes occurrences
		
		
	}
	public static void main(String[] args)
	{
		MainGame game = new MainGame();
		game.start();
		
	}
	
	public void start()
	{
		try
		{
			TextUtils.print("Hello! Welcome!");
			
			player = new Player("You", 100, 20, this);
			setupItems(); //initializes items
			combatManager = new CombatManager(player, this);//initializes combat manager
			mainMenu();// opens main menu
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(gameWindow.getMainFrame(), e.getMessage());
			System.exit(0);
			
		}
		
	}

	public void mainMenu()
	{
		String[] labels = {"Play", "Stats", "Load", "Quit"};
		
		Runnable[] actions = new Runnable[4];

		TextUtils.print("\n=== Main Menu ===");
		TextUtils.print("1. Start New Journey");
		TextUtils.print("2. View Stats");
		TextUtils.print("3. Load Save");
		TextUtils.print("4. Quit");
		TextUtils.print("Select 1-4");
		
		//sets the first button's runnable to start the game
		actions[0] = () ->
		{
			
			TextUtils.print("\nYour journey begins...");
			startJourney();
		};
		//sets the second button's runnable to show  player stats
		actions[1] = () ->
		{
			player.showStats();
		};
		//sets the third button's runnable to load player's savedata and run the game
		actions[2] = () ->
		{
			try
			{
				loadGame();
				startJourney();
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(gameWindow.getMainFrame(), e.getMessage());
			}
			
			
		};
		//sets the 4th button's runnable to quit the game
		actions[3] = () ->
		{
			//quit
			System.exit(0);
		};
		
		//sets the runnable actions and text of each button
		gameWindow.setButtonActions(labels, actions);

		
	}
	
	

	
	public void startJourney()
	{
		
		String[] labels = {"continue", "Save and Quit"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("\n=== Journey Event ===");
		TextUtils.print("1. Continue traveling 2. Save and return to main menu");
		
		actions[0] = ()-> 
		{
			//continue traveling
			TextUtils.print("You continue your journey...");
			triggerRandomOccurrence();
			
			
			
		};
		
		actions[1] = () ->
		{
			try
			{
				saveGame();
				TextUtils.print("Game Saved");
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(gameWindow.getMainFrame(), e.getMessage());
			}
			finally
			{
				mainMenu();
			}
			
		};
		
		gameWindow.setButtonActions(labels, actions);
		
	}

	public GameWindow getGameWindow()
	{
		return this.gameWindow ;
	}
	


	private void saveGame()
	{
		try (PrintWriter saveWriter = new PrintWriter(new FileWriter("save.txt")))
		{
			saveWriter.println("name=" + player.getName());//writes player name variable to the save file
			saveWriter.println("health=" + player.getHealth());//writes the player's health value to the save file
			saveWriter.println("attack=" + player.getAttack());//writes the player's attack value to the save file
			saveWriter.println("occurrenceCount=" + occurrenceCount);//writes the total number of occurrences visited to the save file
			saveWriter.println("currency=" + player.getCurrency()); // writes the player's currency amount to the save file
	        String[] inv = player.getInventory();

	        saveWriter.print("inventory=");
	        for (int i = 0; i < inv.length; i++) {
	            if (inv[i] == null)
	            	saveWriter.print("null");//if the slot has no item print null to the file
	            else
	            	saveWriter.print(inv[i]);//if there is an item the name of it gets printed to the file

	            if (i < inv.length - 1)
	            	saveWriter.print(",");//separates each item with a comma
	        }
	        saveWriter.println();
	        

		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(gameWindow.getMainFrame(), e.getMessage());
		}
	}
	
	
	private void loadInventory(String data) 
	{
	    String[] items = data.split(",");
	    String[] inventory = player.getInventory();

	    for (int i = 0; i < inventory.length && i < items.length; i++) //iterates through the player inventory
	    {
	        if (items[i].equals("null"))//if the save slot has null the inventory slot is set to null
	            inventory[i] = null;
	        else
	            inventory[i] = items[i];//sets the item slot to the saved item
	    }
	}
	
	private void loadGame()
	{
		try (Scanner saveLoader = new Scanner(new FileReader("save.txt")))
		{
			
			while (saveLoader.hasNextLine())
			{
				String line = saveLoader.nextLine();
				String[] parts = line.split("="); //splits the line at the = to separate the variable from its value
				//the first part will be the name of the variable and the second will be its value
				
				switch (parts[0])//gets the name of the variable
				{
					case "name":
						player.setName(parts[1]);//sets the player's name as the saved name
						break;
					case "HP":
						player.setHealth(Integer.parseInt(parts[1]));//sets the player's health to the saved value
						break;
					case "attack":
						player.setAttack(Integer.parseInt(parts[1]));//sets the player's attack to the saved value
						break;
					case "occurrenceCount" :
						occurrenceCount = (Integer.parseInt(parts[1]));//sets the game's occurrence count to the saved value
						break;
					case "currency":
						player.setCurrency(Integer.parseInt(parts[1]));//sets the player's currency to the saved value
						break;
					case "inventory":
						loadInventory(parts[1]);//sets the player's inventory to the saved values
						break;
					
				}
			}
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(gameWindow.getMainFrame(), e.getMessage());
		}
	}
	
	
	private void setupItems()//creates new item types and adds them to the database
	{
		itemDatabase.put("Potion", new Item("Potion", "Restores 20 HP", 20));
		itemDatabase.put("Super Potion", new Item("Super Potion", "Restores 20 HP", 20));
		itemDatabase.put("Bomb", new Item("Bomb", "Deals 30 damage to the enemy", 30));
		itemDatabase.put("Knife", new Item("Knife", "Deals double your attack value", player.getAttack() * 2));
		
	}
	

	
	
	//adds the occurrences to the possibleOccurences list
	private void setupOccurrences()
	{
		possibleOccurrences.add(new Occurrence(
				"Warrior On the Road", //name
				"You cross paths with a warrior on the road.", //description
				() -> warriorEvent() //runnable is the warriorEvent function in this mainGame class
				));
		possibleOccurrences.add(new Occurrence(
				"Goblin Encounter", //name
				"A goblin jumpscares you on the road.", //description
				() -> goblinEvent()//runnable is the goblinEvent function in this mainGame class
				));
		possibleOccurrences.add(new Occurrence(
				"Goblin robbery", //name
				"A goblin wants your money", //description
				() -> robberyEvent()//runnable is the robberyEvent function in this mainGame class
				));
		possibleOccurrences.add(new Occurrence(
				"Suspicious drawer", //name
				"You found a drawer in an abandoned building", //description
				() -> investigateDrawer()//runnable is the investigateDrawer function in this mainGame class
				));
		possibleOccurrences.add(new Occurrence(
				"Wolf attack", //name
				 "You hear a howl and see a wolf running at you!", //description
				 () -> wolfAttack()//runnable is the wolfAttack function in this mainGame class
				 ));
		possibleOccurrences.add(new Occurrence(
				"Strange Cheese", //name
				"You find a cheese in the middle of the road.", //description
				() -> ratQuest()//runnable is the ratQuest function in this mainGame class
				));
		possibleOccurrences.add(new Occurrence(
				"Ogre roadblock", //name
				"An ogre is standing in front of you...", //description
				() -> ogreBlocking()//runnable is the ogreBlocking function in this mainGame class
				));
	}
	
	
	private void triggerRandomOccurrence()
	{
		//if the player has enough currency they get increased health and attack
		if(player.getCurrency() >= 30)
		{
			if (occurrenceCount != 0)
			{
				TextUtils.print("It has been "+ occurrenceCount + " days since you began this journey");
				TextUtils.print("You spent 25 gold to stay at an inn overnight");
				TextUtils.print("You feel well rested and a little bit stronger");
				player.increaseAttack(2);
				player.heal(10);
				
				player.changeCurrency(-25);
				TextUtils.print("\n The Journey Continues...");
			}
							

			
			//gets a random number corresponding to a possible occurrence
			int index = (int)(Math.random() * possibleOccurrences.size());
			
			//gets the occurrence that corresponds to the gotten index
			Occurrence event = possibleOccurrences.get(index);
			//prints the name of the occurrence
			TextUtils.print("\nEvent: " + event.getName());
			//prints the description of the occurrence
			TextUtils.print(event.getDescription());
			
			event.trigger();
			occurrenceCount += 1;
		}
		
		//if the player does not have enough currency their journey ends
		else
		{
			
			TextUtils.print("You have run out of funds... you cannot continue...");
			TextUtils.print("The journey has come to an abrupt end...");
			mainMenu();
		}
		
	}
	
	private void warriorEvent()
	{
		//the button labels for this event
		String[] labels = {"Fight", "Talk", "Ignore"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("Choose 1. Fight them, 2. Talk to them, 3. Ignore them");
		
		
		
		
		actions[0] = () ->
		//the first button's runnable is this
		{
			TextUtils.print("You draw your weapon and take a fighting stance");
			Enemy warrior = new Warrior();
			combatManager.startBattle(warrior);
		};
		
		actions[1] = () ->
		//the second button's runnable is this
		{
			TextUtils.print("You decide to say a few words.");
			TextUtils.print("Greetings fellow traveler. Take this potion and stay safe.");
			player.addItem("Potion");//adds a potion to the player's inventory
			TextUtils.print("You obtained a potion!");
			startJourney();
		};
		
		actions[2] = () ->
		//the third button's runnable is this
		{
			TextUtils.print("You walk past the warrior, and glance back over your shoulder... He is glaring at you.");
			TextUtils.print("You start running away and trip...");
			player.changeCurrency(-30);
			startJourney();
		};
		
		//there is not a 4th action here so the 4th button will be disabled
		
		gameWindow.setButtonActions(labels, actions);//sets the 

		
	}
	
	
	private void wolfAttack()
	{
		TextUtils.print("You ready yourself for a fight");
		Enemy wolf = new Wolf();
		combatManager.startBattle(wolf);
	}
	
	
	private void ratQuest()
	{	
		//the button labels for this event
		String[] labels = {"Eat", "Hit"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("Choose 1. Eat the cheese, 2. hit the cheese");
		
		actions[0] = () ->
		//the first button's runnable is this
		{
			TextUtils.print("You eat the cheese.");
			player.heal(10);
			player.increaseAttack(2);
			TextUtils.print("You heal 10 health.");
			TextUtils.print("You gain 2 attack.");
			startJourney();
		};
		actions[1] = () ->
		//the second button's runnable is this
		{
			TextUtils.print("An offended rat comes up to you and attacks!");
			TextUtils.print("You ready yourself for a fight");
			Enemy rat = new Rat();
			combatManager.startBattle(rat);
				
		};
		
		actions[2] = () ->
		//the third button's runnable is this
		{
			TextUtils.print("You walk past the warrior, and glance back over your shoulder... He is glaring at you.");
			TextUtils.print("You start running away and trip...");
			player.changeCurrency(-30);
			startJourney();
		};
		//there is not a 4th button in this event
		
		gameWindow.setButtonActions(labels, actions);//sets the button labels and runnable actions to the button
		
		


	}
	

	
	
	
	private void investigateDrawer()
	{
		String[] labels = {"Bottom", "Top"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("Choose 1. Check the bottom drawer, 2. Check the top drawer");
		
		actions[0] = () ->
		{
			TextUtils.print("A Rat jumps out and attacks you!");
			Enemy rat = new Rat();
			combatManager.startBattle(rat);	
		};
		actions[1] = () ->
		{
			TextUtils.print("You search the drawer");
			TextUtils.print("The drawer has a nice looking bomb inside.");
			player.addItem("Bomb");
			TextUtils.print("You obtained a bomb!");
			startJourney();
		};
		

		gameWindow.setButtonActions(labels, actions);

	}
	
	private void goblinEvent()
	{
		String[] labels = {"Attack", "Talk", "Run"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("1. Attack it, 2. Talk to it, 3. Run away from it");
		
		
		
		
		actions[0] = () ->
		{
			TextUtils.print("You strike at the goblin with your weapon!");
			TextUtils.print("The goblin is knocked out...");
			TextUtils.print("Turns out the goblin was just trying to ask for directions...");
			TextUtils.print("You decide to take its money");
			player.changeCurrency(5);
			//gain gold
			startJourney();
		};
		actions[1] = () ->
		{
			TextUtils.print("You ask what the goblin wants");
			TextUtils.print("Goblin: WHERE IS THE NEAREST TOWN!!!!???");
			TextUtils.print("After pointing the goblin towards the nearest town, it thanks you for your kindness.");
			player.addItem("Bomb");
			TextUtils.print("You have obtained a bomb!");
			startJourney();
		};
		
		actions[2] = () ->
		{
			TextUtils.print("You run like the goblin is trying to kill you.");
			TextUtils.print("You hear the goblin yelling at you...");
			TextUtils.print("Goblin: WAIT!!!!!!! I JUST NEED HELP!!!!!");
			TextUtils.print("You feel a little bad for the goblin but you keep running anyways");
			startJourney();
		};

		gameWindow.setButtonActions(labels, actions);
		
	}
	
	
	private void robberyEvent()
	{
		TextUtils.print("You have been robbed by a goblin...");
		TextUtils.print("You lost  gold but managed to take its dagger as it ran away");
		player.changeCurrency(-25);
		player.addItem("Knife");
		startJourney();
	}
	
	private void ogreBlocking()
	{
		TextUtils.print("An ogre attacks you");
		TextUtils.print("You draw your weapon and take a fighting stance" );
		Enemy ogre = new Ogre();
		combatManager.startBattle(ogre);
		
	
		
	}
	public Item getItem(String itemName)
	{
		return  itemDatabase.get(itemName);
	}
	
}

package game;

import java.util.ArrayList;

import java.util.List;

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

	private Player player;//has-a
	private List<Occurrence> possibleOccurrences = new ArrayList<>(); //has-many Occurrences stores all possible occurrences that can be randomly selected
	private int occurrenceCount = 0;//has-a number of events or things have happened
	private GameWindow gameWindow = new GameWindow();//has-a 
	
	private CombatManager combatManager;

	
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
		TextUtils.print("Hello! Welcome!");


		player = new Player("You", 100, 20);

		mainMenu();// opens main menu

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
			
		actions[0] = () ->
		{
			combatManager = new CombatManager(player, this);
			TextUtils.print("\nYour journey begins...");
			startJourney();
		};
		actions[1] = () ->
		{
			player.showStats();
		};
		actions[2] = () ->
		{
			loadGame();
			startJourney();
		};
		actions[3] = () ->
		{
			//quit
			System.exit(0);
		};
		
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
			saveGame();
			TextUtils.print("Game Saved");
			mainMenu();
		};
		
		gameWindow.setButtonActions(labels, actions);
		
	}

	public GameWindow getGameWindow()
	{
		return this.gameWindow ;
	}
	


	private void saveGame()
	{
		// TODO Auto-generated method stub
	}
	
	private void loadGame()
	{
		// TODO Auto-generated method stub	
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
				"Strange Cheese",
				"You find a cheese in the middle of the road.",
				() -> ratQuest()
				));
		possibleOccurrences.add(new Occurrence(
				"Ogre roadblock",
				"An ogre is standing in front of you...",
				() -> ogreBlocking()
				));
	}
	
	
	private void triggerRandomOccurrence()
	{
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
							

			
			
			int index = (int)(Math.random() * possibleOccurrences.size());
			
			Occurrence event = possibleOccurrences.get(index);
			TextUtils.print("\nEvent: " + event.getName());
			TextUtils.print(event.getDescription());
			
			event.trigger();
			occurrenceCount += 1;
		}
		else
		{
			
			TextUtils.print("You have run out of funds... you cannot continue...");
			TextUtils.print("The journey has come to an abrupt end...");
			mainMenu();
		}
		
	}
	
	private void warriorEvent()
	{
		
		String[] labels = {"Fight", "Talk", "Ignore"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("Choose 1. Fight them, 2. Talk to them, 3. Ignore them");
		
		
		
		
		actions[0] = () ->
		{
			TextUtils.print("You draw your weapon and take a fighting stance");
			Enemy warrior = new Warrior();
			combatManager.startBattle(warrior);
		};
		actions[1] = () ->
		{
			TextUtils.print("You decide to say a few words.");
			TextUtils.print("Greetings fellow traveler. Take this potion and stay safe.");
			player.addItem("Potion");
			TextUtils.print("You obtained a potion!");
			startJourney();
		};
		
		actions[2] = () ->
		{
			TextUtils.print("You walk past the warrior, and glance back over your shoulder... He is glaring at you.");
			TextUtils.print("You start running away and trip...");
			player.changeCurrency(-30);
			startJourney();
		};

		gameWindow.setButtonActions(labels, actions);

		
	}
	
	
	private void wolfAttack()
	{
		TextUtils.print("You ready yourself for a fight");
		Enemy wolf = new Wolf();
		combatManager.startBattle(wolf);
	}
	
	
	private void ratQuest()
	{	
		
		String[] labels = {"Eat", "Hit"};
		Runnable[] actions = new Runnable[4];
		TextUtils.print("Choose 1. Eat the cheese, 2. hit the cheese");
		
		actions[0] = () ->
		{
			TextUtils.print("You eat the cheese.");
			player.heal(10);
			player.increaseAttack(2);
			TextUtils.print("You heal 10 health.");
			TextUtils.print("You gain 2 attack.");
			startJourney();
		};
		actions[1] = () ->
		{
			TextUtils.print("An offended rat comes up to you and attacks!");
			TextUtils.print("You ready yourself for a fight");
			Enemy rat = new Rat();
			combatManager.startBattle(rat);
				
		};
		
		actions[2] = () ->
		{
			TextUtils.print("You walk past the warrior, and glance back over your shoulder... He is glaring at you.");
			TextUtils.print("You start running away and trip...");
			player.changeCurrency(-30);
			startJourney();
		};

		gameWindow.setButtonActions(labels, actions);
		
		


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
	
}

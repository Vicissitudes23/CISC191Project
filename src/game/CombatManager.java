package game;

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
 * Use of Lambdas was encouraged by my friend Mofeng Atlass as I was discussing how I could run the events from different functions
 * I then did research on how I could use it properly
 * Retrieved April 20, 2026 from https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#use-case
 * Version/date: 
 * 
 * Responsibilities of class:
 * 
 */
/**
 */

public class CombatManager
{
	private MainGame game;//has-a
	private Player player;//has-a

	
	
	public CombatManager(Player playerCharacter, MainGame mainGame)
	{
		this.game = mainGame;
		this.player = playerCharacter;
	
	}
	
	public void startBattle(Enemy enemy) 
	{
		TextUtils.print("The " + enemy.getName() + " readies itself for a fight!");
		playerTurn(enemy);
	}
	
	public boolean checkEndCombat(Enemy enemy)
	{
		if (player.isAlive() && enemy.isAlive())//if the enemy and player are alive checks
		{
			return false;
		}
		endBattle(enemy);
		return true;
	}
	
	public void endBattle(Enemy enemy)
	{
		if (player.isAlive())//if the player is alive the player wins
		{
			TextUtils.print("You defeated the " + enemy.getName() + "!");
			game.startJourney();
		}
		else//if the player is not alive the player loses
		{
			TextUtils.print("You were defeated...");
			game.mainMenu();
		}

	        	
	}
	
	//start the player's turn
	private void playerTurn(Enemy enemy)
	{
		//reset block at the start of each turn
		player.endBlock();
		
		TextUtils.print("\n=== PLAYER TURN=== ");
		TextUtils.print("1. Attack, 2. Guard, 3. Use Item, 4. Status");
		
		String[] labels = {"Attack", "Guard", "Items", "Status"};
		Runnable[] actions = new Runnable[4];

		
		
		
		//player attacks
		actions[0] = () ->
		{
			int damage = player.getAttack();
			TextUtils.print("Your attack hits, dealing " + damage + " damage!");
			enemy.takeDamage(damage);
			checkEndCombat(enemy);
			enemyTurn(enemy);
		};
		//player defends
		actions[1] = () ->
		{
			TextUtils.print("You brace yourself for an incoming attack!");
			player.startBlock();
			enemyTurn(enemy);
		};
		//player checks bag
		actions[2] = () ->
		{
			 player.showInventory();//prints out the player's inventory
			 String[] inventory = player.getInventory();  //puts the player's items into this array
			 String[] itemLabels = new String[inventory.length + 1]; //contains the names of items
			 Runnable[] itemActions = new Runnable[itemLabels.length];//contains the runnable segments in this array

			 for (int i = 0; i < inventory.length; i++) //adds the item names and runnable segments into their respective arrays
			 {
				 String itemName = inventory[i];
				 itemLabels[i] = (itemName != null) ? itemName : "Empty Slot";
				 int index = i;
				 
				 //if the item exists run its runnable
				 //if it doesnt return back to player menu
				 itemActions[i] = () -> 
				 {
					 if (inventory[index] != null) 
					 {
						 player.useItem(inventory[index], enemy, index);
						 checkEndCombat(enemy);
						 enemyTurn(enemy);
					 } 
					 else 
					 {
						 TextUtils.print("That slot is empty.");
						 playerTurn(enemy); // re-open player turn
					 }
				 };      
			 } 
			 itemLabels[3] = "Cancel";//changes the 4th button to the cancel button
			 itemActions[3] = () -> playerTurn(enemy);//makes the 4th button return back to the player's turn menu
			 game.getGameWindow().setButtonActions(itemLabels, itemActions);//changes the buttons to the players items
			 
		};
		//player checks status
		actions[3] = () ->
		{
			player.showStats();
				playerTurn(enemy);
		};

		
		game.getGameWindow().setButtonActions(labels, actions);//sets the buttons in the GameWindow to reflect the player's items
		
	
	}
	
	//function to star the enemy's turn
	private void enemyTurn(Enemy enemy)
	{
		if (enemy.getHealth() > 0)
		{
			TextUtils.print("\n=== Opposing " + enemy.getName() + "'s turn ===");

			//enemy takes their turn, behavior will change based on their class
			enemy.takeTurn(player);
			if (checkEndCombat(enemy)== false) 
			{
				playerTurn(enemy);
			}
		}
				

		
	}
	
	
	
	
}

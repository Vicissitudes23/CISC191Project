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
 *  
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
		if (player.isAlive() && enemy.isAlive())
		{
			return false;
		}
		endBattle(enemy);
		return true;
	}
	
	public void endBattle(Enemy enemy)
	{
		if (player.isAlive())
		{
			TextUtils.print("You defeated the " + enemy.getName() + "!");
			game.startJourney();
		}
		else
		{
			TextUtils.print("You were defeated...");
			game.mainMenu();
		}

	        	
	}
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
			 player.showInventory();
			 String[] inventory = player.getInventory();
			 String[] itemLabels = new String[inventory.length + 1];
			 Runnable[] itemActions = new Runnable[itemLabels.length];

			 for (int i = 0; i < inventory.length; i++) 
			 {
				 String itemName = inventory[i];
				 itemLabels[i] = (itemName != null) ? itemName : "Empty Slot";
				 int index = i;
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
			 itemLabels[3] = "Cancel";
			 itemActions[3] = () -> playerTurn(enemy);
			 game.getGameWindow().setButtonActions(itemLabels, itemActions);
			 
		};
		//player checks status
		actions[3] = () ->
		{
			player.showStats();
				playerTurn(enemy);
		};

		
		game.getGameWindow().setButtonActions(labels, actions);
		
	
	}
	
	
	private void enemyTurn(Enemy enemy)
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

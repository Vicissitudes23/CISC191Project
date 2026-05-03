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
	private Scanner sc = new Scanner(System.in);
	
	
	public CombatManager(Player playerCharacter, MainGame mainGame)
	{
		this.game = mainGame;
		this.player = playerCharacter;
	
	}
	
	public void startBattle(Enemy enemy) 
	{
		TextUtils.slowPrint("The " + enemy.getName() + " readies itself for a fight!", 30);

	    while (player.getHealth() > 0 && enemy.isAlive()) {

	        boolean turnUsed = false;

	        // Player can keep acting until they use a turn
	        while (!turnUsed) {
	            turnUsed = playerTurn(enemy);
	            if (!enemy.isAlive()) {
	            	TextUtils.slowPrint("You defeated the " + enemy.getName() + "!", 30);
	                return;
	            }
	        }

	        // Enemy turn only happens if player used a turn
	        if (enemyTurn(enemy)) {
	        	TextUtils.slowPrint("You were defeated...", 30);
	            game.mainMenu();
	        }
	    }
	}
	private boolean playerTurn(Enemy enemy)
	{
		player.endBlock();
		
		TextUtils.slowPrint("\n=== PLAYER TURN=== ", 20);
		TextUtils.slowPrint("1. Attack, 2. Guard, 3. Use Item, 4. Status", 15);
		
		String choice = sc.nextLine();
		
		switch (choice)
		{
			case "1" ://player attacks
				int damage = player.getAttack();
				TextUtils.slowPrint("Your attack hits, dealing " + damage + " damage!", 15);
				enemy.takeDamage(damage);

				
				
				return true;
				
			case "2"://player blocks
				TextUtils.slowPrint("You brace yourself for an incoming attack!", 10);
				player.startBlock();

				return true;
				
			case "3"://player opens bag
			    player.showInventory();
			    TextUtils.slowPrint("Select an item number or 0 to cancel:", 15);

			    int index;
			    try {
			        index = Integer.parseInt(sc.nextLine()) - 1;
			    } catch (NumberFormatException e) {
			        TextUtils.slowPrint("Invalid input.", 20);
			        return false;
			    }

			    // Cancel
			    if (index == -1) {
			        TextUtils.slowPrint("Cancelled.", 15);
			        return false;
			    }

			    // Out of bounds
			    if (index < 0 || index >= player.getInventory().length) {
			        TextUtils.slowPrint("Invalid slot.", 20);
			        return false;
			    }

			    String itemName = player.getInventory()[index];

			    if (itemName == null) {
			        TextUtils.slowPrint("That slot is empty.", 15);
			        return false;
			    }

			    // Use item (consumes turn)
			    player.useItem(itemName, enemy, index);
			    return true;

				
			case "4"://player wants to see their status
				player.showStats();
				return false;
		}
		
		return false;//enemy still alive
	
	}
	
	
	private boolean enemyTurn(Enemy enemy)
	{
		System.out.println("\n=== Opposing " + enemy.getName() + "'s turn ===");

		//enemy takes their turn, behavior will change based on their class
		enemy.takeTurn(player);
		
		return player.getHealth() <=0; //true if player is dead
	}
	
	
	
	
}

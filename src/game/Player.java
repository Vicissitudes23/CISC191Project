package game;

import java.util.HashMap;
import java.util.Map;

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

public class Player extends BaseCharacter
//is-a basecharacter
{
	private int currencyAmount;//has-a
	private MainGame game; //has-a
	private String[] inventory = new String[3];//has-many item identifiers uses hashmap
	
	
	public Player(String name, int health, int attack, MainGame game)
	{
		super(name, health, attack);
		this.game = game;
		this.currencyAmount = 200;
		
	}
	
	public int getCurrency()
	{
		return this.currencyAmount;
	}
	
	public String[] getInventory()
	{
		return inventory;
	}
	
	
	public void setCurrency(int amount)
	{
		this.currencyAmount = amount;
	}
	
	public void changeCurrency(int amount)//adds or subtracts currency
	{
		if (amount < 0)
		{
			TextUtils.print("You have lost " + Math.abs(amount) + " gold...");
		}
		else
		{
			TextUtils.print("You have gained " + amount + " gold!");
		}
		this.currencyAmount += amount;
		TextUtils.print("You now have " + this.currencyAmount + " gold");
	}
	
	public boolean addItem(String string) 
	{
	    for (int i = 0; i < inventory.length; i++) {
	        if (inventory[i] == null) {
	            inventory[i] = string;
	            return true; // success
	        }
	    }
	    return false; // inventory full
	}
	
	public void showInventory() 
	{
		TextUtils.print("\n=== Inventory ===");

	    for (int i = 0; i < inventory.length; i++) {
	    	TextUtils.print((i + 1) + ": " +
	            (inventory[i] == null ? "[Empty]" : inventory[i]));
	    }
	}

	

	
	
	public void useItem(String itemName, Enemy enemy, int slot) 
	{
	    Item item = game.getItem(itemName);

	    if (item == null) {
	    	TextUtils.print("Unknown item.");
	        return;
	    }

	    switch (item.getName()) {

	        case "Potion":
	            heal(item.getPower());
	            TextUtils.print(
	                "You recover " + item.getPower() + " HP!"
	            );
	            break;

	        case "Bomb":
	            enemy.takeDamage(item.getPower());
	            TextUtils.print(
	                "The bomb explodes for " + item.getPower() + " damage!"
	            );
	            break;

	        case "Knife":
	            enemy.takeDamage(item.getPower());
	            TextUtils.print(
	                "The knife pierces through the enemy entirely, dealing "
	                + item.getPower() + " damage!"
	            );
	            break;
	    }

	    // Consume Item
	    getInventory()[slot] = null;
	}


	
	

	

}

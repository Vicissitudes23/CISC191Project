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
	private int currency;//has-a
	private String[] inventory = new String[10];//has-many item identifiers uses hashmap
	private Map<String, Item> itemDatabase = new HashMap<>();//has-many possible items
	
	public Player(String name, int health, int attack)
	{
		super(name, health, attack);
		setupItems();
		this.currency = 200;
		
	}
	
	public int getCurrency()
	{
		return this.currency;
	}
	
	public String[] getInventory()
	{
		return inventory;
	}
	
	
	public void setCurrency(int amount)
	{
		this.currency = amount;
	}
	
	public void changeCurrency(int amount)//adds or subtracts currency
	{
		if (amount < 0)
		{
			TextUtils.slowPrint("You have lost " + Math.abs(amount) + " gold...", 30);
		}
		else
		{
			TextUtils.slowPrint("You have gained " + amount + " gold!", 30);
		}
		this.currency += amount;
		TextUtils.slowPrint("You now have " + this.currency + " gold", 30);
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
	    System.out.println("\n=== Inventory ===");

	    for (int i = 0; i < inventory.length; i++) {
	        System.out.println((i + 1) + ": " +
	            (inventory[i] == null ? "[Empty]" : inventory[i]));
	    }
	}

	
	private void setupItems()
	{
		itemDatabase.put("Potion", new Item("Potion", "Restores 20 HP", 20));
		itemDatabase.put("Super Potion", new Item("Super Potion", "Restores 20 HP", 20));
		itemDatabase.put("Bomb", new Item("Bomb", "Deals 30 damage to the enemy", 30));
		itemDatabase.put("Knife", new Item("Knife", "Deals double your attack value", getAttack() * 2));
		
	}
	
	
	public void useItem(String itemName, Enemy enemy, int slot) 
	{
	    Item item = itemDatabase.get(itemName);

	    if (item == null) {
	        System.out.println("Unknown item.");
	        return;
	    }

	    switch (item.getName()) {

	        case "Potion":
	            heal(item.getPower());
	            TextUtils.slowPrint(
	                "You recover " + item.getPower() + " HP!", 25
	            );
	            break;

	        case "Bomb":
	            enemy.takeDamage(item.getPower());
	            TextUtils.slowPrint(
	                "The bomb explodes for " + item.getPower() + " damage!", 25
	            );
	            break;

	        case "Knife":
	            enemy.takeDamage(item.getPower());
	            TextUtils.slowPrint(
	                "The knife pierces through the enemy entirely, dealing "
	                + item.getPower() + " damage!", 25
	            );
	            break;
	    }

	    // Consume Item
	    getInventory()[slot] = null;
	}
	
	

	

}

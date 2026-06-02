package game;

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

public class BaseCharacter
{

	private String name;//has-a
	private int healthPoints;//has-a
	private int attackPoints;//has-a
	private boolean guarding;//has-a guarding state to take less damage on the next attack

		
	public BaseCharacter(String name, int health, int attack) //sets character stats
	{
		this.name = name;
		this.healthPoints = health;
		this.attackPoints = attack;
	}
		
	public void takeDamage(int damage)
	{
		if (guarding == true)//when blocking damage is reduced by 25%
		{
			int reducedDamage = damage - (int)(damage *0.25);
			healthPoints -= reducedDamage;
			if (healthPoints < 0) healthPoints = 0; //if the attack did not defeat the character
			TextUtils.print(name + " blocked the attack, reducing the damage of the blow."); 
			TextUtils.print(name + " took " + reducedDamage + " damage!");
			
		}
		else
		{
			healthPoints -= damage; //damage is normal when not blocking
			if (healthPoints < 0) healthPoints = 0; //if the attack did not defeat the character
			TextUtils.print(name + " took " + damage + " damage!");
		}
			
		TextUtils.print(name + " has "+ healthPoints + " health remaining");

		
	}

	//Getters
	public int getHealth()
	{
		return healthPoints;
	}
	
	public int getAttack()
	{
		return attackPoints;
	}
		
	public String getName()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		if (healthPoints > 0)
		{
			return true;
		}
		return false;
	}
		
		
	//Setters
	public void setName(String name)
	{
		this.name = name;
	}
		
	public void setHealth(int amount)
	{
		this.healthPoints = amount;
	}
		
	public void setAttack(int amount)
	{
		this.attackPoints = amount;
	}
		
	public void increaseAttack(int amount)
	{
		this.attackPoints += amount;
	}
	
	public void decreaseAttack(int amount)
	{
		this.attackPoints -= amount;
		if (this.attackPoints < 0)//caps the lower limit to 0
		{
			this.attackPoints = 0;
		}
	}
	
		
	public void heal(int amount)
	{
		healthPoints += amount;
	}
	
	public void startBlock()//Blocking
	{
		guarding = true;
	}
	
	public void endBlock()//sets blocking to false
	{
		if (guarding == true)
		{
			TextUtils.print("You lowered your guard");
		}
		
		guarding = false;
	}
		
		
	//Show Stats
	public void showStats()
	{
		TextUtils.print("\nCharacter: " + name + " | HP: "+ healthPoints + " | attack: " + attackPoints );
	}
	

}

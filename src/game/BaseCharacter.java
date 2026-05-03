package game;
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
	private int health;//has-a
	private int attack;//has-a
	private boolean blocking;

		
	public BaseCharacter(String name, int health, int attack) //sets character stats
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
		
	public void takeDamage(int damage)
	{
		if (blocking == true)//when blocking damage is reduced by 25%
		{
			int reducedDamage = damage - (int)(damage *0.25);
			health -= reducedDamage;
			if (health < 0) health = 0; //if the attack did not defeat the character
			System.out.println(name + " blocked the attack, reducing the damage of the blow."); 
			System.out.println(name + " took " + reducedDamage + " damage!");
			
		}
		else
		{
			health -= damage; //damage is normal when not blocking
			if (health < 0) health = 0; //if the attack did not defeat the character
			System.out.println(name + " took " + damage + " damage!");
		}
			
		System.out.println(name + " has "+ health + " health remaining");

		
	}

	//Getters
	public int getHealth()
	{
		return health;
	}
	
	public int getAttack()
	{
		return attack;
	}
		
	public String getName()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		if (health > 0)
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
		this.health = amount;
	}
		
	public void setAttack(int amount)
	{
		this.attack = amount;
	}
		
	public void increaseAttack(int amount)
	{
		this.attack += amount;
	}
	
	public void decreaseAttack(int amount)
	{
		this.attack -= amount;
		if (this.attack < 0)//caps the lower limit to 0
		{
			this.attack = 0;
		}
	}
		
		
	public void heal(int amount)
	{
		health += amount;
	}
	
	public void startBlock()//Blocking
	{
		blocking = true;
	}
	
	public void endBlock()
	{
		blocking = false;
	}
		
		
	//Show Stats
	public void showStats()
	{
		System.out.println("\nCharacter: " + name + " | HP: "+ health + " | attack: " + attack);
	}
	

}

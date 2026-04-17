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
	private int ATK;//has-a
	private boolean Blocking;

		
	public BaseCharacter(String name, int health, int attack) //sets character stats
	{
		this.name = name;
		this.health = health;
		this.ATK = attack;
	}
		
	public void takeDamage(int damage)
	{
		if (Blocking == true)//when blocking damage is reduced by 25%
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
			
		System.out.println(name + " has "+ health + " remaining");
		
	}

	//Getters
	public int getHealth()
	{
		return health;
	}
	
	public int getAttack()
	{
		return ATK;
	}
		
	public String getName()
	{
		return name;
	}
		
		
		
	//Setters
	public void setName(String name)
	{
		this.name = name;
	}
		
	public void setHP(int hp)
	{
		this.health = hp;
	}
		
	public void setAttack(int attack)
	{
		this.ATK = attack;
	}
		
	public void increaseAttack(int amount)
	{
		this.ATK += amount;
	}
	
	public void decreaseAttack(int amount)
	{
		this.ATK -= amount;
		if (this.ATK < 0)//caps the lower limit to 0
		{
			this.ATK = 0;
		}
	}
		
		
	public void heal(int amount)
	{
		health += amount;
	}
	
	public void StartBlock()//Blocking
	{
		Blocking = true;
	}
	
	public void EndBlock()
	{
		Blocking = false;
	}
		
		
	//Show Stats
	public void showStats()
	{
		System.out.println("\nCharacter: " + name + " | HP: "+ health + " | ATK: " + ATK);
	}
	

}

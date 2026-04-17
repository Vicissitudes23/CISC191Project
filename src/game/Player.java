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

public class Player extends BaseCharacter
//is-a basecharacter
{
	private int currency;//has-a
	public Player(String name, int health, int attack)
	{
		super(name, health, attack);
		
	}
	
	public int getCurrency()
	{
		return this.currency;
	}
	
	public void addCurrency(int amount)
	{
		this.currency += amount;
	}

}

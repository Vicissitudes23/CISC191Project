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

public class Warrior extends game.Enemy
{
	private int turnCount = 1;
	private boolean charged;
	
	public Warrior()
	{
		super("Warrior", 90, 15);
		
	}
	
	
	@Override
	public void takeTurn(Player player)
	{
		if (turnCount == 1)
		{
			TextUtils.print("The " + getName() + "'s aura is devestating your mind.");
			
		}
		else
		{
			if (charged)
			{
				TextUtils.print("The " + getName() + " cleaves you in two!!!");
				player.takeDamage(50);
				charged = false;
				
			}
			else
			{
				charged = true;
				TextUtils.print("The " + getName() + "'s aura flares up");
			}
		}
		
		
		turnCount += 1;
	}
}
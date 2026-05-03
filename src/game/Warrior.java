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
	private int turn = 1;
	private boolean charged;
	
	public Warrior()
	{
		super("Warrior", 90, 15);
		
	}
	
	
	@Override
	public void takeTurn(Player player)
	{
		if (turn == 1)
		{
			TextUtils.slowPrint("The " + getName() + "'s aura is devestating your mind.", 20);
			
		}
		else
		{
			if (charged)
			{
				TextUtils.slowPrint("The " + getName() + " cleaves you in two!!!", 20 );
				player.takeDamage(50);
				charged = false;
				
			}
			else
			{
				charged = true;
				TextUtils.slowPrint("The " + getName() + "'s aura flares up", 30);
			}
		}
		
		
		turn += 1;
	}
}
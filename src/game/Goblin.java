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

import util.TextUtils;

public class Goblin extends Enemy
//is-a enemy
{
	public Goblin()
	{
		super("Goblin", 20, 10);
	}
	
	@Override
	public void takeTurn(Player player)
	{
		
		TextUtils.slowPrint("The goblin strikes you!", 20 );

		player.takeDamage(getAttack());
	}
}

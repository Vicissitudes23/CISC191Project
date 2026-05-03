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

public class Wolf extends Enemy
{
	
	public Wolf()
	{
		super("Wolf", 35, 10);
	}
	
	
	@Override
	public void takeTurn(Player player)
	{
		
		TextUtils.slowPrint("The Wolf bites you and drains your health!", 20 );
		
		

		player.takeDamage(getAttack());
		TextUtils.slowPrint("The wolf has gained " + 10 + " health", 20);
	}
}
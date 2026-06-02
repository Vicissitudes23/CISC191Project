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
public class Rat extends Enemy
{
	private int turnCount = 1;//has-a
	private int biteCount;//has-a
	public Rat()
	{
		super("Rat", 90, 2);
	}

	@Override
	public void takeTurn(Player player)
	{
		
		TextUtils.print("The Rat bites you, and it really hurts" );
		player.takeDamage(getAttack());
		biteCount += 1;
		
		if (turnCount != 1)
		{
			
			TextUtils.print("Your previous wounds hurt so much..." );//player takes extra damage every turn
			player.takeDamage(biteCount *4);
		}
		turnCount += 1;
	}
}

	
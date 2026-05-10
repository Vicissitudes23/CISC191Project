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

public class Ogre extends Enemy
//is-a enemy
{
	private int rage = 0;//has-a
	private int turn = 1;//has-a
	
	public Ogre()
	{
		super("Ogre", 80, 25);
	}
	


	@Override
	public void takeTurn(Player player)
	{
		if (turn == 1)
		{
			TextUtils.print("The ogre slowly walks up to you while readying itself");
			rage += 2;
		}
		else 
		{
			int damage = getAttack() + rage;
			TextUtils.print("The ogre angrily swings at you" );
			player.takeDamage(damage);
			
			TextUtils.print("The ogre's face reddens");
			this.increaseAttack(5);
		}
		turn += 1;
			
	}
}

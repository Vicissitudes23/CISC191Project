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

public class Occurrence
{
	private String name;//has-a
	private String description;//has-a
	private Runnable action;//has-a
	
	public Occurrence(String name, String description, Runnable action)
	{
		this.name = name;
		this.description = description;
		this.action = action;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public void trigger()
	{
		action.run();
	}
}

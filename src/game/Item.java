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
public class Item
{
	private String name;//has-a
	private String description;//has-a
	private int power;//has-a
	
	
	
	public Item(String name, String description, int power)
	{
		this.name = name;
		this.description = description;
		this.power = power;
	}

	public String getName()
	{
		return name;
	}

	public int getPower()
	{
		return power;
	}
	public String getDescription()
	{
		return description;
	}
	
}

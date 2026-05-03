package util;
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
 * Oracle. (n.d.). Thread (Java Platform SE 8 ). Oracle.
 * Retrieved April 22, 2026, from https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
 * got the idea from observing unit test from file IO module
 *  
 * Version/date: 
 * 
 * Responsibilities of class:
 * 
 */
/**
 */
public class TextUtils
{
	public static void slowPrint(String text, int duration)
	{
		for (int i = 0; i <text.length(); i++)
		{
			System.out.print(text.charAt(i));
			try
			{
				Thread.sleep(duration);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			
		}
		System.out.println();
	}

}
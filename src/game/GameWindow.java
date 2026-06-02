package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


import javax.swing.*;
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
public class GameWindow extends JFrame
//is-a JFrame
{

	//change to "this" for all mainFrame variables

	private JTextArea textArea;//has-a
	private JPanel buttonPanel;//has-a
	private Runnable[] buttonActions = new Runnable[4];
	
	
	//the 4 total buttons are all on the same line and change based on the current occurrence
	private JButton menuButton1;//has-a the first button in the gui
	private JButton menuButton2;//has-a the second button in the gui
	private JButton menuButton3;//has-a the third button in the gui
	private JButton menuButton4;//has-a the fourth button in the gui
	
	
	
	public GameWindow()
	{

		TextUtils.setWindow(this);
		
		setTitle("Adventure");
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(textArea);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		menuButton1 = new JButton("1"); // signal 1
		menuButton2 = new JButton("2"); // signal 2
		menuButton3 = new JButton("3"); // signal 3
		menuButton4 = new JButton("4"); // signal 4
		
		//adds ActionListeners to each button
		menuButton1.addActionListener(e-> runButton(0));
		menuButton2.addActionListener(e-> runButton(1));
		menuButton3.addActionListener(e-> runButton(2));
		menuButton4.addActionListener(e-> runButton(3));
		
		
		this.setLayout(new BorderLayout());
		this.add(scrollPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.add(menuButton1);
		buttonPanel.add(menuButton2);
		buttonPanel.add(menuButton3);
		buttonPanel.add(menuButton4);
		
		
		this.setSize(600,500);
		this.setVisible(true);
		
		
	}
	
		public JFrame getMainFrame()
	{
		return this;
	}

	
	private void runButton(int index) 
	{
		if (buttonActions[index] != null) 
		{
			buttonActions[index].run(); //runs the button's runnable action
		}
	}
	
	//sets each button's actions and text
	public void setButtonActions(String[] labels, Runnable[] actions)
	{
		//iterates through 4 buttons
		for (int i = 0; i < 4; i ++)
		{
			//checks to see if there are enough labels for each button
			if (i < labels.length && i < actions.length)
			{
				buttonActions[i] = actions[i];
				setButtonEnabled(i, true);
				setButtonText(i, labels[i]);
				
				
				
			}
			//if there are too many buttons for the given actions disable the extra buttons
			else
			{
				buttonActions[i] = null;//makes this button in buttonActions null
				setButtonText(i, "");//clears the text on the button
				setButtonEnabled(i, false);//disables the button
				
			}
		}
	}

	//sets the text of the corresponding index parameter
	private void setButtonText(int index, String text) 
	{
		
		switch (index) 
	    {
	    	case 0:
	    		menuButton1.setText(text);
	    		break;
	    	case 1:
	    		menuButton2.setText(text);
	    		break;
	    	case 2:
	    		menuButton3.setText(text);
	    		break;
	    	case 3:
	    		menuButton4.setText(text);
	    		break;
	    	default:
	    		throw new IllegalArgumentException("Index invalid");
	    		
	    }
		
	}
	
	//enables the button of the corresponding buttonNumber parameter
	public void setButtonEnabled(int buttonNumber, boolean enabled)
	{
		switch(buttonNumber)
		{
	    	case 0:
	    		menuButton1.setEnabled(enabled);
	    		break;
	    	case 1:
	    		menuButton2.setEnabled(enabled);
	    		break;
	    	case 2:
	    		menuButton3.setEnabled(enabled);
	    		break;
	    	case 3:
	    		menuButton4.setEnabled(enabled);
	    		break;
	    	default:
	    		throw new IllegalArgumentException("Button does not exist");
	    	
			
		}
	}
	

	public void print(String text)
	{
		textArea.append(text); //converts the char into a string and appends it to the text area
	}

	public void newLine()
	{
		textArea.append("\n");//creates a new line in the text area
		
	}
}

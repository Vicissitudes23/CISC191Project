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
public class GameWindow
{
	private JFrame mainFrame;//has-a
	private JTextArea textArea;//has-a
	private JPanel buttonPanel;//has-a
	private Runnable[] buttonActions = new Runnable[4];
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	
	
	
	public GameWindow()
	{

		TextUtils.setWindow(this);
		mainFrame = new JFrame("Adventure");
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(textArea);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		button1 = new JButton("1"); // signal 1
		button2 = new JButton("2"); // signal 2
		button3 = new JButton("3"); // signal 3
		button4 = new JButton("4"); // signal 4
		
		button1.addActionListener(e-> runButton(0));
		button2.addActionListener(e-> runButton(1));
		button3.addActionListener(e-> runButton(2));
		button4.addActionListener(e-> runButton(3));
		
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(scrollPanel, BorderLayout.CENTER);
		mainFrame.add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		
		
		mainFrame.setSize(600,500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		
	}
	
	
	private void runButton(int index) 
	{
		if (buttonActions[index] != null) 
		{
			buttonActions[index].run();
		}
	}
	
	
	public void setButtonActions(String[] labels, Runnable[] actions)
	{
		for (int i = 0; i < 4; i ++)
		{
			if (i < labels.length && i < actions.length)
			{
				buttonActions[i] = actions[i];
				setButtonEnabled(i, true);
				setButtonText(i, labels[i]);
				
				
				
			}
			else
			{
				buttonActions[i] = null;
				setButtonText(i, "");
				setButtonEnabled(i, false);
				
			}
		}
	}

	private void setButtonText(int index, String text) 
	{
		switch (index) 
	    {
	    	case 0 -> button1.setText(text);
	    	case 1 -> button2.setText(text);
	    	case 2 -> button3.setText(text);
	    	case 3 -> button4.setText(text);
	    }
	}
	
	public void setButtonEnabled(int buttonNumber, boolean enabled)
	{
		switch(buttonNumber)
		{
			case 0 -> button1.setEnabled(enabled);
			case 1 -> button2.setEnabled(enabled);
			case 2 -> button3.setEnabled(enabled);
			case 3 -> button4.setEnabled(enabled);
			
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

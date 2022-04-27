import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
public class GUI2 {
	//GUI constructor that holds all the GUI Info
	public GUI2() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		JButton calc = new JButton("Calculate");
		//label for current level
		JLabel label = new JLabel("Current Level");
		label.setBounds(10,20,80,25);
		//label for the level the user would like to reach
		JLabel label2 = new JLabel("Desired Level");
		label2.setBounds(10,40,100,50);
		//label for how many runes the user recieves per kill
		JLabel label3 = new JLabel("Runes on kill");
		label3.setBounds(10,60,120,70);
		//calculates for the first for loop data down below
		JLabel calcData = new JLabel("");
		calcData.setBounds(10,140,300,100);
		panel.add(calcData);
		//Label to display second for loop data down below
		JLabel calcData2 = new JLabel("");
		calcData2.setBounds(10,160,300,100);
		panel.add(calcData2);
		
		frame.setResizable(false);
		frame.setSize(450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		frame.setTitle("Elden Ring Calculator");
		
		panel.setLayout(null);
		panel.add(label);
		panel.add(label2);
		panel.add(label3);
		panel.setBackground(Color.GREEN);
		//text field to recieve input for current level label
		JTextField startRune = new JTextField();
		startRune.setBounds(100,20,165,25);
		panel.add(startRune);
		//text field to recieve input for desired level label
		JTextField endRune = new JTextField();
		endRune.setBounds(100,50,165,25);
		panel.add(endRune);
		//text field to recieve input for the amount of runes per kill 
		JTextField runePerKill = new JTextField();
		runePerKill.setBounds(100,80,165,25);
		panel.add(runePerKill);
		//button to get pressed to calculate user data and further display calcData and calcData2
		//JButton calc = new JButton("Calculate");
		calc.setBounds(10,120,120,25);
		calc.addActionListener(null);
		panel.add(calc);
		
		frame.setVisible(true);
		
		//Action listener to give button functionality 
		calc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//arraylist to store user info
			try {
			ArrayList<Integer> userArray = new ArrayList<Integer>();
			int start = Integer.parseInt(startRune.getText()); //gets the starting level and coverts it to an int for the calculator to accept
			userArray.add(start); //adds first value to arraylist
			
			int end = Integer.parseInt(endRune.getText()); //gets the desired level and coverts it to an int for the calculator to accept
			userArray.add(end); //adds desired level to arraylist
			
			int kill = Integer.parseInt(runePerKill.getText()); //gets the runes on kill and coverts it to an int for the calculator to accept
			userArray.add(kill); //adds runes per kill to arraylist
			
			File fileName = new File(""); //change to your file path
			
			
			int usedRunes = 0;
			int neededRunes = 0;
			
			if(start < 12) {
				JOptionPane.showMessageDialog(calc, "Starting level must be 12 or higher.");
			} 
			else if(start <= end) {
				for(int i = 12; i <= start; i++) { //for loop to add the cost of the current level from 12 and up(Formula will only work from level 12 and up)
					usedRunes = (int) (usedRunes + (0.02 * Math.pow(i, 3) + 3.06 * Math.pow(i, 2) + 105.6 * i - 895)); //formula that finds and adds the cost of each level
				}
				for(int i = 12; i <= end; i++) { //repeats the loop above but until it reaches the desired level
					neededRunes = (int) (neededRunes + (0.02 * Math.pow(i, 3) + 3.06 * Math.pow(i, 2) + 105.6 * i - 895)); 
				}
				int calc_data = neededRunes - usedRunes;
				userArray.add(calc_data);
				calcData.setText("Runes needed to level are: " + calc_data); //rounds the total down to a whole number and the difference for runes
				
				int calc_data2 = (neededRunes - usedRunes)/kill;
				userArray.add((int) calc_data2);
				calcData2.setText("You will need to farm " + calc_data2 + " times!");
				
				String save = userArray.toString() + "test5.txt"; //saves user data to file 
				File userSave = new File(save);
				userSave.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));				
				writer.write(userArray.toString());
				writer.close(); 
				
			}
			else if(start >= end) {
				JOptionPane.showMessageDialog(calc, "Starting level cannot exceed desired level or be the same.");
			}
				//this loop writes the array to a text file for the user to access wfor personal use
			}catch (IOException e1) {
				e1.printStackTrace();
			} catch(Exception e1) { //both NullPointerException and NumberFormatException are caught by the general Exception. Those are the two most likely to occur. 
				JOptionPane.showMessageDialog(null, "Input isn't a number or input was entered as a decimal value, try again."); //Alert message asked for in instructions.
			}
			
			} 
		});
	}
}


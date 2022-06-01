import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class testjump implements ActionListener{
	//properties
	testlogic tlmodel;
	tjpanel thepanel = new tjpanel();
	JFrame theframe = new JFrame("test jump");
	
	//methods
	public void actionPerformed(ActionEvent evt){
	}
	
	//constructor
	public testjump(){
		thepanel.setPreferredSize(new Dimension(800, 600));
		
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);
	}
	
	//main method
	public static void main(String[] args){
		new testjump();
	}

}

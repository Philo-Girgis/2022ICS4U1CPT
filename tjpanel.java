import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class tjpanel extends JPanel{
	//properties
	int intBallY = 100;
	int intBallX = 100;
	int intXchange = 0;
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(-100,400,1000,500);
		g.setColor(Color.RED);
		g.fillRect(intBallX,intBallY,20,20);
		//change ball x
		intBallX = intBallX + intXchange;
		
	}
	
	//constructor
	public tjpanel(){
	}
}

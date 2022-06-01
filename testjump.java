import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class testjump implements ActionListener, KeyListener{
	//properties
	tjpanel thepanel = new tjpanel();
	JFrame theframe = new JFrame("test jump");
	Timer theTimer = new Timer (1000/60, this);
	boolean blnjump = false;
	testlogic tlmodel = new testlogic();
	double dblTime = 0;
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thepanel.repaint();
			if(blnjump == true){
				thepanel.intBallY = tlmodel.jump(thepanel.intBallY, dblTime);
			}
			thepanel.intBallY = tlmodel.freefall(thepanel.intBallY, dblTime);
			
			//time
			dblTime = tlmodel.time(dblTime);
			
		}
		//free fall
		
	}
	
	public void keyReleased(KeyEvent evt){
	}
	public void keyPressed(KeyEvent evt){	
	}
	
	public void keyTyped(KeyEvent evt){
		if(evt.getKeyChar() == ' '){
			blnjump = true;
		}
	}
	
	//constructor
	public testjump(){
		
		thepanel.setPreferredSize(new Dimension(800, 600));
		
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);
		theframe.addKeyListener(this);
		theTimer.start();
	}
	
	//main method
	public static void main(String[] args){
		new testjump();
	}

}

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
	boolean blnstop = false;
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thepanel.repaint();
			
			//time
			if(blnstop == false || blnjump == true){
				dblTime = tlmodel.time(dblTime);
				blnstop = false;
			}
			
			//jump
			if(blnjump == true){
				thepanel.intBallY = tlmodel.jump(thepanel.intBallY, dblTime);
			}
			
			//freefall
			if(blnstop == false){
				thepanel.intBallY = tlmodel.freefall(thepanel.intBallY, dblTime);
			}
			
			//stop
			blnstop = tlmodel.stop(thepanel.intBallY);
			if(blnstop == true){
				dblTime = 0;
				thepanel.intBallY = 400-20;
				blnjump = false;
			}
			
		}
		
	}
	
	public void keyReleased(KeyEvent evt){
	}
	public void keyPressed(KeyEvent evt){	
	}
	
	//jump
	public void keyTyped(KeyEvent evt){
		if(evt.getKeyChar() == ' ' && thepanel.intBallY == 400-20){
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

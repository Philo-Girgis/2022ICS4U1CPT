import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PicoPark implements ActionListener, KeyListener{
	//properties
	JFrame theframe = new JFrame("Pico Park");
	Menu menupanel = new Menu();
	Lobby lobbypanel = new Lobby();
	Help helppanel = new Help();
	Levels levelspanel = new Levels();
	Timer thetimer = new Timer(1000/60, this);
	JButton joinbutton = new JButton("Join");
	JButton hostbutton = new JButton("Host");
	JButton helpbutton = new JButton("Help");
	//Should the port number be a set value?
	//that way players just have to enter the ip address of who they want to join
	//i'm gonna go with that for now cause it makes things simpler
	//which means we gotta pick a constant port number now
	JTextField joinfield = new JTextField();
	JLabel faillabel = new JLabel("");
	//faillabel.setFont(new Font) should allow me to change the font
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			menupanel.repaint();
			lobbypanel.repaint();
			helppanel.repaint();
			levelspanel.repaint();
		}
	}
	
	public void keyPressed(KeyEvent evt){
		
	}
	
	public void keyReleased(KeyEvent evt){
		
	}
	
	public void keyTyped(KeyEvent evt){
		
	}
	
	//constructor
	public PicoPark(){
		//panels
		menupanel.setPreferredSize(new Dimension(1280, 720));
		menupanel.setLayout(null);
		lobbypanel.setPreferredSize(new Dimension(1280, 720));
		lobbypanel.setLayout(null);
		helppanel.setPreferredSize(new Dimension(1280, 720));
		helppanel.setLayout(null);
		levelspanel.setPreferredSize(new Dimension(1280, 720));
		levelspanel.setLayout(null);
		
		//add buttons to panels
		//add action listeners to buttons
		
		//frame
		theframe.setContentPane(menupanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);
		theframe.addKeyListener(this);
		thetimer.start();
		
	}
	
	//main method
	public static void main(String[] args){
		new PicoPark();
	}
	
}

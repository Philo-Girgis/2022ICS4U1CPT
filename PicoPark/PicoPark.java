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
	JButton joinbutton = new JButton("Join Game");
	JButton hostbutton = new JButton("Host Game");
	JButton helpbutton = new JButton("Help Me");
	//Should the port number be a set value?
	//that way players just have to enter the ip address of who they want to join
	//i'm gonna go with that for now cause it makes things simpler
	//which means we gotta pick a constant port number now
	JTextField joinfield = new JTextField();
	JLabel faillabel = new JLabel("", SwingConstants.CENTER);
	JLabel iplabel = new JLabel("Enter Server IP Address", SwingConstants.CENTER);
	//faillabel.setFont(new Font) should allow me to change the font
	//or maybe i won't use it
	JButton menubutton = new JButton("Main Menu");
	
	//gonna work on help menu next to set up the player input stuff
	//gonna create a csv for this one too
	//gonna worry about supersocketmaster only once i create the lobby
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			menupanel.repaint();
			lobbypanel.repaint();
			helppanel.repaint();
			levelspanel.repaint();
		}
		
		if(evt.getSource() == helpbutton){
			theframe.setContentPane(helppanel);
			theframe.pack();
		}
		
		if(evt.getSource() == menubutton){
			theframe.setContentPane(menupanel);
			theframe.pack();
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
		hostbutton.setSize(150,50);
		hostbutton.setLocation(300,275);
		hostbutton.addActionListener(this);
		menupanel.add(hostbutton);
		
		joinbutton.setSize(150,50);
		joinbutton.setLocation(565,300);
		joinbutton.addActionListener(this);
		menupanel.add(joinbutton);
		
		helpbutton.setSize(150,50);
		helpbutton.setLocation(830,275);
		helpbutton.addActionListener(this);
		menupanel.add(helpbutton);
		
		menubutton.setSize(150,50);
		menubutton.setLocation(1050,570);
		menubutton.addActionListener(this);
		helppanel.add(menubutton);
		
		//textfield
		joinfield.setSize(150,50);
		joinfield.setLocation(565,250);
		menupanel.add(joinfield);
		
		//labels
		iplabel.setSize(150,50);
		iplabel.setLocation(565,200);
		menupanel.add(iplabel);
		faillabel.setSize(150,40);
		faillabel.setLocation(565,350);
		menupanel.add(faillabel);
		
		
		//frame
		theframe.setContentPane(menupanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

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
	SuperSocketMaster ssm;
	Timer thetimer = new Timer(1000/60, this);
	JButton joinbutton = new JButton("Join Game");
	JButton hostbutton = new JButton("Host Game");
	JButton helpbutton = new JButton("Help Me");
	JTextField joinfield = new JTextField();
	JLabel faillabel = new JLabel("", SwingConstants.CENTER);
	JLabel iplabel = new JLabel("Enter Server IP Address", SwingConstants.CENTER);
	JButton menubutton = new JButton("Main Menu");
	String strpanel = "menu";
	JButton readybutton = new JButton("Ready");
	JButton leavebutton = new JButton("Leave");
	JButton sendbutton = new JButton("Send");
	JTextArea chatarea = new JTextArea();
	JTextField messagefield = new JTextField();
	JScrollPane theScroll = new JScrollPane(chatarea);
	
	//gonna work on help menu next to set up the player input stuff
	//gonna create a csv for this one too
	//gonna worry about supersocketmaster only once i create the lobby
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			if(strpanel.equals("menu")){
				menupanel.repaint();
			}else if(strpanel.equals("lobby")){
				lobbypanel.repaint();
			}else if(strpanel.equals("help")){
				helppanel.repaint();
			}else if(strpanel.equals("level")){
				levelspanel.repaint();
			}
		}
		
		if(evt.getSource() == helpbutton){
			theframe.setContentPane(helppanel);
			theframe.pack();
			//request focus on the frame makes keylistener function
			//this may get complicated when chat comes into play
			//unless i can request focus on the frame once send is entered
			//figure that out later
			theframe.requestFocus();
			//identify which panel we are on
			strpanel = "help";
			//reset menu
			menupanel.intP1X = -250;
			menupanel.intP1Y = 580;
			menupanel.intP2X = -200;
			menupanel.intP2Y = 580;
			menupanel.intP3X = -150;
			menupanel.intP3Y = 580;
			menupanel.intP4X = -100;
			menupanel.intP4Y = 580;
			menupanel.intP5X = -50;
			menupanel.intP5Y = 580;
			menupanel.dblT1 = 0;
			menupanel.dblT2 = 0;
			menupanel.dblT3 = 0;
			menupanel.dblT4 = 0;
			menupanel.dblT5 = 0;
			menupanel.blnjump1 = false;
			menupanel.blnjump2 = false;
			menupanel.blnjump3 = false;
			menupanel.blnjump4 = false;
			menupanel.blnjump5 = false;
			menupanel.blnstop1 = true;
			menupanel.blnstop2 = true;
			menupanel.blnstop3 = true;
			menupanel.blnstop4 = true;
			menupanel.blnstop5 = true;
		}
		
		if(evt.getSource() == menubutton){
			theframe.setContentPane(menupanel);
			theframe.pack();
			strpanel = "menu";
			//resetting help panel
			helppanel.intPX = 80;
			helppanel.intPY = 80;
			helppanel.dblT = 0;
			helppanel.blnjump = false;
			helppanel.blnstop = false;
			helppanel.blnstart = false;
		}
		
		//and so networking begins
		
		//ssm.disconnect();
		if(evt.getSource() == hostbutton){
			//gonna have a set port number
			//only ipaddress changes
			 ssm = new SuperSocketMaster(1111, this);
			 boolean gotConnect = ssm.connect();
			 if(gotConnect){
				theframe.setContentPane(lobbypanel);
				theframe.pack();
				theframe.requestFocus();
				strpanel = "lobby";
				//resetting menu animation
				menupanel.intP1X = -250;
				menupanel.intP1Y = 580;
				menupanel.intP2X = -200;
				menupanel.intP2Y = 580;
				menupanel.intP3X = -150;
				menupanel.intP3Y = 580;
				menupanel.intP4X = -100;
				menupanel.intP4Y = 580;
				menupanel.intP5X = -50;
				menupanel.intP5Y = 580;
				menupanel.dblT1 = 0;
				menupanel.dblT2 = 0;
				menupanel.dblT3 = 0;
				menupanel.dblT4 = 0;
				menupanel.dblT5 = 0;
				menupanel.blnjump1 = false;
				menupanel.blnjump2 = false;
				menupanel.blnjump3 = false;
				menupanel.blnjump4 = false;
				menupanel.blnjump5 = false;
				menupanel.blnstop1 = true;
				menupanel.blnstop2 = true;
				menupanel.blnstop3 = true;
				menupanel.blnstop4 = true;
				menupanel.blnstop5 = true;
				//lobby things
				//host is automatically player 1
				lobbypanel.stridentity = "Player 1";
				//gonna have to also apply this to the levels panel
				
			}
		}
		
		//forget about join for now, set up host lobby first
		if(evt.getSource() == joinbutton){
			ssm = new SuperSocketMaster(joinfield.getText(), 1111, this);
			boolean gotConnect = ssm.connect();
			if(gotConnect){
				//send text join
				//ssm.sendText
				
				//evt.getSource == ssm
				//ssm!=null
				
				
			}else{
				faillabel.setText("Invalid IP Address");
			}
		}
		
		if(evt.getSource() == leavebutton){
			theframe.setContentPane(menupanel);
			theframe.pack();
			strpanel = "menu";
			ssm.disconnect();
			//reset lobby
			lobbypanel.strready = "Not Ready";
			readybutton.setText("Ready");
			chatarea.setText("");
			chatarea.append("HelpBot: Type a message and hit send");
			chatarea.append("\nHelpBot: If you ever lose control of your character, try hitting send to regain control");
			lobbypanel.stridentity = "";
			lobbypanel.intready = 0;
		}
		
		if(evt.getSource() == readybutton){
			//change status to ready
			//i'm thinking we should make the game require five
			//sure, cadawas said min of 4
			//but that's more like at least 4
			//i'll ask just in case
			if(lobbypanel.strready.equals("Not Ready")){
				lobbypanel.strready = "Ready";
				readybutton.setText("Not Ready");
				lobbypanel.intready = lobbypanel.intready +1;
			}else if(lobbypanel.strready.equals("Ready")){
				lobbypanel.strready = "Not Ready";
				readybutton.setText("Ready");
				lobbypanel.intready = lobbypanel.intready -1;
			}
			
		}
		
		if(evt.getSource() == sendbutton){
			//append field to text area
			//must take player number into account
			//that's probably gonna be a property on this side of the fence
			if(lobbypanel.stridentity.equals("Player 1") && !messagefield.getText().equals("")){
				chatarea.append("\nP1: "+messagefield.getText());
				messagefield.setText("");
			}
			theframe.requestFocus();
		}
		
		if(evt.getSource() == ssm){
			//host gets messages from client
			//client gets messages from host
			
		}
		
	}
	
	
	public void keyPressed(KeyEvent evt){
		if(strpanel.equals("help") && helppanel.blnstart == true){
			if(evt.getKeyChar() == 'w' && helppanel.blnstop == true){
				helppanel.blnjump = true;
			}else if(evt.getKeyChar() == 'a'){
				helppanel.intXchange = -5;
			}else if(evt.getKeyChar() == 'd'){
				helppanel.intXchange = 5;
			}
		}else if(strpanel.equals("lobby")){
			
		}
	}
	
	public void keyReleased(KeyEvent evt){
		if(strpanel.equals("help")){
			if(evt.getKeyChar() == 'a'){
				helppanel.intXchange = 0;
			}else if(evt.getKeyChar() == 'd'){
				helppanel.intXchange = 0;
			}
		}else if(strpanel.equals("lobby")){
			
		}
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
		
		readybutton.setSize(150,50);
		readybutton.setLocation(75,125);
		readybutton.addActionListener(this);
		lobbypanel.add(readybutton);
		
		leavebutton.setSize(150,50);
		leavebutton.setLocation(75,200);
		leavebutton.addActionListener(this);
		lobbypanel.add(leavebutton);
		
		//sendbutton
		sendbutton.setSize(75,30);
		sendbutton.setLocation(1165,610);
		sendbutton.addActionListener(this);
		lobbypanel.add(sendbutton);
		
		//text area, actually scroll pane
		theScroll.setSize(240,500);
		theScroll.setLocation(1000,100);
		chatarea.setEditable(false);
		lobbypanel.add(theScroll);
		chatarea.append("HelpBot: Type a message and hit send");
		chatarea.append("\nHelpBot: If you ever lose control of your character, try hitting send to regain control");
		//will probably add scroll
		
		
		//textfields
		joinfield.setSize(150,50);
		joinfield.setLocation(565,250);
		menupanel.add(joinfield);
		
		messagefield.setSize(160,30);
		messagefield.setLocation(1000,610);
		lobbypanel.add(messagefield);
		
		
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

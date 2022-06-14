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
				//send an update with timer
				//at all times
				//this is the way
				if(lobbypanel.stridentity.equals("Player 1")){
					//player 1 be sending all the info
					//send number of players ready
					//send all coordinates
					//or maybe that's only when they get coordinates themselves
					//System.out.println("TEST P1X: "+lobbypanel.intP1X);
					//System.out.println("TEST P1Y: "+lobbypanel.intP1Y);
					ssm.sendText("P1X:" +lobbypanel.intP1X);
					ssm.sendText("P1Y:" +lobbypanel.intP1Y);
				}else if(lobbypanel.stridentity.equals("Player 2")){
					//all other players will only be sending coordinates
					
					ssm.sendText("P2X:" +lobbypanel.intP2X);
					ssm.sendText("P2Y:" +lobbypanel.intP2Y);
				}else if(lobbypanel.stridentity.equals("Player 3")){
					//all other players will only be sending coordinates
					
					ssm.sendText("P3X:" +lobbypanel.intP3X);
					ssm.sendText("P3Y:" +lobbypanel.intP3Y);
				}else if(lobbypanel.stridentity.equals("Player 4")){
					//all other players will only be sending coordinates
					
					ssm.sendText("P4X:"+lobbypanel.intP4X);
					ssm.sendText("P4Y:"+lobbypanel.intP4Y);
				}else if(lobbypanel.stridentity.equals("Player 5")){
					//all other players will only be sending coordinates
					
					ssm.sendText("P5X:"+lobbypanel.intP5X);
					ssm.sendText("P5Y:"+lobbypanel.intP5Y);
				}
				
				if(lobbypanel.intready == 5){
					leavebutton.setEnabled(false);
					readybutton.setEnabled(false);
				}
				
				if(lobbypanel.intT == 3){
					//levels begin
					//reset lobby, but keep in mind that players are still there
					//pack frame with levels
					strpanel = "level";
					lobbypanel.intP1X = 150;
					lobbypanel.intP1Y = 300;
					lobbypanel.intP2X = 300;
					lobbypanel.intP2Y = 300;
					lobbypanel.intP3X = 450;
					lobbypanel.intP3Y = 300;
					lobbypanel.intP4X = 600;
					lobbypanel.intP4Y = 300;
					lobbypanel.intP5X = 750;
					lobbypanel.intP5Y = 300;
					lobbypanel.dblT = 0;
					lobbypanel.blnjump = false;
					lobbypanel.blnstop = false;
					lobbypanel.intXchange = 0;
					lobbypanel.blnstart = false;
					lobbypanel.intCD = 0;
					lobbypanel.intT = 0;
					lobbypanel.intready = 0;
					lobbypanel.strready = "Not Ready";
					readybutton.setText("Ready");
					readybutton.setEnabled(true);
					leavebutton.setEnabled(true);
					theframe.setContentPane(levelspanel);
					theframe.pack();
					//oh right, add the chat to the levels panel
					levelspanel.add(theScroll);
					levelspanel.add(sendbutton);
					levelspanel.add(messagefield);
					levelspanel.stridentity = lobbypanel.stridentity;
				}
				
			}else if(strpanel.equals("help")){
				helppanel.repaint();
			}else if(strpanel.equals("level")){
				levelspanel.repaint();
				
				//will also have to send coordinates, but now from level panel
				
				if(levelspanel.stridentity.equals("Player 1")){
					ssm.sendText("P1X:" +levelspanel.intP1X);
					ssm.sendText("P1Y:" +levelspanel.intP1Y);
				}else if(levelspanel.stridentity.equals("Player 2")){					
					ssm.sendText("P2X:" +levelspanel.intP2X);
					ssm.sendText("P2Y:" +levelspanel.intP2Y);
				}else if(levelspanel.stridentity.equals("Player 3")){					
					ssm.sendText("P3X:" +levelspanel.intP3X);
					ssm.sendText("P3Y:" +levelspanel.intP3Y);
				}else if(levelspanel.stridentity.equals("Player 4")){
					ssm.sendText("P4X:"+levelspanel.intP4X);
					ssm.sendText("P4Y:"+levelspanel.intP4Y);
				}else if(levelspanel.stridentity.equals("Player 5")){
					ssm.sendText("P5X:"+levelspanel.intP5X);
					ssm.sendText("P5Y:"+levelspanel.intP5Y);
				}
				
				
				if(levelspanel.strlevel.equals("Win") && levelspanel.intT == 3){
					strpanel = "lobby";
					levelspanel.intT = 0;
					levelspanel.intCD = 0;
					levelspanel.strlevel = "Level 1";
					levelspanel.stridentity = "";
					theframe.setContentPane(lobbypanel);
					theframe.pack();
					//chat is here too
					lobbypanel.add(theScroll);
					lobbypanel.add(sendbutton);
					lobbypanel.add(messagefield);
					
				}
				
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
			faillabel.setText("");
		}
		
		if(evt.getSource() == menubutton){
			theframe.setContentPane(menupanel);
			theframe.pack();
			strpanel = "menu";
			//resetting help panel
			helppanel.intPX = 80;
			helppanel.intPY = 80;
			helppanel.dblT = 0;
			helppanel.intXchange = 0;
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
				faillabel.setText("");
				//lobby things
				//host is automatically player 1
				lobbypanel.stridentity = "Player 1";
				lobbypanel.intplayers = lobbypanel.intplayers +1;
				//gonna have to also apply this to the levels panel
				lobbypanel.intP1Y = 300;
				
			}
		}
		
		//evt.getSource == ssm
		//ssm!=null
		
		//forget about join for now, set up host lobby first
		if(evt.getSource() == joinbutton){
			ssm = new SuperSocketMaster(joinfield.getText(), 1111, this);
			boolean gotConnect = ssm.connect();
			if(gotConnect){
				//send text join
				//ssm.sendText
				ssm.sendText("join");
				//is that it?
				//cause everything else seems to depend on a message
				
			}else{
				faillabel.setText("Invalid IP Address");
			}
		}
		
		if(evt.getSource() == leavebutton){
			if(lobbypanel.strready.equals("Ready")){
				lobbypanel.intready = lobbypanel.intready -1;
				ssm.sendText("PAR:"+lobbypanel.intready);
			}
			theframe.setContentPane(menupanel);
			theframe.pack();
			strpanel = "menu";
			//send message before hand
			//or client should check for disconnect which forces them back to lobby
			if(lobbypanel.stridentity.equals("Player 1")){
				ssm.sendText("P1R:null");
			}else if(lobbypanel.stridentity.equals("Player 2")){
				ssm.sendText("P2R:null");
			}else if(lobbypanel.stridentity.equals("Player 3")){
				ssm.sendText("P3R:null");
			}else if(lobbypanel.stridentity.equals("Player 4")){
				ssm.sendText("P4R:null");
			}else if(lobbypanel.stridentity.equals("Player 5")){
				ssm.sendText("P5R:null");
			}
			ssm.disconnect();
			//reset lobby
			lobbypanel.strready = "Not Ready";
			readybutton.setText("Ready");
			chatarea.setText("");
			chatarea.append("HelpBot: Type a message and hit send");
			chatarea.append("\nHelpBot: If you ever lose control of your character, try hitting send to regain control");
			lobbypanel.stridentity = "";
			lobbypanel.intready = 0;
			lobbypanel.intplayers = 0;
			lobbypanel.strP1 = null;
			lobbypanel.strP2 = null;
			lobbypanel.strP3 = null;
			lobbypanel.strP4 = null;
			lobbypanel.strP5 = null;
			lobbypanel.dblT = 0;
			lobbypanel.intXchange = 0;
			lobbypanel.blnjump = false;
			lobbypanel.blnstop = false;
			lobbypanel.blnstart = false;
			lobbypanel.intP1X = 150;
			lobbypanel.intP1Y = -100;
			lobbypanel.intP2X = 300;
			lobbypanel.intP2Y = -100;
			lobbypanel.intP3X = 450;
			lobbypanel.intP3Y = -100;
			lobbypanel.intP4X = 600;
			lobbypanel.intP4Y = -100;
			lobbypanel.intP5X = 750;
			lobbypanel.intP5Y = -100;
		}
		
		if(evt.getSource() == readybutton){
			//change status to ready
			//i'm thinking we should make the game require five
			//sure, cadawas said min of 4
			//but that's more like at least 4
			//i'll ask just in case
			//send text ready
			if(lobbypanel.strready.equals("Not Ready")){
				lobbypanel.strready = "Ready";
				readybutton.setText("Not Ready");
				lobbypanel.intready = lobbypanel.intready +1;
				ssm.sendText("PAR:"+lobbypanel.intready);
			}else if(lobbypanel.strready.equals("Ready")){
				lobbypanel.strready = "Not Ready";
				readybutton.setText("Ready");
				lobbypanel.intready = lobbypanel.intready -1;
				ssm.sendText("PAR:"+lobbypanel.intready);
			}
			theframe.requestFocus();
		}
		
		if(evt.getSource() == sendbutton){
			//append field to text area
			//must take player number into account
			//that's probably gonna be a property on this side of the fence
			if(lobbypanel.stridentity.equals("Player 1") && !messagefield.getText().equals("")){
				chatarea.append("\nP1: "+messagefield.getText());
				ssm.sendText("P1C:"+messagefield.getText());
				messagefield.setText("");
			}else if(lobbypanel.stridentity.equals("Player 2") && !messagefield.getText().equals("")){
				chatarea.append("\nP2: "+messagefield.getText());
				ssm.sendText("P2C:"+messagefield.getText());
				messagefield.setText("");
			}else if(lobbypanel.stridentity.equals("Player 3") && !messagefield.getText().equals("")){
				chatarea.append("\nP3: "+messagefield.getText());
				ssm.sendText("P3C:"+messagefield.getText());
				messagefield.setText("");
			}else if(lobbypanel.stridentity.equals("Player 4") && !messagefield.getText().equals("")){
				chatarea.append("\nP4: "+messagefield.getText());
				ssm.sendText("P4C:"+messagefield.getText());
				messagefield.setText("");
			}else if(lobbypanel.stridentity.equals("Player 5") && !messagefield.getText().equals("")){
				chatarea.append("\nP5: "+messagefield.getText());
				ssm.sendText("P5C:"+messagefield.getText());
				messagefield.setText("");
			}
			theframe.requestFocus();
		}
		
		if(evt.getSource() == ssm){
			//host gets messages from client
			//client gets messages from host
			//this code is for both client and host
			//this should be limited to the host
			//but for some reason...
			//if i have this limited to the host, it doesn't work
			//why?
			//it works now... and i don't know why...
			//guess i just won't touch it
			
			if(ssm.readText().equals("join") && lobbypanel.stridentity.equals("Player 1")){
				if(lobbypanel.intplayers < 5){
					//check which players are active
					if(lobbypanel.strP2 == null){
						lobbypanel.strP2 = "false";
						ssm.sendText("Player 2");
						ssm.sendText("PAR:"+lobbypanel.intready);
					}else if(lobbypanel.strP3 == null){
						lobbypanel.strP3 = "false";
						ssm.sendText("Player 3");
						ssm.sendText("PAR:"+lobbypanel.intready);
					}else if(lobbypanel.strP4 == null){
						lobbypanel.strP4 = "false";
						ssm.sendText("Player 4");
						ssm.sendText("PAR:"+lobbypanel.intready);
					}else if(lobbypanel.strP5 == null){
						lobbypanel.strP5 = "false";
						ssm.sendText("Player 5");
						ssm.sendText("PAR:"+lobbypanel.intready);
					}
					//host side of things
					lobbypanel.intplayers = lobbypanel.intplayers+1;					
					
				}else{
					ssm.sendText("null");
				}
			}else if(lobbypanel.stridentity.equals("")){
				if(ssm.readText().equals("null")){
				//you are not in lobby and host prevents you from joining
					ssm.disconnect();
					faillabel.setText("Lobby is full/active");
				}else if(ssm.readText().equals("Player 2")){
					lobbypanel.stridentity = "Player 2";
					lobbypanel.intP2Y = 300;
				}else if(ssm.readText().equals("Player 3")){
					lobbypanel.stridentity = "Player 3";
					lobbypanel.intP3Y = 300;
				}else if(ssm.readText().equals("Player 4")){
					lobbypanel.stridentity = "Player 4";
					lobbypanel.intP4Y = 300;
				}else if(ssm.readText().equals("Player 5")){
					lobbypanel.stridentity = "Player 5";
					lobbypanel.intP5Y = 300;
				}
				if(!lobbypanel.stridentity.equals("")){
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
					faillabel.setText("");
				}
			}else if(ssm.readText().equals("P1R:null")){
				//forces clients to return to menu
				ssm.disconnect();
				//return to menu
				theframe.setContentPane(menupanel);
				theframe.pack();
				strpanel = "menu";
				faillabel.setText("The Lobby was closed");
				//reset lobby
				lobbypanel.strready = "Not Ready";
				readybutton.setText("Ready");
				chatarea.setText("");
				chatarea.append("HelpBot: Type a message and hit send");
				chatarea.append("\nHelpBot: If you ever lose control of your character, try hitting send to regain control");
				lobbypanel.stridentity = "";
				lobbypanel.intready = 0;
				lobbypanel.intplayers = 0;
				lobbypanel.strP1 = null;
				lobbypanel.strP2 = null;
				lobbypanel.strP3 = null;
				lobbypanel.strP4 = null;
				lobbypanel.strP5 = null;
				lobbypanel.dblT = 0;
				lobbypanel.intXchange = 0;
				lobbypanel.blnjump = false;
				lobbypanel.blnstop = false;
				lobbypanel.blnstart = false;
				lobbypanel.intP1X = 150;
				lobbypanel.intP1Y = -100;
				lobbypanel.intP2X = 300;
				lobbypanel.intP2Y = -100;
				lobbypanel.intP3X = 450;
				lobbypanel.intP3Y = -100;
				lobbypanel.intP4X = 600;
				lobbypanel.intP4Y = -100;
				lobbypanel.intP5X = 750;
				lobbypanel.intP5Y = -100;
			}else if(ssm.readText().equals("P2R:null")){
				//resets specific player
				lobbypanel.strP2 = null;
				lobbypanel.intP2X = 300;
				lobbypanel.intP2Y = -100;
				lobbypanel.intplayers = lobbypanel.intplayers -1;
			}else if(ssm.readText().equals("P3R:null")){
				lobbypanel.strP3 = null;
				lobbypanel.intP3X = 450;
				lobbypanel.intP3Y = -100;
				lobbypanel.intplayers = lobbypanel.intplayers -1;
			}else if(ssm.readText().equals("P4R:null")){
				lobbypanel.strP4 = null;
				lobbypanel.intP4X = 600;
				lobbypanel.intP4Y = -100;
				lobbypanel.intplayers = lobbypanel.intplayers -1;
			}else if(ssm.readText().equals("P5R:null")){
				lobbypanel.strP5 = null;
				lobbypanel.intP5X = 750;
				lobbypanel.intP5Y = -100;
				lobbypanel.intplayers = lobbypanel.intplayers -1;
			}else if(ssm.readText().substring(0,4).equals("PAR:")){
				lobbypanel.intready = Integer.parseInt(ssm.readText().substring(4,5));
			}else if(ssm.readText().substring(0,4).equals("P1Y:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP1Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP1Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P1X:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP1X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP1X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}	
			}else if(ssm.readText().substring(0,4).equals("P2Y:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP2Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP2Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P2X:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP2X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP2X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P3Y:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP3Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP3Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P3X:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP3X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP3X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P4Y:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP4Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP4Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P4X:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP4X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP4X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P5Y:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP5Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP5Y = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P5X:")){
				if(strpanel.equals("lobby")){
					lobbypanel.intP5X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}else if(strpanel.equals("level")){
					levelspanel.intP5X = Integer.parseInt(ssm.readText().substring(4,ssm.readText().length()));
				}
			}else if(ssm.readText().substring(0,4).equals("P1C:")){
				chatarea.append("\nP1: "+ssm.readText().substring(4,ssm.readText().length()));
			}else if(ssm.readText().substring(0,4).equals("P2C:") && !lobbypanel.stridentity.equals("Player 2")){
				chatarea.append("\nP2: "+ssm.readText().substring(4,ssm.readText().length()));
			}else if(ssm.readText().substring(0,4).equals("P3C:") && !lobbypanel.stridentity.equals("Player 3")){
				chatarea.append("\nP3: "+ssm.readText().substring(4,ssm.readText().length()));
			}else if(ssm.readText().substring(0,4).equals("P4C:") && !lobbypanel.stridentity.equals("Player 4")){
				chatarea.append("\nP4: "+ssm.readText().substring(4,ssm.readText().length()));
			}else if(ssm.readText().substring(0,4).equals("P5C:") && !lobbypanel.stridentity.equals("Player 5")){
				chatarea.append("\nP5: "+ssm.readText().substring(4,ssm.readText().length()));
			}
			
			
			//we're also gonna need to do chat messages
			//oh boy
			//chat was surprisingly easy
			
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
		}else if(strpanel.equals("lobby") && lobbypanel.blnstart == true){
			if(evt.getKeyChar() == 'w' && lobbypanel.blnstop == true){
				lobbypanel.blnjump = true;
			}else if(evt.getKeyChar() == 'a'){
				lobbypanel.intXchange = -5;
			}else if(evt.getKeyChar() == 'd'){
				lobbypanel.intXchange = 5;
			}
		}else if(strpanel.equals("level") && levelspanel.blnstart == true){
			if(evt.getKeyChar() == 'w' && levelspanel.blnstop == true){
				levelspanel.blnjump = true;
			}else if(evt.getKeyChar() == 'a'){
				levelspanel.intXchange = -5;
			}else if(evt.getKeyChar() == 'd'){
				levelspanel.intXchange = 5;
			}
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
			if(evt.getKeyChar() == 'a'){
				lobbypanel.intXchange = 0;
			}else if(evt.getKeyChar() == 'd'){
				lobbypanel.intXchange = 0;
			}
		}else if(strpanel.equals("level")){
			if(evt.getKeyChar() == 'a'){
				levelspanel.intXchange = 0;
			}else if(evt.getKeyChar() == 'd'){
				levelspanel.intXchange = 0;
			}
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

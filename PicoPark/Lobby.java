import java.awt.*;
import javax.swing.*;

public class Lobby extends JPanel{
	//properties
	PP ppmodel = new PP();
	Font bigfont = new Font(null, 0, 40);
	String strready = "Not Ready";
	String stridentity = "";
	int intready = 0;
	int intplayers = 0;
	//these are really only used to check if the player spot has been taken
	String strP1 = null;
	String strP2 = null;
	String strP3 = null;
	String strP4 = null;
	String strP5 = null;
	//the player properties
	double dblT = 0;
	boolean blnjump = false;
	boolean blnstop = false;
	int intXchange = 0;
	boolean blnstart = false;
	
	//all player coordinates
	int intP1X = 150;
	int intP1Y = -100;
	int intP2X = 300;
	int intP2Y = -100;
	int intP3X = 450;
	int intP3Y = -100;
	int intP4X = 600;
	int intP4Y = -100;
	int intP5X = 750;
	int intP5Y = -100;
	
	//this should hold the code for only one player
	//depending on their identity, only one set of coordinates will be affected by the player
	//the rest are just sent here by ssm
	//one time method
	//string determines if player is present and if they are ready
	
	//player 1 = red
	//player 2 = orange
	//player 3 = yellow
	//player 4 = blue
	//player 5 = green
	
	//print player identity using their color
	//print status of player
	//print how many players are ready out of needed number to begin
	//don't need to print amount of players because that's obvious
	//there should be like a three second timer
	//a warning before the levels begin
	
	//gonna have to figure out how to print players
	//they'll all have a set spawn
	//they'll drop like in help
	//they'll probably be waiting somewhere offscreen in the meantime
	//in that case, there needs to be a boolean that leaves them in limbo
	//am starting to get a little overwhelmed
	//one step at a time
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String strmaparray[][] = ppmodel.readarray("Lobby.csv");
		for(int intRow = 0; intRow < 17; intRow++){
			for(int intCol = 0; intCol < 25; intCol++){
				String strread = strmaparray[intRow][intCol];
				if(strread.equals("w")){
					g.setColor(Color.ORANGE);
					g.fillRect(intCol*40, intRow*40,40,40);
				}else if(strread.equals("b")){
					g.setColor(Color.WHITE);
					g.fillRect(intCol*40, intRow*40,40,40);
				}else if(strread.equals("k")){
					g.setColor(Color.YELLOW);
					g.fillRect(intCol*40, intRow*40,40,40);
				}else if(strread.equals("d")){
					g.setColor(Color.BLACK);
					g.fillRect(intCol*40, intRow*40,40,40);
				}else{
					System.out.println("this string is not recognized");
				}
			}	
		}
		
		//print players
		g.setColor(Color.RED);
		g.fillRect(intP1X,intP1Y,20,20);
		g.setColor(Color.ORANGE);
		g.fillRect(intP2X,intP2Y,20,20);
		g.setColor(Color.YELLOW);
		g.fillRect(intP3X,intP3Y,20,20);
		g.setColor(Color.BLUE);
		g.fillRect(intP4X,intP4Y,20,20);
		g.setColor(Color.GREEN);
		g.fillRect(intP5X,intP5Y,20,20);
		
		
		if(stridentity.equals("Player 1")){
			intP1X = intP1X + intXchange;
			intP1X = ppmodel.adjustX(strmaparray, intP1X, intP1Y);
		}else if(stridentity.equals("Player 2")){
			intP2X = intP2X + intXchange;
			intP2X = ppmodel.adjustX(strmaparray, intP2X, intP2Y);
		}else if(stridentity.equals("Player 3")){
			intP3X = intP3X + intXchange;
			intP3X = ppmodel.adjustX(strmaparray, intP3X, intP3Y);
		}else if(stridentity.equals("Player 4")){
			intP4X = intP4X + intXchange;
			intP4X = ppmodel.adjustX(strmaparray, intP4X, intP4Y);
		}else if(stridentity.equals("Player 5")){
			intP5X = intP5X + intXchange;
			intP5X = ppmodel.adjustX(strmaparray, intP5X, intP5Y);
		}
		
		//ensure time is o
		if(dblT == 0){
			ppmodel.dblChange1 = 0;
		}
		//check if jump is active
		if(blnjump == true){
			blnstop = false;
		}
		//start time
		dblT = ppmodel.P1time(dblT, blnstop);
		
		if(stridentity.equals("Player 1")){
			//start jump
			intP1Y = ppmodel.jump(intP1Y, dblT, blnjump);
			//then i begin freefall
			intP1Y = ppmodel.freefall(intP1Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP1Y, intP1X, dblT);
		}else if(stridentity.equals("Player 2")){
			//start jump
			intP2Y = ppmodel.jump(intP2Y, dblT, blnjump);
			//then i begin freefall
			intP2Y = ppmodel.freefall(intP2Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP2Y, intP2X, dblT);
		}else if(stridentity.equals("Player 3")){
			//start jump
			intP3Y = ppmodel.jump(intP3Y, dblT, blnjump);
			//then i begin freefall
			intP3Y = ppmodel.freefall(intP3Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP3Y, intP3X, dblT);
		}else if(stridentity.equals("Player 4")){
			//start jump
			intP4Y = ppmodel.jump(intP4Y, dblT, blnjump);
			//then i begin freefall
			intP4Y = ppmodel.freefall(intP4Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP4Y, intP4X, dblT);
		}else if(stridentity.equals("Player 5")){
			//start jump
			intP5Y = ppmodel.jump(intP5Y, dblT, blnjump);
			//then i begin freefall
			intP5Y = ppmodel.freefall(intP5Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP5Y, intP5X, dblT);
		}
		
		
		//this is the time reset
		//if dblT = 0, jump = false, change = 0
		if(dblT == 0){
			blnjump = false;
			ppmodel.dblChange1 = 0;
		}
		
		if(stridentity.equals("Player 1")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP1X, intP1Y);
		}else if(stridentity.equals("Player 2")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP2X, intP2Y);
		}else if(stridentity.equals("Player 3")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP3X, intP3Y);
		}else if(stridentity.equals("Player 4")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP4X, intP4Y);
		}else if(stridentity.equals("Player 5")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP5X, intP5Y);
		}
		
		//if blnstop = true, blnjump = false, blnstart = true
		if(blnstop == true){
			blnjump = false;
			blnstart = true;
		}
		
		if(stridentity.equals("Player 1")){
			//the i make adjustments for Y
			intP1Y = ppmodel.adjustY(strmaparray, intP1X, intP1Y);
		}else if(stridentity.equals("Player 2")){
			//the i make adjustments for Y
			intP2Y = ppmodel.adjustY(strmaparray, intP2X, intP2Y);
		}else if(stridentity.equals("Player 3")){
			//the i make adjustments for Y
			intP3Y = ppmodel.adjustY(strmaparray, intP3X, intP3Y);
		}else if(stridentity.equals("Player 4")){
			//the i make adjustments for Y
			intP4Y = ppmodel.adjustY(strmaparray, intP4X, intP4Y);
		}else if(stridentity.equals("Player 5")){
			//the i make adjustments for Y
			intP5Y = ppmodel.adjustY(strmaparray, intP5X, intP5Y);
		}
				
		
		//underneath level
		g.setColor(Color.ORANGE);
		g.fillRect(1000,0,300,750);
		//for chat box
		g.fillRect(0,680,1000,40);
		//chat
		g.setColor(Color.WHITE);
		g.setFont(bigfont);
		g.drawString("Chat",1070,80);
		g.setColor(Color.ORANGE);
		g.drawString("Lobby", 100, 100);
		g.drawString("Players Ready: " +intready+ "/5",300,250);
		if(stridentity.equals("Player 1")){
			g.setColor(Color.RED);
			g.drawString("You are Player 1",300,100);
			g.drawString("Your Status: " +strready, 300,175);
		}else if(stridentity.equals("Player 2")){
			g.setColor(Color.ORANGE);
			g.drawString("You are Player 2",300,100);
			g.drawString("Your Status: " +strready, 300,175);
		}else if(stridentity.equals("Player 3")){
			g.setColor(Color.YELLOW);
			g.drawString("You are Player 3",300,100);
			g.drawString("Your Status: " +strready, 300,175);
		}else if(stridentity.equals("Player 4")){
			g.setColor(Color.BLUE);
			g.drawString("You are Player 4",300,100);
			g.drawString("Your Status: " +strready, 300,175);
		}else if(stridentity.equals("Player 5")){
			g.setColor(Color.GREEN);
			g.drawString("You are Player 5",300,100);
			g.drawString("Your Status: " +strready, 300,175);
		}
		
		
		
	}
	
	//constructor
	public Lobby(){
	}
	
}

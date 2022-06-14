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
	
	//countdown stuff
	int intCD = 0;
	int intT = 0;
	
	//once intready reached 5, start a countdown
	//it should print after the map
	//it'll use one of the times in pp model
	
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
					g.setColor(Color.GRAY);
					g.fillRect(intCol*40, intRow*40,40,40);
				}else if(strread.equals("d")){
					g.setColor(Color.BLACK);
					g.fillRect(intCol*40, intRow*40,40,40);
				}else{
					System.out.println("this string is not recognized");
				}
			}	
		}
		
		if(intready == 5){
			intT = ppmodel.countdown(intCD);
			g.setFont(bigfont);
			g.setColor(Color.ORANGE);
			intCD++;
			if(intT == 0){
				g.drawString("Starting in 3",300,325);
			}else if(intT == 1){
				g.drawString("Starting in 2",300,325);
			}else if(intT == 2){
				g.drawString("Starting in 1",300,325);
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
			//player collision adjustment first,
			intP1X = ppmodel.adjustPX(intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
			//wall collision
			intP1X = ppmodel.adjustX(strmaparray, intP1X, intP1Y);
		}else if(stridentity.equals("Player 2")){
			intP2X = intP2X + intXchange;
			//player collision adjustment first,
			intP2X = ppmodel.adjustPX(intP2X, intP2Y, intP1X, intP1Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
			//wall collision
			intP2X = ppmodel.adjustX(strmaparray, intP2X, intP2Y);
		}else if(stridentity.equals("Player 3")){
			intP3X = intP3X + intXchange;
			//player collision adjustment first,
			intP3X = ppmodel.adjustPX(intP3X, intP3Y, intP1X, intP1Y, intP2X, intP2Y, intP4X, intP4Y, intP5X, intP5Y);
			//wall collision
			intP3X = ppmodel.adjustX(strmaparray, intP3X, intP3Y);
		}else if(stridentity.equals("Player 4")){
			intP4X = intP4X + intXchange;
			//player collision adjustment first,
			intP4X = ppmodel.adjustPX(intP4X, intP4Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP5X, intP5Y);
			//wall collision
			intP4X = ppmodel.adjustX(strmaparray, intP4X, intP4Y);
		}else if(stridentity.equals("Player 5")){
			intP5X = intP5X + intXchange;
			//player collision adjustment first,
			intP5X = ppmodel.adjustPX(intP5X, intP5Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y);
			//wall collision
			intP5X = ppmodel.adjustX(strmaparray, intP5X, intP5Y);
		}
		
		//ensure time is 0
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
			//check if you hit a player "cieling"
			dblT = ppmodel.resetPT(dblT, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
		}else if(stridentity.equals("Player 2")){
			//start jump
			intP2Y = ppmodel.jump(intP2Y, dblT, blnjump);
			//then i begin freefall
			intP2Y = ppmodel.freefall(intP2Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP2Y, intP2X, dblT);
			//check if you hit a player "cieling"
			dblT = ppmodel.resetPT(dblT, intP2X, intP2Y, intP1X, intP1Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
		}else if(stridentity.equals("Player 3")){
			//start jump
			intP3Y = ppmodel.jump(intP3Y, dblT, blnjump);
			//then i begin freefall
			intP3Y = ppmodel.freefall(intP3Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP3Y, intP3X, dblT);
			//check if you hit a player "cieling"
			dblT = ppmodel.resetPT(dblT, intP3X, intP3Y, intP1X, intP1Y, intP2X, intP2Y, intP4X, intP4Y, intP5X, intP5Y);
		}else if(stridentity.equals("Player 4")){
			//start jump
			intP4Y = ppmodel.jump(intP4Y, dblT, blnjump);
			//then i begin freefall
			intP4Y = ppmodel.freefall(intP4Y, dblT, blnstop);
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP4Y, intP4X, dblT);
			//check if you hit a player "cieling"
			dblT = ppmodel.resetPT(dblT, intP4X, intP4Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP5X, intP5Y);
		}else if(stridentity.equals("Player 5")){
			//start jump
			intP5Y = ppmodel.jump(intP5Y, dblT, blnjump);
			//then i begin freefall
			intP5Y = ppmodel.freefall(intP5Y, dblT, blnstop);			
			//then i check if we hit a cieling
			dblT = ppmodel.timereset(strmaparray, intP5Y, intP5X, dblT);
			//check if you hit a player "cieling"
			dblT = ppmodel.resetPT(dblT, intP5X, intP5Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y);
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
			//check if you hit a player "floor"
			if(blnstop != true){
				blnstop = ppmodel.stopPY(intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
			}
		}else if(stridentity.equals("Player 2")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP2X, intP2Y);
			//check if you hit a player "floor"
			if(blnstop != true){
				blnstop = ppmodel.stopPY(intP2X, intP2Y, intP1X, intP1Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
			}
		}else if(stridentity.equals("Player 3")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP3X, intP3Y);
			//check if you hit a player "floor"
			if(blnstop != true){
				blnstop = ppmodel.stopPY(intP3X, intP3Y, intP1X, intP1Y, intP2X, intP2Y, intP4X, intP4Y, intP5X, intP5Y);
			}
		}else if(stridentity.equals("Player 4")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP4X, intP4Y);
			//check if you hit a player "floor"
			if(blnstop != true){
				blnstop = ppmodel.stopPY(intP4X, intP4Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP5X, intP5Y);
			}
		}else if(stridentity.equals("Player 5")){
			//then i check if we hit the floor
			blnstop = ppmodel.stopY(strmaparray, intP5X, intP5Y);
			//check if you hit a player "floor"
			if(blnstop != true){
				blnstop = ppmodel.stopPY(intP5X, intP5Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y);
			}
		}
		
		//if blnstop = true, blnjump = false, blnstart = true
		if(blnstop == true){
			blnjump = false;
			blnstart = true;
		}
		
		if(stridentity.equals("Player 1")){
			//player collision adjustment first,
			intP1Y = ppmodel.adjustPY(intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
			//the i make adjustments for Y
			intP1Y = ppmodel.adjustY(strmaparray, intP1X, intP1Y);
		}else if(stridentity.equals("Player 2")){
			//player collision adjustment first,
			intP2Y = ppmodel.adjustPY(intP2X, intP2Y, intP1X, intP1Y, intP3X, intP3Y, intP4X, intP4Y, intP5X, intP5Y);
			//the i make adjustments for Y
			intP2Y = ppmodel.adjustY(strmaparray, intP2X, intP2Y);
		}else if(stridentity.equals("Player 3")){
			//player collision adjustment first,
			intP3Y = ppmodel.adjustPY(intP3X, intP3Y, intP1X, intP1Y, intP2X, intP2Y, intP4X, intP4Y, intP5X, intP5Y);
			//the i make adjustments for Y
			intP3Y = ppmodel.adjustY(strmaparray, intP3X, intP3Y);
		}else if(stridentity.equals("Player 4")){
			//player collision adjustment first,
			intP4Y = ppmodel.adjustPY(intP4X, intP4Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP5X, intP5Y);
			//the i make adjustments for Y
			intP4Y = ppmodel.adjustY(strmaparray, intP4X, intP4Y);
		}else if(stridentity.equals("Player 5")){
			//player collision adjustment first,
			intP5Y = ppmodel.adjustPY(intP5X, intP5Y, intP1X, intP1Y, intP2X, intP2Y, intP3X, intP3Y, intP4X, intP4Y);
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
		super();
	}
	
}

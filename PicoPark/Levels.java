import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Levels extends JPanel{
	//properties
	PP ppmodel = new PP();
	Font bigfont = new Font(null, 0, 50);
	Font superfont = new Font(null, 0, 100);
	BufferedImage Spike = null;
	boolean blndoor = false;
	
	//important things
	String stridentity = "";
	String strlevel = "Level 1";
	boolean blnCD = true;
	String strFile = "Level1.csv";
	
	//the player properties
	double dblT = 0;
	boolean blnjump = false;
	boolean blnstop = false;
	int intXchange = 0;
	boolean blnstart = false;
	
	//all player coordinates (there will have to change)
	int intP1X = 80;
	int intP1Y = -20;
	int intP2X = 80;
	int intP2Y = -40;
	int intP3X = 80;
	int intP3Y = -60;
	int intP4X = 80;
	int intP4Y = -80;
	int intP5X = 80;
	int intP5Y = -100;
	
	//countdown stuff
	int intCD = 0;
	int intT = 0;
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//basically copy and paste everything from lobby
		//but with a twist
		//should try this in help panel first
		//once jerry is done there that is
		
		//so, since the array is always gonna be reinstanted...
		//I can't exactly make keys disappear
		//so instead, some one needs to stand in the golden area, or now it can be grey, so that someone can activate the door
		//teamwork go brr
		String strmaparray[][] = ppmodel.readarray(strFile);
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
					//there should only be one "key" per map
					//if player is standing on key, door is true
				}else if(strread.equals("d")){
					//if door is true, door is printed
					//else, it's covered as background
					g.setColor(Color.BLACK);
					g.fillRect(intCol*40, intRow*40,40,40);
					//if player is standing in door, because door is visible, then it becomes the next level
					//spawn gets reset
				}else if(strread.equals("s")){
					g.drawImage(Spike, intCol*40, intRow*40, null);
				}else{
					System.out.println("this string is not recognized");
					//may update this based on jerry's additions
				}
			}	
		}
		
		//should probably do a thing where i tell them it's level 1
		//let's set up movement first
		//which means we gotta set spawn
		//ugh
		//spawn is gonna be the same as help spawn
		//however, characters will spawn one at a time
		//that is to say that collision detection only activates after spawn
		//before then, there all gonna be in one place
		//that will wait until player a space is free before they begin their descent
		
		//the countdown only activates when a level is complete
		//speaking of which, i need to add that logic
		
		if(blnCD == true){
			g.setColor(Color.ORANGE);
			g.fillRect(0,0,1000,700);
			intT = ppmodel.countdown(intCD);
			intCD++;
			g.setColor(Color.WHITE);
			g.setFont(superfont);
			g.drawString(strlevel,300,300);
			
			g.setFont(bigfont);
			if(intT == 0){
				g.drawString("Starting in 3",300,400);
			}else if(intT == 1){
				g.drawString("Starting in 2",300,400);
			}else if(intT == 2){
				g.drawString("Starting in 1",300,400);
			}
		}else if(strlevel.equals("Win")){
			//countdown, but this time return to menu
			g.setColor(Color.ORANGE);
			g.drawRect(0,0,1000,700);
			intT = ppmodel.countdown(intCD);
			intCD++;
			g.setColor(Color.WHITE);
			g.setFont(superfont);
			g.drawString("You Win",300,300);
			
			g.setFont(bigfont);
			if(intT == 0){
				g.drawString("Returning to Lobby in 3",200,400);
			}else if(intT == 1){
				g.drawString("Returning to Lobby in 2",300,400);
			}else if(intT == 2){
				g.drawString("Returning to Lobby in 1",300,400);
			}
		}
		
		//spawn players
		//spawn is super janky... but it works?
		if(intT == 3 && !strlevel.equals("Win")){
			blnCD = false;
			intT = 0;
			intCD = 0;
			if(stridentity.equals("Player 1")){
				intP1Y = 100;
			}else if(stridentity.equals("Player 2")){
				intP2Y = 150;
			}else if(stridentity.equals("Player 3")){
				intP3Y = 200;
			}else if(stridentity.equals("Player 4")){
				intP4Y = 250;
			}else if(stridentity.equals("Player 5")){
				intP5Y = 300;
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
		
		if(blnCD == false){
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
		}
		
		//thinking of making it so that a player has to be within the gold area for the door to activate
		
		
		
		//underneath level
		g.setColor(Color.ORANGE);
		g.fillRect(1000,0,300,750);
		//for chat box
		g.fillRect(0,680,1000,40);
		//chat
		g.setColor(Color.WHITE);
		g.setFont(bigfont);
		g.drawString("Chat",1070,80);
		
	}
	
	//constructor
	public Levels(){
		super();
		try{
			Spike = ImageIO.read(new File("Spike.jpg"));
		}catch(IOException e){
			System.out.println("Image not found");
		}
	}
	
}

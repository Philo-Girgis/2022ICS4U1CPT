import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Help extends JPanel{
	//properties
	PP ppmodel = new PP();
	Font bigfont = new Font(null, 0, 50);
	boolean blndoor = false;
	Font normalfont = new Font(null, 0, 15);
	
	//fun fact, this ppmodel is only applicable to this program
	//can't alter it from anywhere else
	//pretty cool, huh?
	
	//player stuff
	int intPX = 80;
	int intPY = 80;
	double dblT = 0;
	boolean blnjump = false;
	boolean blnstop = false;
	int intXchange = 0;
	boolean blnstart = false;
	BufferedImage Spike = null;
	
	//40 by 40 squares
	//1000 by 720 level
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String strmaparray[][] = ppmodel.readarray("Help.csv");
		
		blndoor = true;
		for(int intRow = 0; intRow < 17; intRow++){
			for(int intCol = 0; intCol < 25; intCol++){
				String strread = strmaparray[intRow][intCol];
				if(strread.equals("k")){
					if(intPX+20 > intCol*40 && intPX < intCol*40+40 && intPY+20 > intRow*40 && intPY < intRow*40+40){
					}else{
						blndoor = false;
					}
				}
			}
		}
		
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
					if(blndoor == true){
						g.setColor(Color.BLACK);
					}else{
						g.setColor(Color.WHITE);
					}
					g.fillRect(intCol*40, intRow*40,40,40);
				}else if(strread.equals("s")){
					g.drawImage(Spike, intCol*40, intRow*40, null);
					if(intPX+20 > intCol*40 && intPX < intCol*40+40 && intPY+20 > intRow*40 && intPY < intRow*40+40){
						intPX = 80;
						intPY = 80;
						dblT = 0;
						blnjump = false;
						blnstop = false;
						intXchange = 0;
						blnstart = false;
					}
				}else{
					System.out.println("this string is not recognized");
				}
			}	
		}
		
		//time to create collision detection
		//I think spawn is gonna have to be set
		//or I can try dropping them out of the sky
		//from a set spawn though
		//they can't move until they reach the floor
		
		g.setColor(Color.RED);
		g.fillRect(intPX, intPY, 20,20);
		
		//for X movement
		intPX = intPX + intXchange;
		
		//adjustment for wall
		intPX = ppmodel.adjustX(strmaparray, intPX, intPY);
		
		//we're using an already existing time method, i need to reset the menu animation
		//done now this
		
		//ensure that time is indeed 0
		if(dblT == 0){
			ppmodel.dblChange1 = 0;
		}
		
		//first i check if jump is active so i deactivate stop
		if(blnjump == true){
			blnstop = false;
		}
		
		//then i start time
		dblT = ppmodel.P1time(dblT, blnstop);
		
		//then i let the player begin jumping
		intPY = ppmodel.jump(intPY, dblT, blnjump);
		
		//then i begin freefall
		intPY = ppmodel.freefall(intPY, dblT, blnstop);
		
		//then i check if we hit a cieling
		dblT = ppmodel.timereset(strmaparray, intPY, intPX, dblT);
		
		//this is the time reset
		//if dblT = 0, jump = false, change = 0
		if(dblT == 0){
			blnjump = false;
			ppmodel.dblChange1 = 0;
		}
		
		//then i check if we hit the floor
		blnstop = ppmodel.stopY(strmaparray, intPX, intPY);
		
		//if blnstop = true, blnjump = false, blnstart = true
		if(blnstop == true){
			blnjump = false;
			blnstart = true;
		}
		
		//the i make adjustments for Y
		intPY = ppmodel.adjustY(strmaparray, intPX, intPY);
		
		
		//jump is a player input
		//they can change jump to true
		//jump will remain true until stop is true once more
		
		//when time is 0, jump is changed to false if it is not already
		//but then jump is possible again, so...
		//actually it isn't, because stop is not true
		
		//stop check comes at the end as usual
		
		//help menu needs to check for key and door as well
		//change of plans
		//what if, the some one has to stand in the key for the door to be open
		//help menu will just be parkour
		//this makes teamwork more important
		//speaking of which, help menu gonna need some rework
		//help menu will consist of more parkour, but it will feature the door mechanic
		//they'll just never be able to reach the door
		//gonna also add spikes
		
		
		g.setColor(Color.ORANGE);
		//underneath level
		g.fillRect(1000,0,300,750);
		//for help
		g.fillRect(0,680,1000,40);
		g.setColor(Color.WHITE);
		//to make word clear
		g.fillRect(1000,40,240,600);
		g.setColor(Color.ORANGE);
		g.setFont(bigfont);
		g.drawString("Help",1060,100);
		
		//print help instructions
		g.setFont(normalfont);
		g.drawString("Hold 'a' to move left", 1010, 150);
		g.drawString("Hold 'd' to move right", 1010, 175);
		g.drawString("Press 'w' to jump", 1010, 200);
		g.drawString("Don't touch the spikes", 1010, 250);
		g.drawImage(Spike, 1175, 225, null);
		g.drawString("Gray reveals the door", 1010, 325);
		g.setColor(Color.GRAY);
		g.fillRect(1175,300,40,40);
		g.setColor(Color.ORANGE);
		g.drawString("Get to the door", 1010, 400);
		g.setColor(Color.BLACK);
		g.fillRect(1175,375,40,40);
		g.setColor(Color.ORANGE);
		g.drawString("You may need other players", 1010, 475);
		g.fillRect(1025,500,20,20);
		g.setColor(Color.YELLOW);
		g.fillRect(1075,500,20,20);
		g.setColor(Color.BLUE);
		g.fillRect(1125,500,20,20);
		g.setColor(Color.GREEN);
		g.fillRect(1175,500,20,20);
		
		/*
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Use A & D to move your", 1010, 200);
		g.drawString("character left and right.", 1010, 225);
		g.drawString("Use W to jump.", 1010, 250);
		g.drawString("Stand on the all of the buttons", 1002, 280);
		g.drawString("(grey blocks) to make the", 1010, 305);
		g.drawString("door appear.", 1010, 330);
		g.drawString("One player must each the door", 1010, 355);
		g.drawString("to get to the next level.", 1010, 380);
		g.drawString("ENJOY!", 1085, 470);
		*/
		
	}
	
	//constructor
	public Help(){
		super();
		try{
			//Spike = ImageIO.read(new File("Spike.jpg"));
			Spike = ImageIO.read(this.getClass().getResourceAsStream("Spike.jpg"));
		}catch(IOException e){
			System.out.println("Image not found");
		}
	}
	
}

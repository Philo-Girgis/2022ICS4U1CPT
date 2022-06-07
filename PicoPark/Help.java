import java.awt.*;
import javax.swing.*;

public class Help extends JPanel{
	//properties
	PP ppmodel = new PP();
	Font bigfont = new Font(null, 0, 50);
	
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
	
	//40 by 40 squares
	//1000 by 720 level
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String strmaparray[][] = ppmodel.readarray("Help.csv");
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
		
	}
	
	//constructor
	public Help(){
	}
	
}

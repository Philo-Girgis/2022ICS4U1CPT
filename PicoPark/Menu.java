import java.awt.*;
import javax.swing.*;

//am gonna try to set up a background animation

public class Menu extends JPanel{
	//properties
	int intP1X = -250;
	int intP1Y = 580;
	int intP2X = -200;
	int intP2Y = 580;
	int intP3X = -150;
	int intP3Y = 580;
	int intP4X = -100;
	int intP4Y = 580;
	int intP5X = -50;
	int intP5Y = 580;
	int intXchange = 5;
	PP ppmodel = new PP();
	double dblT1 = 0;
	double dblT2 = 0;
	double dblT3 = 0;
	double dblT4 = 0;
	double dblT5 = 0;
	boolean blnjump1 = false;
	boolean blnjump2 = false;
	boolean blnjump3 = false;
	boolean blnjump4 = false;
	boolean blnjump5 = false;
	boolean blnstop1 = true;
	boolean blnstop2 = true;
	boolean blnstop3 = true;
	boolean blnstop4 = true;
	boolean blnstop5 = true;
	Font bigfont = new Font(null, 0, 100);
	
	//might write a reset method so that once the menu is left, the animation resets
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1280,720);
		g.setColor(Color.BLACK);
		g.fillRect(0, 600, 1500,400);
		g.setColor(Color.RED);
		g.fillRect(intP1X,intP1Y,20,20);
		g.setColor(Color.GREEN);
		g.fillRect(intP2X,intP2Y,20,20);
		g.setColor(Color.YELLOW);
		g.fillRect(intP3X,intP3Y,20,20);
		g.setColor(Color.BLUE);
		g.fillRect(intP4X,intP4Y,20,20);
		g.setColor(Color.ORANGE);
		g.fillRect(intP5X,intP5Y,20,20);
		//animation
		intP1X = intP1X + intXchange;
		intP2X = intP2X + intXchange;
		intP3X = intP3X + intXchange;
		intP4X = intP4X + intXchange;
		intP5X = intP5X + intXchange;
		//reset
		intP1X = ppmodel.menureset(intP1X);
		intP2X = ppmodel.menureset(intP2X);
		intP3X = ppmodel.menureset(intP3X);
		intP4X = ppmodel.menureset(intP4X);
		intP5X = ppmodel.menureset(intP5X);
		//creatures will start to jump once each passes intX = 500
		
		//when to jump
		blnjump1 = ppmodel.menujump(intP1X, blnstop1);
		blnjump2 = ppmodel.menujump(intP2X, blnstop2);
		blnjump3 = ppmodel.menujump(intP3X, blnstop3);
		blnjump4 = ppmodel.menujump(intP4X, blnstop4);
		blnjump5 = ppmodel.menujump(intP5X, blnstop5);
		
		//check for jump
		if(blnjump1 == true){
			blnstop1 = false;
		}
		if(blnjump2 == true){
			blnstop2 = false;
		}
		if(blnjump3 == true){
			blnstop3 = false;
		}
		if(blnjump4 == true){
			blnstop4 = false;
		}
		if(blnjump5 == true){
			blnstop5 = false;
		}
		
		//time
		dblT1 = ppmodel.P1time(dblT1, blnstop1);
		dblT2 = ppmodel.P2time(dblT2, blnstop2);
		dblT3 = ppmodel.P3time(dblT3, blnstop3);
		dblT4 = ppmodel.P4time(dblT4, blnstop4);
		dblT5 = ppmodel.P5time(dblT5, blnstop5);
		
		//jump
		intP1Y = ppmodel.jump(intP1Y, dblT1, blnjump1);
		intP2Y = ppmodel.jump(intP2Y, dblT2, blnjump2);
		intP3Y = ppmodel.jump(intP3Y, dblT3, blnjump3);
		intP4Y = ppmodel.jump(intP4Y, dblT4, blnjump4);
		intP5Y = ppmodel.jump(intP5Y, dblT5, blnjump5);
		
		//freefall
		intP1Y = ppmodel.freefall(intP1Y, dblT1, blnstop1);
		intP2Y = ppmodel.freefall(intP2Y, dblT2, blnstop2);
		intP3Y = ppmodel.freefall(intP3Y, dblT3, blnstop3);
		intP4Y = ppmodel.freefall(intP4Y, dblT4, blnstop4);
		intP5Y = ppmodel.freefall(intP5Y, dblT5, blnstop5);
		
		//when to stop
		blnstop1 = ppmodel.menustop(intP1Y);
		blnstop2 = ppmodel.menustop(intP2Y);
		blnstop3 = ppmodel.menustop(intP3Y);
		blnstop4 = ppmodel.menustop(intP4Y);
		blnstop5 = ppmodel.menustop(intP5Y);
		
		//adjustments
		intP1Y = ppmodel.menuadjust(intP1Y);
		intP2Y = ppmodel.menuadjust(intP2Y);
		intP3Y = ppmodel.menuadjust(intP3Y);
		intP4Y = ppmodel.menuadjust(intP4Y);
		intP5Y = ppmodel.menuadjust(intP5Y);
		
		
		//will this still work for freefall? probably
		//depends actually
		//the stop command would change to false if it were not on solid ground
		//which means time would begin and freefall would activate
		//jump activates only if enter is pressed and player was touching the ground when it was
		//the one thing i don't have is adjusments
		//that can be done though
		
		//include a block for them to jump over
		g.setColor(Color.GRAY);
		g.fillRect(600,560, 40,40);
		
		//to hold the menu options
		g.setColor(Color.ORANGE);
		g.fillRect(240,200,800,200);
		g.setFont(bigfont);
		g.drawString("PICO PARK", 360,110);
		
	}
	
	//constructor
	public Menu(){
	}
	
}

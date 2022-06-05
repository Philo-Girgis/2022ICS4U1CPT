import java.awt.*;
import javax.swing.*;

//am gonna try to set up a background animation

public class Menu extends JPanel{
	//properties
	int intP1X = -250;
	int intP1Y = 480;
	int intP2X = -200;
	int intP2Y = 480;
	int intP3X = -150;
	int intP3Y = 480;
	int intP4X = -100;
	int intP4Y = 480;
	int intP5X = -50;
	int intP5Y = 480;
	int intXchange = 5;
	PP ppmodel = new PP();
	double dblT1 = 0;
	double dblT2 = 0;
	double dblT3 = 0;
	double dblT4 = 0;
	double dblT5 = 0;
	
	//methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1280,720);
		g.setColor(Color.BLACK);
		g.fillRect(0, 500, 1500,400);
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
		
	}
	
	//constructor
	public Menu(){
	}
	
}

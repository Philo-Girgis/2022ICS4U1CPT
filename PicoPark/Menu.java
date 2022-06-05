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
		intP1X = intP1X + intXchange;
		intP2X = intP2X + intXchange;
		intP3X = intP3X + intXchange;
		intP4X = intP4X + intXchange;
		intP5X = intP5X + intXchange;
		if(intP1X > 1500){
			intP1X = -100;
		}else if(intP2X > 1500){
			intP2X = -100;
		}else if(intP3X > 1500){
			intP3X = -100;
		}else if(intP4X > 1500){
			intP4X = -100;
		}else if (intP5X > 1500){
			intP5X = -100;
		}
	}
	
	//constructor
	public Menu(){
	}
	
}

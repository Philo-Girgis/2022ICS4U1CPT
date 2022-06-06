import java.awt.*;
import javax.swing.*;

public class Help extends JPanel{
	//properties
	PP ppmodel = new PP();
	Font bigfont = new Font(null, 0, 50);
	
	//player stuff
	int intPX = 50;
	int intPY = 50;
	double dblT = 0;
	boolean blnjump = false;
	boolean blnstop = true;
	
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
		
		g.setColor(Color.RED);
		g.fillRect(intPX, intPY, 20,20);
		
		//jump is a player input
		//they can change jump to true
		//jump will remain true until stop is true once more
		
		//when time is 0, jump is changed to false if it is not already
		
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

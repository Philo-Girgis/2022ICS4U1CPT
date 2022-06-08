import java.awt.*;
import javax.swing.*;

public class Lobby extends JPanel{
	//properties
	PP ppmodel = new PP();
	Font bigfont = new Font(null, 0, 40);
	String strready = "Not Ready";
	String stridentity = "";
	String strtotal = "";
	int intready = 0;
	
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
		g.drawString("Your Status: " +strready, 300,175);
		g.drawString("Players Ready: " +intready+ "/5",300,250);
		if(stridentity.equals("Player 1")){
			g.setColor(Color.RED);
			g.drawString("You are Player 1",300,100);
		}
		
		
		
	}
	
	//constructor
	public Lobby(){
	}
	
}

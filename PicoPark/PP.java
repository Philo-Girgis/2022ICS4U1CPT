//Application: Pico Park
//Creator: Philo
//Last Edited: 6/15/2022
//Version: 1.01


//speaking of which, someone pls consider adding descritions here
//i'm pretty sure this is the only program that requires a javadoc
//so it begins
import java.io.*;

public class PP{
	//properties
	/** time change value for player 1 */
	public double dblChange1 = 0;
	/** time change value for player 2 */
	public double dblChange2 = 0;
	/** time change value for player 3 */
	public double dblChange3 = 0;
	/** time change value for player 4 */
	public double dblChange4 = 0;
	/** time change value for player 5 */
	public double dblChange5 = 0;
	/** time change value for countdown */
	public double dblCount = 0;
	
	//methods
	
	//read array method
	/** creates an array by reading the given file */
	public String[][] readarray(String strfilename){
		String strmaparray[][] = new String[17][25];
		try{
			BufferedReader readfile = new BufferedReader(new FileReader(strfilename));
			for(int intRow = 0; intRow < 17; intRow++){
				String strline = readfile.readLine();
				String strRow[] = strline.split(",");
				for(int intCol = 0; intCol < 25; intCol++){
					strmaparray[intRow][intCol] = strRow[intCol];
				}
			}
			readfile.close();
		}catch(IOException a){
			System.out.println("file not found");
		}
		return strmaparray;
	}
	
	//general stop detection method for y
	//return boolean
	/** checks if the player has reached the floor */
	public boolean stopY(String[][] strarray, int intX, int intY){
		//checks if the floor has been reached
		boolean stop = false;
		for(int intRow = 0; intRow < 17; intRow++){
			for(int intCol = 0; intCol < 25; intCol++){
				String strread = strarray[intRow][intCol];
				if(strread.equals("w")){
					if(intX+20 > intCol*40 && intX < intCol*40+40 && intY+20 >= intRow*40 && intY+20 < intRow*40+30){
						stop = true;
					}
				}
			}
		}
		return stop;
	}
	
	//general reset time method
	//returns double
	//checks if cieling is touched
	//if yes, then time is reset
	/** checks if the player has reached the cieling */
	public double timereset(String[][] strarray, int intY, int intX, double dblT){
		//takes array, takes intY, takes times, checks if cieling is touched
		//if cieling is touched, return time as 0
		for(int intRow = 0; intRow < 17; intRow++){
			for(int intCol = 0; intCol < 25; intCol++){
				String strread = strarray[intRow][intCol];
				if(strread.equals("w")){
					if(intX+20 > intCol*40 && intX < intCol*40+40 && intY <= intRow*40+40 && intY >= intRow*40+30){
						dblT = 0;
					}
				}
			}
		}
		
		return dblT;
	}
	
	
	//general adjustment for x
	//return int
	/** adjusts X value of player so that they are not in a wall */
	public int adjustX(String[][] strarray, int intX, int intY){
		//takes array, takes intX, is inside a wall, they get put outside of it
		//must run only after intX movement is registered but before it's printed to the screen
		//no adjustment should be needed, change should = 0 when it's next to a wall
		//or should it
		//in that case, this adjustment needs to run before stopY check
		for(int intRow = 0; intRow < 17; intRow++){
			for(int intCol = 0; intCol < 25; intCol++){
				String strread = strarray[intRow][intCol];
				if(strread.equals("w")){
					if(intX+20 >= intCol*40 && intX+20 <= intCol*40+5 && intY+20 > intRow*40 && intY < intRow*40+40){
						//players hits left of wall
						intX = intCol*40-20;
					}else if(intX <= intCol*40+40 && intX >= intCol*40+35 && intY+20 > intRow*40 && intY < intRow*40+40){
						//players hits right of wall
						intX = intCol*40+40;
					}
				}
			}
		}
		return intX;
	}
	
	//general adjustment for y;
	//return int
	/** adjusts Y value of player so that they are not in a floor/cieling */
	public int adjustY(String[][] strarray, int intX, int intY){
		//takes array, takes intY, checks if inside a wall, puts it outside of it
		//must run after collision detection
		//i'm gonna change it so it's dependant 
		for(int intRow = 0; intRow < 17; intRow++){
			for(int intCol = 0; intCol < 25; intCol++){
				String strread = strarray[intRow][intCol];
				if(strread.equals("w")){
					if(intX+20 > intCol*40 && intX < intCol*40+40 && intY+20 > intRow*40 && intY < intRow*40+30){
						//players hit ground
						intY = intRow*40-20;
					}else if(intX+20 > intCol*40 && intX < intCol*40+40 && intY < intRow*40+40 && intY > intRow*40+30){
						//players hit cieling
						intY = intRow*40+40;
					}
				}
			}
		}
		
		return intY;
	}
	
	//gonna have to create a seperate detection for players now
	//ugh
	
	//reset method for menu animation
	/** resets the animation for the menu */
	public int menureset (int intX){
		if(intX > 1500){
			intX = -100;
		}
		return intX;
	}
	
	//three second countdown
	/** countdown method used in lobby and actual gameplay */
	public int countdown(int intCD){
		int intT = intCD/45;
		return intT;
		
	}
	
	//P1 time method
	/** time method for player 1, used as a default */
	public double P1time(double dblT, boolean blnstop){
		if(blnstop == false){
			dblT = 0.5 + dblChange1/60;
			dblChange1++;
		}else{
			dblChange1 = 0;
			dblT = 0;
		}
		return dblT;
	}
	
	//P2 time method
	/** time method for player 2, used to distinguish in menu animation */
	public double P2time(double dblT, boolean blnstop){
		if(blnstop == false){
			dblT = 0.5 + dblChange2/60;
			dblChange2++;
		}else{
			dblChange2 = 0;
			dblT = 0;
		}
		return dblT;
	}
	
	//P3 time method
	/** time method for player 3, used to distinguish in menu animation */
	public double P3time(double dblT, boolean blnstop){
		if(blnstop == false){
			dblT = 0.5 + dblChange3/60;
			dblChange3++;
		}else{
			dblChange3 = 0;
			dblT = 0;
		}
		return dblT;
	}
	
	//P4 time method
	/** time method for player 4, used to distinguish in menu animation */
	public double P4time(double dblT, boolean blnstop){
		if(blnstop == false){
			dblT = 0.5 + dblChange4/60;
			dblChange4++;
		}else{
			dblChange4 = 0;
			dblT = 0;
		}
		return dblT;
	}
	
	//P5 time method
	/** time method for player 5, used to distinguish in menu animation */
	public double P5time(double dblT, boolean blnstop){
		if(blnstop == false){
			dblT = 0.5 + dblChange5/60;
			dblChange5++;
		}else{
			dblChange5 = 0;
			dblT = 0;
		}
		return dblT;
	}
	
	//when to stop for menu animation
	/** tells the menu animation when it has reached the floor */
	public boolean menustop(int intY){
		if(intY >= 580){
			return true;
		}else{
			return false;
		}
	}
	
	//when to jump for menu animation
	/** tells the menu animation when to jump */
	public boolean menujump(int intX, boolean blnstop){
		if(intX >= 450 && intX <=500 || blnstop == false){
			return true;
		}else{
			return false;
		}	
	}
	
	//adjustment method for menu animation
	/** adjusts y value for menu animation so that it is not in a floor */
	public int menuadjust(int intY){
		if(intY >= 580){
			intY = 580;
		}
		return intY;
	}
	
	//general jump method
	/** method for calculating jump height */
	public int jump(int intY, double dblT, boolean blnjump){
		if(blnjump == true){
			double dblY;
			dblY = (double)intY - 10.1*dblT;
			intY = (int)dblY;
		}
		return intY;
	}
	
	//general free fall method
	/** method for calculating how far you fall */
	public int freefall(int intY, double dblT, boolean blnstop){
		if(blnstop == false){
			double dblY;
			dblY = (double)intY + 9.8*dblT*dblT;
			intY = (int)dblY;
		}
		return intY;
	}
	
	//repeat code for collision and adjusment but now for players
	
	//player stop check for y (hitting the floor)
	//checks if it has hit other players
	//need player coordinates instead of an array
	/** checks if the player has touched the top of another player */
	public boolean stopPY(int intX1, int intY1, int intX2, int intY2, int intX3, int intY3, int intX4, int intY4, int intX5, int intY5){
		//if the bottom of "player1" touches the top of any other player, then stop = true
		//else they have no touched and stop is false
		if(intX1+20 > intX2 && intX1 < intX2+20 && intY1+20 >= intY2 && intY1+20 < intY2+20){
			return true;
		}else if(intX1+20 > intX3 && intX1 < intX3+20 && intY1+20 >= intY3 && intY1+20 < intY3+20){
			return true;
		}else if(intX1+20 > intX4 && intX1 < intX4+20 && intY1+20 >= intY4 && intY1+20 < intY4+20){
			return true;
		}else if(intX1+20 > intX5 && intX1 < intX5+20 && intY1+20 >= intY5 && intY1+20 < intY5+20){
			return true;
		}else{
			return false;
		}
	}
	
	
	//player time reset for y (hitting cieling)
	/** checks if the player has touched the bottom of another player */
	public double resetPT(double dblT, int intX1, int intY1, int intX2, int intY2, int intX3, int intY3, int intX4, int intY4, int intX5, int intY5){
		if(intX1+20 > intX2 && intX1 < intX2+20 && intY1 <= intY2+20 && intY1 > intY2){
			return 0;
		}else if(intX1+20 > intX3 && intX1 < intX3+20 && intY1 <= intY3+20 && intY1 > intY3){
			return 0;
		}else if(intX1+20 > intX4 && intX1 < intX4+20 && intY1 <= intY4+20 && intY1 > intY4){
			return 0;
		}else if(intX1+20 > intX5 && intX1 < intX5+20 && intY1 <= intY5+20 && intY1 > intY5){
			return 0;
		}else{
			return dblT;
		}
	}
	
	//player x adjustment
	/** adjusts player x value so that they are not in another player's side */
	public int adjustPX(int intX1, int intY1, int intX2, int intY2, int intX3, int intY3, int intX4, int intY4, int intX5, int intY5){
		//left of "P1" hits right of a player
		if(intX1 <= intX2+20 && intX1 > intX2 && intY1+20 > intY2 && intY1 < intY2+20){
			intX1 = intX2+20;
		}else if(intX1 <= intX3+20 && intX1 > intX3 && intY1+20 > intY3 && intY1 < intY3+20){
			intX1 = intX3+20;
		}else if(intX1 <= intX4+20 && intX1 > intX4 && intY1+20 > intY4 && intY1 < intY4+20){
			intX1 = intX4+20;
		}else if(intX1 <= intX5+20 && intX1 > intX5 && intY1+20 > intY5 && intY1 < intY5+20){
			intX1 = intX5+20;
		}
		
		//right of "P1" left of a player
		if(intX1+20 >= intX2 && intX1+20 < intX2+20 && intY1+20 > intY2 && intY1 < intY2+20){
			intX1 = intX2-20;
		}else if(intX1+20 >= intX3 && intX1+20 < intX3+20 && intY1+20 > intY3 && intY1 < intY3+20){
			intX1 = intX3-20;
		}else if(intX1+20 >= intX4 && intX1+20 < intX4+20 && intY1+20 > intY4 && intY1 < intY4+20){
			intX1 = intX4-20;
		}else if(intX1+20 >= intX5 && intX1+20 < intX5+20 && intY1+20 > intY5 && intY1 < intY5+20){
			intX1 = intX5-20;
		}
		
		return intX1;
	}
	
	
	//player y adjustment
	/** adjusts y value of player so that they are not in the head or bottom of another player */
	public int adjustPY(int intX1, int intY1, int intX2, int intY2, int intX3, int intY3, int intX4, int intY4, int intX5, int intY5){
		//player "cieling" 
		if(intX1+20 > intX2 && intX1 < intX2+20 && intY1 <= intY2+20 && intY1 > intY2){
			intY1 = intY2+20;
		}else if(intX1+20 > intX3 && intX1 < intX3+20 && intY1 <= intY3+20 && intY1 > intY3){
			intY1 = intY3+20;
		}else if(intX1+20 > intX4 && intX1 < intX4+20 && intY1 <= intY4+20 && intY1 > intY4){
			intY1 = intY4+20;
		}else if(intX1+20 > intX5 && intX1 < intX5+20 && intY1 <= intY5+20 && intY1 > intY5){
			intY1 = intY5+20;
		}
		
		//player "floor"
		if(intX1+20 > intX2 && intX1 < intX2+20 && intY1+20 >= intY2 && intY1+20 < intY2+20){
			intY1 = intY2-20;
		}else if(intX1+20 > intX3 && intX1 < intX3+20 && intY1+20 >= intY3 && intY1+20 < intY3+20){
			intY1 = intY3-20;
		}else if(intX1+20 > intX4 && intX1 < intX4+20 && intY1+20 >= intY4 && intY1+20 < intY4+20){
			intY1 = intY4-20;
		}else if(intX1+20 > intX5 && intX1 < intX5+20 && intY1+20 >= intY5 && intY1+20 < intY5+20){
			intY1 = intY5-20;
		}
		
		return intY1;
	}
	
	
	//constuctor
	/** contructor of the pico park game model */
	public PP(){
	}
	
}

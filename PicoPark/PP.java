//Application: Pico Park
//Creators: Philo, Jerry, Alan
//Last Edited: 6/6/2022
//Version: 0.1

//i'm pretty sure this is the only program that requires a javadoc
import java.io.*;

public class PP{
	//properties
	double dblChange1;
	double dblChange2;
	double dblChange3;
	double dblChange4;
	double dblChange5;
	
	//methods
	
	//read array method
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
	public boolean stopY(){
		//takes array, takes intY, checks if inside wall, tells it it can stop now
		//if it gets to the floor, everything stops
		//if it gets the cieling, jump gets turned off and time resets
		//will figure out later
		
		return true;
	}
	
	//general reset time method
	//returns double
	//checks if cieling is touched
	//if yes, then time is reset
	public double timereset(){
		//takes array, takes intY, takes times, checks if cieling is touched
		//if cieling is touched, return time as 0
		
		return 0.0;
	}
	
	
	//general adjustment for x
	//return int
	public int adjustX(){
		//takes array, takes intX, is inside a wall, they get put outside of it
		//must run only after intX movement is registered but before it's printed to the screen
		
		return 0;
	}
	
	//general adjustment for y;
	//return int
	public int adjustY(){
		//takes array, takes intY, checks if inside a wall, puts it outside of it
		//must run after collision detection
		
		return 0;
	}
	
	//gonna have to create a seperate detection for players now
	//ugh
	
	//reset method for menu animation
	public int menureset (int intX){
		if(intX > 1500){
			intX = -100;
		}
		return intX;
	}
	
	//P1 time method
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
	public boolean menustop(int intY){
		if(intY >= 580){
			return true;
		}else{
			return false;
		}
	}
	
	//when to jump for menu animation
	public boolean menujump(int intX, boolean blnstop){
		if(intX >= 450 && intX <=500 || blnstop == false){
			return true;
		}else{
			return false;
		}	
	}
	
	//adjustment method for menu animation
	public int menuadjust(int intY){
		if(intY >= 580){
			intY = 580;
		}
		return intY;
	}
	
	//general jump method
	public int jump(int intY, double dblT, boolean blnjump){
		if(blnjump == true){
			double dblY;
			dblY = (double)intY - 10.1*dblT;
			intY = (int)dblY;
		}
		return intY;
	}
	
	//general free fall method
	public int freefall(int intY, double dblT, boolean blnstop){
		if(blnstop == false){
			double dblY;
			dblY = (double)intY + 9.8*dblT*dblT;
			intY = (int)dblY;
		}
		return intY;
	}
	
	//constuctor
	public PP(){
	}
	
}

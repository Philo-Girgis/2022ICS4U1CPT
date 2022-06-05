//Application: Pico Park
//Creators: Philo, Jerry, Alan
//Last Edited: 6/4/2022
//Version: 0.005

//i'm pretty sure this is the only program that requires a javadoc

public class PP{
	//properties
	double dblChange1;
	double dblChange2;
	double dblChange3;
	double dblChange4;
	double dblChange5;
	
	//methods
	//reset method for menu animation
	public int menureset (int intX){
		if(intX > 1500){
			intX = -100;
		}
		return intX;
	}
	
	//P1 time method
	public double P1time(double dblT){
		dblT = 0.5 + dblChange1/60;
		dblChange1++;
		return dblT;
	}
	
	//P2 time method
	public double P2time(double dblT){
		dblT = 0.5 + dblChange2/60;
		dblChange2++;
		return dblT;
	}
	
	//P3 time method
	public double P3time(double dblT){
		dblT = 0.5 + dblChange3/60;
		dblChange3++;
		return dblT;
	}
	
	//P4 time method
	public double P4time(double dblT){
		dblT = 0.5 + dblChange4/60;
		dblChange4++;
		return dblT;
	}
	
	//P5 time method
	public double P5time(double dblT){
		dblT = 0.5 + dblChange5/60;
		dblChange5++;
		return dblT;
	}
	
	//stop method for menu animation
	public boolean menustop(int intY){
		if(intY >= 480){
			return true;
		}else{
			return false;
		}
	}
	
	//general jump method
	public int jump(int intY, double dblT){
		double dblY;
		dblY = (double)intY - 10.1*dblT;
		intY = (int)dblY;
		return intY;
	}
	
	//general free fall method
	public int freefall(int intY, double dblT){
		double dblY;
		dblY = (double)intY + 9.8*dblT*dblT;
		intY = (int)dblY;
		return intY;
	}
	
	//constuctor
	public PP(){
	}
	
}

//this is where I can do calculations whenever a jump is made

public class testlogic{
	//properties
	private int intChange = 0;

	//methods
	//jump method
	public int jump(int intY, double dblT){
		double dblY;
		dblY = (double)intY - 10.1*dblT;
		intY = (int)dblY;
		return intY;
	}
	
	//free fall method
	public int freefall(int intY, double dblT){
		double dblY;
		dblY = (double)intY + 9.8*dblT*dblT;
		intY = (int)dblY;
		return intY;
	}
	
	//check for stop
	public boolean stop(int intY){
		if(intY >= 400-20){
			intChange = 0;
			return true;
		}else{
			return false;
		}
	}
	
	//time method
	public double time(double dblT){
		double dblChange = (double)intChange;
		dblT = 0.5 + dblChange/60;
		intChange++;
		return dblT;
	}

	//constructor
	public testlogic(){
	}

}

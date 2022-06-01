//this is where I can do calculations whenever a jump is made

public class testlogic{
	//properties
	private int intChange = 0;

	//methods
	public int jump(int intY, double dblT){
		double dblY;
		dblY = (double)intY - 7.5*dblT;
		intY = (int)dblY;
		return intY;
	}
	
	public int freefall(int intY, double dblT){
		double dblY;
		dblY = (double)intY + 5.5*dblT*dblT;
		intY = (int)dblY;
		return intY;
	}
	
	public boolean stop(int intY){
		if(intY >= 400-20){
			return true;
		}else{
			return false;
		}
	}
	
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

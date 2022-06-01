//this is where I can do calculations whenever a jump is made

public class testlogic{
	//properties
	private int intChange = 0;

	//methods
	public int jump(int intY, int intT){
		intY = intY - intT;
		return intY;
	}
	
	public int freefall(int intY, int intT){
		intY = intY + intT*intT;
		return intY;
	}
	
	public boolean stop(int intY){
		if(intY >= 400-20){
			return true;
		}else{
			return false;
		}
	}
	
	public int time(int intT){
		intT = 1 + intChange/20;
		intChange++;
		return intT;
	}

	//constructor
	public testlogic(){
	}

}

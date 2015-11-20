package entity;



public class Dicebox {

	private Dice t1 = new Dice();
	private Dice t2 = new Dice();
     
    
    
	public int roll() {
		
		int res1 = t1.roll();
		int res2 = t2.roll();
		
		int res = res1 + res2;
		return res;
	}
	
	public boolean isEqual(){
		if (t1.getValue()==t2.getValue()){
			 return true;
		}
		return false;
	}

	
	public int getDiceSum(int res){

		return t1.getValue() + t2.getValue();
		
	}

}
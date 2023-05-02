package mbs;

public class Communication {
	public String makePackage(int id, double ph, double light, double water) {
		return String.format("%d %lf %lf %lf",id, ph, light, water); //id ph light water
	}
	
	public void sendPackage(String s) {
		
	}
}

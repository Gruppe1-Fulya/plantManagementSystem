package mbs;

public class Main {
	public static void main(String[] args) {
		Config config = new Config();
		
		config.updatePlantID();
		config.updatewaitTime();
		
		Application app = new Application(config);
		
		System.out.println(app.readPH());
	 }
}

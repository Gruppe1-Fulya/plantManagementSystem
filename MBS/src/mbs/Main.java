package mbs;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		Config config = new Config();
		Communication com = new Communication();
		
		double ph, light, water;
		
		Application app = new Application(config);
		while(app.phScanner.hasNextLine()) {
			ph = app.readPH();
			light = app.readLight();
			water = app.readWater();
			
			com.sendPackage(com.makePackage(config.getplantID(), ph, light, water));
			
			try {
				TimeUnit.SECONDS.sleep(config.getwaitTime());
			} catch (Exception e) {
				System.out.println("Sleep error!");
			}
		}
	 }
}

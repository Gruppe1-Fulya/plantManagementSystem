package mbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {

	File configFile;
	Scanner configScanner;
	
	private int plantID;
	private int waitTime;
	
	public Config() {
		try {
			this.configFile = new File("config.txt");
			this.configScanner = new Scanner(this.configFile);
		} catch (FileNotFoundException e) {
			System.out.println("config file doesn't exist.");
		}
		
		plantID = Integer.parseInt(this.configScanner.nextLine());
		waitTime = Integer.parseInt(this.configScanner.nextLine());
	}
	
	
	public int getplantID() {
		return plantID;
	}
	
	public int getwaitTime() {
		return waitTime;
	}
}

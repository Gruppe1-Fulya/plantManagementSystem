package mbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
	Config config;
	File phFile;
	File waterFile;
	File lightFile;
	Scanner phScanner;
	Scanner waterScanner;
	Scanner lightScanner;
	public Application(Config config) {
		this.config = config;
		try {
		this.phFile = new File(String.format("%d_ph.txt", config.getplantID()));
		this.waterFile = new File(String.format("%d_water.txt", config.getplantID()));
		this.lightFile = new File(String.format("%d_light.txt", config.getplantID()));
		
		this.phScanner = new Scanner(this.phFile);
		this.waterScanner = new Scanner(this.waterFile);
		this.lightScanner = new Scanner(this.lightFile);
		} catch (FileNotFoundException e) {
			System.out.println("A file not found.");
		}
		
	}
	
	public double readPH() {
		System.out.println(phFile.getAbsolutePath());
		return Double.parseDouble(this.phScanner.nextLine());
	}
	
	public double readWater() {
		return Double.parseDouble(this.waterScanner.nextLine());
	}
	
	public double readLight() {
		return Double.parseDouble(this.lightScanner.nextLine());
	}
}

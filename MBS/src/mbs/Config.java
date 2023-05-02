package mbs;

public class Config {

	private int plantID = -1;
	private int waitTime = -1;
	
	public void updatePlantID() {
		this.plantID = 1;
	}
	
	public void updatewaitTime() {
		this.waitTime = 1;
	}
	
	public int getplantID() {
		return plantID;
	}
	
	public int getwaitTime() {
		return waitTime;
	}
}

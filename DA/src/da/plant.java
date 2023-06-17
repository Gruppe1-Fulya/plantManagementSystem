package application;

class plant {
    private double light;
    private double water;
    private double pH;
    private double oplight;
    private double opwater;
    private double opph;

    public plant(double light, double water, double pH) {
        this.light = light;
        this.water = water;
        this.pH = pH;
        this.oplight = 50.00;
        this.opwater = 50.00;
        this.opph = 7.00;
    }
    
    public double getLight() {
        return light;
    }

    public double getWater() {
        return water;
    }

    public double getPH() {
        return pH;
    }
    public double getopph() {
        return opph;
    }
    public double getoplight() {
        return oplight;
    }
    public double getopwater() {
        return opwater;
    }
    public void setlight(double op) {
    	this.light = op;
    }
    public void setph(double op) {
    	this.pH = op;
    }
    public void setwater(double op) {
    	this.water = op;
    }
    public void setoplight(double op) {
    	this.oplight = op;
    }
    public void setopwater(double op) {
    	this.opwater = op;
    }
    public void setopph(double op) {
    	this.opph = op;
    }
    
}
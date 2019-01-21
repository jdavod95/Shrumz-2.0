package game.soil;

import render2d.shape.Shape;

public abstract class Soil {

	Shape skin;
	
	final int MAXFRT;
	int fert;
	int tiredness;
	int[] treshold;

	public Soil(int fert, int MAXFRT, Shape skin) {
		this.MAXFRT = MAXFRT;
		this.skin = skin;
		treshold = new int[MAXFRT+1];
		tiredness = 0;
		setFert(fert);
	}

	public int getFert() {
		return fert;
	}

	public abstract void setFert(int fert);

	public void incFert(boolean inc){
		if(inc){
			if(fert < MAXFRT)
				setFert(fert+1);
		}
		else
			if(fert > 0)
				setFert(fert-1);
		setTiredness(0);
	}

	public int getTiredness() {
		return tiredness;
	}

	void setTiredness(int tiredness) {
		this.tiredness = tiredness;
	}

	public void incTire(){
		if(tiredness >= treshold[fert])
			incFert(false);
		else
			tiredness++;
	}
	
	public Shape getSkin() {
		return skin;
	}

	public int[] getTreshold() {
		return treshold;
	}

	public void setTreshold(int[] treshold) {
		this.treshold = treshold;
	}

	
	
}

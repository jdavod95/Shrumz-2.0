package game.plant;

import game.soil.Soil;
import render2d.elements.Point;

public abstract class Plant{
	
	private final String TEXTURENAME;
	private final int ENDSTAGE;
	private final int SPREADSTAGE;
	
	int stage = 0,
		age = 0,
		treshold = 1;
	
	Plant(String texName, int sprst, int endst){
		SPREADSTAGE = sprst;
		ENDSTAGE = endst;
		TEXTURENAME = texName;
	}

	private void incAge(){
		age++;
	}

	public void incStage(){
		stage++;
	}

	public boolean inSpreadStage(){
		return stage == SPREADSTAGE;
	}
	
	public boolean inEndStage(){
		return stage >= ENDSTAGE;
	}
	
	public abstract void grow(Soil s);
	
	// spread pattern relative to tile position
	public abstract Point[] spread();

	public String getTEXTURENAME() {
		return TEXTURENAME;
	}

	public int getStage() {
		return stage;
	}
	
	public final void cycle(Soil s){
		incAge();
		if(age % treshold == 0)		// to be changed
			grow(s);
	}
	
	protected void die(){
		stage = ENDSTAGE+1;
	}
	
	public abstract Plant getNew();
}

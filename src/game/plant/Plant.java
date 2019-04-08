package game.plant;

import elements.IndexPair;
import game.soil.Soil;

public abstract class Plant {
	
	private final int TEXTUREID;
	private final int ENDSTAGE;
	private final int SPREADSTAGE;
	
	int stage = 0,
		age = 0,
		treshold = 1;
	
	Plant(int txid, int sprst, int endst){
		SPREADSTAGE = sprst;
		ENDSTAGE = endst;
		TEXTUREID = txid;
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
	public abstract IndexPair[] spread();

	public int getTEXTUREID() {
		return TEXTUREID;
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

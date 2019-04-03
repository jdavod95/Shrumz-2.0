package game.plant;

import elements.IndexPair;

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

	public void incAge(){
		age++;
		if(age % treshold == 0)		// to be changed
			grow();
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
	
	public abstract void grow();
	
	// spread pattern relative to tile position
	public abstract IndexPair[] spread();

	public int getTEXTUREID() {
		return TEXTUREID;
	}

	public int getStage() {
		return stage;
	}
	
	public abstract Plant getNew();
}

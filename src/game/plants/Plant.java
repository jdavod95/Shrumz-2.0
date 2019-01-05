package game.plants;

import render2d.RectTex;

import org.lwjgl.util.Point;

import game.Tile;

public abstract class Plant {

	RectTex skin;
	
	public final int ENDSTAGE;
	public final int SPRSTAGE; // spreadstage
	int stage;
	int age;
	
	Plant(int x, int y, int scale, int fid, int sprst, int endst){
		skin = new RectTex(x,y,scale,scale,fid,1);
		SPRSTAGE = sprst;
		ENDSTAGE = endst;
		stage = 0;
		age = 0;
	}
	

	public RectTex getSkin(){
		return skin;
	}
	public void incAge(){
		age++;
	}
	public int getAge(){
		return age;
	}
	
	public abstract void setStage();
	public int getStage(){
		return stage;
	}
	
	public abstract void grow();
	public abstract Plant[] spread(Point[][] n, Tile t);
	public abstract boolean cycle(int fert, Tile t);
	// return stage == SPRSTAGE;
}

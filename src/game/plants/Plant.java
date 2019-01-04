package game.plants;

import render2d.RectTex;

import org.lwjgl.util.Point;

import game.Tile;

public abstract class Plant {

	RectTex skin;
	
	final int ENDSTAGE;
	final int SPRSTAGE; // spreadstage
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
	public abstract Tile[] spread(Point[][] n);
	public abstract boolean cycle(int fert, Tile t);
	// return stage == SPRSTAGE;
}

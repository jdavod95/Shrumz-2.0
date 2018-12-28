package game;

import elements.Clickable;
import render2d.Rect;
import render2d.RectTex;
import render2d.Render;
import render2d.Shape;
import root.Shrumz;

public class Tile implements Clickable{
	
	static boolean regen = false;
	static boolean down;
	static boolean hidden = false;
	static int scale = 32;
	Rect soil;
	RectTex shrum;
	
	int fert; 	// 0 - 3
	int stage;	// 0 - 3
	
	/* fert col codes
	 * 160 128 64
	 * 160 96 32
	 * 96 64 32
	 * 64 32 0 
	 * */
	
	Tile(int x, int y){
		fert = 3;
		stage = 0;
		soil = new Rect(x,y,scale,scale,64,32,0);
		shrum = new RectTex(x,y,scale,scale,2,0);
	}
	
	public static int getScale(){
		return scale;
	}
	
	public static void setScale(int sc){
		scale = sc;
	}

	public void reScale(int xdif, int ydif){
		soil.setX(soil.getX()+xdif);
		soil.setY(soil.getY()+ydif);
		shrum.setX(shrum.getX()+xdif);
		shrum.setY(shrum.getY()+ydif);
		
		soil.setW(scale);
		soil.setH(scale);
		shrum.setW(scale);
		shrum.setH(scale);
	}
	
	public int getFert() {
		return fert;
	}

	public void setFert(int fert) {
		if(fert > 3)
			fert = 3;
		else if(fert < 0)
			fert = 0;
		
		this.fert = fert;
		switch(fert){
			case 0: soil.setCol(160, 128, 64); break;
			case 1: soil.setCol(160, 96, 32); break;
			case 2: soil.setCol(96, 64, 32); break;
			case 3: soil.setCol(64, 32, 0); break;
		}
	}

	public void setStage(int stage) {
		if(stage > 3 || stage < 0)
			stage = 0;
		this.stage = stage;
		shrum.setFcu(stage);
	}	
	
	public void toRender(){
		Render.addShape(soil, 2);
		if(!hidden && stage != 0)
			Render.addShape(shrum, 3);
	}
	
	public boolean cycle(){
		switch(stage){
			case 3: return true;	
			case 2: 
			case 1:
				if(fert > 0){
					setStage(stage+1);
					setFert(fert-1);
				}
				else {
					setStage(0);
					if(stage > 1)
						setFert(fert+1);	
				}
				break;
			case 0:
				if(regen && Shrumz.getTicks()%12 == 0)
					setFert(fert+1);
		}
		return false;
	}
	
	public void spread(){
		switch(stage){	
			case 2: 
			case 1: return;
			case 3: setStage(0); break;
			case 0: setStage(1); break;
		}
	}

	@Override
	public Shape getShape() {
		return soil;
	}

	@Override
	public boolean getVis() {
		return true;
	}

	@Override
	public void release() {
		down = false;
	}

	@Override
	public void action() {
		if(!down){
			down = true;
			setStage(1);
			setFert(3);
		}
	}

	public static boolean isRegen() {
		return regen;
	}

	public static void setRegen(boolean regen) {
		Tile.regen = regen;
	}

	public static boolean isHidden() {
		return hidden;
	}

	public static void setHidden(boolean hidden) {
		Tile.hidden = hidden;
	}
}
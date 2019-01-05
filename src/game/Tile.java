package game;

import org.lwjgl.util.Point;

import elements.Clickable;
import game.plants.Plant;
import game.plants.Shrum;
import render2d.Rect;
import render2d.RectTex;
import render2d.Render;
import render2d.Shape;
import root.Shrumz;

public class Tile implements Clickable{
	
	static boolean regen = true;
	static boolean down;
	static boolean hidden = false;
	static int scale = 32;
	Rect soil;
	//RectTex shrum;
	
	Plant plant;

	Point[][] neigh;
	int fert; 	// 0 - 3
	
	/* fert col codes
	 * 160 128 64
	 * 160 96 32
	 * 96 64 32
	 * 64 32 0 
	 * */
	
	public Tile(int x, int y, Plant p){
		fert = 0;
		soil = new Rect(x,y,scale,scale,160,128,64);
		plant = p;
	}
	
	public static int getScale(){
		return scale;
	}
	
	public static void setScale(int sc){
		scale = sc;
	}
	
	public Plant getPlant(){
		return plant;
	}
	
	public void setPlant(Plant p){
		plant = p;
	}
	
	public void reScale(int xdif, int ydif){
		soil.setX(soil.getX()+xdif);
		soil.setY(soil.getY()+ydif);
		
		soil.setW(scale);
		soil.setH(scale);
		
		if(plant == null)
			return;
		
		plant.getSkin().setX(plant.getSkin().getX()+xdif);
		plant.getSkin().setY(plant.getSkin().getY()+ydif);

		plant.getSkin().setW(scale);
		plant.getSkin().setH(scale);
	}
	
	public int getFert() {
		return fert;
	}
	
	public int getStage(){
		if(plant == null)
			return -1;
		return plant.getStage();
	}

	public void setNeigh(Point[][] p){
		neigh = p;
	}
	public Point[][] getNeigh(){
		return neigh;
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


	public void toRender(){
		Render.addShape(soil, 2);
		if(!hidden && plant != null)
			Render.addShape(plant.getSkin(), 3);
		
	}
	public Plant[] spread(){
		return plant.spread(neigh, this);
	}
	
	public boolean cycle(){
		if(Shrumz.getTicks() % 12 == 0 && regen)
			setFert(getFert()+1);
		if(plant == null)
			return false;
		return plant.cycle(fert, this);
		// késõbb soil
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
		// kókány ez..
			setPlant(
				new Shrum(
					soil.getX(),
					soil.getY(),
					scale
				)
			);
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
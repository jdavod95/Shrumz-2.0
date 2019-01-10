package game;

import org.lwjgl.util.Point;

import elements.Clickable;
import game.plants.Plant;
import game.plants.Shrum;
import game.plants.Weed;
import render2d.Render;
import render2d.shape.Rect;
import render2d.shape.Shape;
import root.Shrumz;

public class Tile implements Clickable{
	
	static boolean regen = true;
	static boolean down;
	static boolean hidden = false;
	static int scale = 32;
	
	Plant plant;

	Point[][] neigh;
	// TODO soil class
	Rect soil;
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

	public void setNeigh(Point[][] p){
		neigh = p;
	}
	
	public Point[][] getNeigh(){
		return neigh;
	}
	
//
// --------------------- Clickable ---------------------
// --------------------- Clickable ---------------------
// --------------------- Clickable ---------------------
//
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
			switch(Screen.getBrushPlant()){
			case "Shrum":
				setPlant(
						new Shrum(
							soil.getX(),
							soil.getY()
						)
					);
				break;
			case "Weed":
				setPlant(
						new Weed(
							soil.getX(),
							soil.getY()
						)
					);
				break;
			default: setPlant(null);
			}
			if(Screen.getBrushFert() != -1)
				setFert(Screen.getBrushFert());
		}
	}
	
//
// --------------------- Core ---------------------
// --------------------- Core ---------------------
// --------------------- Core ---------------------
//
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
	
	public void toRender(){
		Render.addShape(soil, 2);
		if(!hidden && plant != null)
			Render.addShape(plant.getSkin(), 3);
		
	}
	
	public void chkDead(){
		if(plant != null)
			plant.die(this);
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
	
	public Plant[] spread(){
		return plant.spread(neigh);
	}
	
	public boolean cycle(){
		if(Shrumz.getTicks() % 12 == 0 && regen)
			setFert(getFert()+1);
		if(plant == null)
			return false;
		return plant.cycle(this) && plant.isSpreading();
		// késõbb soil
	}


}
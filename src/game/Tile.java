package game;

import org.lwjgl.util.Point;

import elements.Clickable;
import game.plant.Plant;
import game.plant.Shrum;
import game.plant.Weed;
import game.soil.Dirt;
import game.soil.Soil;
import render2d.Render;
import render2d.shape.Rect;
import render2d.shape.RectIsom;
import render2d.shape.Shape;
import root.Shrumz;

public class Tile implements Clickable{
	
	static boolean regen = true;
	static boolean down;
	static boolean hidden = false;
	static int scale = 32;
	
	Plant plant;
	Soil soil;
	Point[][] neigh;
	     
	public Tile(int x, int y, Plant p){
		soil = new Dirt(x,y,Tile.getScale());
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
	
	
	public Soil getSoil() {
		return soil;
	}

	public void setSoil(Soil soil) {
		this.soil = soil;
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
		return soil.getSkin();
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
							getShape().getX()+getShape().getW()/4,
							getShape().getY()-getShape().getH()/2
						)
					);
				break;
			case "Weed":
				setPlant(
						new Weed(
							getShape().getX()+getShape().getW()/4,
							getShape().getY()-getShape().getH()/2
						)
					);
				break;
			default: setPlant(null);
			}
			if(Screen.getBrushFert() != -1)
				soil.setFert(Screen.getBrushFert());
		}
	}
	@Override
	public boolean contains(Point m){

		if(m.getX() < getShape().getX() || m.getX() > getShape().getX() + getShape().getW())
			return false;
		if(m.getY() < getShape().getY() || m.getY() > getShape().getY() + getShape().getH())
			return false;
		
		int x = getShape().getX();
		int y = getShape().getY();
		
		int mx = m.getX();
		int my = m.getY();

		int w = getShape().getW()/2;

		if(mx < x+w){
			if(my < (mx-x+w)/2+y && my > (x-mx+w)/2+y)
				return true;
		} else
			if(my < (x-mx+w)/2+w+y && my > (mx-x-w)/2+y)
				return true;
		
		return false;
	}
	
	@Override
	public void hover() {	
		Render.addShape(new RectIsom(getShape().getX(), getShape().getY(), getShape().getH(),128, 128, 128,0.5),4);
	}
//
// --------------------- Core ---------------------
// --------------------- Core ---------------------
// --------------------- Core ---------------------
//
	public void reScale(int xdif, int ydif){
		getShape().setX(getShape().getX()+xdif);
		getShape().setY(getShape().getY()+ydif);
		
		getShape().setW(scale);
		getShape().setH(scale/2);
		
		if(plant == null)
			return;
		
		plant.getSkin().setX(getShape().getX()+getShape().getW()/4);
		plant.getSkin().setY(getShape().getY()-getShape().getH()/2);

		plant.getSkin().setW(scale);
		plant.getSkin().setH(scale);
	}
	
	public void toRender(){
		Render.addShape(getShape(), 2);
		if(!hidden && plant != null)
			Render.addShape(plant.getSkin(), 3);
	}
	
	public void chkDead(){
		if(plant != null)
			plant.die(this);
	}
	
	public Plant[] spread(){
		return plant.spread(neigh);
	}
	
	public boolean cycle(){
//		if(Shrumz.getTicks() % 12 == 0 && regen)
//			setFert(getFert()+1);
		if(plant == null)
			return false;
		return plant.cycle(this) && plant.isSpreading();
		// késõbb soil
	}




}
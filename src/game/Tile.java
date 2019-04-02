package game;

import org.lwjgl.util.Point;

import elements.Clickable;
import elements.IndexPair;
import game.plant.NoPlant;
import game.plant.Plant;
import game.soil.Dirt;
import game.soil.Soil;
import game.soil.Water;
import render2d.Color;
import render2d.Render;
import render2d.shape.Rect;
import render2d.shape.RectIsom;
import render2d.shape.RectTex;
import render2d.shape.Shape;

public class Tile implements Clickable{

	private IndexPair pos;

	static boolean down;
	static int scale = 32;
	
	private Plant plant;
	private Soil soil;

	private RectTex plantSkin;
	private Rect soilSkin;
	
	public Tile(int x, int y, Plant p, IndexPair pos){
		soil = new Dirt(x,y,Tile.getScale());
		setPlant(p);
		this.pos = pos;
	}
	
	public static int getScale(){
		return scale;
	}
	
	public static void setScale(int sc){
		scale = sc;
	}
	
	public boolean hasPlant(){
		return !(plant instanceof NoPlant);
	}
	
	public Plant getPlant(){
		return plant;
	}
	
	public void setPlant(Plant p){
		plant = p;
		plantSkin = new RectTex(
				soil.getSkin().getX()+soil.getSkin().getW()/4, 
				soil.getSkin().getY()-soil.getSkin().getH()/2,
				scale, scale,
				p.getTEXTUREID(), 0
				);
	}
	
	public Soil getSoil() {
		return soil;
	}

	public void setSoil(Soil soil) {
		this.soil = soil;
	}

	public RectTex getPlantSkin() {
		return plantSkin;
	}

	public void setPlantSkin(RectTex plantSkin) {
		this.plantSkin = plantSkin;
	}

	public Rect getSoilSkin() {
		return soilSkin;
	}

	public void setSoilSkin(Rect soilSkin) {
		this.soilSkin = soilSkin;
	}
	
	public IndexPair getPos() {
		return pos;
	}

	//
// --------------------- Clickable ---------------------
// --------------------- Clickable ---------------------
// --------------------- Clickable ---------------------
//
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
			setPlant(Screen.getBrushPlant());
			
			switch(Screen.getBrushSoil()){
				case "Dirt":
					if(!(soil instanceof Dirt)){
						soil = new Dirt(
								soil.getSkin().getX(),
								soil.getSkin().getY(),
								Tile.getScale());
						soil.setFert(Screen.getBrushFert());
					}
				break;

			}
			if(Screen.getBrushFert() != -1)
				soil.setFert(Screen.getBrushFert());
		}
	}
	@Override
	public boolean contains(Point m){
		
		int x = getShape().getX();
		int y = getShape().getY();

		int w = getShape().getW()/2;
		
		int mx = m.getX();
		int my = m.getY();

		if(mx < x || mx > x + getShape().getW())
			return false;
		if(my < y || my > y + getShape().getH())
			return false;

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
		Render.addScn(
				new RectIsom(
						getShape().getX(),
						getShape().getY(),
						getShape().getH(),
						Color.GRAY,
						0.5),
				4);
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
		
		if(!hasPlant())
			return;
		
		plantSkin.setX(getShape().getX()+getShape().getW()/4);
		plantSkin.setY(getShape().getY()-getShape().getH()/2);

		plantSkin.setW(scale);
		plantSkin.setH(scale);
	}
	
	public void toRender(){
		Render.addScn(getShape(), 0);
		if(hasPlant())
			Render.addScn(plantSkin, 1);
	}
	
	private void cycleSoil(){
		if(soil instanceof Water)
			soil.incTire();
	} 

	private void cyclePlant(){
		if(hasPlant()){
			plant.incAge();
			plantSkin.setFcu(plant.getStage());
			if(plant.inSpreadStage())
				Map.subSpread(this);
			if(plant.inEndStage())
				Map.subDead(this);
		}
	}
	
	public void cycle(){
		cyclePlant();
		cycleSoil();
	}

	public void killPlant() {
		plant = null;			// or epmtyGameObject
	}

	public IndexPair[] spreadPlant(){
		return plant.spread();
	}
	
}
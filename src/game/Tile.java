package game;

import elements.Cursor;
import elements.IndexPair;
import elements.MyEvent;

import game.plant.NoPlant;
import game.plant.Plant;
import game.soil.Dirt;
import game.soil.Soil;
import game.soil.SoilEffect;

import render2d.Color;
import render2d.Render;

import render2d.shapeNew.Shape;

public class Tile{

	private static int scale = 32;
	
	private IndexPair pos;

	private Plant plant;
	private Soil soil;
	private Affector aff;
	
	private RectTex plantSkin;
	private DiamondClickable soilSkin;

	
	private MyEvent click = new MyEvent(){
		public void action(){
			if(Screen.getBrushPlant() != null)
				setPlant(Screen.getBrushPlant().getNew());
			
			if(Screen.getBrushSoil() != null)
				setSoil(Screen.getBrushSoil().getNew());
		}
	};
	
	private MyEvent hover = new MyEvent(){
		public void action(){
			Render.addScn(
				new Shape(
					new Point(
						soilSkin.getX(),
						soilSkin.getY()),
					soilSkin.getW(),
					soilSkin.getH(),
					Color.GRAY),
				4);
		}
	};
	
	public Tile(int x, int y, IndexPair pos){
		setSoil(new Dirt());
		setPlant(new NoPlant());
		
		soilSkin = new DiamondClick(x, y, scale, soil.getColor());
		soilSkin.setClick(click);
		soilSkin.setHover(hover);
		
		this.pos = pos;
	}
	
	public void setPlant(Plant p){
		plant = p;
		plantSkin = new RectTex(
				soilSkin.getX()+soilSkin.getW()/4, 
				soilSkin.getY()-soilSkin.getH()/2,
				scale, scale,
				p.getTEXTUREID(), 0
				);
	}
	
	public void setSoil(Soil soil) {
		this.soil = soil;
		if(soil instanceof Affector)
			aff = (Affector) soil;
		else
			aff = null;
	}
	
	public boolean hasPlant(){
		return !(plant instanceof NoPlant);
	}
	
	public IndexPair getPos() {
		return pos;
	}
	
	private void cycleSoil(){
		soil.cycle(hasPlant());
		soilSkin.setCol(soil.getColor());
	} 

	private void cyclePlant(){
		if(hasPlant()){
			plant.cycle(soil);
			plantSkin.setFcu(plant.getStage());
			if(plant.inSpreadStage())
				Map.subSpread(this);
			if(plant.inEndStage())      
				Map.subDead(this);
		}
	}
	
	private void cycleAffector(){
		if(aff != null)
			Map.subAffect(this);
	}
	
	public void cycle(){
		cyclePlant();
		cycleSoil();
		cycleAffector();
	}

	public void killPlant() {
		setPlant(new NoPlant());
	}

	public void applySoilEffect(SoilEffect[] se){
		for(SoilEffect s : se)
			soil.addEffect(s);
	}
	
	public IndexPair[] spreadPlant(){
		return plant.spread();
	}
	
	public IndexPair[] affectorRange(){
		return aff.getEffectRange();
	}
	
	public SoilEffect[] affectorEffects() {
		return aff.getEffects();
	}
	
	public Plant getNewPlant(){
		return plant.getNew();
	}
	
	public void toClick(){
		Cursor.addClck(soilSkin);
	}
	
	// ---- detach these --- 
	
	public static int getScale(){
		return scale;
	}
	
	public static void setScale(int sc){
		scale = sc;
	}
	
	public void reScale(int xdif, int ydif){

		soilSkin.setX(soilSkin.getX()+xdif);
		soilSkin.setY(soilSkin.getY()+ydif);
		
		soilSkin.setW(scale);
		soilSkin.setH(scale/2);
		
		if(hasPlant()) {
			plantSkin.setX(soilSkin.getX()+soilSkin.getW()/4);
			plantSkin.setY(soilSkin.getY()-soilSkin.getH()/2);
	
			plantSkin.setW(scale);
			plantSkin.setH(scale);
		}
	}
	
	public void toRender(){
		Render.addScn(soilSkin, 0);
		if(hasPlant())
			Render.addScn(plantSkin, 1);
	}
}
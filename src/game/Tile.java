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
import render2d.shape.Clickable;
import render2d.shape.Colorable;
import render2d.shape.Point;
import render2d.shape.ShapeFactory;
import render2d.shape.Textureable;
import render2d.shape.diamond.Diamond;
import render2d.shape.rectangle.Rectangle;

public class Tile{

	private static int scale = 32;
	
	private IndexPair pos;

	private Plant plant;
	private Soil soil;
	private Affector aff;
	
	private Rectangle plantSkin;
	private Diamond soilSkin;

	
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
				ShapeFactory.createRectCol(
					new Point(
						soilSkin.getPos().getX(),
						soilSkin.getPos().getY()),
					soilSkin.getW(),
					soilSkin.getH(),
					Color.GRAY),
				4);
		}
	};
	
	public Tile(int x, int y, IndexPair pos){
		setSoil(new Dirt());
		
		soilSkin = ShapeFactory.createDiamColClick(
				new Point(x, y), scale, soil.getColor(),
				click, MyEvent.EMPTY, hover);
		this.pos = pos;
		
		setPlant(new NoPlant());
	}
	
	public void setPlant(Plant p){
		plant = p;
		plantSkin = ShapeFactory.createRectTex(new Point(
				soilSkin.getPos().getX()+soilSkin.getW()/4, 
				soilSkin.getPos().getY()-soilSkin.getH()/2),
				scale, scale,
				p.getTEXTUREID()
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
		((Colorable) soilSkin).setColor(soil.getColor());
	} 

	private void cyclePlant(){
		if(hasPlant()){
			plant.cycle(soil);
			((Textureable) plantSkin).setCurrentFrame(plant.getStage());
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
		Cursor.addClck((Clickable) soilSkin);
	}
	
	// ---- detach these --- 
	
	public static int getScale(){
		return scale;
	}
	
	public static void setScale(int sc){
		scale = sc;
	}
	
	public void reScale(int xdif, int ydif){
		soilSkin.setPos(
				soilSkin.getPos().getX()+xdif,
				soilSkin.getPos().getY()+ydif
				);
		soilSkin.reScale(scale, scale/2);
		
		if(hasPlant()) {
			plantSkin.setPos(
					soilSkin.getPos().getX()+soilSkin.getW()/4,
					soilSkin.getPos().getY()-soilSkin.getH()/2
					);
			plantSkin.reScale(scale, scale);
		}
	}
	
	public void toRender(){
		Render.addScn(soilSkin, 0);
		if(hasPlant())
			Render.addScn(plantSkin, 1);
	}
}
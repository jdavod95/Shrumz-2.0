package game;

import game.plant.NoPlant;
import game.plant.Plant;
import game.soil.Dirt;
import game.soil.Soil;
import game.soil.SoilEffect;

import render2d.Render;
import render2d.drawable.Diamond;
import render2d.drawable.DiamondClick;
import render2d.drawable.Rectangle;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Cursor;
import render2d.elements.Point;

public class Tile{

	private static final ShapeBuilder SHAPES = new ShapeBuilder();
	private static int scale = 32;
	
	private Point pos;

	private Plant plant;
	private Soil soil;
	private Affector aff;
	
	private Rectangle plantSkin;
	private Diamond soilSkin;
	
	public Tile(int x, int y, Point pos){
		Soil startSoil = new Dirt();
		Plant startPlant = new NoPlant();
		this.pos = pos;
		
		SHAPES.newDiamond(new Point(x, y), scale*2, scale)
			.setColor(startSoil.getColor())
			.setClickable(
					DiamondClick.class, 
					new TileActions(this, (Diamond) SHAPES.getShape()));
		soilSkin = (Diamond) SHAPES.getShape();
		
		SHAPES.newRectangle(new Point(x+scale/2, y-scale/4), scale, scale)
			.setTexture(startPlant.getTEXTURENAME());
		plantSkin = (Rectangle) SHAPES.getShape();

		
		setSoil(startSoil);
		setPlant(startPlant);
	}
	
	public void setPlant(Plant p){
		plant = p;
		plantSkin.setTexName(p.getTEXTURENAME());
	}
	
	public void setSoil(Soil soil) {
		this.soil = soil.getNew();
		soilSkin.setColor(soil.getColor()); 
		
		if(soil instanceof Affector)
			aff = (Affector) soil;
		else
			aff = null;
	}
	
	public boolean hasPlant(){
		return !(plant instanceof NoPlant);
	}
	
	public Point getPos() {
		return pos;
	}
	
	private void cycleSoil(){
		soil.cycle(hasPlant());
		soilSkin.setColor(soil.getColor());
	} 

	private void cyclePlant(){
		if(hasPlant()){
			plant.cycle(soil);
			plantSkin.setCurrentFrame(plant.getStage());
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
	
	public Point[] spreadPlant(){
		return plant.spread();
	}
	
	public Point[] affectorRange(){
		return aff.getEffectRange();
	}
	
	public SoilEffect[] affectorEffects() {
		return aff.getEffects();
	}
	
	public Plant getNewPlant(){
		return plant.getNew();
	}
	
	public void toClick(){
		Cursor.addClickable(soilSkin.getClickable());
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
				soilSkin.getPos().getX() + xdif,
				soilSkin.getPos().getY() + ydif
				);
		soilSkin.reScale(scale, scale/2);
		
		if(hasPlant()) {
			plantSkin.setPos(
					soilSkin.getPos().getX() + soilSkin.getW()/4,
					soilSkin.getPos().getY() - soilSkin.getH()/2
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
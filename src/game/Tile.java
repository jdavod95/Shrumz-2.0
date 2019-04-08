package game;


import elements.Cursor;
import elements.IndexPair;
import elements.MyEvent;
import elements.clickable.RectIsomClickable;
import game.plant.NoPlant;
import game.plant.Plant;
import game.soil.Dirt;
import game.soil.Soil;
import game.soil.SoilEffect;
import render2d.Color;
import render2d.Render;
import render2d.shape.RectIsom;
import render2d.shape.RectTex;

public class Tile{

	private IndexPair pos;

	static boolean down;
	static int scale = 32;
	
	private Plant plant;
	private Soil soil;

	private RectTex plantSkin;
	private RectIsomClickable soilSkin;
	
	public Tile(int x, int y, IndexPair pos){
		soil = new Dirt();
		RectIsomClickable ric = new RectIsomClickable(x, y, scale, soil.getColor());
		ric.setClick(new MyEvent(){
			public void action(){
				setPlant(Screen.getBrushPlant().getNew());
			//	setSoil(Screen.getBrushSoil());
			}
		});
		ric.setHover(new MyEvent(){
			public void action(){
				Render.addScn(
					new RectIsom(
						soilSkin.getX(),
						soilSkin.getY(),
						soilSkin.getH(),
						Color.GRAY,
						0.5),
					4);
			}
		});
		soilSkin = ric;
		setPlant(new NoPlant());
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
	}

	public RectTex getPlantSkin() {
		return plantSkin;
	}

	public void setPlantSkin(RectTex plantSkin) {
		this.plantSkin = plantSkin;
	}

	public RectIsomClickable getSoilSkin() {
		return soilSkin;
	}

	public void setSoilSkin(RectIsomClickable soilSkin) {
		this.soilSkin = soilSkin;
	}
	
	public IndexPair getPos() {
		return pos;
	}

	public void reScale(int xdif, int ydif){
		
		soilSkin.setX(soilSkin.getX()+xdif);
		soilSkin.setY(soilSkin.getY()+ydif);
		
		soilSkin.setW(scale);
		soilSkin.setH(scale/2);
		
		if(!hasPlant())
			return;
		
		plantSkin.setX(soilSkin.getX()+soilSkin.getW()/4);
		plantSkin.setY(soilSkin.getY()-soilSkin.getH()/2);

		plantSkin.setW(scale);
		plantSkin.setH(scale);
	}
	
	public void toRender(){
		Render.addScn(soilSkin, 0);
		if(hasPlant())
			Render.addScn(plantSkin, 1);
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
	
	public void cycle(){
		cyclePlant();
		cycleSoil();
	}

	public void killPlant() {
		setPlant(new NoPlant());			// or epmtyGameObject
	}

	public void applySoilEffect(SoilEffect se){
		soil.addEffect(se);
	}
	
	public IndexPair[] spreadPlant(){
		return plant.spread();
	}
	
	public Plant getNewPlant(){
		return plant.getNew();
	}
	
	public void toClick(){
		Cursor.addClck(soilSkin);
	}
}
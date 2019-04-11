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
	private Affector aff;
	public Tile(int x, int y, IndexPair pos){
		soil = new Dirt();
		RectIsomClickable ric = new RectIsomClickable(x, y, scale, soil.getColor());
		
		ric.setClick(new MyEvent(){
			public void action(){
				if(Screen.getBrushPlant() != null)
					setPlant(Screen.getBrushPlant().getNew());
				
				if(Screen.getBrushSoil() != null)
					setSoil(Screen.getBrushSoil().getNew());
				
				System.out.println(soil.getFertility());
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
		if(soil instanceof Affector)
			aff = (Affector) soil;
		else
			aff = null;
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
	
	private void cycleAffector(){
		System.out.println(aff != null);
		if(aff != null)
			Map.subAffect(this);
	}
	public void cycle(){
		cyclePlant();
		cycleSoil();
		cycleAffector();
	}

	public void killPlant() {
		setPlant(new NoPlant());			// or epmtyGameObject
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
}
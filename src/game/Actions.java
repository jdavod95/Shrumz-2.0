package game;

import game.plant.BluShrum;
import game.plant.Shrum;
import game.plant.Weed;
import game.soil.Dirt;
import game.soil.Water;
import render2d.elements.Action;

public class Actions {

	public final static Action GROWMAPX = ()->{
		if(Map.getX() < 128)
			Map.setTable(Map.getX()+8, Map.getY());	
	};
		
	public final static Action SHRINKMAPX = ()->{
		if(Map.getX() > 8)
			Map.setTable(Map.getX()-8, Map.getY());
	};
		
	public final static Action GROWMAPY = ()->{
		if(Map.getY() < 128)
			Map.setTable(Map.getX(), Map.getY()+8);
	};
	public final static Action SHRINKMAPY = ()->{
		if(Map.getY() > 8)
			Map.setTable(Map.getX(), Map.getY()-8);	
	};
	public final static Action SCALEUP = ()->{
		if(Tile.getScale() < 64){
			Map.reScale(Tile.getScale()+2);
		}
	};
	public final static Action SCALEDOWN = ()->{
		if(Tile.getScale() > 2){
			Map.reScale(Tile.getScale()-2);
		}
	};

	public final static Action BRPLSHRUM = ()->{
		Screen.setBrushPlant(new Shrum());
	};
	public final static Action BRPLBLUSHRUM = ()->{
		Screen.setBrushPlant(new BluShrum());
	};
	public final static Action BRPLWEED = ()->{
		Screen.setBrushPlant(new Weed());
	};
	public final static Action BRPLNULL = ()->{
		Screen.setBrushPlant(null);
	};
	
	public final static Action BRSL_NO = ()->{
		Screen.brushSoil = null;
	};
	
	public final static Action BRSL_0 = ()->{
		Screen.brushSoil = new Dirt();
	};
	
	public final static Action BRSL_D = ()->{
		Screen.brushSoil = new Dirt();
	};
	
	public final static Action BRSL_W = ()->{
		Screen.brushSoil = new Water();
	};
	
	public final static Action CTRLPLAY = ()->{
		if(Screen.isPaused())
			Screen.setPaused(false);			
	};
	
	public final static Action CTRLPAUSE = ()->{
		if(!Screen.isPaused())
			Screen.setPaused(true);			
	};
	
	public final static Action CTRLSTOP = ()->{
		Screen.load();
	};

	public final static Action CTRLSTEP = ()->{
		Screen.setPaused(true);
		Map.cycle();		
	};
	
	public final static Action CTRLFRUP = ()->{
		if(Screen.getCycleat() > 20)
			Screen.setCycleat(Screen.getCycleat()+5);	
		else
			Screen.setCycleat(Screen.getCycleat()+1);
	};
	
	public final static Action CTRLFRDOWN = ()->{
		if(Screen.getCycleat() > 20)
			Screen.setCycleat(Screen.getCycleat()-5);	
		else
			Screen.setCycleat(Screen.getCycleat()-1);
	};
}

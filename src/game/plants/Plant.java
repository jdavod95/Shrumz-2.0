package game.plants;

import org.lwjgl.util.Point;

import game.Map;
import game.Tile;
import render2d.shape.RectTex;

public abstract class Plant {

	RectTex skin;

	public final int ENDSTAGE;
	public final int SPRSTAGE; // spreadstage
	protected boolean spreading = true;

	int stage;
	int age;
	
	Plant(int x, int y, int fid, int sprst, int endst){
		stage = 0;
		age = 0;
		
		skin = new RectTex(x,y,Tile.getScale(),Tile.getScale(),fid,stage);
		SPRSTAGE = sprst;
		ENDSTAGE = endst;

	}
	public boolean isSpreading() {
		return spreading;
	}
	public RectTex getSkin(){
		return skin;
	}
	public void incAge(){
		age++;
	}
	public int getAge(){
		return age;
	}
	
	public void incStage(boolean inc){
		if(inc)
			stage++;
		else{
			stage--;
			if(stage < 0)
				stage = ENDSTAGE-1;
		}
		getSkin().setFcu(stage);
	}

	public int getStage(){
		return stage;
	}
	
	public void die(Tile t){
		if(stage == ENDSTAGE)
			t.setPlant(null);
	}

	public boolean cycle(Tile t){
		incAge();
		grow(t);
		return stage-1 == SPRSTAGE;
	}

	// ez még csúnya
	public int nCount(int c, Tile t){
		c +=1;
		int count = 0;
		int i = 0;
		while(count < c && i < 3){
			int j = 0;
			while(count < c && j < 3){
				Point p = t.getNeigh()[i][j];
					try{
						if(Map.getTable()[p.getX()][p.getY()].getPlant() != null)
							count++;
					} catch (Exception e){}
				j++;
			}
			i++;
		}
		return count-1;
		// p[1][1] = önmaga
	}
	
	public abstract void grow(Tile t);
	public abstract Plant[] spread(Point[][] n);
}
